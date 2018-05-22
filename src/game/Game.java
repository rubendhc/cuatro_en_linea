package game;


import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Permission;
import javax.sound.sampled.*;
import javax.swing.JFrame;



public class Game extends Applet implements Runnable, MouseListener {

	// ----------------------------------------------------------------Atributes----------------------------------------------------
	
	private int b_width_init;
	private int b_width_end;
	private int b_height_init;
	private int b_height_end;
	private Graphics2D g2d;
	private Board main_board; // Representaci�n del tablero de juego
	private Chip chips_array[];
	private int chip_counter = 0;
	private Thread gameloop;
	private BufferedImage backbuffer;
	private AffineTransform identity = new AffineTransform();
	private Player turn;
	private Player player1;
	private Player player2;
	private Dimension screenSize;
	Image image1, image2, red_chip, gold_chip, winp1, winp2, win, playagain,reset,win_chip;
	String filename = "gong.wav";
	AudioInputStream sample;

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Methods++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public void init() {
		screenSize = new Dimension(900,650);
		b_width_init = 100;
		b_width_end = 800;
		b_height_init = 0;
		b_height_end = 600;
		backbuffer = new BufferedImage(screenSize.width, screenSize.height,
		BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		Toolkit tk = Toolkit.getDefaultToolkit();
		image1 = tk.getImage(getURL("/images/p1.png"));
		image2 = tk.getImage(getURL("images/p2.png"));
		red_chip = tk.getImage(getURL("images/red.png"));
		gold_chip = tk.getImage(getURL("images/gold.png"));
		winp1 = tk.getImage(getURL("images/winp1.png"));
		winp2 = tk.getImage(getURL("images/winp2.png"));
		playagain = tk.getImage(getURL("images/playagain.png"));
		reset = tk.getImage(getURL("images/reset.png"));
		win_chip = tk.getImage(getURL("images/win.png"));
		chip_counter=0;
		AffineTransform identity = new AffineTransform();
		
		
		// Inicializaci�n del tablero
		main_board = new Board(6, 7, 4);
		

		// Inicilizaci�n de las fichas
		chips_array = new Chip[42];

		//Inicializaci�n de players
		player1 = new Player("Player1",1);
		player2 = new Player("Player2",2);
		
		
		setSize(screenSize.width, screenSize.height);
		turn = player1;
		
		
				
				
		addMouseListener(this);
		
		//play_sound();
		

	}

//::::::::::::::::::::::::::::::::::::::::::::::::::::::Zona de Dibujo:::::::::::::::::::::::::::::::::::::::::::::::::::::: 
	public void paint(Graphics g) {
		// Nombre draw_lines
		// Prop�sito: Dibujar toda la pantalla del juego
		// Datos de entrada Objeto de tipo Graphics g2d
		// Salida: vac�o
		// Tama�o pantalla 700 x 600 (100 por cada fila y columna)
		super.paint(g);

		g.drawImage(backbuffer, 0, 0, this);

		g2d.setTransform(identity);

		g2d.setPaint(Color.gray);
		g2d.fillRect(0, 0, screenSize.width, screenSize.height);
		
		g2d.setPaint(Color.black);
		g2d.fillRect(b_width_init, b_height_init, b_width_end-100, b_height_end);

		draw_lines();
		draw_chips();
		draw_info();
		draw_buttons();
		draw_win();
		
		
		
	}

	public void draw_lines() {
		// Nombre draw_lines
		// Prop�sito: Dibujar las l�neas del tablero
		// Datos de entrada Objeto de tipo Graphics g2d
		// Salida: vac�o
		// Tamaño pantalla 700 x 600 (100 por cada fila y columna)
		
		//L�neas horizontales
		for (int i = 0; i <= b_height_end; i += 100) {
			g2d.setTransform(identity);
			g2d.setColor(Color.WHITE);
			g2d.drawLine(b_width_init, i, b_width_end, i);
			
		}
		//L�neas verticales
		for (int i = 0; i <= b_width_end; i += 100) {
			g2d.setTransform(identity);
			g2d.setColor(Color.WHITE);
			g2d.drawLine(i, b_height_init, i, b_height_end);
		}
	}
	
	public void draw_info(){
		g2d.setColor(Color.ORANGE);
		g2d.drawImage(image1,25,10,this);
		g2d.drawImage(image2,825,10,this);
		g2d.drawString("Turno "+ turn.getName(), 400, 620);
		
		//Tablero izquierdo playe1
		g2d.drawString("Victorias", 25, 116);
		g2d.drawRect(10, 120, 80, 40);
		g2d.drawString(""+player1.getN_wins(), 45, 145);
		
		//Tablero derecho playe2
		g2d.drawString("Victorias", 825, 116);
		g2d.drawRect(810, 120, 80, 40);
		g2d.drawString(""+player2.getN_wins(), 845, 145);
	}
	
	public void draw_buttons(){
		g2d.drawImage(playagain,100,600,this);
		g2d.drawImage(reset,600,600,this);
	}

	public void draw_chips() {
		// Nombre draw_chips
		// Prop�sito: Dibujar las fichas puestas en el tablero
		// Datos de entrada: Arreglo de coordenadas de los puntos
		// Salida: vac�o
		if (chip_counter > 0) {
			for (int i = 0; i < chip_counter; i++) {
				g2d.setTransform(identity);
				// g2d.translate(array_points[i].x, array_points[i].y);
				/*
				g2d.setColor(chips_array[i].get_color());
				g2d.fillOval(chips_array[i].get_coor_x()*100, chips_array[i].get_coor_y()*100, 100, 100);
				
				g2d.setColor(Color.BLACK);
				g2d.drawOval(chips_array[i].get_coor_x()*100+10, chips_array[i].get_coor_y()*100+10, 80, 80);
				*/
				g2d.drawImage(chips_array[i].getImage(),chips_array[i].get_coor_x()*100, chips_array[i].get_coor_y()*100,this);
			}
		}

	}
	
	public void draw_win(){
		if(player1.isWin()){
			for(int i = 0; i < 4; i++ ){
				g2d.drawImage(win_chip,(main_board.get_win_points()[i].x+1)*100,main_board.get_win_points()[i].y*100,this);
			}
			g2d.drawImage(winp1,250,200,this);
			
		}else if(player2.isWin()){
			for(int i = 0; i < 4; i++ ){
				g2d.drawImage(win_chip,(main_board.get_win_points()[i].x+1)*100,main_board.get_win_points()[i].y*100,this);
			}
			g2d.drawImage(winp2,250,200,this);
		}
	}
	
	public void update(Graphics g) {
		// Sobrescribir el m�todo update

		paint(g);

	}

//::::::::::::::::::::::::::::::::::::::::::::::::::::::L�gica de juego::::::::::::::::::::::::::::::::::::::::::::::::::::::

	public void insert_new_point(Point p) {
		// Nombre insert_new_point
		// Prop�sito: Ingresa un objeto de tipo punto en el array de puntos
		// Datos de entrada: objeto tipo point
		// Salida: vac�o
		
	int aux_turn = 0;
	int x = p.x / 100;
	int column = x-1;
	
	
	if (chip_counter < 42 && !(player1.isWin() || player2.isWin() )) {//Valida que existan espacios en el tablero
		
		if(validate_pos(p)){//Valida que se presione click dentro del tablero
			
			if(main_board.pos_empty(column)){//Valida que existan posiciones disponible en el tablero
				
				int y = main_board.get_row_free_pos(column);
				int row = y;
				main_board.insert_chip(column, turn.getNumber());
				
				if (turn == player1) {
					
					Chip chip = new Chip(x, y, red_chip);
					chips_array[chip_counter] = chip;
					chip_counter++;
					aux_turn = 3;
					//System.out.println("Player1 = " + main_board.detect_four_in_a_row(row, column, turn));
					player1.setWin(main_board.detect_four_in_a_row(row, column, turn.getNumber()));
					
				} else if (turn == player2) {
						
						Chip chip = new Chip(x, y, gold_chip);
						chips_array[chip_counter] = chip;
						chip_counter++;
						aux_turn = 4;
						//System.out.println("Player2 = " + main_board.detect_four_in_a_row(row, column, turn));
						player2.setWin(main_board.detect_four_in_a_row(row, column, turn.getNumber()));
				}

				if (aux_turn == 3) {// Sedo turno a player2
					turn = player2;
				}else if(aux_turn == 4){//Sedo turno a player1
					turn = player1;
				}
				
				
				winner();
				
			}
		}
		
	}
		
}
	

	public void winner(){
		
		if(player1.isWin()){	
			player1.inc_wins();
			//stop();
			
		}else if(player2.isWin()){
			player2.inc_wins();
			//stop();
		}
		
	}
	
	
	
	
	
	public void restart_game(Point p){
		
		if(p.x > 100 && p.x < 300 && p.y > 600 && p.y < 650){
			main_board = new Board(6, 7, 4);
			chips_array = new Chip[42];
			chip_counter = 0;
			player1.setWin(false);
			player2.setWin(false);
			//start();
		}
		
	}
	
	public void reset_game(Point p){
		if(p.x > 600 && p.x < 800 && p.y > 600 && p.y < 650){
			main_board = new Board(6, 7, 4);
			chips_array = new Chip[42];
			chip_counter = 0;
			player1.setWin(false);
			player2.setWin(false);
			player1.setN_wins(0);
			player2.setN_wins(0);
		}
	}
	
	public boolean validate_pos(Point p){
		int x = p.x;
		int y = p.y;
		
		boolean validate = (x >= b_width_init && x <= b_width_end) && (y >= b_height_init && y <= b_height_end);
		System.out.print(validate);
		return validate;
	}

	
	
	

//::::::::::::::::::::::::::::::::::::::::::::::::::::::Hilos ::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	public void start() {
		// Inicilizaci�n del hilo
		gameloop = new Thread(this);
		gameloop.start();
	}

