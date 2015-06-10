/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import aviao.Aviao;
import aviao.AviaoInexistenteException;
import empresas.Empresa;
import index.ErroInternoException;
import index.Fachada;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import viagens.Viagem;
import viagens.ViagemExistenteException;
import viagens.ViagemInexistenteException;

/**
 *
 * @author Raquel Calado
 */
@ManagedBean
@SessionScoped
public class ManagedBeanViagem implements Serializable{
    
    @EJB
    private Fachada fachada;
    private Viagem viagem;
    private long viagemSelecionada;
    private long aviaoSelecionado;
    private Empresa empresa;
    private Aviao aviao;
    private List<Viagem> listaViagens;

    public ManagedBeanViagem() {
        this.viagem = new Viagem();
    }

    public long getViagemSelecionada() {
        return viagemSelecionada;
    }

    public void setViagemSelecionada(long viagemSelecionada) {
        this.viagemSelecionada = viagemSelecionada;
    }

    public long getAviaoSelecionado() {
        return aviaoSelecionado;
    }

    public void setAviaoSelecionado(long aviaoSelecionado) {
        this.aviaoSelecionado = aviaoSelecionado;
    }

    public List<Viagem> getListaViagens() throws ErroInternoException {
        try {
            return this.fachada.listaViagens();
        } catch (ErroInternoException ex) {
            throw ex;
        }
    }

    public void setListaViagens(List<Viagem> listaViagens) {
        this.listaViagens = listaViagens;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Aviao getAviao() throws ErroInternoException, AviaoInexistenteException {
        try {
            this.viagem = fachada.buscarViagem(viagemSelecionada);
        } catch (ViagemInexistenteException ex) {
            Logger.getLogger(ManagedBeanViagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.fachada.buscarAviao(viagem.getAviao().getId_aviao());
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public String formatarData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);    
    }

    public String formatarHora(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(data);
    }

    public String viagemSelecionada(long id) throws ViagemInexistenteException, ErroInternoException, IOException {
        try{
        this.fachada.buscarViagem(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("lista-poltrona.xhtml");
        }
        catch(IOException ioe){
            throw ioe;
        }
        catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado! " + eie.getMessage());
            contexto.addMessage(null, msg);     
        }
        catch (ViagemInexistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Viagem Inexistente", "A viagem não existe");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public Aviao aviaoSelecionado(long id) throws ErroInternoException, AviaoInexistenteException {
        try{
        return this.fachada.buscarAviao(id);
        }
        catch(AviaoInexistenteException oie){
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Avião inexistente! ");
            contexto.addMessage(null, msg);   
        }
        return null;
    }
    

    public String adicionarViagem() throws AviaoInexistenteException {
        try {
            Aviao o = aviaoSelecionado(aviaoSelecionado);
            this.viagem.setAviao(o);
            this.viagem.setEmpresa(this.empresa);
            this.fachada.adicionar(this.viagem);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Viagem cadastrada com sucesso");
            contexto.addMessage(null, msg);
            this.viagem = new Viagem();

        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado! " + eie.getMessage());
            contexto.addMessage(null, msg);

        } catch (ViagemExistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Viagem Existente", "Viagem já existe");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String consultaViagens() {
        try {
            this.listaViagens = (this.fachada.consultaViagens(viagem.getOrigem(), viagem.getDestino(), viagem.getData()));
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Exibindo viagens de " + viagem.getOrigem() + " para " + viagem.getDestino() + " em " + formatarData(viagem.getData()));
            contexto.addMessage(null, msg);
            return "lista-aviao.xhtml";
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Não existe nenhuma viagem de " + viagem.getOrigem() + " para " + viagem.getDestino() + " em " + formatarData(viagem.getData()));
            contexto.addMessage(null, msg);

        } catch (ViagemInexistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Viagem Inexistente", "A viagem não existe");
            contexto.addMessage(null, msg);
        }
        return null;
    }
    
}
