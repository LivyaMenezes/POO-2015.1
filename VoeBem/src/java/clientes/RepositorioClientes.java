/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raquel Calado
 */
@Local
public interface RepositorioClientes extends Serializable {
    
    public void adicionar (Cliente cliente) throws ErroInternoException, ClienteExistenteException;
    public void removerCliente (long id_cliente) throws ErroInternoException, ClienteInexistenteException;
    public Cliente buscarCliente (long id_cliente) throws ErroInternoException, ClienteInexistenteException;
    public void atualizar (Cliente cliente) throws ErroInternoException, ClienteInexistenteException;
    public Cliente loginCliente(String cpf, String senha) throws ErroInternoException, ClienteInexistenteException;
    public List<Cliente> listaCliente (Cliente cliente) throws ErroInternoException;
    
}
