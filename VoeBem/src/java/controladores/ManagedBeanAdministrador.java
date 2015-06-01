/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import administrador.Administrador;
import administrador.AdministradorExistenteException;
import administrador.AdministradorInexistenteException;
import clientes.ClienteInexistenteException;
import index.ErroInternoException;
import index.Fachada;
import java.io.IOException;
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
public class ManagedBeanAdministrador implements Serializable{
    
    @EJB
    private Fachada fachada;
    private Administrador administrador;
    private boolean login;

    public ManagedBeanAdministrador() {
        this.administrador = new Administrador();
    }
    
    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String adicionarAdministrador() throws AdministradorExistenteException, AdministradorInexistenteException, ClienteInexistenteException {
        try {
            this.fachada.adicionarAdministrador(this.administrador);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro realizado com sucesso.");
            contexto.addMessage(null, msg);
            this.administrador = new Administrador();

        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", " Ocorreu um erro interno inesperado " + eie.getMessage());
            contexto.addMessage(null, msg);

        } catch (AdministradorExistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", " Esse administrador já está cadastrado");
            contexto.addMessage(null, msg);
        } catch (AdministradorInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador Inexistente", "Administrador não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String removerAdministrador() throws AdministradorInexistenteException {
        try {
            this.fachada.removerAdm(administrador.getId_administrador());
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro removido com sucesso.");
            contexto.addMessage(null, msg);
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", " Ocorreu um erro interno inesperado " + eie.getMessage());
            contexto.addMessage(null, msg);
        } catch (AdministradorInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador Inexistente", "Administrador não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String buscarAdministrador() throws AdministradorInexistenteException {
        try {
            this.fachada.buscarAdministrador(administrador.getId_administrador());
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro encontrado com sucesso.");
            contexto.addMessage(null, msg);
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", " Ocorreu um erro interno inesperado " + eie.getMessage());
            contexto.addMessage(null, msg);
        } catch (AdministradorInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador Inexistente", "Administrador não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String atualizarsenhaAdministrador() throws IOException {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("atualizarsenha-administrador.xhtml");
        }
        catch(IOException ioe){
            throw ioe;
        }
        return null;
    }

    public String senhaatualizadaAdministrador()  {
        try {
            this.fachada.atualizarAdministrador(this.administrador);
            return "dadospessoais-administrador.xhtml";
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ErroInterno", "Ocorreu um erro interno");
            contexto.addMessage(null, msg);
        } catch (AdministradorInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador Inexistente", "Administrador não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String atualizarAdministrador() throws ErroInternoException,AdministradorInexistenteException {
        try {
            this.fachada.atualizarAdministrador(administrador);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Dados atualizados com sucesso");
            contexto.addMessage(null, msg);
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ErroInterno", "Ocorreu um erro interno");
            contexto.addMessage(null, msg);
        } catch (AdministradorInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador Inexistente", "Administrador não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String loginAdministrador() throws ErroInternoException, AdministradorInexistenteException {
        try {
            this.administrador = this.fachada.loginAdministrador(administrador.getCpf(), administrador.getSenha());
            this.login = true;
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Administrador logado com sucesso");
            contexto.addMessage(null, msg);
            return "index.xhtml";
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado! " + eie.getMessage());
            contexto.addMessage(null, msg);

        } catch (AdministradorInexistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", "O Administrador não está cadastrado no sistema. Verifique o CPF e senha e tente novamente.");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String logout() throws ErroInternoException, IOException {
        try{
        login = false;
        this.administrador = new Administrador();          
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        catch(IOException ioe){
            throw ioe;
        }
        return null;
    }

    public List<Administrador> listaAdministrador() throws ErroInternoException {
        try {
            List<Administrador> listaAdministrador = this.fachada.listaAdministrador(administrador);
            return listaAdministrador;
        } catch (ErroInternoException e) {
            throw new ErroInternoException(e);
        }
    }
    
}
