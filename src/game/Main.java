package game;
import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 
		    //Crear un objeto f de la clase JFrame 
		    JFrame f = new JFrame("Test Applet/Aplicación"); 

		    //Crear una instancia de TestApplet 
		    Game game = new Game(); 

		    //A�adir la instancia del applet al marco 
		    f.getContentPane().setLayout(new BorderLayout()); 
		    f.getContentPane().add("Center", game); 

		    //Inicializar las variables al ancho y el alto de la tag <applet> 
		    int width = 900; 
		    int height = 700; 
		    f.setSize(width, height); 

		    //Llamar a init() y a start() si es necesario 
		    game.init(); 
		    game.start(); 
		    //game.play_sound();
		    //Hacer visible el marco 
		    f.setVisible(true); 
		    
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  


	}

}
