/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import administrador.Administrador;
import administrador.AdministradorExistenteException;
import administrador.AdministradorInexistenteException;
import administrador.CadastroAdministrador;
import aviao.Aviao;
import aviao.AviaoExistenteException;
import aviao.AviaoInexistenteException;
import aviao.CadastroAviao;
import clientes.CadastroClientes;
import clientes.Cliente;
import clientes.ClienteExistenteException;
import clientes.ClienteInexistenteException;
import empresas.CadastroEmpresa;
import empresas.Empresa;
import empresas.EmpresaExistenteException;
import empresas.EmpresaInexistenteException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import poltronas.CadastroPoltrona;
import poltronas.Poltrona;
import poltronas.PoltronaIndisponivelException;
import poltronas.PoltronaInexistenteException;
import vendas.CadastroVenda;
import vendas.Venda;
import vendas.VendaInexistenteException;
import viagens.CadastroViagem;
import viagens.Destinos;
import viagens.Viagem;
import viagens.ViagemExistenteException;
import viagens.ViagemInexistenteException;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class Fachada  implements Serializable{
    
    @EJB
    private CadastroClientes cadCliente;
    @EJB
    private CadastroEmpresa empresa;
    @EJB
    private CadastroAviao cadAviao;
    @EJB
    private CadastroPoltrona poltrona;
    @EJB
    private CadastroVenda venda;
    @EJB
    private CadastroViagem viagem;
    @EJB
    private CadastroAdministrador administrador;

    public Fachada() {

    }

    //CLIENTE
    
    public void adicionar(Cliente cliente) throws ErroInternoException, ClienteExistenteException, ClienteInexistenteException {
        this.cadCliente.adicionar(cliente);
    }

    public Cliente buscarCliente(long id_cliente) throws ClienteInexistenteException, ErroInternoException {
        return this.cadCliente.buscarCliente(id_cliente);
    }

    public void atualizar(Cliente c) throws ClienteInexistenteException, ErroInternoException {
        this.cadCliente.atualizar(c);
    }

    public void remover(long id_cliente) throws ErroInternoException, ClienteInexistenteException, AdministradorInexistenteException {
        this.cadCliente.remover(id_cliente);
    }

    public Cliente loginCliente(String cpf, String senha) throws ErroInternoException, ClienteInexistenteException {
        return this.cadCliente.loginCliente(cpf, senha);
    }

    public List<Cliente> listaCliente(Cliente cliente) throws ErroInternoException {
        return this.cadCliente.listaClientes(cliente);
    }
    
    //ADMINISTRADOR
    
    public void adicionarAdministrador(Administrador administrador) throws ErroInternoException, AdministradorExistenteException, ClienteInexistenteException, AdministradorInexistenteException {
        this.administrador.adicionar(administrador);
    }

    public Administrador buscarAdministrador(long id_administrador) throws ErroInternoException, AdministradorInexistenteException {
        return this.administrador.buscarAdministrador(id_administrador);
    }

    public void atualizarAdministrador(Administrador a) throws ErroInternoException, AdministradorInexistenteException {
        this.administrador.atualizar(a);
    }

    public void removerAdm(long id_administrador) throws ErroInternoException, AdministradorInexistenteException {
        this.administrador.remover(id_administrador);
    }

    public Administrador loginAdministrador(String cpf, String senha) throws ErroInternoException, AdministradorInexistenteException {
        return this.administrador.loginAdministrador(cpf, senha);
    }

    public List<Administrador> listaAdministrador(Administrador administrador) throws ErroInternoException {
        return this.administrador.listaAdministrador(administrador);
    }
    
    //EMPRESA

    public void adicionar(Empresa ep) throws ErroInternoException, EmpresaExistenteException {
        this.empresa.adicionar(ep);

    }

    public List<Empresa> listaEmpresa() throws ErroInternoException {
        return this.empresa.listaEmpresa();
    }

    public void remove(long Id_empresa) throws EmpresaInexistenteException, ErroInternoException {
        this.empresa.remove(Id_empresa);
    }

    public Empresa buscarEmpresa(long Id_empresa) throws EmpresaInexistenteException, ErroInternoException {
        return this.empresa.buscarEmpresa(Id_empresa);
    }

    public void atualizar(Empresa ep) throws EmpresaInexistenteException, ErroInternoException {
        this.empresa.atualizar(ep);
    }

    public Empresa loginEmpresa(String cnpj, String senha) throws ErroInternoException, EmpresaInexistenteException {
        return this.empresa.loginEmpresa(cnpj, senha);
    }
    
    public List<Viagem> listarViagens (Empresa empresa) throws ErroInternoException{
        return this.empresa.listarViagens(empresa);
    }

    public void cadastrar(Aviao aviao) throws ErroInternoException, AviaoExistenteException {
        this.cadAviao.cadastrar(aviao);
    }

    public Aviao buscarAviao(long id_aviao) throws ErroInternoException, AviaoInexistenteException {
        return this.cadAviao.buscarAviao(id_aviao);
    }

    public List<Aviao> listaAviao(Empresa empresa) throws ErroInternoException, AviaoInexistenteException {
        return this.cadAviao.listaAviao(empresa);
    }

    public void editar(Aviao aviao) throws ErroInternoException, AviaoInexistenteException {
        this.cadAviao.editar(aviao);
    }

    public void excluir(long id_aviao) throws ErroInternoException, AviaoInexistenteException {
        this.cadAviao.excluir(id_aviao);
    }

    public void adicionar(Poltrona p) throws ErroInternoException, PoltronaInexistenteException, PoltronaIndisponivelException {
        this.poltrona.adicionar(p);
    }

    public List<Poltrona> listar(long id_viagem) throws ErroInternoException {
        return this.poltrona.listar(id_viagem);
    }

    public Poltrona buscarPoltrona(long id_poltrona) throws ErroInternoException, PoltronaInexistenteException {
        return this.poltrona.buscarPoltrona(id_poltrona);
    }

    public void adicionar(Venda venda) throws ErroInternoException {
        this.venda.adicionar(venda);
    }

    public Venda buscarVenda(long id) throws ErroInternoException, VendaInexistenteException {
        return this.venda.buscarVenda(id);
    }

    public List<Venda> vendasPorViagem(long id_viagem) throws ErroInternoException, VendaInexistenteException {
        return this.venda.vendasPorViagem(id_viagem);
    }
    
      public void removerVenda(long codigoVenda) throws ErroInternoException, VendaInexistenteException {
        this.venda.remover(codigoVenda);
                
    }

    public void adicionar(Viagem v) throws ErroInternoException, ViagemExistenteException {
        this.viagem.adicionar(v);
    }

    public void removeViagem(long Id_viagem) throws ViagemInexistenteException, ErroInternoException {
        this.viagem.removeViagem(Id_viagem);
    }

    public Viagem buscarViagem(long Id_viagem) throws ViagemInexistenteException, ErroInternoException {
        return this.viagem.buscarViagem(Id_viagem);
    }

    public void atualizar(Viagem v) throws ViagemInexistenteException, ErroInternoException {
        this.viagem.atualizar(v);
    }

    public List<Viagem> consultaViagens(Destinos origem, Destinos destino, Date data) throws ErroInternoException, ViagemInexistenteException {
        return this.viagem.consultaViagens(origem, destino, data);
    }

    public List<Long> poltronasCompradas(Viagem viagem) throws ErroInternoException {
        return this.poltrona.poltronasCompradas(viagem);
    }

    public List<Venda> listarVendasCliente(Cliente cliente) throws ErroInternoException {
        return this.venda.listarVendasCliente(cliente);
    }
    
}
