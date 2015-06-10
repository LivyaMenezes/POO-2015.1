/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas;

import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import viagens.Viagem;

/**
 *  repositorio chama os metodos da classe RepositorioEmpresaJPA, que a classe que se comunica com o banco
 * @author Raquel Calado
 */
@Local
public interface RepositorioEmpresa extends Serializable {
    public void adicionar (Empresa ep) throws ErroInternoException;
    public Empresa buscarEmpresa (long id_empresa) throws ErroInternoException,EmpresaInexistenteException;
    public void atualizar (Empresa ep) throws ErroInternoException, EmpresaInexistenteException;
    public void remover   (long id_empresa) throws ErroInternoException, EmpresaInexistenteException;
    public List<Empresa> listaEmpresa () throws ErroInternoException;
    public Empresa loginEmpresa (String cnpj, String senha) throws ErroInternoException, EmpresaInexistenteException;
    public List<Viagem> listarViagens (Empresa empresa) throws ErroInternoException;

}
