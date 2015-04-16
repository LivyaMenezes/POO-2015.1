/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

/**
 *
 * @author Raquel Calado
 */
public class ClienteExistenteException extends Exception {
    public ClienteExistenteException(){
        super("O cliente já está cadastrado!");
    }
}
