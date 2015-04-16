/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poltronas;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Raquel Calado
 */
@Entity
public class Poltrona implements Serializable {
    
    private long id_poltrona;
    private long numero_poltrona;

    public Poltrona() {

    }

    public Poltrona(long numero_poltrona) {
        this.numero_poltrona = numero_poltrona;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId_poltrona() {
        return id_poltrona;
    }

    public void setId_poltrona(long id_poltrona) {
        this.id_poltrona = id_poltrona;
    }

    @Column(unique = true)
    public long getNumero_poltrona() {
        return numero_poltrona;
    }

    public void setNumero_poltrona(long numero_poltrona) {
        this.numero_poltrona = numero_poltrona;
    }

}
