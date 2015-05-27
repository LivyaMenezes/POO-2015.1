/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viagens;

import aviao.Aviao;
import empresas.Empresa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import vendas.Venda;

/**
 *
 * @author Raquel Calado
 */
@Entity
public class Viagem implements Serializable {
    
    private Destinos origem;
    private Destinos destino;
    private List<Destinos> listaDestinos;
    private Date hora;
    private Date data;
    private Aviao aviao;
    private Empresa empresa;
    private long id_viagem;
    private double valor;

    public Viagem() {

    }

    public Viagem(Destinos origem, Destinos destino, Date hora, Date data, Aviao aviao, Empresa empresa, double valor) {
        this.origem = origem;
        this.destino = destino;
        this.hora = hora;
        this.data = data;
        this.aviao = aviao;
        this.empresa = empresa;
        this.valor = valor;
    }

 
    @Enumerated(EnumType.STRING) 
    public Destinos getOrigem() {
        return origem;
    }

    public void setOrigem(Destinos origem) {
        this.origem = origem;
    }

    @Enumerated(EnumType.STRING) 
    public Destinos getDestino() {
        return destino;
    }

    public void setDestino(Destinos destino) {
        this.destino = destino;
    }

    @Temporal(TemporalType.TIME)
    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    @Temporal(TemporalType.DATE)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @ManyToOne
    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(long id_viagem) {
        this.id_viagem = id_viagem;
    }

    @ManyToOne
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Viagem other = (Viagem) obj;
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        if (!Objects.equals(this.listaDestinos, other.listaDestinos)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.aviao, other.aviao)) {
            return false;
        }
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Viagem{" + "origem=" + origem + ", destino=" + destino 
                + ", listaDestinos=" + listaDestinos + ", hora=" + hora 
                + ", data=" + data + ", aviao=" + aviao + ", empresa=" + 
                empresa + ", id_viagem=" + id_viagem + ", valor=" + valor + '}';
    }
    
   

    @Transient
    public List<Destinos> getListaDestinos() {
        return Arrays.asList(Destinos.values());
    }

    public void setListaDestinos(List<Destinos> listaDestinos) {
        this.listaDestinos = listaDestinos;
    }

   
    
}
