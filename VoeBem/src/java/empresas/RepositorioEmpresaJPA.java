/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas;

import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import viagens.Viagem;

/**
 * classe de persitencia de dados no banco 
 * @author Raquel Calado
 */
@Stateless
public class RepositorioEmpresaJPA implements RepositorioEmpresa, Serializable {

    @PersistenceContext
    private EntityManager em;

    public RepositorioEmpresaJPA() {
        
    }
/**
 * metodo de adicionar empresas 
 * 
 * 
 */
    @Override
    public void adicionar(Empresa ep) throws ErroInternoException {
        try {
            this.em.persist(ep);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
/**
 * Metodo de remoção de empresas
 * 
 * 
 */
    @Override
    public void remover(long id_empresa) throws ErroInternoException, EmpresaInexistenteException {
        Empresa ep = buscarEmpresa(id_empresa);
        try {
            this.em.remove(ep);
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }
/**
 * metodo de atualização de empresas
 */
    @Override
    public void atualizar(Empresa ep) throws ErroInternoException, EmpresaInexistenteException {

        buscarEmpresa(ep.getId_empresa());
        try {
            this.em.merge(ep);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Empresa buscarEmpresa(long id_empresa) throws ErroInternoException, EmpresaInexistenteException {
        try {
            Empresa ep = this.em.find(Empresa.class, id_empresa);
            if (ep == null) {
                throw new EmpresaInexistenteException();
            }
            return ep;
        } catch (EmpresaInexistenteException en) {
            throw en;
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }

    @Override
    public List<Empresa> listaEmpresa() throws ErroInternoException {

        try {
            TypedQuery<Empresa> listaEmpresa = this.em.createQuery("SELECT e FROM Empresa e", Empresa.class);
            if (listaEmpresa.getResultList().isEmpty()){
                throw new EmpresaInexistenteException();
            }
            return listaEmpresa.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    public Empresa loginEmpresa (String cnpj, String senha) throws ErroInternoException, EmpresaInexistenteException{
        try{
            TypedQuery<Empresa> query = this.em.createQuery("SELECT emp FROM Empresa emp WHERE emp.cnpj = :cnpj AND emp.senha = :senha", Empresa.class);
            query.setParameter("cnpj", cnpj);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        }
       
        catch(NoResultException nre){
            throw new EmpresaInexistenteException();
        }
        catch(Exception e){
            throw new ErroInternoException(e);
       }
    }
/**
 * 
 * metodo de listagem de viagens
 * 
 * 
 */
    @Override
    public List<Viagem> listarViagens(Empresa empresa) throws ErroInternoException {
        TypedQuery<Viagem> listarViagens = this.em.createQuery("SELECT v FROM Viagem v WHERE v.empresa = :empresa", Viagem.class);
        listarViagens.setParameter("empresa", empresa);
        return listarViagens.getResultList();
    }
    
    
}

