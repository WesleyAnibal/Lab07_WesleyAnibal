package usuario;

import jogo.Jogo;

public interface TipoDeUsuarioIF {
	double compraJogo(Jogo jogo)throws Exception;
	int punir(Jogo nomeJogo);
	int recompensar(Jogo nomeJogo);
	
}
