/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviao;

/**
 *
 * @author Raquel Calado
 */
public class AviaoExistenteException extends Exception {
    
    public AviaoExistenteException(){
        super("Esse avião já está cadastrado no sistema.");
    }
    
}
