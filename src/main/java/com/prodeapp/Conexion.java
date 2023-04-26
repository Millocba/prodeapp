package com.prodeapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
  public static List<String> conectar(String sql, String nombreBd, String ipBd, String port) {
    String url = "jdbc:mysql://"+ipBd+":"+port+"/"+nombreBd;
    String user = "root";
    String password = "user";
    String query = sql;//"SELECT * FROM view_pronostico";
    List<String> result = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {

        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();
        
        result.add("\nConsultando base de datos...........");
        while (rs.next()) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int i = 1; i <= numColumns; i++) {
                rowBuilder.append(rs.getString(i));
                if (i != numColumns) {
                    rowBuilder.append(";");
                }
            }
            result.add(rowBuilder.toString());
        }
        
        //******para control de los datos extraidos */
        /* for (String a : result){
          System.out.println(a);
        } */
          
      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("ERROR: problemas con la base de datos");
        System.exit(1);
      }
      System.out.println("");
      return result;
    

  }
  public static int actualizar(String consulta, String nombreBd, String ipBd, String port) {
    String url = "jdbc:mysql://"+ipBd+":"+port+"/"+nombreBd;
    String user = "root";
    String password = "user";
    int filasActualizadas = 0;

    try (Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement()) {

        filasActualizadas = stmt.executeUpdate(consulta);

      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("ERROR: problemas con la base de datos");
        System.exit(1);
      }

      return filasActualizadas;
  }

}
