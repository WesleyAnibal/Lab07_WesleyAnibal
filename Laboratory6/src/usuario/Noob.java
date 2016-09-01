package usuario;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Noob implements TipoDeUsuarioIF {
	public static final double DESCONTO_NOOB = 0.9;

	@Override
	public double compraJogo(Jogo jogo) throws Exception {
		
		double desconto = jogo.getPreco() - (jogo.getPreco() * 0.10);
		return desconto;
	}
	public int getX2p(Jogo jogo){
		return (int) (jogo.getPreco() * 10);
	}

	/*
	 * @Override public String toString() { String myString = this.getLogin() +
	 * FIM_DE_LINHA; myString += this.getNome() + " - Jogador Noob" +
	 * FIM_DE_LINHA; myString += "Lista de Jogos:" + FIM_DE_LINHA;
	 * 
	 * Iterator itr = getMeusJogos().iterator(); while (itr.hasNext()) { Jogo j
	 * = (Jogo) itr.next(); myString += j.toString(); } myString +=
	 * FIM_DE_LINHA; myString += "Total de pre�o dos jogos: R$ " +
	 * this.calculaPrecoTotal() + FIM_DE_LINHA; myString +=
	 * "--------------------------------------------"; return myString; }
	 */

	public int recompensar(Jogo jogo) {
		int totalRecompensa = 0;
		if (jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)) {
			totalRecompensa += 30;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.MULTIPLAYER)) {
			totalRecompensa += 10;
		}
		return totalRecompensa;

	}

	@Override
	public int punir(Jogo jogo) {
		int totalRecompensa = 0;
		if (jogo.getJogabilidade().contains(Jogabilidade.ONLINE)) {
			totalRecompensa += 10;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)) {
			totalRecompensa += 50;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)) {
			totalRecompensa += 20;
		}
		return totalRecompensa;
	}
	

}