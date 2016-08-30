package loja;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.Rpg;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

public class FactoryJogo {
	private HashMap<String, Jogabilidade> mapJogabildades;

	public Jogo criaJogo(String jogoNome, double preco, String tiposJogabilidades, String estiloJogo)
			throws StringInvalidaException, PrecoInvalidoException {
		Set<Jogabilidade> jogabilidades = createJogabilidades(tiposJogabilidades);
		String estilo = estiloJogo.toLowerCase();
		if (estilo.equals("rpg")) {
			return criaRPG(jogoNome, preco, jogabilidades);
		} else if (estilo.equals("plataforma")) {
			return criaPlataforma(jogoNome, preco, jogabilidades);
		} else if (estilo.equals("luta")) {
			return criaLuta(jogoNome, preco, jogabilidades);
		} else {
			return null;
		}
	}

	private Jogo criaRPG(String nome, double preco, Set<Jogabilidade> tiposJogabilidade)
			throws StringInvalidaException, PrecoInvalidoException {
		return new Rpg(nome, preco);
	}

	private Jogo criaPlataforma(String nome, double preco, Set<Jogabilidade> tiposJogabilidade)
			throws StringInvalidaException, PrecoInvalidoException {
		return new Plataforma(nome, preco);
	}

	private Jogo criaLuta(String nome, double preco, Set<Jogabilidade> tiposJogabilidade)
			throws StringInvalidaException, PrecoInvalidoException {
		return new Luta(nome, preco);
	}

	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}

	private Set<Jogabilidade> createJogabilidades(String names1) {
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(",");

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();
			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabildades.get(element);
				jogabilidades.add(tipojogabilidade);
			}
		}

		return jogabilidades;

	}
}
