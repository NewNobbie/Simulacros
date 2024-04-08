package controller;

import entity.Pasajero;
import model.PasajeroModel;

import javax.swing.*;

public class PasajeroController {


    public static void getAll(){
        PasajeroModel objModel = new PasajeroModel();

        String listPasajeros = " PASENGERS LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Pasenger
            Pasajero objPasajero = (Pasajero) i;
            listPasajeros+=objPasajero.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listPasajeros);
    }

    public static String getAllSting(){
        PasajeroModel objModel = new PasajeroModel();

        String listPasajeros = " PASENGERS LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Pasenger
            Pasajero objPasajero = (Pasajero) i;
            listPasajeros+=objPasajero.toString() + "\n";
        }
        return listPasajeros;
    }

    public static void create(){
        PasajeroModel objModel = new PasajeroModel();

        String nombre = JOptionPane.showInputDialog("Insert the name: ");
        String apellido = JOptionPane.showInputDialog("Insert the surname: ");
        String documento_identidad = JOptionPane.showInputDialog("Insert the Identity Document: ");

        Pasajero objPasajero = new Pasajero();
        objPasajero.setNombre(nombre);
        objPasajero.setApellido(apellido);
        objPasajero.setDocumento_identidad(documento_identidad);

        //Call method insert and save
        objPasajero = (Pasajero) objModel.insert(objPasajero);

        JOptionPane.showMessageDialog(null, objPasajero.toString());
    }

    public static void delete(){
        PasajeroModel objModel = new PasajeroModel();

        String listPasajeros = getAllSting();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros + "\n Enter ID of the Pasenger to delete: "));

        Pasajero objPasajero = objModel.findById(idDelete);

        int confirm =1;
        if (objPasajero == null){
            JOptionPane.showMessageDialog(null, "Pasenger not found!");

        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are u sure want to delete the Pasenger? \n"+ objPasajero.toString());

            if (confirm == 0) objModel.delete(objPasajero);
        }
    }

    public static void getByName(){
         String nombre = JOptionPane.showInputDialog("insert Name: ");

         PasajeroModel objModel = new PasajeroModel();

         String listString = " RESULTS \n";
         for (Pasajero i: objModel.findByName(nombre)){
             listString += i.toString()+ "\n";
         }

         JOptionPane.showMessageDialog(null, listString);
    }

    public static void update(){
        //Use Model
        PasajeroModel objModel = new PasajeroModel();

        String listPasajeros = getAllSting();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros+"\n Enter the Pasenger ID to edit: "));

        //Obtain Pasengers for Id added
        Pasajero objPasajero = objModel.findById(idUpdate);
        //Valid existent of Pasengers
        if (objPasajero == null){
            JOptionPane.showMessageDialog(null, "Pasenger not founded...");
        }else {
            String nombre = JOptionPane.showInputDialog(null, "Enter the new name: ", objPasajero.getNombre());

            String apellido = JOptionPane.showInputDialog(null, "Enter the new surname: ", objPasajero.getApellido());

            String documento_identidad = JOptionPane.showInputDialog(null, "Enter the new Identity Document: ", objPasajero.getDocumento_identidad());

            objPasajero.setNombre(nombre);
            objPasajero.setApellido(apellido);
            objPasajero.setDocumento_identidad(documento_identidad);

            objModel.update(objPasajero);
        }
    }
}
