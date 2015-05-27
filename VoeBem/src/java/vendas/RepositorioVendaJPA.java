/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import clientes.Cliente;
import index.ErroInternoException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class RepositorioVendaJPA implements RepositorioVenda {

    @PersistenceContext
    private EntityManager em;

    public RepositorioVendaJPA() {
    }

    @Override
    public void adicionar(Venda ven) throws ErroInternoException {
        try {
            this.em.persist(ven); //aqui adiciona no bd
        } catch (Exception e) { //se houver algum problema  entra aqui
            throw new ErroInternoException(e); //e lanca esse erro
        } //obs:ta sem regra esse adicionar, pelo menos foi o que achei vendo o codigo

    }

    public List<Venda> listarVendasCliente(Cliente cliente) throws ErroInternoException {
        try {
            //Aqui vai fazer uma na tabela criada no bd Venda, de acordo com id do cliente
            TypedQuery<Venda> query = this.em.createQuery("SELECT v FROM Venda v WHERE v.id_cliente = :cliente", Venda.class);
            //aqui passa como parametro cliente
            query.setParameter("cliente", cliente);
            //e retorno uma lista
            return query.getResultList();
        } catch (Exception e) {//se entrar aqui
            throw new ErroInternoException(e);//lanca a excecao 
        }
    }

    @Override
    public Venda buscarVenda(long codigoVenda) throws ErroInternoException, VendaInexistenteException {
        try {
            //aqui ta pegando o "objeto" Venda  apelida ve atribui para encontrar um codigo na classe Venda
            Venda v = this.em.find(Venda.class, codigoVenda);
            
            if (v == null) { //se essa busca for vazia ou inexistente 
                throw new VendaInexistenteException(); //lanca essa excessao
            }
            return v; //retorna para venda
        } catch (VendaInexistenteException ex) { //pode entra nesse catch 
            throw ex; // e lanca isso(nao sei o que)
        } catch (Exception e) {
            throw new ErroInternoException(e);//lanca essa exception, vai ser o que tem la no ErroInternoException
        }
    }

    @Override
    public List<Venda> vendasPorViagem(long id_viagem) throws ErroInternoException {
        try {
            //faz uma selecao no bd da tabela Venda onde id-da viagem for igual ao buscado, e lista
            TypedQuery<Venda> query = this.em.createQuery("SELECT v FROM Venda v WHERE v.id_viagem = :id_viagem ", Venda.class);
            query.setParameter("id_viagem", id_viagem);
            return query.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    
    @Override
    public List<Venda> listarVendas() throws ErroInternoException{
        try {
           // lista
            TypedQuery<Venda> query = this.em.createQuery("SELECT v FROM Venda v WHERE v.codigo = :codigo ", Venda.class);
            //query.setParameter("codigo", venda);
            return query.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    
    @Override
    public void remover(long codigoVenda) throws ErroInternoException, VendaInexistenteException {
    Venda venda = this.buscarVenda(codigoVenda);
            try{
                this.em.remove(venda);
               
            }catch(Exception e){
                throw new ErroInternoException(e); 
            }    
    }
}

