/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lubin Reyes
 */
public class ConexionBD {

    public static Connection conexion;

    private ConexionBD(){
        try {
            String driverBD = "com.mysql.cj.jdbc.Driver";
            String urlBD = "jdbc:mysql://localhost/sistemaClientes";
            String usuarioBD = "root";
            String claveBD = "";
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(urlBD, usuarioBD, claveBD);
        } catch (ClassNotFoundException ex) {
            System.out.println("No encontro el driver " + ex.getMessage());

        } catch (SQLException ex) {
            System.out.println("Error al encontrarme" + ex.getMessage());
        }
    }

    public static void desconectar() {

        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al desconectar:" + ex.getMessage());
        }
    }

    public static ConexionBD getInstance() {
        return conexionBDHolder.INSTANCE;
    }

    private static class conexionBDHolder {

        private static final ConexionBD INSTANCE = new ConexionBD();
    }
}
