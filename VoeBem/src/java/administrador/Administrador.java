/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import vendas.Venda;

/**
 *
 * @author Raquel Calado
 */
@Table(name = "administrador")
@Entity
public class Administrador implements Serializable {
    
    //Atributos
    private long id_administrador;
    private String senha, nome, cpf, telefone;
    
    //Construtor Vazio
    public Administrador(){
        
    }
    
    //Construtor
    public Administrador(String nome, String cpf, String telefone, String senha){ 
        
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
    
    //Gets e Sets
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId_administrador() {
        return id_administrador;
    }
    
    public void setId_administrador(long id_administrador) {
        this.id_administrador = id_administrador;
    }
    
    @Column (nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Column (name = "nome", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column (unique = true, nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    @Column (nullable = false)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String toString(){
        return (this.nome);
    }

 
    
}