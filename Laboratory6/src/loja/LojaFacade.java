package loja;

import easyaccept.EasyAccept;
import excecoes.BuscaInvalidaException;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.TrocaInvalidoException;
import excecoes.ValorInvalidoException;

public class LojaFacade {
	private LojaController controller;

	public LojaFacade() {
		this.controller = new LojaController();
	}

	public void criaUsuario(String nome, String login,String tipo) throws Exception,StringInvalidaException {
		try{
			controller.criaUsuario(nome, login);
		}catch(StringInvalidaException ex){			System.out.println(ex.getMessage());
			throw new StringInvalidaException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		controller.criaUsuario(nome, login);
	}
	public double confereCredito(String login) throws Exception{
		return controller.confereCredito(login);
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) throws ValorInvalidoException, StringInvalidaException, PrecoInvalidoException, BuscaInvalidaException, Exception {
		try{
			controller.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
		}catch(StringInvalidaException ex){
			throw new StringInvalidaException(ex.getMessage());
		}catch(ValorInvalidoException ex){
			throw new ValorInvalidoException(ex.getMessage());
		}catch(PrecoInvalidoException ex){
			throw new PrecoInvalidoException(ex.getMessage());
		}catch(BuscaInvalidaException ex){
			throw new BuscaInvalidaException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		
	}


	public void adicionaCredito(String login, double credito) throws Exception,ValorInvalidoException, BuscaInvalidaException  {
		try{
			controller.adicionaCredito(login, credito);
		}catch(ValorInvalidoException ex){
			throw new ValorInvalidoException(ex.getMessage());
		}catch(BuscaInvalidaException ex){
			throw new BuscaInvalidaException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}

	public void upgrade(String login) throws Exception,BuscaInvalidaException, TrocaInvalidoException  {
		try{
			controller.upgrade(login);
		}catch(BuscaInvalidaException ex){
			throw new BuscaInvalidaException(ex.getMessage());
		}catch(TrocaInvalidoException ex){
			throw new TrocaInvalidoException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	public void downgrade(String login) throws Exception, BuscaInvalidaException, TrocaInvalidoException{
		try{
			controller.downgrade(login);
		}catch(BuscaInvalidaException ex){
			throw new BuscaInvalidaException(ex.getMessage());
		}catch(TrocaInvalidoException ex){
			throw new TrocaInvalidoException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}

	public int getX2p(String login) throws Exception, BuscaInvalidaException{
		try{
			return controller.getX2p(login);
		}catch(BuscaInvalidaException ex){
			throw new BuscaInvalidaException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	public void punir(String login, String nome, int score, boolean zerou) throws Exception, ValorInvalidoException, BuscaInvalidaException{
		try{
			controller.punir(login, nome, score, zerou);
		}catch(ValorInvalidoException ex){
			throw new ValorInvalidoException(ex.getMessage());
		}catch(BuscaInvalidaException ex){
			throw new BuscaInvalidaException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	public void recompensar(String login, String nome, int score, boolean zerou) throws Exception, ValorInvalidoException, BuscaInvalidaException{
		try{
			controller.recompensar(login, nome, score, zerou);
		}catch(BuscaInvalidaException ex){
			throw new BuscaInvalidaException(ex.getMessage());
		}catch(ValorInvalidoException ex){
			throw new ValorInvalidoException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	public String informacaoUsuarios(){
		return controller.informacaoUsuarios();
	}
	
	public static void main(String[] args) {
		args = new String[] { "loja.LojaFacade", "acceptance_test/us1.txt", "acceptance_test/us2.txt",
				"acceptance_test/us3.txt" };
		System.out.println();
		EasyAccept.main(args);
	}
}
