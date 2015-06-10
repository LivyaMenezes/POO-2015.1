/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas;

/**
 *lança uma execeção caso a empresa não esteja cadastrada
 * @author Raquel Calado
 */
public class EmpresaInexistenteException extends Exception{
    
    public EmpresaInexistenteException(){
        super("Essa empresa não está cadastrada no sistema.");
    }
    
}
