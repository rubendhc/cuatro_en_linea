package game;


import java.awt.Color;
import java.awt.Image;


public class Chip {
	private int coor_x;
	private int coor_y;
	private int row;
	private int column;
	private Color color;
	private Image image;

	public Chip(int coor_x,int coor_y, Color color) {
		this.coor_x = coor_x;
		this.coor_y = coor_y;
		this.color = color;
	}
	
	public Chip(int coor_x,int coor_y, Image image) {
		this.coor_x = coor_x;
		this.coor_y = coor_y;
		this.image = image;
	}

	
	public void set_coor_x(int coor_x) {
		this.coor_x = coor_x;
	}
	
	
	public void set_coor_y(int coor_y) {
		this.coor_y = coor_y;
	}

	
	public void set_row(int row) {
		this.row = row;
	}

	
	public void set_column(int column) {
		this.column = column;
	}
	
	
	public void set_color(Color color){
		this.color = color;
	}
	
	public int get_coor_x() {
		return coor_x;
	}

	
	public int get_coor_y() {
		return coor_y;
	}

	
	public int get_row() {
		return row;
	}

	
	public int get_column() {
		return column;
	}
	
	public Color get_color(){
		return color;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	

}
