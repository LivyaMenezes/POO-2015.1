/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviao;

import empresas.Empresa;
import index.ErroInternoException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raquel Calado
 */
@Local
public interface RepositorioAviao extends Serializable{
    
     public void cadastrar(Aviao aviao) throws ErroInternoException, AviaoExistenteException;
    
    public void excluir(long id_aviao) throws ErroInternoException, AviaoInexistenteException;
    
    public void editar(Aviao aviao) throws ErroInternoException, AviaoInexistenteException;
    
    public Aviao buscarAviao(long id) throws ErroInternoException, AviaoInexistenteException;
    
    public List<Aviao> listaAviao(Empresa empresa) throws ErroInternoException, AviaoInexistenteException;
    
}
