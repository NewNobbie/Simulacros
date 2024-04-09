package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection(){

        try {
            //Call Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Clases for Connection
            String url = "jdbc:mysql://bez8b5mmr8sw5dt6smzx-mysql.services.clever-cloud.com:3306/bez8b5mmr8sw5dt6smzx";
            String user = "uvnllpdxwfb2ycc4";
            String password = "3TKK9cUneqkwKBDkQK8j";

            //Establish the Connection
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Connection on!");

        }catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver no installed...");
        }catch (SQLException error){
            System.out.println("ERROR >> error to connectiong with the DB"+ error.getMessage());
        }

        return objConnection;
    }

    //Method to end Connection
    public static void closeConnection(){
        try {
            //Close Connection
            if (objConnection != null){
                objConnection.close();
                System.out.println("Connection completed successfully");
            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
