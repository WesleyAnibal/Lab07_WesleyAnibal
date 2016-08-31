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
	private FactoryDeUsuario criaUsuario;
	private FactoryJogo criaJogo;
	private HashMap<String, Jogabilidade> mapJogabildades;


	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.criaUsuario = new FactoryDeUsuario();
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

	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		try {
			Usuario usr = this.buscaUsuario(login);
			usr.registradaJogada(nomeJogo, score, venceu);
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
		Usuario usuario = criaUsuario.criaUsuario(nome, login, tipo);
		meusUsuarios.add(usuario);
	}

	public void upgrade(String login) throws Exception {
		Usuario antigo = buscaUsuario(login);
		if (antigo instanceof Veterano) {
			throw new UpgradeInvalidoException("Upgrade impossivel de ser realizado, usuario ja eh veterano");
		} else if (antigo.getXp2() < 1000) {
			throw new UpgradeInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
		}
		Usuario novo = new Veterano(antigo.getNome(), antigo.getLogin());
		novo.setCredito(antigo.getCredito());
		novo.setXp2(antigo.getXp2());
		novo.setMeusJogos(antigo.getMeusJogos());
		meusUsuarios.remove(antigo);
		meusUsuarios.add(novo);
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
		registraJogada(login, nome, score, zerou);
		usuario.punir(nome, score, zerou);
	}
	public void recompensar(String login, String nome, int score, boolean zerou) throws Exception{
		Usuario usuario = buscaUsuario(login);
		registraJogada(login, nome, score, zerou);
		usuario.recompensar(nome, score, zerou);
	}

	public static void main(String[] args) {
		args = new String[] { "loja.LojaController", "acceptance_test/us1.txt", "acceptance_test/us2.txt",
				"acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}

}
