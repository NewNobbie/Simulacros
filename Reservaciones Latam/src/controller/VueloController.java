package controller;

import entity.Vuelo;
import model.VueloModel;

import javax.swing.*;

public class VueloController {

    public static void getAll() {
        VueloModel objModel = new VueloModel();
        String listVuelos = " FLIES LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Fly
            Vuelo objVuelo = (Vuelo) i;
            listVuelos+=objVuelo.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listVuelos);
    }

    public static String getAllString(){
        VueloModel objModel = new VueloModel();
        String listVuelos = " FLIES LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Fly
            Vuelo objVuelo = (Vuelo) i;
            listVuelos+=objVuelo.toString() +"\n";
        }
        return listVuelos;
    }

    public static void getAllDestinations(){
        String destino = JOptionPane.showInputDialog("Insert the destination: ");
        VueloModel objModel =new VueloModel();

        String listVuelos = " Flights List for Destinations: \n";
        for (Object i: objModel.findByDestination(destino)){
            Vuelo objVuelo = (Vuelo) i;
            listVuelos+=objVuelo.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listVuelos);
    }

    public static void create(){
        VueloModel objModel = new VueloModel();

        String destino = JOptionPane.showInputDialog("Insert destiny name: ");
        String fecha_salida = JOptionPane.showInputDialog("Insert Deperture date: ");
        String hora_salida = JOptionPane.showInputDialog("Insert Deperture time: ");
        int id_avion_fk = Integer.parseInt(JOptionPane.showInputDialog("\n Insert the Plane ID: "));

        Vuelo objVuelo = new Vuelo();
        objVuelo.setDestion(destino);
        objVuelo.setFecha_salida(fecha_salida);
        objVuelo.setHora_salida(hora_salida);
        objVuelo.setId_avion_vuelo(id_avion_fk);

        //Insertion method and save obj that return Flies / Castear
        objVuelo = (Vuelo) objModel.insert(objVuelo);

        JOptionPane.showInputDialog(null, objVuelo.toString());

    }

    public static void update(){
        //Use Model
        VueloModel objModel = new VueloModel();

        String listVuelos = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVuelos +"\n Enter ID of the Fly to edit: "));

        //Obtain Fly ID
        Vuelo objVuelo = objModel.findById(idUpdate);
        //Valid the existence
        if (objVuelo == null){
            JOptionPane.showInputDialog(null, "Fly not founded...");
        }else {
            String destino = JOptionPane.showInputDialog(null, "Enter the new Destiny: ", objVuelo.getDestion());
            String fecha_salida = JOptionPane.showInputDialog(null, "Enter the new Departure date: ");
            String hora_salida = JOptionPane.showInputDialog(null, "Enter the new Deperture time: ");
            int id_avion_fk = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the ID of the new Plane: ", objVuelo.getId_avion_vuelo()));

            objVuelo.setDestion(destino);
            objVuelo.setFecha_salida(fecha_salida);
            objVuelo.setHora_salida(hora_salida);
            objVuelo.setId_avion_vuelo(id_avion_fk);

            objModel.update(objVuelo);
        }
    }

    public static void delete(){
        VueloModel objModel = new VueloModel();

        String listVuelos = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVuelos+"\n Enter the Fly ID to delete: "));

        Vuelo objVuelo = objModel.findById(idDelete);

        int confirm =1;
        if (objVuelo == null){
            JOptionPane.showInputDialog(null, "Fly not founded...");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are u sure want to delete the Fly?\n"+ objVuelo.toString());

            if (confirm == 0) objModel.delete(objVuelo);
        }
    }




}
