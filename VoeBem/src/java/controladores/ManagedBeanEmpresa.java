/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import empresas.Empresa;
import empresas.EmpresaExistenteException;
import empresas.EmpresaInexistenteException;
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
import viagens.Viagem;

/**
 *
 * @author Raquel Calado
 */
@ManagedBean
@SessionScoped
public class ManagedBeanEmpresa implements Serializable{
    
    @EJB
    private Fachada fachada;
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    private boolean login;
    private List<Viagem> listaViagem;

    public ManagedBeanEmpresa() {
        this.empresa = new Empresa();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Viagem> getListaViagem(Empresa empresa) throws ErroInternoException{
        try{
            return this.fachada.listarViagens(empresa);
        }
        catch(ErroInternoException eie){
            throw eie;
        }
    }

    public void setListaViagem(List<Viagem> listaViagem) {
        this.listaViagem = listaViagem;
    }
       
    public List<Empresa> getListaEmpresa() throws ErroInternoException {
        try {
            return this.fachada.listaEmpresa();
        } catch (ErroInternoException ex) {
            throw ex;
        }
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String adicionarEmpresa() throws ErroInternoException, EmpresaExistenteException {
        try {
            this.fachada.adicionar(this.empresa);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro realizado com sucesso");
            contexto.addMessage(null, msg);
            this.empresa = new Empresa();

        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado! " + eie.getMessage());
            contexto.addMessage(null, msg);
            return null;
        } catch (EmpresaExistenteException eee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", "Essa empresa já está cadastrada no sistema");
            contexto.addMessage(null, msg);
            return null;
        }
        return null;
    }

    public String senhaatualizadaEmpresa() throws ErroInternoException, EmpresaInexistenteException {
        try {
            this.fachada.atualizar(this.empresa);
            return "dadosjuridicos-empresa.xhtml";
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", " Ocorreu um erro interno");
            contexto.addMessage(null, msg);
            return null;
        } catch (EmpresaInexistenteException cie) {
            return null;
        }
    }

    public String loginEmpresa() throws ErroInternoException, EmpresaInexistenteException {
        try {
            this.empresa = this.fachada.loginEmpresa(empresa.getCnpj(), empresa.getSenha());
            this.login = true;
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Empresa logada");
            contexto.addMessage(null, msg);
            return "index.xhtml";
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado! " + eie.getMessage());
            contexto.addMessage(null, msg);           
        } catch (EmpresaInexistenteException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", " A empresa não está cadastrada no sistema. Verifique o CNPJ e senha e tente novamente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String atualizarsenhaEmpresa() throws IOException {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("atualizarsenha-empresa.xhtml");
        }
        catch(IOException ioe){
            throw ioe;
        }
        return null;
    }

    public String logout() throws IOException {
        try{
        login = false;
        this.empresa = new Empresa();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        catch(IOException ioe){
            throw ioe;
        }
        return null;
    }

    public String atualizarEmpresa() throws ErroInternoException, EmpresaInexistenteException {
        try {
            this.fachada.atualizar(empresa);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Os dados foram atualizados com sucesso");
            contexto.addMessage(null, msg);
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ErroInterno", "Ocorreu um erro interno");
            contexto.addMessage(null, msg);
        } catch (EmpresaInexistenteException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa Inexistente", "A empresa não cadastrada no sistema!");
            contexto.addMessage(null, msg);
            return null;
        }
        return null;
    }

    
}
