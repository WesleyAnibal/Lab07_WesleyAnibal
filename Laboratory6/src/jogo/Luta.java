package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public class Luta extends Jogo{
	/**
	 * SubClasse de Jogo, para definir o tipo Luta.
	 * 
	 * @author Wesley Anibal
	 * @param String
	 *            nome
	 * @param double preco
	 * @throws Exception
	 */
	public Luta(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}
	
	public Luta (String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco, jogabilidades);
	}
	/**
	 * metodo sobrescrito de registra jogada,para o calculo do X2p,no qual é
	 * levado em conta o score.
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
		return score/1000;
	}
	public String toString() {
		final String FIM_DE_LINHA = System.lineSeparator();
		String resultado = getNome() + " - Luta:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}
}
