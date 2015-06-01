/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador;

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
public class RepositorioAdministradorJPA implements RepositorioAdministrador, Serializable {
    
    @PersistenceContext
    private EntityManager em;

    public RepositorioAdministradorJPA() {
        
    }

    @Override
    public void adicionar(Administrador administrador) throws ErroInternoException, AdministradorExistenteException {
        try {
            this.em.persist(administrador);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void remover(long id_administrador) throws ErroInternoException, AdministradorInexistenteException {
        Administrador administrador = this.buscarAdministrador(id_administrador);
        try {
            this.em.remove(administrador);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Administrador buscarAdministrador(long id_administrador) throws ErroInternoException, AdministradorInexistenteException {
        try {
            Administrador c = this.em.find(Administrador.class, id_administrador);
            if (c == null) {
                throw new AdministradorInexistenteException();
            }
            return c;
        } catch (AdministradorInexistenteException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizar(Administrador administrador) throws ErroInternoException, AdministradorInexistenteException {
        this.buscarAdministrador(administrador.getId_administrador());
        try {
            this.em.merge(administrador);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Administrador loginAdministrador(String cpf, String senha) throws AdministradorInexistenteException, ErroInternoException {
        try {
            TypedQuery<Administrador> query = this.em.createQuery("SELECT c FROM Administrador c WHERE c.cpf = :cpf AND c.senha = :senha", Administrador.class);
            query.setParameter("cpf", cpf);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new AdministradorInexistenteException();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Administrador> listaAdministrador(Administrador administrador) throws ErroInternoException {
        TypedQuery <Administrador> listaAdministrador = this.em.createQuery("SELECT c FROM Administrador c", Administrador.class);    
        return listaAdministrador.getResultList();
    }
}
