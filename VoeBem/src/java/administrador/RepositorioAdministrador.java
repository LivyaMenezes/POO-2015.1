/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador;

import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raquel Calado
 */
@Local
public interface RepositorioAdministrador extends Serializable {
    
    public void adicionar (Administrador administrador) throws ErroInternoException, AdministradorExistenteException;
    public void remover (long id_administrador) throws ErroInternoException, AdministradorInexistenteException;
    public Administrador buscarAdministrador (long id_administrador) throws ErroInternoException, AdministradorInexistenteException;
    public void atualizar (Administrador administrador) throws ErroInternoException, AdministradorInexistenteException;
    public Administrador loginAdministrador(String cpf, String senha) throws ErroInternoException, AdministradorInexistenteException;
    public List<Administrador> listaAdministrador (Administrador administrador) throws ErroInternoException;
    
}
