package entity;

public class Vuelo {

    private int id_vuelo;
    private String destion;
    private String fecha_salida;
    private String hora_salida;
    private int id_avion_vuelo;

    public Vuelo() {
    }

    public Vuelo(int id_vuelo, String destion, String fecha_salida, String hora_salida, int id_avion_vuelo) {
        this.id_vuelo = id_vuelo;
        this.destion = destion;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.id_avion_vuelo = id_avion_vuelo;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public String getDestion() {
        return destion;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public int getId_avion_vuelo() {
        return id_avion_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public void setDestion(String destion) {
        this.destion = destion;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public void setId_avion_vuelo(int id_avion_vuelo) {
        this.id_avion_vuelo = id_avion_vuelo;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id_vuelo=" + id_vuelo +
                ", destion='" + destion + '\'' +
                ", fecha_salida='" + fecha_salida + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", id_avion_vuelo=" + id_avion_vuelo +
                '}';
    }
}
