package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;

public class AvionController {

    public static void getAll(){
        AvionModel objAvionModel = new AvionModel();

        String listAviones = " PLANES LIST \n";

        for (Object i: objAvionModel.findAll()){
            //Convert to obj Plane
            Avion objAvion = (Avion) i;
            listAviones+= objAvion.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listAviones);
    }

    public static String getAllString(){
        AvionModel objModel = new AvionModel();
        String listAviones = " PLANES LIST \n";

        for (Object i: objModel.findAll()){
            //Convert obj to Plane
            Avion objAvion = (Avion) i;
            listAviones += objAvion.toString() +"\n";
        }
        return listAviones;
    }

    public static void create(){
        AvionModel objAvionModel = new AvionModel();

        String modelo = JOptionPane.showInputDialog("Insert Plane");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Insert capacity"));

        Avion objAvion = new Avion();
        objAvion.setModelo(modelo);
        objAvion.setCapacidad(capacidad);

        //Call method insert and save
        objAvion = (Avion) objAvionModel.insert(objAvion);

        JOptionPane.showMessageDialog(null, objAvion.toString());
    }

    public static void delete(){
        AvionModel objAvionModel = new AvionModel();

        String listAviones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\n Enter the Plan ID to delete: "));

        Avion objAvion = objAvionModel.findById(idDelete);

        int confirmo = 1;
        if (objAvion == null){
            JOptionPane.showMessageDialog(null, "Plane not found!");
        }else {
            confirmo = JOptionPane.showConfirmDialog(null, "Are u sure want to delete the Plane? \n" + objAvion.toString());

            if (confirmo == 0) objAvionModel.delete(objAvion);
        }
    }

    public static void getByname(){
        String model = JOptionPane.showInputDialog("Insert the Plane model to serach: ");
        AvionModel objModel = new AvionModel();

        String listString = " RESULTS \n";
        for (Avion i: objModel.findByModelo(model)){
            listString += i.toString() +"\n";
        }
    }

    public static void update(){
        //Use model
        AvionModel objModel = new AvionModel();

        String listAviones = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAviones +"\n Enter the Plane ID to edit: "));

        //Obtain Plane for Id added
        Avion objAvion = (Avion) objModel.findById(idUpdate);
        //Valid existent of Plane
        if (objAvion == null){
            JOptionPane.showMessageDialog(null, "Plane not founded...");
        }else {
            String modelo = JOptionPane.showInputDialog(null, "Enter the new model: ", objAvion.getModelo());

            int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new Plane: ", objAvion.getCapacidad()));

            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);

            objModel.update(objAvion);
        }
    }
}
