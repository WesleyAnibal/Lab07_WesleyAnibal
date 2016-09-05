package jogo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public abstract class Jogo {
	private HashMap<String, Jogabilidade> mapJogabildades;
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private String nome;
	private double preco;
	private int vezesJogadas;
	private int vezesConcluidas;
	private int maiorScore;
	private Set<Jogabilidade> jogabilidades;
	/**
	 * @author Wesley Anibal. Classe responsável por moldar os jogos.
	 * 
	 * 
	 * @throws Exception
	 *             Caso o nome do jogo seja nulo ou vazio, ou o valor for abaixo
	 *             de 0, o jogo nao sera criado e sera lancado Exception.
	 *Esse construtor inicia com o set vazio.
	 * @param String
	 *            nome
	 * @param double preco
	 * 
	 */
	public Jogo(String nome, double preco) throws StringInvalidaException,
			PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		this.jogabilidades = new HashSet<Jogabilidade>();
	}
	/**
	 * @param String nome
	 * @param double preco
	 * @param Set jogabilidades
	 * 
	 * Sobrecarga para receber atributo o set e não iniciar vazio.
	 */
	public Jogo(String nome, double preco, Set<Jogabilidade> jogabilidades)
			throws StringInvalidaException, PrecoInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		this.jogabilidades = jogabilidades;
	}
	/**
	 * Metodo abstrato para o registro de jogada.
	 * 
	 * @param score int
	 * @param venceu boolean
	 */
	public abstract int registraJogada(int score, boolean venceu)throws ValorInvalidoException;

	public double getPreco() {
		return this.preco;
	}

	public String getNome() {
		return this.nome;
	}

	public int getMaiorScore() {
		return this.maiorScore;
	}

	public void setMaiorScore(int novoScore) throws ValorInvalidoException {
		if(novoScore < 0 ){
			throw new ValorInvalidoException("Novo score não pode ser menor que zero.");
		}
		this.maiorScore = novoScore;
	}

	public int getvezesConcluidas() {
		return this.vezesConcluidas;
	}

	public void setVezesConcluidas(int novaQuantidade) throws ValorInvalidoException {
		if(novaQuantidade< 0){
			throw new ValorInvalidoException("Quantidade de conclusões não pode ser menor que zero.");
		}
		this.vezesConcluidas = novaQuantidade;
	}

	public int getVezesJogadas() {
		return this.vezesJogadas;
	}

	public void setVezesJogadas(int novaQuantidade) throws ValorInvalidoException {
		if(novaQuantidade<0){
			throw new ValorInvalidoException("Quantidade de jogadas não pode ser menor que zero.");
		}
		this.vezesJogadas = novaQuantidade;
	}
	public Set<Jogabilidade> getJogabilidade(){
		return this.jogabilidades;
	}

	@Override
	public String toString() {
		String resultado = String.format("==> Jogou %d vez(es)\n", getVezesJogadas());
		resultado += String.format("==> Zerou %d vez(es)\n", getvezesConcluidas());
		resultado += String.format("==> Maior Score: %d \n", getMaiorScore());
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Jogo) {
			Jogo temp = (Jogo) obj;

			return this.getNome().equals(temp.getNome())
					&& this.getPreco() == temp.getPreco();

		} else {
			return false;
		}

	}
}
