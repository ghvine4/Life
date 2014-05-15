package com.george.life;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Cell {

	private boolean living;
	private int x;
	private int y;
	public  static int height;
	public static int width;
	
	public Cell(int x,int y){
		this.x=x;
		this.y=y;
		
		living=false;
		
		width=height=6;
	}
	
	public boolean getState(){
		return living;
	}
	
	public void kill(){
		living=false;
	}
	
	public void live(){
		living=true;
	}
	
	
	
	public void draw(ShapeRenderer shape){
		if(living){
			shape.begin(ShapeType.Filled);
			shape.setColor(Color.RED);
			shape.rect(x, y, width, height);
			shape.end();
		}
		
		else{
			shape.begin(ShapeType.Filled);
			shape.setColor(Color.GRAY);
			shape.rect(x, y, width, height);
			shape.end();
		}
	}
	
	public boolean contains(int xx, int yy){
		if((xx >=x && xx <= x+width) && (yy>=Life.HEIGHT-y-height && yy<=Life.HEIGHT-y)){
			return true;
		}
		else{
			return false;
		}
	}
}

