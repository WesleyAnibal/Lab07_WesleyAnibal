package usuario;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano implements TipoDeUsuarioIF{
	public static final double DESCONTO_VETERANO = 0.20;
	public static final int VALOR_XP2 = 15;
	

	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		double desconto = jogo.getPreco() - (jogo.getPreco() * DESCONTO_VETERANO);
		return desconto;

	}
	public int getX2p(Jogo jogo){
		return (int) (jogo.getPreco() * VALOR_XP2);
	}

	@Override
	public int recompensar(Jogo jogo) {
		int totalRecompensa = 0;
		if(jogo.getJogabilidade().contains(Jogabilidade.ONLINE)){
			totalRecompensa += 10;
		}
		if(jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)){
			totalRecompensa += 20;
		}
		return totalRecompensa;
	}

	@Override
	public int punir(Jogo jogo) {
		int totalRecompensa = 0;
		if(jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)){
			totalRecompensa += 20;
		}
		if(jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)){
				totalRecompensa += 20;
		}
		return totalRecompensa;
		
	}

	public String toString() {
		String myString = "Jogador Veterano: ";
		return myString;
	}

}
