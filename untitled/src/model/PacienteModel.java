package model;

import database.CRUD;
import database.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to obj
        Paciente objPaciente = (Paciente) obj;

        try {
            //3. Write SQL
            String sql = "INSERT INTO paciente(nombre,apellidos,fecha_nacimiento,documento_identidad) VALUES(?,?,?,?);";

            //4. Prepare Statement + Return_Genereted_Keys, that return statement od ids generated
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign values to ???
            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFecha_nacimiento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain result with ids generated
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterate where there is a record
            while (objRest.next()){
                //Can obtain value also with indices
                objPaciente.setId_paciente(objRest.getInt(1));

                JOptionPane.showMessageDialog(null,"Patient insertion was successful!");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list to save that the DB return
        List<Object> listPacientes = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Write query into sql
            String sql = "SELECT * FROM paciente;";
            //4. Use prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute query to obtain result (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While there is a next result do
            while (objResult.next()){

                //6.1 Create Patient
                Paciente objPaciente = new Paciente();

                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objPaciente.setFecha_nacimiento(dateFormat.format((objResult.getDate("fecha_nacimiento"))));

                objPaciente.setDocumento_identidad(objResult.getNString("documento_identidad"));

                objPaciente.setId_paciente(objResult.getInt("id_paciente"));

                //6.2 Add patient to list
                listPacientes.add(objPaciente);

            }
        } catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        //7. Close DB
        ConfigDB.closeConnection();
        return listPacientes;
    }

    @Override
    public boolean update(Object obj) {
        //1. OpenCOnnection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to Patient
        Paciente objPaciente = (Paciente) obj;

        //3. Varible to know status conecction
        boolean isUpdated = false;

        try {
            //4. Statement sql
            String sql = "UPDATE paciente SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id_paciente = ?;";

            //5. Create statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Assign value to parameters of query
            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFecha_nacimiento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());
            objPrepare.setInt(5,objPaciente.getId_paciente());

            //7. Execute query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"The update was successful!");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convert obj entity
        Paciente objPaciente = (Paciente) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Variable State
        boolean isDeleted = false;

        try {
            //4. Write sql
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";

            //5. Create prepareStatement
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);

            //6. Give value to the wsymbol
            objPrepare.setInt(1,objPaciente.getId_paciente());

            //7. Execute query (executeUpdate) return number of registers
            int totalRowsAffected = objPrepare.executeUpdate();

            //If rows afected > 0, it means that deleted somethig
            if (totalRowsAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The update was suuccessful!");

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Close COnnection
        ConfigDB.closeConnection();
        return isDeleted;
    }
    public Paciente findById(int id_paciente){
        //1. OpenConnection
        Connection objConnection = ConfigDB.openConnection();
        //Create patient to return
        Paciente objPaciente = null;

        try {
            //3. Sentencia sql
            String sql = "SELECT * FROM paciente WHERE id_paciente = ?;";
            //4.Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give value to parameter of query
            objPrepare.setInt(1,id_paciente);

            //6. Execute Query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }

    public List<Paciente> findByName(String nombre){
        //Create List
        List<Paciente> listPaciente = new ArrayList<>();
        //Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia sql
            String sql = "SELECT * FROM paciente WHERE nombre LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+nombre+"%");

            ResultSet objResult = objPrepare.executeQuery();


            while (objResult.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPaciente.add(objPaciente);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listPaciente;
    }

    public Paciente findByDocument(String documento_identidad){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Create Patient to return
        Paciente objPaciente = null;

        try {
            //3. Sentencia sql
            String sql = "SELECT * FROM paciente WHERE documento_identidad LIKE ?;";
            //4. Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give value to parameter of query
            objPrepare.setString(1, "%"+documento_identidad+"%");

            //6.Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }
}
