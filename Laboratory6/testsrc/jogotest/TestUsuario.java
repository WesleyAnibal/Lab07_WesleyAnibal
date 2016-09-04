package jogotest;
import org.junit.Before;
import org.junit.Test;

import excecoes.BuscaInvalidaException;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.TrocaInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Rpg;
import usuario.Noob;
import usuario.Usuario;
import usuario.Veterano;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class TestUsuario {
	private Usuario cheester;
	@Before
	public void test() throws Exception{
		cheester = new Usuario("Wesley Anibal", "wesley.anibal");
		cheester.setCredito(300);
	}
	@Test
	public void testCompra() throws Exception{
		Jogo jogo = new Rpg("wow", 10);
		cheester.compraJogo(jogo);
		assertEquals(291.0, cheester.getCredito(),0);
		assertEquals(100, cheester.getXp2());
		assertEquals(10, cheester.calculaPrecoTotal(),0);
	}
	public void upgradeAndDowngrade() throws TrocaInvalidoException{
		cheester.setXp2(1000);
		assertTrue(cheester.getStatusDoUsuario() instanceof Noob);
		cheester.upgrade();
		assertTrue(cheester.getStatusDoUsuario() instanceof Veterano);
		cheester.setXp2(999);
		cheester.downgrade();
		assertTrue(cheester.getStatusDoUsuario() instanceof Noob);
	}
	public void punirAndRecompensar() throws StringInvalidaException, PrecoInvalidoException, BuscaInvalidaException{
		Set<Jogabilidade> jogabilidades = new HashSet<>();
		jogabilidades.add(Jogabilidade.COMPETITIVO);
		jogabilidades.add(Jogabilidade.OFFLINE);
		Jogo jogo = new Rpg("Mario bros.", 10,jogabilidades);
		cheester.compraJogo(jogo);
		assertEquals(100, cheester.getXp2());
		cheester.recompensar("Mario bros.", 100, false);
		assertEquals(130, cheester.getXp2());
		cheester.punir("Mario bros.", 300, true);
		assertEquals(110, cheester.getXp2());
		
	}
	@Test
	public void testUsuarioComExceptions() throws Exception{
		try{
			Usuario invalido = new Usuario("", "wesley.anibal");
			fail();
		}catch(StringInvalidaException e){
			assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
		try{
			Usuario invalido = new Usuario(null, "George.Lucas");
			fail();
		}catch(StringInvalidaException e){
			assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
		try{
			Usuario invalido = new Usuario("R2D2", "  ");
			fail();
		}catch(StringInvalidaException e){
			assertEquals("Login nao pode ser nulo ou vazio.", e.getMessage());
		}
		try{
			Usuario invalido = new Usuario("C3PO", null);
			fail();
		}catch(StringInvalidaException e){
			assertEquals("Login nao pode ser nulo ou vazio.", e.getMessage());
		}try{
			Usuario invalido = new Usuario("C3PO", "c.3.p.o");
			invalido.upgrade();
			fail();
		}catch(TrocaInvalidoException e){
			assertEquals("Impossivel realizar upgrade, quantidade de x2p insuficiente!", e.getMessage());
		}try{
			Usuario invalido = new Usuario("C3PO", "c.3.p.o");
			invalido.setXp2(1000);
			invalido.upgrade();
			invalido.upgrade();
			fail();
		}catch(TrocaInvalidoException e){
			assertEquals("Impossivel realizar upgrade, usuario ja e veterano!", e.getMessage());
		}try{
			Usuario invalido = new Usuario("C3PO", "c.3.p.o");
			invalido.downgrade();
			fail();
		}catch(TrocaInvalidoException e){
			assertEquals("downgrade impossivel de ser realizado, usuario ja eh Noob!", e.getMessage());
		}
		try{
			Usuario invalido = new Usuario("kurt", "kurt.cobain");
			Jogo jogo = new Rpg("Come as you are", 100);
			invalido.compraJogo(jogo);
			fail();
		}catch(ValorInvalidoException e){
			assertEquals("Dinheiro insuficiente.", e.getMessage());
		}
	}
	
}