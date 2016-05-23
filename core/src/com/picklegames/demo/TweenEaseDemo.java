package com.picklegames.demo;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.picklegames.Tweening.Easing;
import com.picklegames.Tweening.FontAccessor;
import com.picklegames.Tweening.ShapeAccessor;
import com.picklegames.shape.Circle;
import com.picklegames.shape.Shape;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class TweenEaseDemo implements InputProcessor {
	private ShapeRenderer sr;
	private TweenManager tweenManagerBall, tweenManagerText;
	private BitmapFont font;
	private GlyphLayout layout;

	private List<Shape> shapes;
	private boolean isTween = false;
	private Shape cir1;

	private final float EASE = .1f;
	private final float DURATION = 1f;
	private Vector2 originalPosition = new Vector2();

	private enum DemoCase {
		demo1, demo2, demo3
	}

	DemoCase demoCase = DemoCase.demo1;

	private Color[] colors = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.PURPLE, Color.ORANGE };
	private TweenEquation[] tweenEquation = new TweenEquation[] { 
													TweenEquations.easeNone, TweenEquations.easeInQuad,
													TweenEquations.easeInCubic, TweenEquations.easeInQuart,
													TweenEquations.easeInQuint };

	public TweenEaseDemo() {
		Tween.registerAccessor(Shape.class, new ShapeAccessor());
		Tween.registerAccessor(BitmapFont.class, new FontAccessor());

		sr = new ShapeRenderer();
		tweenManagerBall = new TweenManager();
		tweenManagerText = new TweenManager();

		font = new BitmapFont(Gdx.files.internal("font/comicsan.fnt"));
		font.setColor(Color.WHITE);
		font.getData().scaleX = 2f;
		font.getData().scaleY = 2f;
		layout = new GlyphLayout();

		cir1 = new Circle(50, 50, 10);
		originalPosition.set(cir1.getPosition());

		shapes = new ArrayList<Shape>();

		int section = Gdx.graphics.getHeight() / 5;
		for (int i = 0; i < 5; i++) {
			Shape s = new Circle(50, 50 + section * i, 10);
			s.setColor(colors[i]);
			shapes.add(s);
		}

		Gdx.input.setInputProcessor(this);
	}

	private Vector2 mousePosition = new Vector2();
	private Vector2 mouseClick = new Vector2();
	private Vector2 target = new Vector2();
	private float timeElapsed;
	float changeX;
	float changeY;

	public void demo1(float dt) {
		mousePosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		target = mouseClick;

		System.out.println(timeElapsed);
		System.out.println(changeX + " " + changeY);
		if (isTween) {
			timeElapsed += dt;

			if (timeElapsed < DURATION) {

				cir1.setPosition(easeTo(timeElapsed, originalPosition.x, changeX, DURATION),
						easeTo(timeElapsed, originalPosition.y, changeY, DURATION));

			} else {
				timeElapsed = 0;
				cir1.setPosition(target.x, target.y);
				originalPosition.set(target);
				isTween = false;

			}
		}
	}

	public void demo2(float dt) {

		isTween = false;
		Tween.to(font, FontAccessor.SCALE, .5f).target(3f, 3f).ease(TweenEquations.easeNone).repeatYoyo(20, 0f)
				.start(tweenManagerText);

		Tween.set(font, FontAccessor.COLOR).target(Color.RED.r, Color.RED.g, Color.RED.b).delay(1f)
				.start(tweenManagerText);
		Tween.set(font, FontAccessor.COLOR).target(Color.ORANGE.r, Color.ORANGE.g, Color.ORANGE.b).delay(2f)
				.start(tweenManagerText);
		Tween.set(font, FontAccessor.COLOR).target(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b).delay(3f)
				.start(tweenManagerText);
		Tween.set(font, FontAccessor.COLOR).target(Color.GREEN.r, Color.GREEN.g, Color.GREEN.b).delay(4f)
				.start(tweenManagerText);
		Tween.set(font, FontAccessor.COLOR).target(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b).delay(5f)
				.start(tweenManagerText);
		Tween.set(font, FontAccessor.COLOR).target(Color.VIOLET.r, Color.VIOLET.g, Color.VIOLET.b).delay(6f)
				.start(tweenManagerText);
		Tween.set(font, FontAccessor.COLOR).target(Color.PINK.r, Color.PINK.g, Color.PINK.b).delay(8f)
				.start(tweenManagerText);

		Tween.to(cir1, ShapeAccessor.XY, 2f).target(50, Gdx.graphics.getHeight() - 50).ease(TweenEquations.easeInBounce)
				.start(tweenManagerBall);
		Tween.to(cir1, ShapeAccessor.XY, 2f).target(Gdx.graphics.getWidth() - 50, Gdx.graphics.getHeight() - 50)
				.delay(2f).ease(TweenEquations.easeInElastic).start(tweenManagerBall);
		Tween.to(cir1, ShapeAccessor.XY, 2f).target(Gdx.graphics.getWidth() - 50, 50).delay(4f)
				.ease(TweenEquations.easeInOutExpo).start(tweenManagerBall);
		Tween.to(cir1, ShapeAccessor.XY, 2f).target(50, 50).delay(6f).ease(TweenEquations.easeInSine)
				.start(tweenManagerBall);

	}

	public void demo3(float dt) {
		isTween = false;
		for (int i = 0; i < shapes.size(); i++) {
			Tween.to(shapes.get(i), ShapeAccessor.X, 2f).target(Gdx.graphics.getWidth() - 50).ease(tweenEquation[i])
					.repeatYoyo(1, .5f).start(tweenManagerBall);
		}
	}

	private void resetDemo3() {
		int section = Gdx.graphics.getHeight() / 5;
		for (int i = 0; i < 5; i++) {
			shapes.get(i).setPosition(50, 50 + section * i);

		}
	}

	public void update(float dt) {
		System.out.println(isTween);
		System.out.println(tweenManagerBall.size());

		switch (demoCase) {
		case demo1:
			demo1(dt);
			break;
		case demo2:
			tweenManagerBall.update(dt);
			tweenManagerText.update(dt);

			if (isTween) {
				demo2(dt);
			}

//			if (tweenManagerBall.size() == 0) {
//				isTween = true;
//			}
			
		case demo3:
			tweenManagerBall.update(dt);
			if (isTween) {
				demo3(dt);
			}

			if (tweenManagerBall.size() == 0) {
				isTween = true;
				resetDemo3();
			}
			break;
		default:
			break;
		}
	}

	public void render(SpriteBatch batch) {
		switch (demoCase) {
		case demo1:
			cir1.render(sr);
			break;
		case demo2:
			layout.setText(font, "Calculus AB");
			float width = layout.width;
			float height = layout.height;

			cir1.render(sr);

			batch.begin();
			font.draw(batch, "Calculus AB", Gdx.graphics.getWidth() / 2 - width / 2,
					Gdx.graphics.getHeight() / 2 + height);
			batch.end();
			break;
		case demo3:
			for (Shape s : shapes) {
				s.render(sr);
			}
			break;
		default:
			break;
		}

	}

	public float easeTo(float t, float b, float c, float d) {
		return Easing.easeInCos(t, b, c, d);
	}

	public void easeTo(Shape shape, Vector2 target) {
		float dx = target.x - shape.getPosition().x;
		float dy = target.y - shape.getPosition().y;
		float vx = dx * EASE;
		float vy = dy * EASE;

		if (Math.abs(dx) <= 0.05f && Math.abs(dy) <= 0.05f) {
			shape.setPosition(target.x, target.y);
			return;
		}
		shape.translate(vx, vy);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			if (!isTween) {
				target = mouseClick.set(screenX, Gdx.graphics.getHeight() - screenY);
				changeX = target.x - originalPosition.x;
				changeY = target.y - originalPosition.y;
				isTween = true;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {

			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
