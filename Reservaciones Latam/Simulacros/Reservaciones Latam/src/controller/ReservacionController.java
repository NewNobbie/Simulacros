package controller;

import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.PasajeroModel;
import model.ReservacionModel;
import model.VueloModel;

import javax.crypto.interfaces.PBEKey;
import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.util.List;

public class ReservacionController {

    public static void create() {
        ReservacionModel objReservacionModel = new ReservacionModel();
        PasajeroModel objPasajeroModel = new PasajeroModel();
        VueloModel objVueloModel = new VueloModel();

        Reservacion objReservacion = new Reservacion();
        List<Object> listPasajeros = objPasajeroModel.findAll();
        List<Object> listVuelos = objVueloModel.findAll();

        String[] num1 = new String[listPasajeros.size()];
        String[] num2 = new String[listVuelos.size()];

        int c1 = 0;
        int c2 = 0;
        for (Object i : listPasajeros) {
            Pasajero pasajero = (Pasajero) i;
            num1[c1] = pasajero.getNombre() + " " + pasajero.getApellido();
            c1++;
        }

        String input1 = (String) JOptionPane.showInputDialog(null, "\n Select a Pasenger...", "pasengers available", JOptionPane.QUESTION_MESSAGE, null, num1, num1[0]);

        int Id_pasajero = 0;
        for (Object i : listPasajeros) {
            Pasajero pasajero = (Pasajero) i;
            String full_info_pasajero = pasajero.getNombre() + " " + pasajero.getApellido() + " " + pasajero.getId_pasajero();
            if (full_info_pasajero.equals(input1)) {
                Id_pasajero = pasajero.getId_pasajero();
            }
        }

        for (Object i2 : listVuelos) {
            JOptionPane.showMessageDialog(null, "Focus brad");
            Vuelo vuelo = (Vuelo) i2;
            num2[c2] = vuelo.getDestion() + " " + vuelo.getFecha_salida() + " " + vuelo.getHora_salida() + " " + vuelo.getId_avion_vuelo();
        }

        String input2 = (String) JOptionPane.showInputDialog(null, "\n select a fly...", "Fly available", JOptionPane.QUESTION_MESSAGE, null, num2, num2[0]);

        int Id_vuelo = 0;
        for (Object i3 : listVuelos) {
            Vuelo vuelo = (Vuelo) i3;
            String full_info_vuelo = vuelo.getDestion() + " " + vuelo.getFecha_salida() + " " + vuelo.getHora_salida() + " " + vuelo.getId_avion_vuelo();
            if (full_info_vuelo.equals(input2)) {
                Id_vuelo = vuelo.getId_vuelo();
            }
        }

        String fecha_reservacion = JOptionPane.showInputDialog("Insert the reservation date: ");
        String asiento = JOptionPane.showInputDialog("Insert the seat reservation: ");

        objReservacion.setId_pasajero_reservacion(Id_pasajero);
        objReservacion.setId_vuelo_reservacion(Id_vuelo);
        objReservacion.setFecha_reservacion(fecha_reservacion);
        objReservacion.setAsiento(asiento);
        objReservacion = (Reservacion) objReservacionModel.insert(objReservacion);
        JOptionPane.showInputDialog(null, objReservacion.toString());
    }

    public static void getAll(){
        ReservacionModel objModel = new ReservacionModel();

        String listReservaciones = " Bookings List \n";
        for (Object i: objModel.findAll()){
            Reservacion objReservacion = (Reservacion) i;
            listReservaciones+= objReservacion.toString()+"\n";
        }
        JOptionPane.showInputDialog(null, listReservaciones);
    }

    public static String getAllString(){
        ReservacionModel objModel = new ReservacionModel();

        String listReservaciones = " Bookings List \n";
        for (Object i: objModel.findAll()){
            Reservacion objReservacion = (Reservacion) i;
            listReservaciones+= objReservacion.toString()+"\n";
        }
        return listReservaciones;
    }

    public static void getAll(String destino){
        ReservacionModel objModel = new ReservacionModel();

        String listReservaciones = " Bookings List: \n";
        for (Object i: objModel.findAll()){
            Reservacion reservacion = (Reservacion) i;
            listReservaciones+=reservacion.toString()+"\n";
        }
        JOptionPane.showInputDialog(null,listReservaciones);
    }

