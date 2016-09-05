package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public class Rpg extends Jogo{
	public final static int TAXA_XP2 = 10;
	/**
	 * Subclasse de Jogo, para definir o tipo RPG.
	 * 
	 * @author Wesley Anibal.
	 * @param String
	 *            nome
	 * @param double preco
	 * @throws Exception
	 */
	public Rpg(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}
	
	public Rpg (String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco, jogabilidades);
	}
	/**
	 * metodo sobrescrito de registra jogada,para o calculo do X2p,no qual é
	 * levado em conta a quantidade de vezes no qual foi jogado.
	 * 
	 * @param int score
	 * @param boolean venceu
	 */
	@Override
	public int registraJogada(int score, boolean venceu) throws ValorInvalidoException {
		setVezesJogadas(getVezesJogadas()+ 1);
		if(score > this.getMaiorScore()){
			setMaiorScore(score);
		}
		if(venceu){
			setVezesConcluidas(getvezesConcluidas() + 1);
			
		}
		return TAXA_XP2;
	}
	
	public String toString() {
		String resultado = getNome() + " - RPG:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}

}
