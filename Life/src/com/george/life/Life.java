package com.george.life;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Life implements ApplicationListener {
	private OrthographicCamera camera;
	
	
	private Grid grid;
	
	private ShapeRenderer shape;
	
	public static int WIDTH;
	public static int HEIGHT;
	
	private boolean paused;
	private static final long PAUSE_DELAY=250;
	private long lastPause;
	
	@Override
	public void create() {		
		WIDTH=Gdx.graphics.getWidth();
		HEIGHT=Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.translate(WIDTH / 2, HEIGHT / 2);
		camera.update();
		
		shape=new ShapeRenderer();
		grid=new Grid();
		
		paused=true;
		lastPause=0;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		if(!paused){
			grid.update();
			if(Gdx.input.isKeyPressed(Keys.SPACE) && System.currentTimeMillis()-lastPause>PAUSE_DELAY){
				paused=true;
				lastPause=System.currentTimeMillis();
			}
		}
		
		else{
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				for(int i=0;i<grid.width;i++){
					for(int x=0;x<grid.height;x++){
						if(grid.cells[i][x].contains(Gdx.input.getX(),Gdx.input.getY())){
							grid.cells[i][x].live();
							System.out.println("this cell is at the location" + i + "," +x);
							System.out.println("at the coordinates" + Gdx.input.getX()+","+Gdx.input.getY());
						}
					}
				}
			}
			
			if(Gdx.input.isKeyPressed(Keys.SPACE) && System.currentTimeMillis()-lastPause>PAUSE_DELAY){
				paused=false;
				lastPause=System.currentTimeMillis();
			}
			
		}
		
		
		grid.draw(shape);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
