package model;

import database.CRUD;
import database.ConfigDB;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to obj
        Medico objMedico = (Medico) obj;

        try {
            //3. Write SQL
            String sql = "INSERT INTO medico(nombre,apellidos,id_especialidad_medico) VALUES (?,?,?);";

            //4. Statement + Retur_Genereted_Keys, to do sentence SQL return ids genereted for DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign value to symbols ???
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getId_especialidad_medico());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain result of ids (generated keys)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iteration while there is a record
            while (objRest.next()){
                //Can obtain value also with the symbols
                objMedico.setId_medico(objRest.getInt(1));

                JOptionPane.showMessageDialog(null,"Medic insertion was successful");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list
        List<Object> listMedicos = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Write query into sql
            String sql = "SELECT * FROM medico;";
            //4. Use prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute query and obtain next result (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While as there is a result do the following
            while (objResult.next()) {

                //6.1 Create a medic
                Medico objMedico = new Medico();

                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setId_especialidad_medico(objResult.getInt("id_especialidad_medico"));

                //6.3 Add medic to the list
                listMedicos.add(objMedico);

            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        //7. Close connection
        ConfigDB.closeConnection();
        return listMedicos;
    }

    @Override
    public boolean update(Object obj) {

        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to Medic
        Medico objMedico = (Medico) obj;

        //3. Variable for know status of connection
        boolean isUpdated = false;

        try {
            //4. Statement SQL
            String sql = "UPDATE medico SET nombre = ?, apellidos = ?, id_especialidad_medico=? WHERE id_medico =?;";
            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Assign value for parameters of query
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3, objMedico.getId_especialidad_medico());
            objPrepare.setInt(4, objMedico.getId_medico());

            //7. Execute query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"The update was successful!");

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error Update"+e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convert obj to entity
        Medico objMedico = (Medico) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create statement state
        boolean isDeleted = false;

        try {
            //4. Write SQL
            String sql = "DELETE FROM medico WHERE id_medico = ?;";

            //5. Create prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to symbol
            objPrepare.setInt(1, objMedico.getId_medico());

            //7. Execute query to return number of records
            int totalAffectedArrow = objPrepare.executeUpdate();

            //If affected rows>0 it means that deleted something
            if (totalAffectedArrow > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The change was successful!");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //8. Close connection
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Medico findById(int id_medico){
        //1. Open COnnection
        Connection objConnetion = ConfigDB.openConnection();
        //Create patient to return
        Medico objMedico = null;

        try {
            //3. Sentencia sql
            String sql = "SELECT * FROM medico WHERE id_medico = ?;";
            //4. Prepare Statement
            PreparedStatement objPrepare = objConnetion.prepareStatement(sql);

            //5. Give value to parameter of query
            objPrepare.setInt(1, id_medico);

            //6 Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objMedico = new Medico();
                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad_medico(objResult.getInt("id_especialidad_medico"));

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

    public List<Medico> findBySpeciality(String medico_especialidad){
        //Create List
        List<Medico> listMedicSpeciality = new ArrayList<>();
        //Open COnnection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia sql
            String sql = "SELECT * FROM medico INNER JOIN especialidad WHERE especialidad.nombre LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+medico_especialidad+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                 Medico objMedico = new Medico();
                 objMedico.setId_medico(objResult.getInt("id_medico"));
                 objMedico.setNombre(objResult.getString("medico.nombre"));
                 objMedico.setApellidos(objResult.getString("apellidos"));
                 objMedico.setEspecialidad(objResult.getString("especialidad.nombre"));

                 listMedicSpeciality.add(objMedico);

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return  listMedicSpeciality;
    }

    public Object findByName(String nombre){
        //Open COnnection
        Connection objConnection = ConfigDB.openConnection();

        Medico objMedico = null;
        try {
            //Sentencia sql
            String sql = "SELECT * FROM medico WHERE nombre LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%"+nombre+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objMedico = new Medico();
                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad_medico(objResult.getInt("id_especialidad_medico"));

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objMedico;
    }


}
