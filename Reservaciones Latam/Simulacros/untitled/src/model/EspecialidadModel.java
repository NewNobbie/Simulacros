package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1.OpenConnection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj
        Especialidad objEspecialidad = (Especialidad) obj;

        try {
            //3. Escribir el SQL
            String sql = "INSERT INTO especialidad(id_especialidad,nombre,descripcion) VALUES (?,?,?);";

            //4. Preparar el Statement + return_Generated_Keys, que hace la sentencia SQL nos retorne los id generados por la DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar valor a los simbolos de ???
            objPrepare.setInt(1, objEspecialidad.getId_especialidad());
            objPrepare.setString(2, objEspecialidad.getNombre());
            objPrepare.setString(3, objEspecialidad.getDescripcion());

            //6. Ejecutar el Query
            objPrepare.execute();

            //7. Obtener el resultado con los ids generados
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterar mientras haya un registro
            while (objRest.next()){
                //Podemos obtener
                objEspecialidad.setId_especialidad(objRest.getInt(1));

                JOptionPane.showMessageDialog(null, "Speciality insertion was successful");
            }

        }   catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        ConfigDB.closeConnection();

        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list to save returns of DB
        List<Object> listEspecialidades = new ArrayList<>();

        //2. Open conection
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Query sql
            String sql = "SELECT * FROM especialidad;";
            //4. Use prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecute query and obtain result (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un resultado siguiente hacer:
            while (objResult.next()) {

                //6.1 Create especilidad
                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));

                //6.3 Add speciality to the list
                listEspecialidades.add(objEspecialidad);

            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        //7. Close conection
        ConfigDB.closeConnection();
        return listEspecialidades;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to speciality
        Especialidad objEspecialidad = (Especialidad)  obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try {
            //4. Sentencia SQL
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";
            //5.Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //6. Asign value to parametros del query
            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());
            objPrepare.setInt(3,objEspecialidad.getId_especialidad());

            //7. Ejecute el query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"The update was successful");
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
        //1. Convert obj into entity
        Especialidad objEspecialidad = (Especialidad) obj;

        //2. Open connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create state variable
        boolean isDeleted = false;

        try {
            //4. Write SQL
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";

            //5. Create prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to de symbol
            objPrepare.setInt(1,objEspecialidad.getId_especialidad());

            //7. Execute query (executeUpdate) return number of registers affected
            int totalAffectedRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a 0, quiere decur que si eliminó algo
            if (totalAffectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The update was successful");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());

            //8. Close connection
            ConfigDB.closeConnection();
            return  isDeleted;
        }
        return false;
    }

    public Especialidad findById(int id){
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Create speciality to return
        Especialidad objEspecialidad = null;

        try {
                //3. Sentencia SQL
                String sql = "SELECT * FROM especialidad WHERE id_especialidad = ?;";
                //4. Prepare statement
                PreparedStatement objPrepare = objConnection.prepareStatement(sql);

                //5. Give value to parameter of query
                objPrepare.setInt(1,id);

                //6. Execute query
                ResultSet objResult = objPrepare.executeQuery();
                if (objResult.next()){
                    objEspecialidad = new Especialidad();
                    objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                    objEspecialidad.setNombre(objResult.getString("nombre"));
                    objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }

    public List<Especialidad> findByName(String nombre){
        //Create List
        List<Especialidad> listEspecialidad = new ArrayList<>();
        //Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia sql
            String sql = "SELECT * FROM especialidad WHERE nombre LIKE ?;";

            //Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%"+nombre+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));

                listEspecialidad.add(objEspecialidad);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listEspecialidad;
    }
}
