/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viagens;

import index.ErroInternoException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *Essa classe se comunica com o Banco de dados mysql
 * @author Raquel Calado
 */
@Stateless
public class RepositorioViagemJPA implements RepositorioViagem, Serializable {

    @PersistenceContext
    private EntityManager em;

    public RepositorioViagemJPA() {
    }

    @Override
    public void adicionar(Viagem v) throws ErroInternoException, ViagemExistenteException {
        try {
            this.em.persist(v);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

    }

    @Override
    public void removerViagem(long id_viagem) throws ErroInternoException, ViagemInexistenteException {

        Viagem v = buscarViagem(id_viagem);//buscar esta fora do try  para não prender a exceçao
        try {

            this.em.remove(v);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

    }

    @Override
    public void atualizar(Viagem v) throws ErroInternoException, ViagemInexistenteException {

        buscarViagem(v.getId_viagem());// não precisa do lançar o erro inexistente pois o buscar esta fora do try e ele ja faz isso
        try {
            this.em.merge(v);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Viagem buscarViagem(long id_viagem) throws ErroInternoException, ViagemInexistenteException {
        try {
            Viagem v = this.em.find(Viagem.class, id_viagem);
            if (v == null) {
                throw new ViagemInexistenteException();
            }
            return v;
        } catch (ViagemInexistenteException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ErroInternoException(e);

        }
    }

    @Override
    public List<Viagem> consultaViagens(Destinos origem, Destinos destino, Date data) throws ErroInternoException, ViagemInexistenteException {
        try {
            TypedQuery<Viagem> query = this.em.createQuery("SELECT v FROM Viagem v WHERE v.origem = :origem AND v.destino = :destino AND v.data = :data", Viagem.class);
            query.setParameter("origem", origem);
            query.setParameter("destino", destino);
            query.setParameter("data", data);
            if (query.getResultList().isEmpty()) {
                throw new ViagemInexistenteException();
            }
            return query.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    @Override
    public List<Viagem> listaViagens() throws ErroInternoException {

        try {
            TypedQuery<Viagem> listaViagens = this.em.createQuery("SELECT v FROM Viagem v", Viagem.class);
            if (listaViagens.getResultList().isEmpty()){
                throw new ViagemInexistenteException();
            }
            return listaViagens.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
}

