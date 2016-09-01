package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.Rpg;
import usuario.Noob;
import usuario.Usuario;
import usuario.Veterano;

public class LojaController {
	private List<Usuario> meusUsuarios;
	private FactoryJogo criaJogo;
	private HashMap<String, Jogabilidade> mapJogabildades;


	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.criaJogo = new FactoryJogo();
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {

		try {
			Usuario buscado = this.buscaUsuario(loginUser);
			buscado.compraJogo(criaJogo.criaJogo(jogoNome, preco, jogabilidades, estiloJogo));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void adicionaCredito(String login, double credito) throws Exception {
		Usuario user = this.buscaUsuario(login);
		user.setCredito(user.getCredito() + credito);
	}

	public Usuario buscaUsuario(String login) throws Exception {
		for(Usuario usuario : meusUsuarios){
			if(usuario.getLogin().equalsIgnoreCase(login)){
				return usuario;
			}
		}throw new Exception("");
	}

	public void criaUsuario(String nome, String login, String tipo) throws StringInvalidaException {
		Usuario usuario = new Usuario(nome, login);
		meusUsuarios.add(usuario);
	}

	public void upgrade(String login) throws Exception {
		Usuario antigo = buscaUsuario(login);
		if (antigo.getStatusDoUsuario() instanceof Veterano) {
			throw new UpgradeInvalidoException("Upgrade impossivel de ser realizado, usuario ja eh veterano");
		}
		antigo.upgrade();
	}

	public double confereCredito(String login) throws Exception {
		Usuario procurado = buscaUsuario(login);
		return procurado.getCredito();
	}

	public String informacaoUsuarios() {
		final String FIM_DE_LINHA = System.lineSeparator();
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
		}
		return myString;
	}

	public int getX2p(String login) throws Exception {
		Usuario buscado = this.buscaUsuario(login);
		if(buscado == null){
			throw new Exception("");
		}
		return buscado.getXp2();
	}
	public void punir(String login, String nome, int score, boolean zerou) throws Exception{
		Usuario usuario = buscaUsuario(login);
		usuario.punir(nome, score, zerou);
	}
	public void recompensar(String login, String nome, int score, boolean zerou) throws Exception{
		Usuario usuario = buscaUsuario(login);
		usuario.recompensar(nome, score, zerou);
	}

	public static void main(String[] args) {
		args = new String[] { "loja.LojaController", "acceptance_test/us1.txt", "acceptance_test/us2.txt",
				"acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}

}
