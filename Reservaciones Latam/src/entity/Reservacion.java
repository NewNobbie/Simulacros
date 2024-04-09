package entity;

public class Reservacion {

    private int id_reservacion;
    private int id_pasajero_reservacion;
    private int id_vuelo_reservacion;
    private String fecha_reservacion;
    private String asiento;
    private String destino;

    public Reservacion() {
    }

    public Reservacion(int id_reservacion, int id_pasajero_reservacion, int id_vuelo_reservacion, String fecha_reservacion, String asiento, String destino) {
        this.id_reservacion = id_reservacion;
        this.id_pasajero_reservacion = id_pasajero_reservacion;
        this.id_vuelo_reservacion = id_vuelo_reservacion;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
        this.destino = destino;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public int getId_pasajero_reservacion() {
        return id_pasajero_reservacion;
    }

    public int getId_vuelo_reservacion() {
        return id_vuelo_reservacion;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public String getDestino() {
        return destino;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public void setId_pasajero_reservacion(int id_pasajero_reservacion) {
        this.id_pasajero_reservacion = id_pasajero_reservacion;
    }

    public void setId_vuelo_reservacion(int id_vuelo_reservacion) {
        this.id_vuelo_reservacion = id_vuelo_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Reservacion{" +
                "id_reservacion=" + id_reservacion +
                ", id_pasajero_reservacion=" + id_pasajero_reservacion +
                ", id_vuelo_reservacion=" + id_vuelo_reservacion +
                ", fecha_reservacion='" + fecha_reservacion + '\'' +
                ", asiento='" + asiento + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}