package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();
	private TipoDeUsuarioIF statusDoUsuario;
	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	protected int xp2;

	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		meusJogos = new HashSet<Jogo>();
		this.credito = 0;
		this.statusDoUsuario = new Noob(nome, login);
	}

	public void compraJogo(Jogo jogo) throws Exception{
		this.credito -= statusDoUsuario.compraJogo(jogo);
		meusJogos.add(jogo);
	}

	public void setXp2(int novoValor) {
		this.xp2 = novoValor;
	}

	public int getXp2() {
		return this.xp2;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}
	
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou){
		
	}
	
	public void punir(String nomeJogo, int scoreObtido, boolean zerou){
		
	}

	public void registradaJogada(String nomeJogo, int score, boolean venceu) throws Exception {
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo == null) {
			throw new Exception("teste");
		}
		setXp2(getXp2() + jogo.registraJogada(score, venceu));
	}

	public Jogo buscaJogo(String nomeJogo) {
		for(Jogo jogo : meusJogos){
			if(jogo.getNome().equalsIgnoreCase(nomeJogo)){
				return jogo;
			}
		}
		return null;
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}
	
	public void addJogo(Jogo jogo){
		meusJogos.add(jogo);
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
}
