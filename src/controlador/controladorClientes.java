/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import modelo.Clientes;

/**
 *
 * @author Lubin Reyes
 */
public class controladorClientes {

    public void controlarAccion(ActionEvent evento, Clientes unCliente) {
        String accion = evento.getActionCommand();
        switch(accion){
            case "Insertar" -> unCliente.insertar();
            case "Modificar" -> unCliente.modificar();
            case "Eliminar" -> unCliente.eliminar();
        }
    }
}