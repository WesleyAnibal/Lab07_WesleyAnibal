package loja;

import excecoes.StringInvalidaException;

public class LojaFacade {
	private LojaController controller;

	public LojaFacade() {
		this.controller = new LojaController();
	}

	public void criaUsuario(String nome, String login,String tipo) throws StringInvalidaException {
		controller.criaUsuario(nome, login,tipo);
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {
		controller.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
	}

	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		controller.registraJogada(login, nomeJogo, score, venceu);
	}

	public void adicionaCredito(String login, double credito) throws Exception {
		controller.adicionaCredito(login, credito);
	}

	public void upgrade(String login) throws Exception {
		controller.upgrade(login);
	}

	public int getX2P(String login) throws Exception {
		return controller.getX2p(login);
	}
}
