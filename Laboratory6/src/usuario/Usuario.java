package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();
	private TipoDeUsuarioIF statusDoUsuario;
	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;

	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		this.meusJogos = new HashSet<Jogo>();
		this.credito = 0;
		this.statusDoUsuario = new Noob();
		this.xp2 = 0;
	}
	
	

	public TipoDeUsuarioIF getStatusDoUsuario() {
		return statusDoUsuario;
	}



	public void setStatusDoUsuario(TipoDeUsuarioIF statusDoUsuario) {
		this.statusDoUsuario = statusDoUsuario;
	}



	public void compraJogo(Jogo jogo) throws Exception {
		xp2 += statusDoUsuario.getX2p(jogo);
		this.credito -= statusDoUsuario.compraJogo(jogo);
		meusJogos.add(jogo);
	}

	public void upgrade() throws UpgradeInvalidoException {
	if (xp2 < 1000) {
		throw new UpgradeInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
	}
		this.statusDoUsuario = new Veterano();
	}

	public void downgrade() {
		this.statusDoUsuario = new Noob();
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

	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		xp2 += statusDoUsuario.recompensar(jogo);
		xp2+= jogo.registraJogada(scoreObtido, zerou);
	}

	public void punir(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		xp2+= jogo.registraJogada(scoreObtido, zerou);
		xp2 -= statusDoUsuario.punir(jogo);
	}

	public Jogo buscaJogo(String nomeJogo) {
		for (Jogo jogo : meusJogos) {
			if (jogo.getNome().equalsIgnoreCase(nomeJogo)) {
				return jogo;
			}
		}
		return null;
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void addJogo(Jogo jogo) {
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
