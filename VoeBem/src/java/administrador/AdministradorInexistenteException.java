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
public class AdministradorInexistenteException extends Exception {
    public AdministradorInexistenteException(){
        super("Administrador não cadastrado!");
    }
}
