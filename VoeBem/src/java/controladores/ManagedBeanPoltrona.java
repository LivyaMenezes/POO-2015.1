/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import index.ErroInternoException;
import index.Fachada;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import poltronas.Poltrona;
import poltronas.PoltronaIndisponivelException;
import poltronas.PoltronaInexistenteException;
import viagens.Viagem;

/**
 *
 * @author Raquel Calado
 */
@ManagedBean
@SessionScoped
public class ManagedBeanPoltrona implements Serializable{
    
     @EJB
    private Fachada fachada;
    private Poltrona poltrona;
    private Viagem viagem;
    private long poltronaSelecionada;

    public ManagedBeanPoltrona() {
        this.poltrona = new Poltrona();
    }

    public long getPoltronaSelecionada() {
        return poltronaSelecionada;
    }

    public void setPoltronaSelecionada(long poltronaSelecionada) throws PoltronaIndisponivelException {
        this.poltronaSelecionada = poltronaSelecionada;
    }

    public Poltrona getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(Poltrona poltrona) {
        this.poltrona = poltrona;
    }

    public Poltrona adicionarPoltrona() throws ErroInternoException, PoltronaIndisponivelException, PoltronaInexistenteException {
        try {
            this.poltrona.setNumero_poltrona(poltronaSelecionada);
            this.fachada.adicionar(this.poltrona);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Poltrona cadastrada com sucesso");
            contexto.addMessage(null, msg);
            return this.poltrona;
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado!");
            contexto.addMessage(null, msg);
        } catch (PoltronaIndisponivelException pei) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poltrona Indisponivel", "Essa poltrona não está disponível");
            contexto.addMessage(null, msg);
        } catch (PoltronaInexistenteException pie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Poltrona Inexistente", "Essa poltrona não existe");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public List<Long> poltronasCompradas(Viagem viagem) throws ErroInternoException {
        try{
        return fachada.poltronasCompradas(viagem);
        }
        catch(ErroInternoException eie){
            throw eie;
        }
    }

    public String buscarPoltrona() throws ErroInternoException, PoltronaInexistenteException {
        try {
            this.fachada.buscarPoltrona(this.poltrona.getId_poltrona());
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado!");
            contexto.addMessage(null, msg);
        } catch (PoltronaInexistenteException pie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Essa poltrona não existe!");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String listarPoltrona() {
        try {
            this.fachada.listar(this.viagem.getId_viagem());
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado!");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public boolean poltronaIndisponivel(Viagem viagem, long id) throws ErroInternoException {
        try {
            for (Long poltronasComprada : poltronasCompradas(viagem)) {
                if (poltronasComprada.equals(id)) {
                    return true;
                }
            }
            return false;
        } catch (ErroInternoException e) {
            throw e;
        }
    }
    
}
