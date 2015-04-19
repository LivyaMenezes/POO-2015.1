/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import aviao.Aviao;
import aviao.AviaoExistenteException;
import aviao.AviaoInexistenteException;
import empresas.Empresa;
import index.ErroInternoException;
import index.Fachada;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Raquel Calado
 */
@ManagedBean
@SessionScoped
public class ManagedBeanAviao implements Serializable{
    
     @EJB
    private Fachada fachada;
    private Aviao aviao;
    private List<Aviao> listaAviao;
    private Empresa empresa;

    public ManagedBeanAviao() {
        this.aviao = new Aviao();
    }


    public List<Aviao> getListaAviao(Empresa empresa) throws ErroInternoException, AviaoInexistenteException {
        try{
        return this.fachada.listaAviao(empresa);
        }
        catch(ErroInternoException eie){
            throw eie;
        }
    }

    public void setListaAviao(List<Aviao> listaAviao) {
        this.listaAviao = listaAviao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
        public Fachada getFachada() {
        return fachada;
    }

    public void setFachada(Fachada fachada) {
        this.fachada = fachada;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }
    

    public String cadastrarAviao() throws AviaoExistenteException, ErroInternoException {
        try {
            this.aviao.setEmpresa(empresa);
            this.fachada.cadastrar(this.aviao);          
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Avião cadastrado com sucesso");
            contexto.addMessage(null, msg);
            this.aviao = new Aviao();
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado!");
            contexto.addMessage(null, msg);
            
        } catch (AviaoExistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Esse avião já está cadastrado");
            contexto.addMessage(null, msg);
            
        }
        return null;
    }
    
}