	public void run() {
		// Se obtiene el hilo actual;
		Thread t = Thread.currentThread();

		while (t == gameloop) {
			try {

				game_update();
				Thread.sleep(20);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			repaint();
		}

	}

	public void stop() {
		gameloop = null;
		
	}

	private void game_update() {
		
	}

	public void update_chip() {
		
		

	}
	
	
//::::::::::::::::::::::::::::::::::::::::::::::::::::::Mouse listener::::::::::::::::::::::::::::::::::::::::::::::::::::::

	void game_mouse_event(Point p){
		insert_new_point(p);
		restart_game(p);
		reset_game(p);
		//fin(p);
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		game_mouse_event(e.getPoint());
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
//::::::::::::::::::::::::::::::::::::::::::::::::::::::M�todos auxiliares::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	private URL getURL(String filename) {
		URL url = null;
		try {
	    
		url = getClass().getResource(filename);
		}
		catch (Exception e) { }
		return url;
		}
	
	
	public void play_sound(){
		try {
			sample = AudioSystem.getAudioInputStream(getURL(filename));
			//create a sound buffer
			Clip clip = AudioSystem.getClip();
			//load the audio file
			clip.open(sample);
			//play sample
			clip.start();
			
			while(clip.isRunning()){
				Thread.sleep(100);
			}
			
			} catch (MalformedURLException e) {
			} catch (IOException e) {} catch (LineUnavailableException e) {
			} catch (UnsupportedAudioFileException e) {
			} catch (Exception e) { }
	}

	 	
}
