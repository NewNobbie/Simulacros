package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;
import entity.Reservacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj
        Pasajero objPasajero = (Pasajero) obj;

        try {
            //3. Write sql
            String sql = "INSERT INTO pasajero(id_pasajero, nombre, apellido, documento_identidad) VALUES (?,?,?,?);";

            //4. Prepare statement + return_Generated_Keys, do sentencia sql return
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign values to ????
            objPrepare.setInt(1, objPasajero.getId_pasajero());
            objPrepare.setString(2, objPasajero.getNombre());
            objPrepare.setString(3, objPasajero.getApellido());
            objPrepare.setString(4, objPasajero.getDocumento_identidad());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain result with ids generated
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. While obtain register
            while (objRest.next()){
                //Can obtain
                objPasajero.setId_pasajero(objRest.getInt(1));

                JOptionPane.showMessageDialog(null, "Passenger insertion was sucessful");
            }

        }catch ( SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list to save returns
        List<Object> listPasajeros = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Query sql
            String sql = "SELECT * FROM pasajero;";

            //4. Use prepareStat4ement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute quuery and obtain result (Resulset)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While there is a result, do
            while (objResult.next()){

                ///6.1 Create Passenger
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                //6.2 Add Passenger to the lisst
                listPasajeros.add(objPasajero);

            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //7. Close connection
        ConfigDB.closeConnection();
        return listPasajeros;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. COnvert to Passenger
        Pasajero objPasajero = (Pasajero) obj;

        //3. Variable to know the state of action
        boolean isUpdated = false;

        try {
            //4. Sentence SQL
            String sql = "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id_pasajero = ?;";
            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Assign value to parameters of query
            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());
            objPrepare.setInt(4, objPasajero.getId_pasajero());

            //7. Execute query
            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null, "The update was successful!");
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
        Pasajero objPasajero = (Pasajero) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create state variable
        boolean isDeleted = false;

        try {
            //4. Write SQL
             String sql = "DELETE FROM pasajero WHERE id_pasajero =?;";

             //5. Create prepare statement
            PreparedStatement objPrepare  = objConnection.prepareStatement(sql);

            //6. Give value to the symbol
            objPrepare.setInt(1, objPasajero.getId_pasajero());

            //7. Execute query (ExecuteUpdate) return number of registers affected
            int totalRowsAffected = objPrepare.executeUpdate();

            // If Rows affected > 0, its means that deleted
            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successful!");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

            //8. Close Connection
            ConfigDB.closeConnection();
            return isDeleted;
        }
        return false;
    }

    public Pasajero findById(int id_pasajero){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Create Passenger to return
        Pasajero objPasajero = null;

        try {
            //3. Sentencia sql
            String sql = "SELECT * FROM pasajero WHERE id_pasajero = ?;";

            //4. Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give value to parameter of query
            objPrepare.setInt(1, id_pasajero);

            //6. Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objPasajero;
    }

    public List<Pasajero> findByName(String nombre){
        //Create List
        List<Pasajero> listPasajeros = new ArrayList<>();
        //Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia sql
            String sql = "SELECT * FROM pasajero WHERE nombre LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%"+nombre+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPasajeros.add(objPasajero);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listPasajeros;
    }
}
