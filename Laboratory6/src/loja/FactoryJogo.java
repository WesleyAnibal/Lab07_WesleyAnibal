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
	public Jogo criaJogo(String jogoNome, double preco, Set<Jogabilidade> tiposJogabilidades, String estiloJogo)
			throws StringInvalidaException, PrecoInvalidoException {

		String estilo = estiloJogo.toLowerCase();
		if (estilo.equals("rpg")) {
			return new Rpg(jogoNome, preco, tiposJogabilidades);
		} else if (estilo.equals("plataforma")) {
			return new Plataforma(jogoNome, preco, tiposJogabilidades);
		} else if (estilo.equals("luta")) {
			return new Luta(jogoNome, preco, tiposJogabilidades);
		} else {
			return null;
		}
	}
	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}
	
	public Set<Jogabilidade> createJogabilidades(String names1) {
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
