package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Point;

import game.Board;

public class BoardTest {
	
	private Board _board;
	private Point _point;
	
	public BoardTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		_board = new Board(6,7,4);
		_point = new Point();
	}

	@After
	public void tearDown() throws Exception {
		_board = null;
		_point = null;
	}


	@Test
	public void testSet_win_player() {
		
		int win_player = 1;
		boolean answer = true;
		_board.set_win_player(win_player, answer);
		
		assertSame("Should be same", win_player, _board.get_win_player());
		//System.out.print(_board.get_win_player());
	}

	@Test
	public void testInsert_chip() {
		int c= 0;
		int player = 1;
		_board.insert_chip(c, player);
		assertSame("Should be same", player, _board.get_value_i_j(5, c));
	}

	@Test
	public void testPos_empty() {
		int c = 0;
		assertTrue("Should be true", _board.pos_empty(c));
	}

	@Test
	public void testGet_row_free_pos() {
		int c= 0;
		int free_column = 5;
		assertSame("Should be same", free_column, _board.get_row_free_pos(c));
		
	}


	@Test
	public void testGetN_row() {
		int cant_row = 6;
		assertSame("Should be same", cant_row, _board.getN_row());
	}


	@Test
	public void testGetN_column() {
		int cant_column = 7;
		assertSame("Should be same", cant_column, _board.getN_column());
	}

	@Test
	public void testSetN_column() {
		
	}

	@Test
	public void testDetect_four_in_a_row() {
		int c  = 0;
		int r = 5;
		int player = 1;
		
		for (int i = 0; i < 4; i++) {
			_board.insert_chip(i, player);
		}
		
		//_board.print_matriz();
		
		assertTrue("Should be true", _board.detect_four_in_a_row(r, c, player));
	}

	@Test
	public void testGet_value_i_j() {
		int c  = 0;
		int r = 5;
		int player = 1;
		
		
		_board.insert_chip(c, player);
		
		
		assertSame("Should be same",player, _board.get_value_i_j(r, c));
	}


	@Test
	public void testV_east() {
		int c  = 0;
		int r = 5;
		int player = 1;
		
		for (int i = 0; i < 4; i++) {
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_east(r, c, player));
	}

	@Test
	public void testV_southeast() {
		int c  = 0;
		int r = 0;
		int player = 1;
		
		for (int i = 0; i < 7; i++) {
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_southeast(r, c, player));
	}

	@Test
	public void testV_south() {
		int c  = 0;
		int r = 0;
		int player = 1;
		
		for (int i = 0; i < 7; i++) {
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_south(r, c, player));
	}

	@Test
	public void testV_southwest() {
		int c  = 6;
		int r = 0;
		int player = 1;
		
		for (int i = 0; i < 7; i++) {
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_southwest(r, c, player));
	}

	@Test
	public void testV_west() {
		int c  = 6;
		int r = 5;
		int player = 1;
		
		for (int i = 0; i < 7; i++) {
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_west(r, c, player));
	}

	@Test
	public void testV_northwest() {
		int c  = 6;
		int r = 5;
		int player = 1;
		
		for (int i = 0; i < 7; i++) {
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_northwest(r, c, player));
	}

	@Test
	public void testV_north() {
		int c  = 0;
		int r = 5;
		int player = 1;
		
		for (int i = 0; i < 7; i++) {
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_north(r, c, player));
	}

	@Test
	public void testV_northeast() {
		int c  = 3;
		int r = 5;
		int player = 1;
		
		for (int i = 0; i < 7; i++) {
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
			_board.insert_chip(i, player);
		}
		
		assertTrue("Should be true", _board.v_northeast(r, c, player));
	}



}
