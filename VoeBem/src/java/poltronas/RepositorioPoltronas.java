/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poltronas;

import index.ErroInternoException;
import java.util.List;
import javax.ejb.Local;
import viagens.Viagem;

/**
 *
 * @author Raquel Calado
 */
@Local
public interface RepositorioPoltronas {
    
    public void adicionar (Poltrona p) throws ErroInternoException, PoltronaIndisponivelException;
    public Poltrona buscarPoltrona (long id_poltrona) throws ErroInternoException, PoltronaInexistenteException;
    public List<Poltrona> listar (long id_viagem) throws ErroInternoException;
    public List<Long> poltronasCompradas(Viagem viagem) throws ErroInternoException;
    
}
