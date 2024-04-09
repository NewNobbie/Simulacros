import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import entity.Cita;
import entity.Especialidad;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import model.EspecialidadModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String option= "";

        do {
            option = JOptionPane.showInputDialog("""
                    Welcome to the Information Management menu of the Cardio Vid Hospital ;)
                    
                    [ Select some Database to interact ]
                    1) Medics DB
                    2) Patients DB
                    3) Specialities DB
                    4) Appointment DB
                    5) Exit
                    
                    Enter one number to select:
                    """);
            switch (option){
                case "1":
                    menu_medics();
                    break;
                case "2":
                    menu_patients();
                    break;
                case "3":
                    menu_specialities();
                    break;
                case "4":
                    menu_appointment();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null,"Leaving to Cardio Vid Menu... ;)");
                    break;


            }
        }while (!option.equals("5"));
}
        public static void menu_medics(){

            String option="";
            MedicoModel objModel = new MedicoModel();

            do {
                option = JOptionPane.showInputDialog("""
                    Menu Medics 
                    1) List Medics
                    2) Insert Medics
                    3) Update Medics
                    4) Delete Medics
                    5) Search Medics...
                    6) Return to the Main Menu
                    """);
                switch (option){
                    case "1":
                        MedicoController.getAll();
                        break;
                    case "2":
                        MedicoController.create();
                        break;
                    case "3":
                        MedicoController.update();
                        break;
                    case "4":
                        MedicoController.delete();
                        break;
                    case "5":
                        String option2 ="";
                        do {
                            option2 = JOptionPane.showInputDialog("""
                            1) Search by Specialities
                            2) Search by name
                            3) Search by ID
                            4) Return
                            """);
                            switch (option2){
                                case "1":
                                    EspecialidadModel objEspecialidadModal = new EspecialidadModel();

                                    List<Object> listSpeciality = objEspecialidadModal.findAll();

                                    String[] ele1 = new String[listSpeciality.size()];
                                    int index1 = 0;

                                    int id_especialidades = 0;

                                    // Give all specialities in selector
                                    for (Object i: listSpeciality){
                                        Especialidad especialidad = (Especialidad) i;
                                        ele1[index1] = especialidad.getNombre();
                                        index1++;
                                    }

                                    // Dropdown
                                    String input1 = (String) JOptionPane.showInputDialog(null, "\n Select the Medic Speciality", "Speciality available",JOptionPane.QUESTION_MESSAGE,null,ele1,ele1[0]);

                                    MedicoModel objMedicModal = new MedicoModel();
                                    List<Medico> listMedic = objMedicModal.findBySpeciality(input1);

                                    MedicoController.getAll(listMedic);

                                    break;

                                case  "2":
                                    String name = JOptionPane.showInputDialog(null,"Insert the name to search: ");
                                    Medico medico = (Medico) objModel.findByName(name);
                                    JOptionPane.showMessageDialog(null,medico.toString());
                                    break;
                                case "3":
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert Medic ID to return: "));
                                    Medico medico2 = (Medico) objModel.findById(id);
                                    JOptionPane.showMessageDialog(null, medico2.toString());
                                    break;
                                case "4":
                                    JOptionPane.showMessageDialog(null,"Returning to the main menu");
                                    break;


                                // Iterate and compare the name selection
                                /*for (Object i: listSpeciality){
                                    Especialidad especialidad = (Especialidad) i;
                                    String nombreSpeci = especialidad.getNombre();
                                    // If name = speciality save id
                                    if(nombreSpeci.equals(input1)){
                                        id_especialidades = especialidad.getId_especialidad();
                                    }
                                }*/

                            }
                        }while (!option2.equals("4"));
                    case "6":
                        JOptionPane.showMessageDialog(null,"Return to the main menu");
                        break;

                }
            }while (!option.equals("6"));
        }

        public static void menu_patients(){
            PacienteModel objPatientModel = new PacienteModel();
            String option = "";

            do {
                option = JOptionPane.showInputDialog("""
                        Menu Patients
                        1) List Patients
                        2) Insert Patients
                        3) Update Patients
                        4) Delete Patients
                        5) Search Patients...
                        6) Return to the Main Menu
                        """);
                switch (option) {
                    case "1":
                        PacienteController.getAll();
                        break;
                    case "2":
                        PacienteController.create();
                        break;
                    case "3":
                        PacienteController.update();
                        break;
                    case "4":
                        PacienteController.delete();
                        break;
                    case "5":
                        String option2 = "";
                        do {
                            option2 = JOptionPane.showInputDialog("""
                                1) Search by Name
                                2) Search by Document
                                3) Search by id
                                4) Return                             
                                """);

                            switch (option2){
                                case "1":
                                    PacienteController.getByName();
                                    break;
                                case "2":
                                    String document = (JOptionPane.showInputDialog(null, "Insert Patient Document to return: "));
                                    Paciente pacienteD = (Paciente)objPatientModel.findByDocument(document);
                                    JOptionPane.showMessageDialog(null, pacienteD.toString());
                                    break;
                                case "3":
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert Patient ID to return: "));
                                    Paciente paciente2 = objPatientModel.findById(id);
                                    if (paciente2 == null){
                                        JOptionPane.showMessageDialog(null,"Patient not founded");
                                    }else {
                                    JOptionPane.showMessageDialog(null, paciente2.toString());
                                    }
                                    break;
                                case "4":
                                    JOptionPane.showMessageDialog(null,"Returning to the Patient menu");
                                    break;
                            }
                        }while (!option2.equals("4"));
                    case "6":
                        JOptionPane.showMessageDialog(null,"Returning to the main Menu");
                        break;
                }
            }while (!option.equals("6"));
        }

        public static void menu_specialities(){
            String option = "";
            EspecialidadModel objEspecialidadModel = new EspecialidadModel();

            do {
                option = JOptionPane.showInputDialog("""
                        Menu Specialities
                        1) List Specialities
                        2) Insert Speciality
                        3) Update Speciality
                        4) Delete Speciality
                        5) Search Speciality...
                        6) Return to the Main Menu
                        """);
                switch (option){
                    case "1":
                        EspecialidadController.getAll();
                        break;
                    case "2":
                        EspecialidadController.create();
                        break;
                    case "3":
                        EspecialidadController.update();
                        break;
                    case "4":
                        EspecialidadController.delete();
                        break;
                    case "5":
                        String option2 = "";
                        do {
                            option2 = JOptionPane.showInputDialog("""
                                    1) Search by Name
                                    2) Search by ID
                                    3) Return to Specialities Menu
                                    """);
                            switch (option2){
                                case "1":
                                    EspecialidadController.getByName();
                                    break;
                                case "2":
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert Speciality ID to return: "));
                                    Especialidad especialidad2 = (Especialidad) objEspecialidadModel.findById(id);
                                    JOptionPane.showMessageDialog(null, especialidad2.toString());
                                    break;
                                case "3":
                                    JOptionPane.showMessageDialog(null,"Returning to Specialities Menu...");
                                    break;
                            }

                        }while (!option2.equals("3"));
                    case "6":
                        JOptionPane.showMessageDialog(null,"Returning to Main Menu...");
                        break;
                }
            }while (!option.equals("6"));
        }

        public static void menu_appointment(){
            CitaModel objCitaModel = new CitaModel();
            String option = "";

            do {
                option = JOptionPane.showInputDialog("""
                        Menu Appointments 
                        1) List Appointments
                        2) Insert Appointment
                        3) Update Appointment
                        4) Delete Appointment
                        5) Search Appointments...
                        6) Return to the Main Menu
                        """);
                switch (option){
                    case "1":
                        CitaController.getAll();
                        break;
                    case "2":
                        CitaController.create();
                        break;
                    case "3":
                        CitaController.update();
                        break;
                    case "4":
                        CitaController.delete();
                        break;
                    case "5":
                        String option2 = "";

                        do {
                            option2 = JOptionPane.showInputDialog("""
                                    1) Search by Date
                                    2) Search by ID
                                    3) Exit 
                                    """);
                            switch (option2){
                                case "1":
                                    CitaController.getAllDate();
                                    break;
                                case "2":
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert Appointment ID to return: "));
                                    Cita cita2 = (Cita) objCitaModel.findById(id);
                                    JOptionPane.showMessageDialog(null, cita2.toString());

                                    break;
                                case "3":
                                    JOptionPane.showMessageDialog(null, "Returning to Appointment menu...");
                                    break;
                            }
                        }while (!option2.equals("3"));
                    case "6":
                        JOptionPane.showMessageDialog(null,"Returning to Main menu");

                }
            }while (!option.equals("6"));
        }
    }




