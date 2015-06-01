/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador;

import index.ErroInternoException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class CadastroAdministrador {
    
    @EJB
    private RepositorioAdministrador repAdministrador;
    
    public CadastroAdministrador(){
    }
    
    public void adicionar(Administrador administrador) throws ErroInternoException, AdministradorExistenteException, AdministradorInexistenteException{    
        try{
            Administrador c2 = repAdministrador.buscarAdministrador(administrador.getId_administrador());
            if(c2 != null){
                throw new AdministradorExistenteException();
            }
        }
        catch(AdministradorInexistenteException cie){
            this.repAdministrador.adicionar(administrador);
        }
    }
    
    public Administrador buscarAdministrador(long id_administrador) throws AdministradorInexistenteException, ErroInternoException{
        try{
        return this.repAdministrador.buscarAdministrador(id_administrador);
        }
        catch(ErroInternoException eie){
            throw new ErroInternoException(eie);
        }
    }
    
    public void atualizar(Administrador administrador) throws AdministradorInexistenteException, ErroInternoException{
        this.repAdministrador.atualizar(administrador);
    }
    
    public void remover (long id_administrador) throws ErroInternoException, AdministradorInexistenteException{
        this.repAdministrador.remover(id_administrador);
    }
    
    public Administrador loginAdministrador(String cpf, String senha) throws AdministradorInexistenteException, ErroInternoException{
        return this.repAdministrador.loginAdministrador(cpf, senha);
    }
    
    public List<Administrador> listaAdministrador(Administrador administrador) throws ErroInternoException{     
            return this.repAdministrador.listaAdministrador(administrador);    
    }
    
    
}
