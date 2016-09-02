package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.BuscaInvalidaException;
import excecoes.StringInvalidaException;
import excecoes.TrocaInvalidoException;
import excecoes.ValorInvalidoException;
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



	public void compraJogo(Jogo jogo) throws ValorInvalidoException {
		if(this.credito < jogo.getPreco()){
			throw new ValorInvalidoException("Dinheiro insuficiente.");
		}
		xp2 += statusDoUsuario.getX2p(jogo);
		this.credito -= statusDoUsuario.compraJogo(jogo);
		meusJogos.add(jogo);
	}

	public void upgrade() throws TrocaInvalidoException {
	if (xp2 < 1000) {
		throw new TrocaInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
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

	public void setNome(String nome) throws StringInvalidaException {
		if(nome == null || nome.trim().isEmpty()){
			throw new StringInvalidaException("Novo nome não pode ser null ou vazia.");
		}
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws StringInvalidaException {
		if(login == null || login.trim().isEmpty()){
			throw new StringInvalidaException("Novo login não pode ser null ou vazio.");
		}
		this.login = login;
	}

	public void setCredito(double novoValor) throws ValorInvalidoException {
		if(novoValor < 0){
			throw new ValorInvalidoException("Novo Valor não pode ser menor que zero.");
		}
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) throws BuscaInvalidaException, ValorInvalidoException {
		Jogo jogo = buscaJogo(nomeJogo);
		xp2 += statusDoUsuario.recompensar(jogo);
		xp2+= jogo.registraJogada(scoreObtido, zerou);
	}

	public void punir(String nomeJogo, int scoreObtido, boolean zerou) throws BuscaInvalidaException, ValorInvalidoException {
		Jogo jogo = buscaJogo(nomeJogo);
		xp2+= jogo.registraJogada(scoreObtido, zerou);
		xp2 -= statusDoUsuario.punir(jogo);
	}

	public Jogo buscaJogo(String nomeJogo) throws BuscaInvalidaException {
		for (Jogo jogo : meusJogos) {
			if (jogo.getNome().equalsIgnoreCase(nomeJogo)) {
				return jogo;
			}
		}
		throw new BuscaInvalidaException("Jogo não encontrado");
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
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
	public String toString(){
		String myString = statusDoUsuario.toString()+getLogin()+"\n";
		myString+= getNome()+ " - "+ xp2+" x2p";
		myString += "Lista de Jogos:\n";
		for(Jogo jogo : meusJogos){
			myString+= "+ "+ jogo.toString()+"\n";
		}
		return myString;
	}
}
