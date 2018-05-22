package test;
import game.Player;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	Player _player;
	
	public PlayerTest(){
		super();
	}

	@Before
	public void setUp() throws Exception {
		_player = new Player("player 1", 1);
	}
	

	@After
	public void tearDown() throws Exception {
		_player = null;
	}


	@Test
	public void testIsWin() {
		boolean win = true;
		_player.setWin(win);
		
		assertTrue("Should be true", _player.isWin());
	}


	@Test
	public void testInc_wins() {
		_player.inc_wins();
		
		assertSame("Should be same", _player.getN_wins(), 1);
	}

}
