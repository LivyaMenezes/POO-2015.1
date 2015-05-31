/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import clientes.Cliente;
import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * aqui vc chama os metodos feitos no RepositorioVendaJPA, aqui é uma interface que se comunica a com o negocio
 * @author Raquel Calado
 */
@Local  
public interface RepositorioVenda extends Serializable{
    //aqui vc chama os metodos feitos no RepositorioVendaJPA, aqui é uma interface que se comunica a nossa fachada
    public void adicionar(Venda venda) throws ErroInternoException;
    
    public Venda buscarVenda(long codigoVenda) throws VendaInexistenteException, ErroInternoException;
    
    public List<Venda> vendasPorViagem(long id_viagem) throws ErroInternoException;
    
    public List<Venda> listarVendasCliente(Cliente cliente) throws ErroInternoException;
    
    public List<Venda> listarVendas() throws ErroInternoException;
    
    public void remover(long codigoVenda) throws ErroInternoException, VendaInexistenteException;

    

   
}
