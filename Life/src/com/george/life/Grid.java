package com.george.life;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Grid {
	
	public Cell[][] cells;
	public boolean[][] tempStates;
	public int startY;
	public int startX;
	public int increment;
	
	public int width;
	public int height;
	
	private static final long UPDATE_DELAY=75;
	private long lastUpdate;
	
	
	
	public Grid(){
		width=Life.WIDTH/6+1;
		height=Life.HEIGHT/6+1;
		
		cells=new Cell[width][height];
		tempStates=new boolean[width][height];
		
		
		
		
		
		
		startY=Life.HEIGHT-6;
		startX=Life.WIDTH-6;
		increment=6;
		
		
		for(int i=0;i<(width);i++){
			for(int j=0;j<(height);j++){
				cells[i][j]=new Cell(startX-increment*i,startY-increment*j);
			}
		}
		

		
		
		
		
		startY=Life.HEIGHT-Cell.height;
		startX=Life.WIDTH-Cell.width;
		increment=Cell.width;
		
		
	
		
	
		
		lastUpdate=0;
		
		
		
		
	}
	
	public int getNumNeighbors(int w,int h){
		int num=0;
		
		if(h+1<height){
			if(cells[w][h+1].getState()){
				num++;
			}
		}
		
		if(h-1>=0){
			if(cells[w][h-1].getState()){
				num++;
			}
		}
		
		if(w-1>=0){
			if(cells[w-1][h].getState()){
				num++;
			}
		}
		
		if(w+1<width){
			if(cells[w+1][h].getState()){
				num++;
			}
		}
		
		if(w-1>=0 && h-1>=0){
			if(cells[w-1][h-1].getState()){
				num++;
			}
		}
		
		if(w-1>=0 && h+1<height){
			if(cells[w-1][h+1].getState()){
				num++;
			}
		}
		
		if(w+1<width && h+1<height){
			if(cells[w+1][h+1].getState()){
				num++;
			}
		}
		
		if(w+1<width  && h-1>=0){
			if(cells[w+1][h-1].getState()){
				num++;
			}
		}
		
		return num;
	}
	
	public void updateTempState(int w,int h){
		if(cells[w][h].getState()){
			if(getNumNeighbors(w,h)<2){
				tempStates[w][h]=false;
			}
			else if(getNumNeighbors(w,h)==2){
			}
			else if(getNumNeighbors(w,h)>3){
				tempStates[w][h]=false;
			}
		}
		else{
			if(getNumNeighbors(w,h)==3){
				tempStates[w][h]=true;
			}
		}
	}
	
	public void update(){
		if(System.currentTimeMillis()-lastUpdate>UPDATE_DELAY){
			
			lastUpdate=System.currentTimeMillis();
			
			for(int i=0;i<width;i++){
				for(int x=0;x<height;x++){
					updateTempState(i,x);
				}
			}
			
			
			for(int i=0;i<width;i++){
				for(int x=0;x<height;x++){
					if(tempStates[i][x]){
						cells[i][x].live();
					}
					else{
						cells[i][x].kill();
					}
				}
			}
		}
		
		
	}
	
	public void draw(ShapeRenderer shape){
		
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				cells[i][j].draw(shape);
			}
		}
		
		
		shape.begin(ShapeType.Line);
		shape.setColor(Color.BLACK);
	
		
		for(int i=0;i<(width);i++){
			shape.line(startX-increment*i, Life.HEIGHT, startX-increment*i, 0);
		}
		for(int i=0;i<(height);i++){
			shape.line(0, startY-increment*i, Life.WIDTH, startY-increment*i);
		}
		shape.end();
	
		
		
		
		
		
		
		
		
		
	}
	
}
