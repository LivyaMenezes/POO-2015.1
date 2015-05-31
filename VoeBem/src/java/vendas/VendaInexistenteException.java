/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

/**
 * Aqui lança um Exception caso a venda não exista
 * @author Raquel Calado
 */
public class VendaInexistenteException extends Exception {
    
    public VendaInexistenteException(){
        super("A venda não existe!");
    }
    
}
