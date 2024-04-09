package controller;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;

public class CitaController {

    public static void create(){
        CitaModel objCitaModel = new CitaModel();
        PacienteModel objPacienteModel = new PacienteModel();
        MedicoModel objMedicoModel = new MedicoModel();

        Cita objCita = new Cita();
        List<Object> listPacientes = objPacienteModel.findAll();
        List<Object> listMedicos = objMedicoModel.findAll();

        String[] num1 = new String[listPacientes.size()];
        String[] num2 = new String[listMedicos.size()];

        int c1 = 0;
        int c2 = 0;
        for (Object i : listPacientes){
            Paciente paciente = (Paciente) i;
            num1[c1] = paciente.getNombre()+" "+paciente.getApellidos();
            c1++;
        }

        String input1 = (String) JOptionPane.showInputDialog(null, "\n select a patient... ", "patients available", JOptionPane.QUESTION_MESSAGE,null,num1,num1[0]);

        int Id_paciente =0;
        for (Object i: listPacientes){
            Paciente paciente = (Paciente) i;
            String full_nombre = paciente.getNombre()+ " "+paciente.getApellidos();
            if (full_nombre.equals(input1)){
                Id_paciente = paciente.getId_paciente();
            }
        }

        for (Object ic: listMedicos){
            JOptionPane.showMessageDialog(null,"pepe");
            Medico medico = (Medico) ic;
            num2[c2] = medico.getNombre()+" "+medico.getApellidos();
            c2++;
        }

        String input2 = (String) JOptionPane.showInputDialog(null,"\n select a medic...", "medics available", JOptionPane.QUESTION_MESSAGE, null,num2,num2[0]);

        int Id_medico=0;
        for (Object i: listMedicos){
            Medico medico = (Medico) i;
            String fullNombre = medico.getNombre()+" "+medico.getApellidos();
            if (fullNombre.equals(input2)){
                Id_medico = medico.getId_medico();
            }
        }

        String hora = JOptionPane.showInputDialog("Insert the appointment time (HH:mm)");

        String fecha = JOptionPane.showInputDialog("Insert the date of appointment (YYYY:MM-DD):");
        String motivo = JOptionPane.showInputDialog("Insert the reason(s)");

        objCita.setId_paciente_cita(Id_paciente);
        objCita.setId_medico_cita(Id_medico);
        objCita.setFecha_cita(fecha);
        objCita.setHora_cita(hora);
        objCita.setMotivo(motivo);
        objCita = (Cita) objCitaModel.insert(objCita);
        JOptionPane.showMessageDialog(null, objCita.toString());

    }

    public static void getAll(){
        CitaModel objCitaModel = new CitaModel();

        String listCitas = " Appointment List \n";
        for (Object i: objCitaModel.findAll()){
            Cita objCita = (Cita) i;
            listCitas+=objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listCitas);
    }

    public static String getAllString(){
        CitaModel objCitaModel = new CitaModel();

        String listCitas = " Appointment List \n";
        for (Object i: objCitaModel.findAll()){
            Cita objCita = (Cita) i;
            listCitas+=objCita.toString()+"\n";
        }
        return listCitas;
    }

    public static void getAllDate(){
        CitaModel objCitaModel = new CitaModel();
        String fecha = JOptionPane.showInputDialog("Enter the appointment date");
        List<Cita> list = objCitaModel.findByDate(fecha);

        String listCitas = " Appointment List \n";
        for (Object i: list){
            Cita objCita = (Cita) i;
            listCitas+= objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listCitas);
    }

    public static void update(){
        CitaModel objCitaModel = new CitaModel();
        PacienteModel objPacienteModel = new PacienteModel();
        MedicoModel objMedicoModel = new MedicoModel();

        List<Object> listPacientes = objPacienteModel.findAll();
        List<Object> listMedicos = objMedicoModel.findAll();

        String list = getAllString();
        int idUpdated =  Integer.parseInt(JOptionPane.showInputDialog(list +"\n Enter the Id of the Appointment"));

        Cita objCita = objCitaModel.findById(idUpdated);

        Paciente objPaciente = objPacienteModel.findById(objCita.getId_paciente_cita());
        Medico objMedico = objMedicoModel.findById(objCita.getId_medico_cita());

        if (objCita == null){
            JOptionPane.showInputDialog(null,"Appointment not founded");
        }else {

            String[] ele1 = new String[listPacientes.size()];
            String[] ele2 = new String[listMedicos.size()];

            int c1 = 0;
            int c2 = 0;
            for (Object i : listPacientes) {
                Paciente paciente = (Paciente) i;
                ele1[c1] = paciente.getNombre() + " " + paciente.getApellidos();
                c1++;
            }

            String fullnamePaciente = objPaciente.getNombre() + " " + objPaciente.getApellidos();
            String fullnameMedico = objMedico.getNombre() + " " + objMedico.getApellidos();

            String input1 = (String) JOptionPane.showInputDialog(null, "\n select a patient...", "patient registered \n" + fullnamePaciente + "patients available", JOptionPane.QUESTION_MESSAGE, null, ele1, ele1[0]);


            int Id_Paciente = 0;
            for (Object i : listPacientes) {
                Paciente paciente = (Paciente) i;
                String fullname = paciente.getNombre() + " " + paciente.getApellidos();
                if (fullname.equals(input1)) {
                    Id_Paciente = paciente.getId_paciente();
                }
            }

            for (Object i: listMedicos){
                Medico medico = (Medico) i;
                ele2[c2] = medico.getNombre() + " "+ medico.getApellidos();
                c2++;
            }

            String input2 = (String) JOptionPane.showInputDialog(null, "\n select a medic...", "medic registered\n"+fullnameMedico+"\n medic available", JOptionPane.QUESTION_MESSAGE, null, ele2, ele2[0]);

            int Id_Medico = 0;
            for (Object i: listMedicos){
                Medico medico = (Medico) i;
                String fullName = medico.getNombre() +" "+ medico.getApellidos();
                if (fullName.equals(input2)){
                    Id_Medico = medico.getId_medico();
                }
            }

            String hora = JOptionPane.showInputDialog("Insert the appointment time (HH:mm)");

            String fecha = JOptionPane.showInputDialog("Insert the date of appointment (YYYY:MM-DD):");
            String motivo = JOptionPane.showInputDialog("Insert the reason(s)");

            objCita.setId_paciente_cita(Id_Paciente);
            objCita.setId_medico_cita(Id_Medico);
            objCita.setFecha_cita(fecha);
            objCita.setHora_cita(hora);
            objCita.setMotivo(motivo);
            objCita = (Cita) objCitaModel.insert(objCita);
            JOptionPane.showInputDialog(null, objCita.toString());

        }
    }

    public  static void delete(){
        CitaModel objCitaModel = new CitaModel();
        String list = getAllString();

        int isDeleted = Integer.parseInt(JOptionPane.showInputDialog(list + "\n Enter the Appointment ID to delete: "));

        Cita objCita = objCitaModel.findById(isDeleted);
        int confirm = 1;
        if (objCitaModel == null){
            JOptionPane.showInputDialog(null, "Medic not founded...");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the patient?\n" + objCita.toString());
            if (confirm==0)objCitaModel.delete(objCita);
        }
    }


}
