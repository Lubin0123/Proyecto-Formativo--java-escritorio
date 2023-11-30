/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Lubin Reyes
 */
public class Clientes {

    private int idCliente;
    private String nombreCliente;
    private String documentoCliente;
    private String direccionCliente;
    private String CelularCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCelularCliente() {
        return CelularCliente;
    }

    public void setCelularCliente(String CelularCliente) {
        this.CelularCliente = CelularCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombreCliente=" + nombreCliente + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idCliente;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clientes other = (Clientes) obj;
        return this.idCliente == other.idCliente;
    }

    public Iterator<Clientes> listar() {
        ArrayList<Clientes> losClientes = new ArrayList<>();
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM  CLIENTES");
            ResultSet rs = sql.executeQuery();
            Clientes unCliente;

            while (rs.next()) {
                unCliente = new Clientes();
                unCliente.setIdCliente(rs.getInt("idClientes"));
                unCliente.setNombreCliente(rs.getString("nombreClientes"));
                unCliente.setDocumentoCliente(rs.getString("documentoClientes"));
                unCliente.setDireccionCliente(rs.getString("direccionClientes"));
                unCliente.setCelularCliente(rs.getString("celularClientes"));
                losClientes.add(unCliente);
            }

        } catch (SQLException eX) {
            System.err.println("eror al listar estudiantes " + eX);

        }
        if (losClientes.isEmpty()) {
            Clientes miCliente = new Clientes();
            miCliente.setNombreCliente("No hay clientes");
            losClientes.add(miCliente);
        }
        return losClientes.iterator();
    }

    public void insertar() {
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("INSERT INTO "
                    + this.getClass().getSimpleName() + " VALUES (NULL,?,?,?,?)");
            sql.setString(1, this.getNombreCliente());
            sql.setString(2, this.getDocumentoCliente());
            sql.setString(3, this.getDireccionCliente());
            sql.setString(4, this.getCelularCliente());
            sql.executeUpdate();
            System.out.println(this.getClass().getSimpleName() + " insertado correctamente");
        } catch (SQLException ex) {
            System.err.println("Error al insertar " + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }

    }

    public void eliminar() {
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("DELETE FROM "
                    + this.getClass().getSimpleName() + " WHERE idClientes = ?");
            sql.setInt(1, this.getIdCliente());
            sql.executeUpdate();
            System.out.println(this.getClass().getSimpleName() + "eliminado Correctamente");
        } catch (SQLException ex) {
            System.err.println("Error al eliminar" + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }
    }

    public Iterator<Clientes> buscar(String busqueda) {
        ArrayList<Clientes> losClientes = new ArrayList<>();
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT * FROM " + this.getClass().getSimpleName()
                    + " WHERE nombreClientes like ? OR documentoClientes Like ? OR direccionClientes like ? or celularClientes LIKE ?");
            sql.setString(1, "%" + busqueda + "%");
            sql.setString(2, "%" + busqueda + "%");
            sql.setString(3, "%" + busqueda + "%");
            sql.setString(4, "%" + busqueda + "%");
            ResultSet rs = sql.executeQuery();
            Clientes unCliente;

            while (rs.next()) {
                unCliente = new Clientes();
                unCliente.setIdCliente((Integer)rs.getInt("idClientes"));
                unCliente.setNombreCliente(rs.getString("nombreClientes"));
                unCliente.setDocumentoCliente(rs.getString("documentoClientes"));
                unCliente.setDireccionCliente(rs.getString("direccionClientes"));
                unCliente.setCelularCliente(rs.getString("celularClientes"));
                losClientes.add(unCliente);
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar" + this.getClass().getSimpleName() + ":" + ex.getMessage());
        }
        return losClientes.iterator();
    }

    public Clientes buscarPorId(int elid) {
        Clientes unCliente = new Clientes();
        unCliente.setNombreCliente("cliente no existe");
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("SELECT *FROM "
                    + this.getClass().getSimpleName() + " WHERE idCleinte = ?");
            sql.setInt(1, elid);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                unCliente.setIdCliente(rs.getInt("idCliente"));
                unCliente.setNombreCliente(rs.getString("nombreCliente"));
                unCliente.setDocumentoCliente(rs.getString("documentoCliente"));
                unCliente.setDireccionCliente(rs.getString("direccionCliente"));
                unCliente.setCelularCliente(rs.getString("celularCliente"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al buscar Id" + ex.getMessage());
        }
        return unCliente;
    }

    public void modificar() {
        try {
            PreparedStatement sql = ConexionBD.conexion.prepareStatement("UPDATE "+this.getClass().getSimpleName()+
                    " SET nombreClientes = ?, documentoClientes = ?, direccionClientes = ?, celularClientes = ? WHERE idClientes = ?" );
            sql.setString(1,this.getNombreCliente());
            sql.setString(2,this.getDocumentoCliente());
            sql.setString(3,this.getDireccionCliente());
            sql.setString(4,this.getCelularCliente());
            sql.setInt(5, this.getIdCliente());
            sql.executeUpdate();
            System.out.println(this.getClass().getSimpleName()+ " modificado correctamente");
        } catch (SQLException ex) {
            System.err.println("  Error al modificar "+this.getClass().getSimpleName()+": "+ex.getMessage());
        }


    }
}

