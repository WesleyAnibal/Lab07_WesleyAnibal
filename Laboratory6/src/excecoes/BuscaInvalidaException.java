package excecoes;

public class BuscaInvalidaException extends Exception{
	public BuscaInvalidaException(){
		super("Busca Invalida");
	}
	public BuscaInvalidaException(String msg){
		super(msg);
	}
}
