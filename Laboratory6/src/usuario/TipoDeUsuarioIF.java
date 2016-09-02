package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogo;

public interface TipoDeUsuarioIF {
	double compraJogo(Jogo jogo)throws ValorInvalidoException;
	int punir(Jogo nomeJogo);
	int recompensar(Jogo nomeJogo);
	int getX2p(Jogo jogo);
}
