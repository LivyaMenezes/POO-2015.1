/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import clientes.Cliente;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import viagens.Viagem;

/**
 *
 * @author Raquel Calado
 */
@Entity
public class Venda implements Serializable{
    
    private Cliente id_cliente;   //atributos
    private long id_poltrona;
    private Viagem id_viagem;
    private double preco;
    private long codigoVenda;
    private Date data;
    
    
    public Venda(){ //construtor vazio
        
    }
    public Venda(Cliente id_cliente, int id_poltrona, Viagem id_viagem, double preco, Date data){
        this.id_cliente = id_cliente;
        this.id_poltrona = id_poltrona;
        this.id_viagem = id_viagem;
        this.preco = preco; 
        this.data = data;
    }
//metodos get's e set's
    @Temporal(TemporalType.DATE) //Temporal para data
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @ManyToOne                        //relacao entre a venda e cliente
    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public long getId_poltrona() {
        return id_poltrona;
    }

    public void setId_poltrona(long id_poltrona) {
        this.id_poltrona = id_poltrona;
    }

    @ManyToOne                       //relacao entre venda/viagem
    public Viagem getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(Viagem id_viagem) {
        this.id_viagem = id_viagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    @Id      //@ de identificacao
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-incremento
    public long getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(long codigoVenda) {
        this.codigoVenda = codigoVenda;
    }
    
}
