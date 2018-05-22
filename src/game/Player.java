package game;

public class Player {
	
	private String name;
	private int number;
	private int n_wins = 0;
	private boolean win = false;
	
	public Player(String name,int number){
		this.name = name;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getN_wins() {
		return n_wins;
	}
	public void setN_wins(int n_wins) {
		this.n_wins = n_wins;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}
	
	public void inc_wins(){
		this.n_wins++;
	}
	
	
	
}
