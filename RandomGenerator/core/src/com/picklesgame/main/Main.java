package com.picklesgame.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Main extends ApplicationAdapter {
	public static int WIDTH;
	public static int HEIGHT;

	private SpriteBatch batch;
	private Texture img;
	private Pixmap pixmap;
	private List<Integer> seedInput;
	private Random rand;
	private MiddleSquare ms;
	private LinearCongruentialGenerator lc;
	private MersenneTwister mt;
	private float[][] map;

	private int y;
	private int x;
	private ShapeRenderer sr;

	private Set<String> set = new HashSet<String>();

	public enum TestState {
		perlin, ms, lc, javalc, mt
	}

	public enum RenderType {
		point, map
	}

	public TestState testState = TestState.perlin;
	public RenderType renderType = RenderType.point;
	private BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		pixmap = new Pixmap(WIDTH, HEIGHT, Format.RGBA8888);

		sr = new ShapeRenderer();

		// lc = new LinearCongruentialGenerator("1", "2", "3", "5");
		lc = new LinearCongruentialGenerator("1");
		ms = new MiddleSquare("1234567890");
		mt = new MersenneTwister(123456);
		rand = new Random(21321);

		System.out.println(142);
		for (int i = 0; i < 100; i++) {
			String n = ms.nextRand().toString();
			System.out.println(n);
			// if(set.contains(n)) break;
			// set.add(n);
		}
		System.out.println(set.size());

		img = new Texture(pixmap);
		map = new float[WIDTH][HEIGHT];
		font = new BitmapFont();

		seedInput = new ArrayList<Integer>();

		generateRandValue();
		// generateRandomMap(map);

	}

	private void generateRandValue() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		x = 0;
		y = HEIGHT - 1;
		if (testState == TestState.perlin) {
			map = PerlinNoiseVariation.generatePerlinNoise(WIDTH, HEIGHT, 8);
			generateRandomMap(map);
			return;
		}

		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (testState == TestState.ms) {
					map[x][y] = ms.nextFloatRand();
				} else if (testState == TestState.lc) {
					map[x][y] = lc.nextFloatRand();
				} else if (testState == TestState.javalc) {
					map[x][y] = rand.nextFloat();
				} else if (testState == TestState.mt) {
					map[x][y] = mt.nextFloat();
				}
			}
		}
		generateRandomMap(map);

	}

	public void generateRandomMap(float[][] map) {
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				pixmap.setColor(randColor(map[x][y]));
				pixmap.drawPixel(x, y);
			}
		}
		img.draw(pixmap, 0, 0);
	}

	private Color randColor(float value) {
		if (value <= 0.5) {
			return Color.RED;
		} else
			return Color.BLUE;
	}

	public void update(float dt) {

		updateInput(dt);
	}

	public void updateInput(float dt) {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			generateRandValue();
		} else if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			testState = TestState.perlin;
			generateRandValue();

		} else if (Gdx.input.isKeyJustPressed(Keys.W)) {
			testState = TestState.ms;
			generateRandValue();

		} else if (Gdx.input.isKeyJustPressed(Keys.E)) {
			testState = TestState.lc;
			generateRandValue();

		} else if (Gdx.input.isKeyJustPressed(Keys.R)) {
			testState = TestState.javalc;
			generateRandValue();

		} else if (Gdx.input.isKeyJustPressed(Keys.T)) {
			testState = TestState.mt;
			generateRandValue();

		}
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			renderType = RenderType.map;
		} else if (Gdx.input.isKeyJustPressed(Keys.P)) {
			renderType = RenderType.point;
		}

		if (Gdx.input.isKeyJustPressed(Keys.NUM_0)) {
			seedInput.add(0);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			seedInput.add(1);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			seedInput.add(2);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
			seedInput.add(3);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
			seedInput.add(4);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
			seedInput.add(5);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_6)) {
			seedInput.add(6);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_7)) {
			seedInput.add(7);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_8)) {
			seedInput.add(8);
		} else if (Gdx.input.isKeyJustPressed(Keys.NUM_9)) {
			seedInput.add(9);
		} else if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE)) {
			if (!seedInput.isEmpty())
				seedInput.remove(seedInput.size() - 1);
		} else if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			if (!seedInput.isEmpty()) {
				String newSeed = getNum(seedInput);
				seedInput.clear();
				generateNewMap(newSeed);
			}
		}
	}

	private void generateNewMap(String newSeed) {
		if (testState == TestState.lc) {
			lc = new LinearCongruentialGenerator(newSeed);
			generateRandValue();
		} else if (testState == TestState.ms) {
			ms = new MiddleSquare(newSeed);
			generateRandValue();
		} else if (testState == TestState.perlin) {
			generateRandValue();
		} else if (testState == TestState.javalc) {
			rand = new Random(Integer.valueOf(newSeed));
			generateRandValue();
		} else if (testState == TestState.mt) {
			mt = new MersenneTwister(Integer.valueOf(newSeed));
			generateRandValue();
		}

	}

	private String getNum(List<Integer> l) {
		String result = "";
		for (Integer i : l) {
			result += i;
		}
		return result;
	}

	// boolean done = false;

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update(Gdx.graphics.getDeltaTime());
		// sr.begin(ShapeType.Filled);
		// for(; x < WIDTH; x++){
		//// System.out.println(x);
		//// System.out.println(y);
		// sr.setColor(randColor(map[x][y]));
		// sr.rect(x, y, 1, 1);
		// }
		// if(y > 1){
		// x = 0;
		// y--;
		// }
		// sr.end();

		if (renderType == RenderType.point) {
			sr.begin(ShapeType.Filled);
			for (; x < WIDTH; x += 5) {
				float rand = map[x][y];
				// float rand = ms.nextFloatRand();
				// float rand = (float) Math.random();
				sr.setColor(randColor(rand));
				sr.rect(x, rand * HEIGHT + 1, 2, 2);
				// System.out.println(x);
				// if (x == WIDTH-1)
				// done = true;
			}
			if (y > 1) {
			    x = 0;
				y--;
			}else{
				x = 0;
				y = HEIGHT - 1;
			}
			
			sr.end();
		}

		batch.begin();
		if (renderType == RenderType.map) {
			batch.draw(img, 0, 0, WIDTH, HEIGHT);
		}
		font.draw(batch, "Press space to generator new map", 100, 400);
		font.draw(batch, "Press Q, W, E, R, T to change test", 100, 500);
		font.draw(batch, "Press O, P to change render type (cool stuff)", 100, 550);
		font.draw(batch, "Input a seed, then press enter: " + seedInput.toString(), 100, 300);
		switch (testState) {
		case perlin:
			font.draw(batch, "Type of prng: Perlin Noise", 100, 200);
			break;
		case ms:
			font.draw(batch, "Type of prng: MiddleSquare", 100, 200);
			break;
		case lc:
			font.draw(batch, "Type of prng: Linear Congruential Generator", 100, 200);
			break;
		case javalc:
			font.draw(batch, "Type of prng: Linear Congruential Generator(java version)", 100, 200);
			break;
		case mt:
			font.draw(batch, "Type of prng: Mersenne Twister", 100, 200);
			break;
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
