package loja;

public class LojaFacade {
	private LojaController controller;

	public LojaFacade() {
		this.controller = new LojaController();
	}

	public void adicionaUsuario(String nome, String login) {
		controller.adicionaUsuario(nome, login);
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {
		controller.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
	}

	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		controller.registraJogada(login, nomeJogo, score, venceu);
	}

	public void adicionaCredito(String login, double credito) {
		controller.adicionaCredito(login, credito);
	}

	public void upgrade(String login) throws Exception {
		controller.upgrade(login);
	}

	public int getX2P(String login) {
		return controller.getX2p(login);
	}
}
