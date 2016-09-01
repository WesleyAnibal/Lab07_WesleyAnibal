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
	public static final double DESCONTO_VETERANO = 0.8;
	private int xp2;
	
	public Veterano(String nome, String login) throws StringInvalidaException {
		this.xp2 = 1000;
	}

	@Override
	public double compraJogo(Jogo jogo) throws Exception {
		xp2 += jogo.getPreco() * 15;
		double desconto = jogo.getPreco() - (jogo.getPreco() * 0.20);
		return desconto;

	}
	public void setXp2(int xp2){
		this.xp2 = xp2;
	}
	public int getXp2(){
		return this.xp2;
	}
	@Override
	public void recompensar(Jogo jogo, int scoreObtido, boolean zerou) {
		int totalRecompensa = 0;
		if(jogo.getJogabilidade().contains(Jogabilidade.ONLINE)){
			totalRecompensa += 10;
		if(jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)){
			totalRecompensa += 20;

		}
		xp2+=totalRecompensa;
		}
	}

	@Override
	public void punir(Jogo jogo, int scoreObtido, boolean zerou) {
		int totalRecompensa = 0;
		if(jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)){
			totalRecompensa += 20;
		}
		if(jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)){
				totalRecompensa += 20;
		}
		xp2-= totalRecompensa;
		
	}

	/*public String toString() {
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
	}*/

}
