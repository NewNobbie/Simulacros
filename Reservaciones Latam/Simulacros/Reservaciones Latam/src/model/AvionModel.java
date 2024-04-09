package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Pasajero;
import entity.Reservacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj
        Avion objAvion = (Avion) obj;

        try {
            //3. Write sql
            String sql = "INSERT INTO avion(id_avion, modelo, capacidad) VALUES (?,?,?);";

            //4. Prepare Statement + return_Generated_Keys, that do return sentencia sql
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign values ???
            objPrepare.setInt(1, objAvion.getId_avion());
            objPrepare.setString(2, objAvion.getModelo());
            objPrepare.setInt(3,objAvion.getCapacidad());

            //6. Execute uery
            objPrepare.execute();

            //7. Obtain results
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterate while get register
            while (objRest.next()){
                //Can obtain
                objAvion.setId_avion(objRest.getInt(1));

                JOptionPane.showMessageDialog(null,"Speciality insertion was successful");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list to sabe returns of DB
        List<Object> listAviones = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Query sql
            String sql = "SELECT * FROM avion;";
            //4. Use prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute query and obtain result (Resulset)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While get resul do next
            while (objResult.next()){

                //6.1 Create plane
                Avion objAvion = new Avion();

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));

                //6.2 Add plane to the list
                listAviones.add(objAvion);

            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //7. Close connection
        ConfigDB.closeConnection();
        return listAviones;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to plane
        Avion objAvion = (Avion) obj;

        //3. Variable for know the state of action
        boolean isUpdated = false;

        try {
            //4. Sentencia sql
            String sql = "UPDATE avion set modelo = ?, capacidad = ? WHERE id_avion = ?;";

            //5. Create statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Assign values to parameters of query
            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());
            objPrepare.setInt(3, objAvion.getId_avion());

            //7. Execute query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convert obj into entity
        Avion objAvion = (Avion) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create state variable
        boolean isDeleted = false;

        try {
            //4. Write sql
            String sql = "DELETE FROM avion WHERE id_avion = ?;";

            //5. Create prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to the symbol
            objPrepare.setInt(1, objAvion.getId_avion());

            //7. Execute query (executeUpdate) return number of registers affected
            int totalRowAffected = objPrepare.executeUpdate();

            //if rowAffected > 0, it means that rdy deleted
            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The update was successful");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

            //8. Close connection
            ConfigDB.closeConnection();
            return isDeleted;
        }
        return false;
    }

    public Avion findById(int id_avion){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Create plane to return
        Avion objAvion = null;

        try {
            //3. sentencia sql
            String sql = "SELECT * FROM avion WHERE id_avion = ?;";
            //4. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give value to parameter of query
            objPrepare.setInt(1, id_avion);

            //6. Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }

    public List<Avion> findByModelo(String modelo){
        //Create list
        List<Avion> listAvion = new ArrayList<>();
        //Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia sql
            String sql = "SELECT * FROM avion WHERE modelo LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+modelo+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Avion objAvion = new Avion();
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));

                listAvion.add(objAvion);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listAvion;
    }
}
