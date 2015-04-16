/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viagens;

/**
 *
 * @author Raquel Calado
 */
public class ViagemInexistenteException extends Exception{
    public ViagemInexistenteException(){
        super("Viagem Inexistente!");
    }   
}
