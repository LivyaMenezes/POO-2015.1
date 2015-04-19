/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.Cliente;
import clientes.ClienteExistenteException;
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
public class ManagedBeanClientes implements Serializable {
    
    @EJB
    private Fachada fachada;
    private Cliente cliente;
    private boolean login;

    public ManagedBeanClientes() {
        this.cliente = new Cliente();
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String adicionarCliente() {
        try {
            this.fachada.adicionar(this.cliente);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro realizado com sucesso.");
            contexto.addMessage(null, msg);
            this.cliente = new Cliente();

        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", " Ocorreu um erro interno inesperado " + eie.getMessage());
            contexto.addMessage(null, msg);

        } catch (ClienteExistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", " Esse cliente já está cadastrado");
            contexto.addMessage(null, msg);
        } catch (ClienteInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", "Cliente não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String removerCliente() {
        try {
            this.fachada.remover(cliente.getId_cliente());
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro removido com sucesso.");
            contexto.addMessage(null, msg);
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", " Ocorreu um erro interno inesperado " + eie.getMessage());
            contexto.addMessage(null, msg);
        } catch (ClienteInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", "Cliente não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String buscarCliente() {
        try {
            this.fachada.buscarCliente(cliente.getId_cliente());
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastro encontrado com sucesso.");
            contexto.addMessage(null, msg);
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", " Ocorreu um erro interno inesperado " + eie.getMessage());
            contexto.addMessage(null, msg);
        } catch (ClienteInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", "Cliente não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String atualizarsenhaCliente() throws IOException {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("atualizarsenha-cliente.xhtml");
        }
        catch(IOException ioe){
            throw ioe;
        }
        return null;
    }

    public String senhaatualizadaCliente()  {
        try {
            this.fachada.atualizar(this.cliente);
            return "dadospessoais-cliente.xhtml";
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ErroInterno", "Ocorreu um erro interno");
            contexto.addMessage(null, msg);
        } catch (ClienteInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", "Cliente não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String atualizarCliente() throws ErroInternoException, ClienteInexistenteException {
        try {
            this.fachada.atualizar(cliente);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Dados atualizados com sucesso");
            contexto.addMessage(null, msg);
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ErroInterno", "Ocorreu um erro interno");
            contexto.addMessage(null, msg);
        } catch (ClienteInexistenteException cie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", "Cliente não existente");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String loginCliente() throws ErroInternoException, ClienteInexistenteException {
        try {
            this.cliente = this.fachada.loginCliente(cliente.getCpf(), cliente.getSenha());
            this.login = true;
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cliente logado com sucesso");
            contexto.addMessage(null, msg);
            return "index.xhtml";
        } catch (ErroInternoException eie) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro Interno", "Ocorreu um errointerno inesperado! " + eie.getMessage());
            contexto.addMessage(null, msg);

        } catch (ClienteInexistenteException cee) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", "O cliente não está cadastrado no sistema. Verifique o CPF e senha e tente novamente.");
            contexto.addMessage(null, msg);
        }
        return null;
    }

    public String logout() throws ErroInternoException, IOException {
        try{
        login = false;
        this.cliente = new Cliente();          
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        catch(IOException ioe){
            throw ioe;
        }
        return null;
    }

    public List<Cliente> listaClientes() throws ErroInternoException {
        try {
            List<Cliente> listaClientes = this.fachada.listaCliente(cliente);
            return listaClientes;
        } catch (ErroInternoException e) {
            throw new ErroInternoException(e);
        }
    }
    
}
