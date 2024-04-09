package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //Atributo con Estado de la Conexión
    public static Connection objConnection = null;

    //Método to conectDB
    public static Connection openConnection(){

        try{
            //Call Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Clases para la conexión
            String url = "jdbc:mysql://b0r52mdgt9ugonyyu1rg-mysql.services.clever-cloud.com:3306/b0r52mdgt9ugonyyu1rg";
            String user = "uoud0etkusnadgja";
            String password = "Vv3L1mghrvZLj9kTxLUZ";

            //Establecemos conexión
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexión establecida Monstrico!");

        }catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver no instalado " + error.getMessage());
        }catch (SQLException error){
            System.out.println("ERROR >> error al conectar con la DB loco..." + error.getMessage());
        }

        return objConnection;
    }

    //Método to finalizar la conexión
    public static void closeConnection(){
        try{
            //Cerrar al detectar conexión activa
            if(objConnection != null){
                objConnection.close();
                System.out.println("Se finalizó la conexión con éxito loco!");
            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

