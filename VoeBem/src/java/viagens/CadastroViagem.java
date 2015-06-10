/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viagens;

import empresas.Empresa;
import index.ErroInternoException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Raquel Calado
 */
@Stateless
public class CadastroViagem {
    
    @EJB
    private RepositorioViagem viagem;
    
    public CadastroViagem(){
        
    }
    
    public void adicionar(Viagem v) throws ErroInternoException, ViagemExistenteException{
        try{
            Viagem viagem2 =  this.viagem.buscarViagem(v.getId_viagem());
            throw new ViagemExistenteException();
     
        }
        catch(ViagemInexistenteException e){
            this.viagem.adicionar(v);
            
        }
        
    }
    
    public void removeViagem(long Id_viagem) throws ViagemInexistenteException, ErroInternoException{
        this.viagem.removerViagem(Id_viagem);
    }

    public Viagem buscarViagem(long Id_viagem) throws ViagemInexistenteException, ErroInternoException{
       return this.viagem.buscarViagem(Id_viagem);
    } 
    public void atualizar(Viagem v) throws ViagemInexistenteException, ErroInternoException{
        this.viagem.atualizar(v);
    }
    public List<Viagem> consultaViagens(Destinos origem, Destinos destino, Date data) throws ViagemInexistenteException, ErroInternoException{
        return this.viagem.consultaViagens(origem, destino, data);
    }

//     public List<Viagem> listaViagens(Viagem viagem) throws ErroInternoException {
//        return this.viagem.listaViagens(viagem);
//    }    

    public List<Viagem> listaViagens () throws ErroInternoException{
        return this.viagem.listaViagens();
    }
}
