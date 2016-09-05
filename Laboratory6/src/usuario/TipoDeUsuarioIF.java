package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogo;

public interface TipoDeUsuarioIF {
	/**
	 * Interface responsável pela definição do tipo e do comportamento de um
	 * usuario.
	 */
	
	/**
	 * Metodo responsável pela compra de Jogo.
	 * @param jogo Jogo
	 * @return double
	 * @throws ValorInvalidoException
	 */
	double compraJogo(Jogo jogo) throws ValorInvalidoException;
	/**
	 * Metodo responsável pela punição do Usuario.
	 * @param nomeJogo
	 * @return
	 */
	int punir(Jogo nomeJogo);
	/**
	 * Metodo responsável 
	 * @param nomeJogo
	 * @return
	 */
	int recompensar(Jogo nomeJogo);
	int getX2p(Jogo jogo);
}
