/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas;

import index.ErroInternoException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import viagens.Viagem;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class CadastroEmpresa {
    
    @EJB
    private RepositorioEmpresa empresa;
    
    public CadastroEmpresa(){
        
    }
    public void adicionar(Empresa ep) throws ErroInternoException, EmpresaExistenteException{
        try{
            Empresa ep2 =  this.empresa.buscarEmpresa(ep.getId_empresa());
            throw new EmpresaExistenteException();
     
        }
        catch(EmpresaInexistenteException e){
            this.empresa.adicionar(ep);
            
        }
        
    }
    public void remove(long Id_empresa) throws EmpresaInexistenteException, ErroInternoException{
        this.empresa.remover(Id_empresa);
    }

    public Empresa buscarEmpresa(long Id_empresa) throws EmpresaInexistenteException, ErroInternoException{
       return this.empresa.buscarEmpresa(Id_empresa);
    } 
    public void atualizar(Empresa ep) throws EmpresaInexistenteException, ErroInternoException{
        this.empresa.atualizar(ep);
    }
    public List<Empresa> listaEmpresa () throws ErroInternoException{
        return this.empresa.listaEmpresa();
    }
    public Empresa loginEmpresa (String cnpj, String senha) throws ErroInternoException, EmpresaInexistenteException{
     return this.empresa.loginEmpresa(cnpj, senha);
}
    public List<Viagem> listarViagens (Empresa empresa) throws ErroInternoException{
        return this.empresa.listarViagens(empresa);
    }
}