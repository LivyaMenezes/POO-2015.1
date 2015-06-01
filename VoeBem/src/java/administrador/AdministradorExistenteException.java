/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador;


/**
 *
 * @author Raquel Calado
 */
public class AdministradorExistenteException extends Exception {
    public AdministradorExistenteException(){
        super("O Administrador já está cadastrado!");
    }
}
