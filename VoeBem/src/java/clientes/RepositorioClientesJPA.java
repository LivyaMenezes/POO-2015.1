/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class RepositorioClientesJPA implements RepositorioClientes, Serializable {
    
    @PersistenceContext
    private EntityManager em;

    public RepositorioClientesJPA() {
        
    }

    @Override
    public void adicionar(Cliente cliente) throws ErroInternoException, ClienteExistenteException {
        try {
            this.em.persist(cliente);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void remover(long id_cliente) throws ErroInternoException, ClienteInexistenteException {
        Cliente cliente = this.buscarCliente(id_cliente);
        try {
            this.em.remove(cliente);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Cliente buscarCliente(long id_cliente) throws ErroInternoException, ClienteInexistenteException {
        try {
            Cliente c = this.em.find(Cliente.class, id_cliente);
            if (c == null) {
                throw new ClienteInexistenteException();
            }
            return c;
        } catch (ClienteInexistenteException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizar(Cliente cliente) throws ErroInternoException, ClienteInexistenteException {
        this.buscarCliente(cliente.getId_cliente());
        try {
            this.em.merge(cliente);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Cliente loginCliente(String cpf, String senha) throws ClienteInexistenteException, ErroInternoException {
        try {
            TypedQuery<Cliente> query = this.em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf AND c.senha = :senha", Cliente.class);
            query.setParameter("cpf", cpf);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new ClienteInexistenteException();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Cliente> listaCliente(Cliente cliente) throws ErroInternoException {
        TypedQuery <Cliente> listaClientes = this.em.createQuery("SELECT c FROM Cliente c", Cliente.class);    
        return listaClientes.getResultList();
    }
}
