package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to obj
        Cita objCita = (Cita) obj;

        try {
            //3. Write sql
            String sql = "INSERT INTO cita(fecha_cita,hora_cita, motivo, id_paciente_cita, id_medico_cita) VALUES(?,?,?,?,?);";

            //4. Prepare Statement + Return_Generated_Keys that statement return generated ids for DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign value of the symbols of ???
            objPrepare.setString(1, objCita.getFecha_cita());
            objPrepare.setString(2, objCita.getHora_cita());
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4, objCita.getId_paciente_cita());
            objPrepare.setInt(5, objCita.getId_medico_cita());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain the result with ids (Keys generated)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterate the registered
            while (objRest.next()) {
                //Can obtain the value of index also
                objCita.setId_cita(objRest.getInt(1));

                JOptionPane.showMessageDialog(null,"Medical appointment insertion was successful!");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        ConfigDB.closeConnection();

        return objCita;
    }

    @Override
    public List<Object> findAll() {
        //1. Create List
        List<Object> listCitas = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Write query
            String sql = "SELECT * FROM cita;";
            //4. Use prepareStatement
            PreparedStatement objPrepapre = objConnection.prepareStatement(sql);

            //5. Execute query to obtain the result (Resulset)
            ResultSet objResult = objPrepapre.executeQuery();

            //6. While get result do
            while (objResult.next()){

                //6.1 Create Cita
                Cita objCita = new Cita();

                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setId_paciente_cita(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico_cita(objResult.getInt("id_medico_cita"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));

                //6.2 Add Appointment to list
                listCitas.add(objCita);

            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        //7. Close the connection
        ConfigDB.closeConnection();
        return listCitas;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert Appointment
        Cita objCita = (Cita) obj;

        //3. Variable for know the state of connection
        boolean isUpdated = false;

        try {
            //4. Statement sql
            String sql = "UPDATE cita SET id_paciente_cita =?, id_medico_cita =?, fecha_cita =?, hora_cita =?, motivo =? WHERE  id_cita =?;";

            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Assign value to parameters of query
            objPrepare.setInt(1, objCita.getId_paciente_cita());
            objPrepare.setInt(2,objCita.getId_medico_cita());
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(6,objCita.getId_cita());

            //7. Execute query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"The update was successful!");

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error update Cita"+ e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convert to entity
        Cita objCita = (Cita) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create variable state
         boolean isDeleted = false;

         try {
             //4. Write sql
             String sql = "DELETE FROM cita WHERE id_cita = ?;";

             //5. Create prepareStatement
             PreparedStatement objPrepare = objConnection.prepareStatement(sql);

             //6. give value to symbol
             objPrepare.setInt(1, objCita.getId_cita());

             //7. Execute query (executeUpdate) return number of registers affected
             int totalAffectedRows = objPrepare.executeUpdate();

             //if rows > 0 is ready deleted
             if (totalAffectedRows > 0) {
                 isDeleted = true;
                 JOptionPane.showMessageDialog(null,"The update was successful!");
             }
         }catch (Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage());
         }

         //8. Close connection
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Cita findById(int id_cita){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Create appointment to return
        Cita objCita = null;

        try {
            //3. Sentencia sql
            String sql = "SELECT * FROM cita WHERE id_cita = ?;";
            //4. Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give value to parameter of query
            objPrepare.setInt(1, id_cita);

            //6. Execute Query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objCita = new Cita();
                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setId_paciente_cita(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico_cita(objResult.getInt("id_medico_cita"));
                objCita .setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }

    public List<Cita> findByDate(String fecha){
        //Create List
        List<Cita> listCita = new ArrayList<>();
        //Open COnnection
        Connection objConnetion = ConfigDB.openConnection();

        try {
            //Sentencia sql
            String sql = "SELECT * FROM cita WHERE fecha_cita LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnetion.prepareStatement(sql);
            objPrepare.setString(1,"%"+fecha+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cita objCita = new Cita();
                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setId_paciente_cita(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico_cita(objResult.getInt("id_medico_cita"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));

                listCita.add(objCita);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listCita;
    }

    /*public Cita updateDate(String fecha_cita){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert Appointment
        Cita objCitaDate = (Cita) obj;

        //3. Variable for know the state of connection
        boolean isUpdated = false;

        try {
            //4. Statement sql
            String sql = "UPDATE cita SET id_paciente_cita =?, id_medico_cita =?, fecha_cita =?, hora_cita =?, motivo =? WHERE  id_cita =?;";

            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Assign value to parameters of query
            objPrepare.setInt(1, objCitaDate.getId_paciente_cita());
            objPrepare.setInt(2,objCitaDate.getId_medico_cita());
            objPrepare.setString(3,objCitaDate.getFecha_cita());
            objPrepare.setString(4,objCitaDate.getHora_cita());
            objPrepare.setString(5,objCitaDate.getMotivo());
            objPrepare.setInt(6,objCitaDate.getId_cita());

            //7. Execute query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"The update was successful!");

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;


    }*/

}
