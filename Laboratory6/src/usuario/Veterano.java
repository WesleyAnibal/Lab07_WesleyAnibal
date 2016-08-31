package usuario;

import java.util.Iterator;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano extends Usuario {
	public static final double DESCONTO_VETERANO = 0.8;

	public Veterano(String nome, String login) throws StringInvalidaException {
		super(nome, login);
		setXp2(1000);
	}

	@Override
	public void compraJogo(Jogo jogo) throws Exception {
		if (getCredito() >= jogo.getPreco()) {
			xp2 += jogo.getPreco() * 15;
			double desconto = jogo.getPreco() - (jogo.getPreco() * 0.20);
			super.addJogo(jogo);
			setCredito(getCredito() - desconto);
		} else {
			throw new Exception("Dinheiro insuficiente.");
		}

	}
	@Override
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) {
		int totalRecompensa = 0;
		Jogo jogo = buscaJogo(nomeJogo);
		if(jogo.getJogabilidade().contains(Jogabilidade.ONLINE)){
			totalRecompensa += 10;
		if(jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)){
			totalRecompensa += 20;

		}
		xp2+=totalRecompensa;
		}
	}

	@Override
	public void punir(String nomeJogo, int scoreObtido, boolean zerou) {
		int totalRecompensa = 0;
		Jogo jogo = buscaJogo(nomeJogo);
		if(jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)){
			totalRecompensa += 20;
		}
		if(jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)){
				totalRecompensa += 20;
		}
		xp2-= totalRecompensa;
		
	}

	public String toString() {
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - Jogador Veterano" + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de pre�o dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}

}
