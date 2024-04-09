package controller;

import entity.Especialidad;
import entity.Paciente;
import model.EspecialidadModel;
import model.PacienteModel;

import javax.swing.*;

public class PacienteController {

    public static void getAll(){
        PacienteModel objModel = new PacienteModel();
        String listPacientes = " PATIENTS LIST \n";

        for (Object iterador: objModel.findAll()){
            //Convert obj to Patient
            Paciente objPaciente = (Paciente) iterador;
            listPacientes+= objPaciente.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listPacientes);
    }

    public static String getAllString(){
        PacienteModel objModel = new PacienteModel();
        String listPacientes = " PATIENT LIST \n";

        for (Object iterador: objModel.findAll()){
            //Convert obj to Patient
            Paciente objPaciente = (Paciente) iterador;
            listPacientes+= objPaciente.toString() +"\n";
        }

        return listPacientes;
    }

    public static void create(){
        PacienteModel objPacienteModel = new PacienteModel();

        String nombre = JOptionPane.showInputDialog("Insert name: ");
        String apellidos = JOptionPane.showInputDialog("Insert lastname: ");
        String fecha_nacieminto = JOptionPane.showInputDialog("Insert birthdate (YYYY-MM-dd): ");
        String documento_identidad = JOptionPane.showInputDialog("Insert Identity document: ");

        Paciente objPaciente = new Paciente();
        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellidos);
        objPaciente.setFecha_nacimiento(fecha_nacieminto);
        objPaciente.setDocumento_identidad(documento_identidad);

        //Call method of insertion to save the obj that return Patient previously, casterar
        objPaciente = (Paciente) objPacienteModel.insert(objPaciente);

        JOptionPane.showMessageDialog(null,objPaciente.toString());

    }

    public static void delete(){
        PacienteModel objPacienteModel = new PacienteModel();

        String listPacientes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\n Enter the Patient ID to delete: "));

        Paciente objPaciente = objPacienteModel.findById(idDelete);

        int confirm =1;
        if (objPaciente == null){
            JOptionPane.showMessageDialog(null,"Patient not founded");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are u sure want to delete the Patient? \n"+objPaciente.toString());

            if (confirm == 0) objPacienteModel.delete(objPaciente);

        }

    }

    public static void  getByName(){
        String nombre = JOptionPane.showInputDialog("Insert Name: ");
        PacienteModel objModel = new PacienteModel();

        String listString = "COINCIDENCIS \n";
        for (Paciente iterador: objModel.findByName(nombre)){
            listString += iterador.toString() +"\n";
        }

        JOptionPane.showMessageDialog(null,listString);
    }

    public static void update(){
        ///1. Use model
        PacienteModel objPacienteModel = new PacienteModel();

        String listPacientes = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPacientes+"\nEnter the Id Patient to edit: "));

        //Obtain patient for id
        Paciente objPaciente = objPacienteModel.findById(idUpdate);
        //Valid that exist
        if (objPaciente == null){
            JOptionPane.showMessageDialog(null,"Patient not founded");
        }else {
            String nombre = JOptionPane.showInputDialog(null, "Enter new name: ", objPaciente.getNombre());
            String apellidos = JOptionPane.showInputDialog(null,"Enter the surname", objPaciente.getApellidos());
            String fecha_nacimiento = JOptionPane.showInputDialog(null,"Enter the date born");
            String  documento_identidad = JOptionPane.showInputDialog(null, "Enter the identification document; ");

            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFecha_nacimiento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documento_identidad);

            objPacienteModel.update(objPaciente);
        }
    }
}