    public static void update(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        PasajeroModel objPasajeroModel = new PasajeroModel();
        VueloModel objVueloModel = new VueloModel();

        List<Object> listPasajeros = objPasajeroModel.findAll();
        List<Object> listVuelos = objVueloModel.findAll();

        String list = getAllString();
        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(list +"\n Enter the ID of the Booking: "));

        Reservacion objReservacion = objReservacionModel.findById(idUpdated);

        Pasajero objPasajero = objPasajeroModel.findById(objReservacion.getId_pasajero_reservacion());
        Vuelo objVuelo = objVueloModel.findById(objReservacion.getId_vuelo_reservacion());

        if (objReservacion == null){
            JOptionPane.showInputDialog(null, "Booking not found! ");
        }else {

            String[] ele1 = new String[listPasajeros.size()];
            String[] ele2 = new String[listVuelos.size()];

            int c1=0;
            int c2=0;
            for (Object i: listPasajeros){
                Pasajero pasajero = (Pasajero) i;
                ele1[c1] = pasajero.getNombre()+" "+pasajero.getApellido()+" "+pasajero.getDocumento_identidad();
                c1++;
            }

            String full_info_pasajero = objPasajero.getNombre()+" "+objPasajero.getApellido()+" "+objPasajero.getDocumento_identidad();
            String full_info_vuelo = objVuelo.getDestion()+" "+objVuelo.getFecha_salida()+" "+objVuelo.getHora_salida()+" "+objVuelo.getId_avion_vuelo();

            String input1 = (String) JOptionPane.showInputDialog(null, "\n select a passenger...", "passenger registered \n"+ full_info_pasajero+"passenger available", JOptionPane.QUESTION_MESSAGE,null, ele1,ele1[0]);

            int Id_pasajero = 0;
            for (Object i2: listPasajeros){
                Pasajero pasajero = (Pasajero) i2;
                String full_info_pasa = pasajero.getNombre()+" "+pasajero.getApellido()+" "+pasajero.getDocumento_identidad();
                if (full_info_pasa.equals(input1)){
                    Id_pasajero = pasajero.getId_pasajero();
                }
            }

            for (Object i3: listVuelos){
                Vuelo vuelo = (Vuelo) i3;
                ele2[c2] = vuelo.getDestion()+" "+ vuelo.getHora_salida()+" "+vuelo.getId_avion_vuelo();
                c2++;
            }

            String input2 = (String) JOptionPane.showInputDialog(null, "\n select a fly...", "fly registered\n"+full_info_vuelo+"\n fly available", JOptionPane.QUESTION_MESSAGE,null, ele2,ele2[0]);

            int Id_Vuelo=0;
            for (Object i4: listVuelos){
                Vuelo vuelo = (Vuelo) i4;
                if (full_info_vuelo.equals(input2)){
                    Id_Vuelo = vuelo.getId_vuelo();
                }
            }

            String fecha_reservacion = JOptionPane.showInputDialog("Insert the Booking date: ");
            String asiento = JOptionPane.showInputDialog("Insert the Booking seat: ");

            objReservacion.setId_pasajero_reservacion(Id_pasajero);
            objReservacion.setId_vuelo_reservacion(Id_Vuelo);
            objReservacion.setFecha_reservacion(fecha_reservacion);
            objReservacion.setAsiento(asiento);
            objReservacion = (Reservacion) objReservacionModel.insert(objReservacion);
            JOptionPane.showInputDialog(null, objReservacion.toString());
        }
    }

    public static void delete(){
        ReservacionModel objModel = new ReservacionModel();
        String list = getAllString();

        int isDeleted = Integer.parseInt(JOptionPane.showInputDialog(list+ "\n Enter the Booking ID to delete: "));

        Reservacion objReservacion = objModel.findById(isDeleted);
        int confirm=1;
        if (objModel == null){
            JOptionPane.showInputDialog(null, "Booking not founded...");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are u sure want to delete the Booking?\n"+objReservacion.toString());
            if (confirm==0)objModel.delete(objReservacion);
        }
    }



}
