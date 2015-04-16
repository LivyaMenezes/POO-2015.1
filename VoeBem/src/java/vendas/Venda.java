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
    
    private Cliente id_cliente;
    private long id_poltrona;
    private Viagem id_viagem;
    private double preco;
    private long codigo;
    private Date data;
    
    
    public Venda(){
        
    }
    public Venda(Cliente id_cliente, int id_poltrona, Viagem id_viagem, double preco, Date data){
        this.id_cliente = id_cliente;
        this.id_poltrona = id_poltrona;
        this.id_viagem = id_viagem;
        this.preco = preco; 
        this.data = data;
    }

    @Temporal(TemporalType.DATE)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @ManyToOne
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

    @ManyToOne
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
}

