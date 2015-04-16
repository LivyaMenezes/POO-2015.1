/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviao;

import empresas.Empresa;
import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class RepositorioAviaoJPA implements RepositorioAviao, Serializable{
    
    @PersistenceContext
    private EntityManager em;
    
    public RepositorioAviaoJPA() {
    }

    @Override
    public void cadastrar(Aviao aviao) throws ErroInternoException, AviaoExistenteException {
        try {
            this.em.persist(aviao);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void excluir(long id_aviao) throws ErroInternoException, AviaoInexistenteException {
        Aviao aviao = buscarAviao(id_aviao);
        try {
            this.em.remove(aviao);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Aviao buscarAviao(long id_aviao) throws ErroInternoException, AviaoInexistenteException {
               try {
            Aviao aviao = this.em.find(Aviao.class, id_aviao);
            if (aviao == null) {
                throw new AviaoInexistenteException();
            }
            return aviao;
        } catch (AviaoInexistenteException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void editar(Aviao aviao) throws ErroInternoException, AviaoInexistenteException {
        buscarAviao(aviao.getId_aviao());
        try {
            this.em.merge(aviao);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    
    @Override
        public List<Aviao> listaAviao(Empresa empresa) throws ErroInternoException {
            TypedQuery<Aviao> listarAviao = this.em.createQuery("SELECT a FROM Aviao a WHERE a.empresa = :empresa ", Aviao.class);
            listarAviao.setParameter("empresa", empresa);
            return listarAviao.getResultList();
        }

}

    

