package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;

public class EspecialidadController {

    public static void getAll(){
        EspecialidadModel objModel = new EspecialidadModel();

        String listEspecialidades = " ESPECIALIDAD LIST \n";

        for (Object iterador: objModel.findAll()){
            //Convert to obj Especialidad
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidades+=objEspecialidad.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listEspecialidades);
    }

    public static String getAllString(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidades = " ESPECIALIDAD LIST \n";

        for (Object iterador: objModel.findAll()){
            //Convert obj to Speciality
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidades+= objEspecialidad.toString() +"\n";
        }
        return listEspecialidades;
    }

    public static void create(){
        EspecialidadModel objEspecialidaModel = new EspecialidadModel();

        String nombre = JOptionPane.showInputDialog("Insert name");
        String descripcion = JOptionPane.showInputDialog("Insert description");

        Especialidad objEspecialidad = new Especialidad();
        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        //Call method insert and save
        objEspecialidad = (Especialidad) objEspecialidaModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }

    public static void delete(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String listEspecialidades = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades + "\n Enter ID of the Speciality to delete: "));

        Especialidad objEspecialidad = objEspecialidadModel.findById(idDelete);

        int confirm =1;
        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"Speciality not found!");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are u sure want to delete the speciality? \n"+ objEspecialidad.toString());

            if (confirm == 0) objEspecialidadModel.delete(objEspecialidad);

        }
    }

    public static void getByName(){
        String nombre = JOptionPane.showInputDialog("Insert Name: ");
        EspecialidadModel objModel = new EspecialidadModel();

        String listaString = "COINCIDENCIA \n";
        for (Especialidad iterador: objModel.findByName(nombre)){
            listaString += iterador.toString() +"\n";
        }

        JOptionPane.showMessageDialog(null,listaString);
    }

    public static void update(){
        //Use model
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String listEspecialidad = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidad+"\n Enter the Speciality ID to edit: "));

        //Obtain speciality for Id added
        Especialidad objEspecialidad = objEspecialidadModel.findById(idUpdate);
        //Valid existent of Speciality
        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"Speciality not founded...");

        }else {
            String nombre = JOptionPane.showInputDialog(null,"Enter the new name: ", objEspecialidad.getNombre());

            String descripcion = JOptionPane.showInputDialog(null,"Enter the new description: ", objEspecialidad.getDescripcion());

            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);

            objEspecialidadModel.update(objEspecialidad);
        }
    }
}
