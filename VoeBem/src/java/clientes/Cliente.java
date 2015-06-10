/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */
package clientes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

import vendas.Venda;

/**
 *
 *
 *
 * @author Raquel Calado
 *
 */
@Table(name = "cliente")

@Entity

public class Cliente implements Serializable {

//Atributos
    private long id_cliente;

    private String senha, nome, cpf, telefone;

    private Venda venda;

//Construtor Vazio
    public Cliente() {

    }

//Construtor
    public Cliente(String nome, String cpf, String telefone, String senha) {

        this.senha = senha;

        this.nome = nome;

        this.telefone = telefone;

        this.cpf = cpf;

    }

//Gets e Sets
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    public long getId_cliente() {

        return id_cliente;

    }

    public void setId_cliente(long id_cliente) {

        this.id_cliente = id_cliente;

    }

    @Column(nullable = false)

    public String getSenha() {

        return senha;

    }

    public void setSenha(String senha) {

        this.senha = senha;

    }

    @Column(name = "nome", nullable = false)

    public String getNome() {

        return nome;

    }

    public void setNome(String nome) {

        this.nome = nome;

    }

    @Column(unique = true, nullable = false)

    public String getCpf() {

        return cpf;

    }

    public void setCpf(String cpf) {

        this.cpf = cpf;

    }

    @Column(nullable = false)

    public String getTelefone() {

        return telefone;

    }

    public void setTelefone(String telefone) {

        this.telefone = telefone;

    }

    @ManyToOne

    public Venda getVenda() {

        return venda;

    }

    public void setVenda(Venda venda) {

        this.venda = venda;

    }

    @Override

    public String toString() {

        return (this.nome);

    }

    boolean contains(List<Venda> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
