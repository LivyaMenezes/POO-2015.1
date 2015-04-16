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
public class PoltronaInexistenteException extends Exception {
    public PoltronaInexistenteException(){
        super ("A poltrona escolhida não existe!");
    }
}
