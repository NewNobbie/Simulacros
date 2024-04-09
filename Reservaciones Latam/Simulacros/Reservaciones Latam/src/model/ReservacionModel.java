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

public class ReservacionModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to obj
        Reservacion objReservacion = (Reservacion) obj;

        try {
            //3. Write SQL
            String sql = "INSERT INTO reservacion(id_pasajero_fk,id_vuelo_fk,fecha_reservacion, asiento) VALUES (?,?,?,?);";

            //4. Statement + Retur_Genereted_Keys, to do sentence SQL return ids genereted for DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign value to symbols ???
            objPrepare.setInt(1,objReservacion.getId_pasajero_reservacion());
            objPrepare.setInt(2,objReservacion.getId_vuelo_reservacion());
            objPrepare.setString(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4, objReservacion.getAsiento());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain result of ids (generated keys)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iteration while there is a record
            while (objRest.next()){
                //Can obtain value also with the symbols
                objReservacion.setId_reservacion(objRest.getInt(1));

                JOptionPane.showMessageDialog(null,"Booking insertion was successful");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list
        List<Object> listReservaciones = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Write query into sql
            String sql = "SELECt u* FROM reservacion;";
            //4. Use PrepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute query and obtain nect resul (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While as there is a result do the nex
            while (objResult.next()){

                //6.1 Create a Booking
                Reservacion objReservacion = new Reservacion();

                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setId_pasajero_reservacion(objResult.getInt("id_pasajero_fk"));
                objReservacion.setId_vuelo_reservacion(objResult.getInt("id_vuelo_fk"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

                //6.2 Add Booiking into the lis
                listReservaciones.add(objReservacion);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //7. CLose connection
        ConfigDB.closeConnection();
        return listReservaciones;
    }

    @Override
    public boolean update(Object obj) {

        //1. Open COnnection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to Booiking
        Reservacion objReservacion = (Reservacion) obj;

        //3. Variable for know status of connection
        boolean isUpdated = false;

        try {
            //4. Statement SQL
            String sql = "UPDATE reservacion SET id_pasajero_fk =?, id_vuelo_fk =?, fecha_reservacion =?, asiento =? WHERE id_reservacion =?;";

            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Assign vakue to parameters of query
            objPrepare.setInt(1, objReservacion.getId_pasajero_reservacion());
            objPrepare.setInt(2, objReservacion.getId_vuelo_reservacion());
            objPrepare.setString(3, objReservacion.getFecha_reservacion());
            objPrepare.setString(4, objReservacion.getAsiento());
            objPrepare.setInt(5, objReservacion.getId_reservacion());

            //7. Execute query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error update Cita" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();

        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convert to entity
        Reservacion objReservacion = new Reservacion();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create variable state
        boolean isDeleted = false;

        try {
            //4. Write sql
            String sql = "DELETE FROM reservacion WHERE id_reservacion = ?;";

            //5. Create PrepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to symbol
            objPrepare.setInt(1, objReservacion.getId_reservacion());

            //7. Execute query (executeUpdate) return number of registers affected
            int totalRowsAffected = objPrepare.executeUpdate();

            //If rows > 0 is ready deleted
            if (totalRowsAffected >0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null, "The update was successful!");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Close COnnection
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public  Reservacion findById(int id_reservacion){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Crate appointment to return
        Reservacion objReservacion = null;

        try {
            //3. Statement sql
            String sql = "SELECt * FROM reservacion WHERE id_reservacion =?:";
            //4. Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give values to parameter of query
            objPrepare.setInt(1, id_reservacion);

            //6. Execute Query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objReservacion = new Reservacion();
                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setId_pasajero_reservacion(objResult.getInt("id_pasajero_fk"));
                objReservacion.setId_vuelo_reservacion(objResult.getInt("id_vuelo_fk"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objReservacion;
    }

    public Object findByFly(int id_vuelo_reservaciones){
        // Open Connection
        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = null;
        try {
            //Statement sql
            String sql = "SELECT * FROM reservaciones WHERE id_vuelo_reservacion = ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Posible Parseada a futuro
            objPrepare.setString(1, "%"+id_vuelo_reservaciones+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objReservacion = new Reservacion();
                objReservacion.setId_reservacion(objResult.getInt("id_servacion"));
                objReservacion.setId_pasajero_reservacion(objResult.getInt("id_pasajero_fk"));
                objReservacion.setId_vuelo_reservacion(objResult.getInt("id_vuelo_fk"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objReservacion;
    }
}
