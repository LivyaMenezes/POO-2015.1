/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

/**
 *
 * @author Raquel Calado
 */
public class ErroInternoException extends Exception{
    
    private Exception origem;
    
    public ErroInternoException(Exception origem){
        super(origem.getMessage());
        this.origem = origem;
    }
    
    public String getMessage(){
        return origem.getMessage();
    }
}
