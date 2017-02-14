package com.picklegames.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SATtest {
	private ShapeRenderer sr;
	private Polygon square1, square2, square_r_1;
	private Polygon s1, s2;
	private Polygon poly1, poly2, poly3, poly4;
	private Polygon poly1B, poly3B;
	private BitmapFont font;
	private GlyphLayout layout;

	enum TestCase {
		TEST1, TEST2, TEST3, TEST4, BUGGED
	}

	public TestCase testCase = TestCase.TEST1;

	public SATtest() {
		sr = new ShapeRenderer();
		font = new BitmapFont();
		layout = new GlyphLayout();

		square1 = new Polygon();
		square1.translate(400, 400);
		square2 = new Polygon();
		square2.translate(600, 400);
		square_r_1 = new Polygon(new float[] { 100, 0, 200, 100, 100, 200, 0, 100 });
		square_r_1.translate(550, 400);

		s1 = new Polygon(new float[] { 10, 0, 20, 0, 30, 20, 20, 40, 10, 40, 0, 20 });
		s1.scl(3);
		s1.translate(400, 400);
		s2 = new Polygon(new float[] { 0, 0, 40, 20, 20, 40, 0, 20 });
		s2.scl(3);
		s2.translate(600, 400);

		poly1 = new Polygon();
		poly1.translate(400, 400);

		poly3 = new Polygon(new float[] { 0, 0, 100, 0, 100, 100, 50, 175, 0, 110 });
		poly3.translate(510, 400);

		poly1B = new Polygon();
		poly1B.translate(400, 400);

		poly3B = new Polygon(new float[] { 0, 0, 100, 0, 100, 100, 50, 175, 0, 110 });
		poly3B.translate(510, 400);

		poly2 = new Polygon(new float[] { 100, 0, 200, 100, 100, 200, 0, 100 });
		poly2.translate(100, 101);
		poly4 = new Polygon(new float[] { 0, 0, 100, 0, 50, 50, });
		// poly4.translate(510, 180);

		System.out.println(poly1.isOverlap(poly3));
	}

	Color color1 = Color.RED;
	Color color2 = Color.WHITE;

	float vx;
	float vy;

	public void update(float dt) {
		keyboard();
		switch (testCase) {
		case TEST1:
			square1.translate(vx, vy);
			if (square1.isOverlap(square2)) {
				square1.setColor(color1);
				square2.setColor(color1);
			} else {
				square1.setColor(color2);
				square2.setColor(color2);
			}
			break;
		case TEST2:
			square_r_1.translate(vx, vy);
			if (square1.isOverlap(square_r_1)) {
				square1.setColor(color1);
				square_r_1.setColor(color1);
			} else {
				square1.setColor(color2);
				square_r_1.setColor(color2);
			}
			break;
		case TEST3:
			s2.translate(vx, vy);
			if (s2.isOverlap(s1)) {
				s1.setColor(color1);
				s2.setColor(color1);
			} else {
				s1.setColor(color2);
				s2.setColor(color2);
			}
			break;
		case TEST4:
			poly3.translate(vx, vy);
			if (poly3.isOverlap(poly1)) {
				poly3.setColor(color1);
				poly1.setColor(color1);
			} else {
				poly3.setColor(color2);
				poly1.setColor(color2);
			}
			break;
		case BUGGED:
			poly3B.translate(vx, vy);
			if (poly1B.isOverlap(poly3B)) {
				poly3B.setColor(color1);
				poly1B.setColor(color1);
			} else {
				poly3B.setColor(color2);
				poly1B.setColor(color2);
			}
			break;

		default:
			break;
		}

	}

	public void keyboard() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			vy = 1;
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			vy = -1;
		} else
			vy = 0;

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			vx = -1;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			vx = 1;
		} else
			vx = 0;

		if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			testCase = TestCase.TEST1;
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			testCase = TestCase.TEST2;
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
			testCase = TestCase.TEST3;
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
			testCase = TestCase.TEST4;
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
			testCase = TestCase.BUGGED;
		}

	}

	public void render(SpriteBatch batch) {
		float width = 0;
		float height = 0;
		String s = "Press numbers: 1 2 3 4 5 for different demo \n" + "Arrow keys to move polygon";
		layout.setText(font, s);
		width = layout.width;
		height = layout.height;

		batch.begin();
		font.draw(batch, s, Gdx.graphics.getWidth() / 2 - width / 2, Gdx.graphics.getHeight() * .9f);
		font.draw(batch, "CASE: " + testCase, Gdx.graphics.getWidth() / 2 - width / 2, Gdx.graphics.getHeight() * .8f);
		batch.end();
		switch (testCase) {
		case TEST1:
			square1.render(sr);
			square2.render(sr);
			break;
		case TEST2:
			square_r_1.render(sr);
			square1.render(sr);
			break;
		case TEST3:
			s1.render(sr);
			s2.render(sr);
			break;
		case TEST4:
			poly1.render(sr);
			poly3.render(sr);
			break;
		case BUGGED:
			poly1B.render(sr);
			poly3B.render(sr);
			break;

		default:
			break;
		}

		// poly1.render(sr);
		// poly2.render(sr);
		// poly3.render(sr);
		// poly4.render(sr);
	}
}
