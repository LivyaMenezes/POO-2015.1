/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas;

/**
 *
 * @author Raquel Calado
 */
public class EmpresaExistenteException extends Exception{
    
    public EmpresaExistenteException(){
        super("Essa empresa já está cadastrada no sistema.");
    }
}
