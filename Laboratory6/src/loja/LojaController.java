package loja;

import java.util.ArrayList;
import java.util.List;
import excecoes.BuscaInvalidaException;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.TrocaInvalidoException;
import excecoes.ValorInvalidoException;
import usuario.Usuario;

public class LojaController {
	private List<Usuario> meusUsuarios;
	private FactoryJogo criaJogo;
	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.criaJogo = new FactoryJogo();
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) throws StringInvalidaException,PrecoInvalidoException,ValorInvalidoException, BuscaInvalidaException {
			Usuario buscado  = this.buscaUsuario(loginUser);
			buscado.compraJogo(criaJogo.criaJogo(jogoNome, preco, jogabilidades, estiloJogo));
	}

	public void adicionaCredito(String login, double credito) throws BuscaInvalidaException, ValorInvalidoException {
		Usuario user = this.buscaUsuario(login);
		user.setCredito(user.getCredito() + credito);
	}

	public Usuario buscaUsuario(String login) throws BuscaInvalidaException{
		Usuario procurado = null;
		for(Usuario usuario : meusUsuarios){
			if(usuario.getLogin().equalsIgnoreCase(login)){
				procurado = usuario;
			}
		}if(procurado == null){
			throw new BuscaInvalidaException("Usuario não encontrado.");
		}else{
			return procurado;
		}
	}

	public void criaUsuario(String nome, String login) throws StringInvalidaException {
		Usuario usuario = new Usuario(nome, login);
		meusUsuarios.add(usuario);
	}

	public void upgrade(String login) throws BuscaInvalidaException, TrocaInvalidoException  {
		Usuario antigo = buscaUsuario(login);
		antigo.upgrade();
	}
	public void downgrade(String login) throws BuscaInvalidaException, TrocaInvalidoException{
		Usuario antigo = buscaUsuario(login);
		antigo.downgrade();
	}

	public double confereCredito(String login) throws BuscaInvalidaException {
		Usuario procurado = buscaUsuario(login);
		return procurado.getCredito();
	}

	public String informacaoUsuarios() {
		final String FIM_DE_LINHA = System.lineSeparator();
		float totalPreco = 0;
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
			totalPreco += meusUsuarios.get(i).calculaPrecoTotal();
		}
		myString += String
				.format("\nTotal de preço dos jogos: R$ %.2f\n\n--------------------------------------------",
totalPreco);
		return myString;
	}

	public int getX2p(String login) throws BuscaInvalidaException  {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getXp2();
	}
	//chamada polimorfica.
	public void punir(String login, String nome, int score, boolean zerou) throws BuscaInvalidaException, ValorInvalidoException {
		Usuario usuario = buscaUsuario(login);
		usuario.punir(nome, score, zerou);
	}
	//chamada polimorfica.
	public void recompensar(String login, String nome, int score, boolean zerou) throws ValorInvalidoException, BuscaInvalidaException {
		Usuario usuario = buscaUsuario(login);
		usuario.recompensar(nome, score, zerou);
	}

}
