/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poltronas;

/**
 *
 * @author Raquel Calado
 */
public class PoltronaIndisponivelException extends Exception {
    public PoltronaIndisponivelException(){
        super("Poltrona indisponível");
    }
}
