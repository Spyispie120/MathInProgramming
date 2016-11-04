package com.picklegames.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	public static int WIDTH ;
	public static int HEIGHT;
	public static final int OCTAVE = 9;
	private SpriteBatch batch;
	private Texture img;
	private Pixmap pixmap;
	//private Color[][] map;
	private float[][] perlin;
	
	private BitmapFont font;
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		pixmap = new Pixmap(WIDTH, HEIGHT,Format.RGBA8888);
		
		img = new Texture(pixmap);
		//map = new Color[pixmap.getWidth()][pixmap.getHeight()];
		perlin = PerlinNoiseGenerator.generatePerlinNoise(WIDTH,HEIGHT,OCTAVE);
		
		generateRandomMap(pixmap);
		img.draw(pixmap, 0, 0);
		
		font = new BitmapFont();
	}
	

	private Color randColor(float value){
		if(value <= 0.4){
			return MapConstant.DEEPWATER;
		}else if(value > 0.4 && value <= 0.5){
			return MapConstant.SHALLOWWATER;
		}else if(value > 0.5 && value <= 0.55){
			return MapConstant.LANDHIGHTLIGHT;
		}else if(value > 0.55 && value <= 0.7){
			return MapConstant.LAND;
		}else if(value > 0.7 && value <= 0.8){
			return MapConstant.MOUNTAIN;
		}else{
			return MapConstant.SNOW;
		}
	}
	
	private void generateRandomMap(Pixmap map){
		for(int x = 0; x < WIDTH; x++){
			for(int y = 0; y < HEIGHT; y++){
				pixmap.setColor(randColor(perlin[x][y]));
				pixmap.drawPixel(x, y);
			}
		}

	}
	
	public void update(float dt){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			perlin = PerlinNoiseGenerator.generatePerlinNoise(WIDTH,HEIGHT,OCTAVE);
			generateRandomMap(pixmap);
			img.draw(pixmap, 0, 0);
		}
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.draw(img, 0, 0, WIDTH, HEIGHT);
		font.draw(batch, "Press space to generator new map", 100, 100);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		pixmap.dispose();
	}
}
