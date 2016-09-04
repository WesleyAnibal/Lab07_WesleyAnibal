package jogotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jogo.*;

import usuario.Usuario;


public class JogoTest {
	private Jogo luta;
	private Jogo plataforma;
	private Jogo rpg;
	@Before
	public void test() throws Exception{
		this.luta = new Luta("The king of figthers", 50);
		this.plataforma = new Plataforma("Crash bandicoot", 100);
		this.rpg = new Rpg("war craft", 150);
	}
	@Test
	public void testScore() throws Exception{
		assertEquals(0, luta.getMaiorScore());
		luta.registraJogada(10, false);
		luta.registraJogada(9, false);
		assertEquals(10, luta.getMaiorScore());
	}
	@Test
	public void testJogadas() throws Exception{
		assertEquals(0, rpg.getVezesJogadas());
		for(int i = 0; i < 5; i++){
			rpg.registraJogada(5000, false);
		}
		assertEquals(5, rpg.getVezesJogadas());
		rpg.registraJogada(4999, true);
		assertEquals(6, rpg.getVezesJogadas());
		assertEquals(1, rpg.getvezesConcluidas());
		assertEquals(5000, rpg.getMaiorScore());
	}
	@Test
	public void testException() throws Exception{
		try{
			Jogo invalido = new Rpg("", 100);
			fail();
		}catch(Exception e){
			assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
		try{
			Jogo invalido = new Plataforma(null, 12);
			fail();
		}catch(Exception e){
			assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
		try{
			Jogo invalido = new Luta("TKoF", -12);
			fail();
		}catch(Exception e){
			assertEquals("Preco nao pode ser negativo", e.getMessage());
		}
	}
}