/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viagens;

/**
 * Aqui apenas lança uma execeção caso o usuario for pesquisar uma venda e a venda ja exista 
 * @author Raquel Calado
 */


public class ViagemExistenteException extends Exception{
    ViagemExistenteException(){
        super("A viagem já existe");
    }       
}
