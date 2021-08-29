
package co.com.uniantioquia.conexiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    public static void main(String[] args) {
        
        try {
            // java.sql
            // javax.sql
            
            // Cargar Driver
            // Forma 1
            // Class.forName("org.sqlite.JDBC");
            // Forma 2
            // DriverManager.registerDriver(new org.sqlite.JDBC());
            
            /*
            // properties
            url=htttps:........
            user=pablito
            pssword=1234
            
            DriverManager.getConnection(String url);
            DriverManager.getConnection(String url, java.util.properties  properties);
            DriverManager.getConnection(String url, String user, String password);
            */

            String urlDB = "jdbc:sqlite:data.db";
            System.out.println("Conectando...");
            Connection conexion = DriverManager.getConnection(urlDB);
            if(conexion != null){
                System.out.println("Conectado");
                
                String query ="INSERT INTO PERSON (name, last_name, nickname, birdthday) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                
                preparedStatement.setString(1, "Pedro");
                preparedStatement.setString(2, "Picapiedra");
                preparedStatement.setString(3, "@Pedro");
                preparedStatement.setInt(4, 31);
                
                // int rows= preparedStatement.executeUpdate();
                // System.out.println("Rows afectadas: " + rows);
                
                boolean execute = preparedStatement.execute();
                System.out.println("Se ejecuto: "+ !execute);
                
                int rows = preparedStatement.getUpdateCount();
                System.out.println("Rows afectadas: " + rows);
                
                /*
                // Informacion conexion
                DatabaseMetaData databaseMetaData =  conexion.getMetaData();
                System.out.println("Driver name: "+ databaseMetaData.getDriverName());
                System.out.println("Driver version: "+ databaseMetaData.getDriverVersion());
                System.out.println("Product name: "+ databaseMetaData.getDatabaseProductName());
                */
                //conexion.commit();
                
                query ="select * from person";
                preparedStatement = conexion.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    System.out.printf("\nId:[%d] \tName: [%s] \tLaste Name: [%s] \tNickname: [%s]",
                            resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4)
                            );
                }
                
                
                conexion.close();
                System.out.println("conexion cerrada");                
            }

        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
