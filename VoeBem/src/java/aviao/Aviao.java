/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviao;

import empresas.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import viagens.Viagem;

/**
 *
 * @author Raquel Calado
 */
@Entity
public class Aviao implements Serializable{
    
    private String modelo;
    private long id_aviao;
    private int qnt_assentos;
    private List<Viagem> listaViagem;
    private Empresa empresa;
  
    
    public Aviao(){
    }
    
    public Aviao(String modelo, int qnt_assentos, Empresa empresa){
        this.modelo = modelo;
        this.qnt_assentos = qnt_assentos;
        this.empresa = empresa;
    }

    @Column (unique = true)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId_aviao() {
        return id_aviao;
    }

    public void setId_aviao(long id_aviao) {
        this.id_aviao = id_aviao;
    }

    public int getQnt_assentos() {
        return qnt_assentos;
    }

    public void setQnt_assentos(int qnt_assentos) {
        this.qnt_assentos = qnt_assentos;
    }

    @OneToMany(mappedBy = "aviao")
    public List<Viagem> getListaViagem() {
        return listaViagem;
    }

    public void setListaViagem(List<Viagem> listaViagem) {
        this.listaViagem = listaViagem;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    @Override
    public String toString(){
        return this.modelo;
    }
      
}
