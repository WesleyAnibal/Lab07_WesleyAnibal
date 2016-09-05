package usuario;


import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Noob implements TipoDeUsuarioIF {
	public static final double DESCONTO_NOOB = 0.9;

	@Override
	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		double desconto = jogo.getPreco() - (jogo.getPreco() * 0.10);
		return desconto;
	}

	public int getX2p(Jogo jogo) {
		return (int) (jogo.getPreco() * 10);
	}

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

	public String toString() {
		String myString = "Jogador Noob: ";
		return myString;
	}

}