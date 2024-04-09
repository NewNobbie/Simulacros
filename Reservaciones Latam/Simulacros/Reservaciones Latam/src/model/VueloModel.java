package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import org.w3c.dom.ls.LSException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class VueloModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj
        Vuelo objVuelo = (Vuelo) obj;

        try {
            //3. Sentencia sql
            String sql = "INSERT INTO vuelo(id_vuelo, destino, fecha_salida, hora_salida, id_avion_fk) VALUES (?,?,?,?,?);";

            //4. Prepare Statement + return_Generated_keys, use sentencia sql to return ids generated for DB
        PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign symbols ????
            objPrepare.setInt(1,objVuelo.getId_vuelo());
            objPrepare.setString(2, objVuelo.getDestion());
            objPrepare.setString(3, objVuelo.getFecha_salida());
            objPrepare.setString(4, objVuelo.getHora_salida());
            objPrepare.setInt(5, objVuelo.getId_avion_vuelo());

                //6. Execute query
            objPrepare.execute();

            //7. Obtain result generated for ids
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterate while get resgiter
            while (objRest.next()){
                //Can obtain
                objVuelo.setId_vuelo(objRest.getInt(1));

                JOptionPane.showMessageDialog(null, "Fly insertion was successful!");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list to save returns of DB
        List<Object> listVuelos = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. query sql
            String sql = "SELECT * FROm vuelo;";
            //4. Use prepapreStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //5. Execute and obtain result (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While there is result, do
            while (objResult.next()){

                //6.1 Create fly
                Vuelo objVuelo = new Vuelo();

                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestion(objResult.getString("destion"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion_vuelo(objResult.getInt("id_avion_fk"));

                //6.2 Add fly to the list
                listVuelos.add(objVuelo);

            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //7. Cloce Connection
        ConfigDB.closeConnection();
        return listVuelos;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to Flies
        Vuelo objVuelo = (Vuelo) obj;

        //3. Variable to know the state of action
        boolean isUpdated = false;

        try {
            //4. Sentence sql
            String sql = "UPDATE vuelo SET destino = ?, fecha_salida = ?, hora_salida = ?, id_avion_fk = ? WHERE id_vuelo = ?;";

            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Assign value to parameters of query
            objPrepare.setString(1,objVuelo.getDestion());
            objPrepare.setString(2, objVuelo.getFecha_salida());
            objPrepare.setString(3, objVuelo.getHora_salida());
            objPrepare.setInt(4, objVuelo.getId_avion_vuelo());
            objPrepare.setInt(5, objVuelo.getId_vuelo());

            //7. Execute query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated=true;
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
        Vuelo objVuelo = (Vuelo) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create Status variable
        boolean isDeleted = false;

        try {
            //4. Write SQL
            String sql = "DELEte FROM value WHERE id_vuelo = ?;";

            //5. Create prepare statement
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);

            //6. Give value to the symbol
            objPrepare.setInt(1, objVuelo.getId_vuelo());

            //7. Execute Query (executeUpdate) return number  of registers affected
            int totalAffectedRows = objPrepare.executeUpdate();

            //If RowsAffected > 0, it means that is deleted
            if (totalAffectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was succesfull");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

            //8. Close Connection
            ConfigDB.closeConnection();
            return isDeleted;
        }

        return false;
    }

    public Vuelo findById(int id_vuelo){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Create Flies to return
        Vuelo objVuelo = null;

        try {
            //3. sql statement
            String sql = "SELECT * FROM vuelo WHERE id_vuelo = ?;";
            //4. Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give value to parameter query
            objPrepare.setInt(1, id_vuelo);

            //6. Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestion(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("hora_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion_vuelo(objResult.getInt("id_avion_fk"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }

    public List<Vuelo> findByDestination(String destino){
        //Create List
        List<Vuelo> listVuelo = new ArrayList<>();
        // Open COnnection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //SQL statement
            String sql = "SELECT * FROM vuelo  WHERE destino LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%"+destino+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestion(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion_vuelo(objResult.getInt("id_avion_fk"));

                listVuelo.add(objVuelo);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listVuelo;
    }
}
