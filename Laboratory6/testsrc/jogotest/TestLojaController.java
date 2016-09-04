package jogotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excecoes.BuscaInvalidaException;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.TrocaInvalidoException;
import excecoes.ValorInvalidoException;
import loja.LojaController;
import usuario.Noob;
import usuario.Veterano;

public class TestLojaController {
	private LojaController loja;
	@Before
	public void test(){
		this.loja = new LojaController();
	}
	@Test
	public void createAndOperations() throws StringInvalidaException, BuscaInvalidaException, PrecoInvalidoException{
		loja.criaUsuario("Paul Gilbert", "paul.gilbert");
		loja.adicionaCredito("paul.gilbert", 150);
		assertEquals(150, loja.confereCredito("paul.gilbert"),0);
		loja.vendeJogo("Crash Bandicoot", 10, "Offline cooperativo", "RPG", "paul.gilbert");
		assertEquals(141.0, loja.confereCredito("paul.gilbert"),0);
	}
	@Test
	public void punirandRecompensar() throws StringInvalidaException, ValorInvalidoException, PrecoInvalidoException, BuscaInvalidaException, TrocaInvalidoException{
		loja.criaUsuario("Stratocaster", "stratocaster.guitar");
		loja.adicionaCredito("stratocaster.guitar", 1000);
		loja.vendeJogo("please sorry", 100, "Offline competitivo", "plataforma", "stratocaster.guitar");
		loja.recompensar("stratocaster.guitar", "please sorry", 1000, false);
		assertEquals(1030, loja.getX2p("stratocaster.guitar"));
		loja.punir("stratocaster.guitar", "please sorry", 1010, false);
		assertEquals(1010,loja.getX2p("stratocaster.guitar"));
		System.out.println(loja.informacaoUsuarios());
	}
	@Test
	public void upgradeAndDowngrade() throws ValorInvalidoException, StringInvalidaException, PrecoInvalidoException, BuscaInvalidaException, TrocaInvalidoException{
		loja.criaUsuario("Stratocaster", "stratocaster.guitar");
		loja.adicionaCredito("stratocaster.guitar", 1000);
		assertTrue(loja.buscaUsuario("stratocaster.guitar").getStatusDoUsuario() instanceof Noob);
		loja.vendeJogo("please sorry", 100, "Offline competitivo", "plataforma", "stratocaster.guitar");
		loja.upgrade("stratocaster.guitar");
		assertTrue(loja.buscaUsuario("stratocaster.guitar").getStatusDoUsuario() instanceof Veterano);
		loja.downgrade("stratocaster.guitar");
		assertTrue(loja.buscaUsuario("stratocaster.guitar").getStatusDoUsuario() instanceof Noob);
	}
	@Test
	public void testToString(){
	}
}
	
