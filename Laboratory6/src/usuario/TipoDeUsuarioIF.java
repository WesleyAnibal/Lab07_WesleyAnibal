package usuario;

import jogo.Jogo;

public interface TipoDeUsuarioIF {
	double compraJogo(Jogo jogo)throws Exception;
	void punir(Jogo nomeJogo, int scoreObtido, boolean zerou);
	void recompensar(Jogo nomeJogo, int scoreObtido, boolean zerou);
	
}
