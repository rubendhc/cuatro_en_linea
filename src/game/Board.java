package game;
import java.awt.Point;



public class Board {
	
	private int[][] matriz;
	private int[] four_line;//Arreglo donde se almacenan las coordenadas de las fichas que forman 4 en l�nea
	private int[] stack_flags; //Arreglo donde se almacena las posici�n disponible en cada columna
	private int win_player=0;
	private int n_row=0;
	private int n_column=0;
	private Point last_point;
	private Point first_point;
	private Point win_point[];


	public Board(int r,int c, int line) {//r = row; c = column
		this.n_row = r;
		this.n_column = c;
				
		matriz = new int[n_row][n_column];
		four_line = new int[line * 2];
		stack_flags = new int[c];
		win_point = new Point[line];

		for(int i = 0; i < n_row; i++){
			for(int j = 0; j < c; j++){
				matriz[i][j]=0;
				
			}
		}

		for(int i = 0; i < line; i++){
			four_line[i] = 0;
		}

		for (int i = 0; i < n_column ; i++ ) {
			stack_flags[i]=r-1;
		}
		
		
	}

	/**
	 * @return void
	 */
	public void set_matriz() {
		// TODO Auto-generated method stub
	}

	/**
	 * @param heigth
	 * @return void
	 */
	public void set_heigth(int heigth) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param width
	 * @return void
	 */
	public void set_width(int width) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return int[][]
	 */
	/*
	public int[][] get_matriz() {
		// TODO Auto-generated method stub
		
	}
	 */
	
	public void set_win_player(int win_player, boolean answer) {
		// TODO Auto-generated method stub
		if(answer){
			this.win_player = win_player;
		}
		
	}
	
	/**
	 * @return int
	 */
	public int get_heigth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return int
	 */
	public int get_width() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param row
	 * @param chip
	 * @return void
	 */
	public void insert_chip(int c, int player) {//py_chip n�mero que identifica al jugador
		System.out.print(c);
		if(stack_flags[c]>=0){
			matriz[stack_flags[c]][c] = player;
			stack_flags[c] = stack_flags[c]-1; 
			first_point = new Point(stack_flags[c],c);
		}

	}


	public boolean pos_empty(int c){
		return stack_flags[c] >= 0;
	}
	
	public int get_row_free_pos(int c){
		return stack_flags[c];
	}
	
	public int get_win_player() {
		// TODO Auto-generated method stub
		return win_player;
	}
	

	
	

	public int getN_row() {
		return n_row;
	}

	public void setN_row(int n_row) {
		this.n_row = n_row;
	}

	public int getN_column() {
		return n_column;
	}

	public void setN_column(int n_column) {
		this.n_column = n_column;
	}

	
		
	//Validaci�n cuatro en l�nea
	
	public boolean detect_four_in_a_row(int r, int c, int player) {
		// TODO Auto-generated method stub
		boolean answer=false;
		
		win_point[0] = new Point(c,r);
		
		if(v_northeast(r, c, player)){
			answer = v_northeast(r, c, player);
		}else if(v_east(r, c, player)){
			answer = v_east(r, c, player);
		}else if(v_southeast(r, c, player)){
			answer = v_southeast(r, c, player);
		}else if(v_south(r, c, player)){
			answer = v_south(r, c, player);
		}else if(v_southwest(r, c, player)){
			answer = v_southwest(r, c, player);
		}else if(v_west(r, c, player)){
			answer = v_west(r, c, player);
		}else if(v_northwest(r, c, player)){
			answer = v_northwest(r, c, player);
		}else if(v_north(r, c, player)){
			answer = v_north(r, c, player);
		}
		
		
		set_win_player(player, answer);
		
		return answer;
	}

	public int get_value_i_j(int c, int r){
		return matriz[c][r];
	}
	
	
	public void print_matriz(){
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.print("\n");
			
		}
	}
	
	
	public boolean v_east(int r, int c, int player){
		// Nombre v_right (validaci�n derecha)
		// Prop�sito: validar si existen tres n�meros iguales a partir de la posici�n ingresada hacia la derecha
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(c<4){
			for(int i = 0; i < 3; i++){
				if(matriz[r][c+1] == player){
					c+=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean v_southeast(int r, int c, int player){
		// Nombre v_southeast (Validaci�n diagonal inferior derecha)
		// Prop�sito: Valida diagonal inferior derecha existen n�meros iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(c<4 && r < 3){
			for(int i = 0; i < 3; i++){
				if(matriz[r+1][c+1] == player){
					c+=1;
					r+=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean v_south(int r, int c, int player){
		// Nombre v_southeast (Validaci�n diagonal inferior derecha)
		// Prop�sito: Valida diagonal inferior derecha existen números iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(r<3){
			for(int i = 0; i < 3; i++){
				if(matriz[r+1][c] == player){
					r+=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean v_southwest(int r, int c, int player){
		// Nombre v_southeast (Validaci�n diagonal inferior derecha)
		// Prop�sito: Valida diagonal inferior derecha existen n�meros iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(r < 3 && c > 2){
			for(int i = 0; i < 3; i++){
				if(matriz[r+1][c-1] == player){
					c-=1;
					r+=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean v_west(int r, int c, int player){
		// Nombre v_southeast (Validaci�n diagonal inferior derecha)
		// Prop�sito: Valida diagonal inferior derecha existen n�meros iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(c>2){
			for(int i = 0; i < 3; i++){
				if(matriz[r][c-1] == player){
					c-=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean v_northwest(int r, int c, int player){
		// Nombre v_southeast (Validaci�n diagonal inferior derecha)
		// Prop�sito: Valida diagonal inferior derecha existen n�meros iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(r > 2 && c > 2 ){
			for(int i = 0; i < 3; i++){
				if(matriz[r-1][c-1] == player){
					c-=1;
					r-=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean v_north(int r, int c, int player){
		// Nombre v_southeast (Validaci�n diagonal inferior derecha)
		// Prop�sito: Valida diagonal inferior derecha existen n�meros iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(r > 2){
			for(int i = 0; i < 3; i++){
				if(matriz[r-1][c] == player){
					r-=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean v_northeast(int r, int c, int player){
		// Nombre v_southeast (Validaci�n diagonal inferior derecha)
		// Prop�sito: Valida diagonal inferior derecha existen n�meros iguales
		// Datos de entrada: entero r(fila), entero c(column), entero player(Jugador)
		// Salida: booleana;
		int count=0; 
		if(c < 4 && r > 2){
			for(int i = 0; i < 3; i++){
				if(matriz[r-1][c+1] == player){
					r-=1;
					c+=1;
					++count;
					win_point[i+1] = new Point(c,r);
				}
				else {
					return false;
				}		
			}
		}
		return counter(count);
	}
	
	public boolean counter(int n){
		// Nombre count (Contador)
		// Prop�sito: valida si un entero n es igual a 3
		// Datos de entrada: entero n
		// Salida: booleana;
		if(n == 3){
			return true;
		}else {
			return false;
		}
	}
	
	public void fill_line(int i, int r, int c){
		
		four_line[i] = r;
		four_line[i+1] = r;
	}

	public Point getLast_point() {
		return last_point;
	}

	public void setLast_point(Point last_point) {
		this.last_point = last_point;
	}

	public Point getFirst_point() {
		return first_point;
	}

	public void setFirst_point(Point first_point) {
		this.first_point = first_point;
	}
	
	public Point[] get_win_points(){
		return win_point;
	}
	
	public int get_win() {
		return this.win_player;
	}
	
	
	

}
