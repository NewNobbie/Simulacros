import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;
import entity.Reservacion;
import model.ReservacionModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option="";

        do {
            option = JOptionPane.showInputDialog("""
                    Welcome to the Booking Database Management of LATAM >;)
                    
                    { Select some DataBase to interact}
                    1) Plane DB
                    2) Passenger DB
                    3) Flight DB
                    4) Booking DB
                    5) Exit
                    """);
            switch (option){
                case "1":
                    menu_planes();
                    break;
                case "2":
                    menu_passengers();
                    break;
                case "3":
                    menu_flight();
                    break;
                case "4":
                    menu_bookings();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Thx for stay with Latam earline :)");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option...");
            }
        }while (!option.equals("5"));
    }
    public static void menu_planes(){

        String option="0";

        do {
            option= JOptionPane.showInputDialog(null, """
                    PLANES MENU
                    1) List Planes
                    2) Insert Plane
                    3) Update Plane
                    4) Delete Plane
                    5) Search Plane by Name
                    6) Return to the Main Menu
                    """);
            switch (option){
                case "1":
                    AvionController.getAll();
                    break;
                case "2":
                    AvionController.create();
                    break;
                case "3":
                    AvionController.update();
                    break;
                case "4":
                    AvionController.delete();
                    break;
                case "5":
                    AvionController.getByname();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Returning to the main menu...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option...");
            }
        }while (!option.equals("6"));
    }

    public static void menu_passengers(){

        String option="";

        do {
            option = JOptionPane.showInputDialog("""
                    PASSENGERS MENU
                    1) List Passengers
                    2) Insert Passenger
                    3) Update Passenger
                    4) Delete Passenger
                    5) Search Passenger by Name
                    6) Exit
                    """);
            switch (option){
                case "1":
                    PasajeroController.getAll();
                    break;
                case "2":
                    PasajeroController.create();
                    break;
                case "3":
                    PasajeroController.update();
                    break;
                case "4":
                    PasajeroController.delete();
                    break;
                case "5":
                    PasajeroController.getByName();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Returning to the main menu...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option...");
                    break;
            }
        }while (!option.equals("6"));
    }

    public static void menu_flight(){

        String option = "";

        do {
            option = JOptionPane.showInputDialog(null, """
                    Flight Menu
                    1) List Flights
                    2) Insert Flight
                    3) Update Flight
                    4) Delete Flight
                    5) Search Flight by Destinations
                    6) Return to Main Menu
                    """);
            switch (option){
                case "1":
                    VueloController.getAll();
                    break;
                case "2":
                    VueloController.create();
                    break;
                case "3":
                    VueloController.update();
                    break;
                case "4":
                    VueloController.delete();
                    break;
                case "5":
                    VueloController.getAllDestinations();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Returning to the main menu...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option...");
            }
        }while (!option.equals("6"));
    }

    public static void menu_bookings(){
        ReservacionModel objModel = new ReservacionModel();

        String option="";

        do {
            option = JOptionPane.showInputDialog("""
                    Menu Bookings
                    1) List Bookings
                    2) Insert Bookings
                    3) Update Bookings
                    4) Delete Bookings
                    5) Search Bookings
                    6) Return to the main menu
                    """);
            switch (option){
                case "1":
                    ReservacionController.getAll();
                    break;
                case "2":
                    ReservacionController.create();
                    break;
                case "3":
                    ReservacionController.update();
                    break;
                case "4":
                    ReservacionController.delete();
                    break;
                case "5":
                    ReservacionController.getAll(JOptionPane.showInputDialog(null,"Enter the name of destination for search: "));
                        break;
                case "6":
                    JOptionPane.showMessageDialog(null,"Returning to the main menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option...");
            }
        }while (!option.equals("6"));
    }
}