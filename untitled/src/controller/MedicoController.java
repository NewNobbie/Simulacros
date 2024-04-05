package controller;

import entity.Medico;
import entity.Paciente;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;
import java.awt.*;
import java.nio.file.WatchService;
import java.util.List;

public class MedicoController {

    public static void getAll(){
        MedicoModel objModel = new MedicoModel();
        String listMedicos = " MEDIC LIST \n ";

        for (Object iterador: objModel.findAll()){
            //Convert obj to medic
            Medico objMedico = (Medico) iterador;
            listMedicos+= objMedico.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listMedicos);
    }

    public static void getAll(List<Medico> pepe){

        String listMedicos = " MEDIC LIST \n ";

        for (Object iterador: pepe){
            //Convert obj to medic
            Medico objMedico = (Medico) iterador;
            listMedicos+= objMedico.toStringSpecial() +"\n";
        }
        JOptionPane.showMessageDialog(null,listMedicos);
    }

    public static String getAllString(){
        MedicoModel objModel = new MedicoModel();
        String listMedicos = " MEDIC LIST \n ";

        for (Object iterador: objModel.findAll()){
            //Convert obj to medic
            Medico objMedico = (Medico) iterador;
            listMedicos+= objMedico.toString() +"\n";
        }

        return listMedicos;
    }

    public static void create(){
        MedicoModel objMedicoModel = new MedicoModel();
        String nombre = JOptionPane.showInputDialog("Insert name");
        String apellidos = JOptionPane.showInputDialog("Insert surname");
        String especialidad = EspecialidadController.getAllString();
        int id_especialidad_medico = Integer.parseInt(JOptionPane.showInputDialog(especialidad+"\n Insert the Speciality Id: "));

        Medico objMedico = new Medico();
        objMedico.setNombre(nombre);
        objMedico.setApellidos(apellidos);
        objMedico.setId_especialidad_medico(id_especialidad_medico);

        //Insertion method and save obj that return patient / castear
        objMedico = (Medico) objMedicoModel.insert(objMedico);

        JOptionPane.showMessageDialog(null,objMedico.toString());

    }

    public static void update(){
        //Use model
        MedicoModel objMedicoModel = new MedicoModel();

        String listMedicos = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listMedicos+"\nEnter ID of the Medic to edit: "));

        //Obtain Medic ID
        Medico objMedico = objMedicoModel.findById(idUpdate);
        //Valid the existence
        if (objMedico == null){
            JOptionPane.showMessageDialog(null,"Medic not founded. . .");
        }else {
            String nombre = JOptionPane.showInputDialog(null,"Enter new name: ", objMedico.getNombre());
            String apellidos = JOptionPane.showInputDialog(null,"Enter new surname: ", objMedico.getApellidos());
            int id_especialidad_medico = Integer.parseInt(JOptionPane.showInputDialog(listMedicos+ "\nEnter the ID of new speciality", objMedico.getId_especialidad_medico()));

            objMedico.setNombre(nombre);
            objMedico.setApellidos(apellidos);
            objMedico.setId_especialidad_medico(id_especialidad_medico);

            objMedicoModel.update(objMedico);

        }
    }

    public static void delete(){
        MedicoModel objMedicModel = new MedicoModel();

        String listMedicos = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedicos+"\n Enter the Medic ID to delete: "));

        Medico objMedic = objMedicModel.findById(idDelete);

        int confirm =1;
        if (objMedic == null){
            JOptionPane.showMessageDialog(null,"Medic not founded. . .");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are u sure want to delete the Medic? \n"+ objMedic.toString());

            if (confirm == 0) objMedicModel.delete(objMedic);
        }
    }
}
