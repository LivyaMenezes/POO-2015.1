/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviao;

import empresas.Empresa;
import index.ErroInternoException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class CadastroAviao {
    
    @EJB
    private RepositorioAviao repAviao;
    
    public CadastroAviao(){
    }
    
    public void cadastrar(Aviao aviao) throws ErroInternoException, AviaoExistenteException{
        try {
            Aviao aviao2 = this.repAviao.buscarAviao(aviao.getId_aviao());
            if(aviao2 != null){
                throw new AviaoExistenteException();
            }       
        } catch (AviaoInexistenteException e){
            this.repAviao.cadastrar(aviao);
        }
    }
    
    public Aviao buscarAviao(long id_aviao) throws ErroInternoException, AviaoInexistenteException{
        try{
            return this.repAviao.buscarAviao(id_aviao);
        }
        catch(ErroInternoException eie){
            throw new ErroInternoException(eie);
        }
    }
    
    public void editar(Aviao aviao) throws ErroInternoException, AviaoInexistenteException{
        this.repAviao.editar(aviao);
    }
    
    public void excluir(long id_aviao) throws ErroInternoException, AviaoInexistenteException{
        this.repAviao.excluir(id_aviao);
    }
    
    public List<Aviao> listaAviao(Empresa empresa) throws ErroInternoException, AviaoInexistenteException{
        return this.repAviao.listaAviao(empresa); 
    }
    
}
