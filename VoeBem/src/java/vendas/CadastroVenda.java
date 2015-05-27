/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import clientes.Cliente;
import index.ErroInternoException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import viagens.Viagem;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class CadastroVenda {
    
    @EJB
    private RepositorioVenda venda;
    private List<Viagem> viagem;
    
    public CadastroVenda(){
    }
    //aqui é onde fica todas as regras de negocio
    
    public void adicionar(Venda venda) throws ErroInternoException{
        this.venda.adicionar(venda); 
    }
    
    public Venda buscarVenda(long id) throws ErroInternoException, VendaInexistenteException{
        return this.venda.buscarVenda(id);
    }
    
    public List<Venda> vendasPorViagem(long id_viagem) throws ErroInternoException, VendaInexistenteException{
            List<Venda> venda = new ArrayList<Venda>();
            for (int i = 0; i < this.viagem.size(); i++){
                if (this.viagem.get(i).getId_viagem() == id_viagem){
                    venda.add(buscarVenda(id_viagem));
                }
            }
            return venda;
    }
    
    public List<Venda> listarVendasCliente(Cliente cliente) throws ErroInternoException{
        return this.venda.listarVendasCliente(cliente);
    }
    
    public void remover(long codigoVenda) throws ErroInternoException, VendaInexistenteException{
        
        /*Venda v = this.venda.buscarVenda(codigoVenda);
        List<Venda> todas = this.venda.listarVendas();
        
        for(Venda ve : todas){
            if(ve.getId_cliente().contains(v) && ve.getId_viagem().contains(v)){
                ve.getId_cliente().remove(v);
                ve.getId_viagem().remove(v);
            }
        }**/
        
        this.venda.remover(codigoVenda);
    }
    
}
