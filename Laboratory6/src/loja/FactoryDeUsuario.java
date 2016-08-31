package loja;

import excecoes.StringInvalidaException;
import usuario.Noob;
import usuario.Usuario;

public class FactoryDeUsuario {
	public Usuario criaUsuario(String nome, String login,String tipo) throws StringInvalidaException{
		Usuario usuario = new Noob(nome, login);
		return usuario;
	}
}
