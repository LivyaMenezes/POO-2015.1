/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import index.ErroInternoException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class CadastroClientes {
    
    @EJB
    private RepositorioClientes repClientes;
    
    public CadastroClientes(){
    }
    
    public void adicionar(Cliente cliente) throws ErroInternoException, ClienteExistenteException, ClienteInexistenteException{    
        try{
            Cliente c2 = repClientes.buscarCliente(cliente.getId_cliente());
            if(c2 != null){
                throw new ClienteExistenteException();
            }
        }
        catch(ClienteInexistenteException cie){
            this.repClientes.adicionar(cliente);
        }
    }
    
    public Cliente buscarCliente(long id_cliente) throws ClienteInexistenteException, ErroInternoException{
        try{
        return this.repClientes.buscarCliente(id_cliente);
        }
        catch(ErroInternoException eie){
            throw new ErroInternoException(eie);
        }
    }
    
    public void atualizar(Cliente cliente) throws ClienteInexistenteException, ErroInternoException{
        this.repClientes.atualizar(cliente);
    }
    
    public void remover (long id_cliente) throws ErroInternoException, ClienteInexistenteException{
        this.repClientes.remover(id_cliente);
    }
    
    public Cliente loginCliente(String cpf, String senha) throws ClienteInexistenteException, ErroInternoException{
        return this.repClientes.loginCliente(cpf, senha);
    }
    
    public List<Cliente> listaClientes(Cliente cliente) throws ErroInternoException{     
            return this.repClientes.listaCliente(cliente);    
    }
    
    
}
