package entity;

public class Medico {

    private int id_medico;
    private String nombre;
    private String apellidos;
    private int id_especialidad_medico;
    private String especialidad;


    public Medico() {
    }

    public Medico(int id_medico, String nombre, String apellidos, int id_especialidad_medico, String especialidad) {
        this.id_medico = id_medico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_especialidad_medico = id_especialidad_medico;
        this.especialidad = especialidad;
    }

    public int getId_medico() {
        return id_medico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getId_especialidad_medico() {
        return id_especialidad_medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setId_especialidad_medico(int id_especialidad_medico) {
        this.id_especialidad_medico = id_especialidad_medico;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id_medico=" + id_medico +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id_especialidad_medico=" + id_especialidad_medico +
                '}';
    }

    public String toStringSpecial() {
        return "Medico{" +
                "id_medico=" + id_medico +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", especialidad =" + especialidad +
                '}';
    }


}
