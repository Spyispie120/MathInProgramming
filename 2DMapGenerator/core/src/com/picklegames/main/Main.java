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
import com.picklegames.noises.PerlinNoise;
import com.picklegames.noises.PerlinNoiseVariation;
import com.picklegames.noises.SimplexNoise;

public class Main extends ApplicationAdapter {
	enum NoiseTest{
		PERLIN, SIMPLEX
	}
	public NoiseTest noiseTest = NoiseTest.PERLIN;
	public static int WIDTH;
	public static int HEIGHT;
	public static int OCTAVE = 4;
	public static float ROUGHNESS = 1f;
	public static float SCALE = .1f;
	
	private SpriteBatch batch;
	private Texture img;
	private Pixmap pixmap;
	// private Color[][] map;
	private float[][] perlin;
	
	private BitmapFont font;

	@Override
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		pixmap = new Pixmap(WIDTH, HEIGHT, Format.RGBA8888);

		img = new Texture(pixmap);
		// map = new Color[pixmap.getWidth()][pixmap.getHeight()];
		updateMap();
		font = new BitmapFont();
		font.getData().scaleX = 1.2f;
		font.getData().scaleY = 1.2f;

	}

	private Color randColor(float value) {
		if (value <= 0.4) {
			return MapConstant.DEEPWATER;
		} else if (value > 0.4 && value <= 0.5) {
			return MapConstant.SHALLOWWATER;
		} else if (value > 0.5 && value <= 0.55) {
			return MapConstant.LANDHIGHTLIGHT;
		} else if (value > 0.55 && value <= 0.7) {
			return MapConstant.LAND;
		} else if (value > 0.7 && value <= 0.8) {
			return MapConstant.MOUNTAIN;
		} else {
			return MapConstant.SNOW;
		}
	}

	private void generateRandomMap(Pixmap map) {
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				pixmap.setColor(randColor(perlin[x][y]));
				pixmap.drawPixel(x, y);
			}
		}

	}

	public void update(float dt) {
		updateOctave();
		switch(noiseTest){
		case PERLIN:	
			break;
		case SIMPLEX:
			//simplex noise
			updateRoughness();
			updateScale();
			break;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			updateMap();
		}else if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			noiseTest = NoiseTest.PERLIN;
			updateMap();
		}else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			noiseTest = NoiseTest.SIMPLEX;
			updateMap();
		}
	}

	PerlinNoise perlin1 = new PerlinNoise();
	
	private void updateMap() {
		switch(noiseTest){
		case PERLIN:
			//modified perlin noise
			perlin = PerlinNoiseVariation.generatePerlinNoise(WIDTH, HEIGHT, OCTAVE);
			break;
		case SIMPLEX:
			//simplex noise
			perlin = SimplexNoise.generateOctavedSimplexNoise(WIDTH, HEIGHT, OCTAVE, ROUGHNESS, SCALE);
			break;
			
		}
		
		
		
		
		//improved perlin noise (not working) :(
		//perlin = perlin1.OctavePerlin(WIDTH, HEIGHT, 0, 8, .8);
		generateRandomMap(pixmap);
		img.draw(pixmap, 0, 0);
	}

	private void updateOctave() {
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			OCTAVE++;
			updateMap();
		} else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if(OCTAVE >= 1)	OCTAVE--;
			updateMap();
		}
		
	}
	
	private void updateRoughness() {
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			ROUGHNESS+=.05f;
			updateMap();
		} else if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			ROUGHNESS-=.05f;
			updateMap();
		}
		
	}
	private void updateScale() {
		if (Gdx.input.isKeyJustPressed(Keys.PERIOD)) {
			SCALE+=.005f;
			updateMap();
		} else if (Gdx.input.isKeyJustPressed(Keys.COMMA)) {
			if(SCALE >= 0) SCALE-=.005f;
			updateMap();
		}
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.draw(img, 0, 0, WIDTH, HEIGHT);
		font.draw(batch, "Press space to generator new map", 100, 400);
		font.draw(batch, "Press 1,2 to change test", 100, 500);
		switch(noiseTest){
		case PERLIN:
			//modified perlin noise
			font.draw(batch, "Octave: " + OCTAVE + " (Up-Down)", 100, 300);
			break;
		case SIMPLEX:
			//simplex noise
			font.draw(batch, "Octave: " + OCTAVE + " (Up-Down)", 100, 300);
			font.draw(batch, "Roughness: " + ROUGHNESS  + " (Left-Rigt)", 100, 200);
			font.draw(batch, "Scale: " + SCALE  + " (comma - period)", 100, 100);
			break;
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		pixmap.dispose();
	}
}
