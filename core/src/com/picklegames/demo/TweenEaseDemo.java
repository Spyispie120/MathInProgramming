package com.picklegames.demo;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.picklegames.Easing;
import com.picklegames.shape.Circle;
import com.picklegames.shape.Shape;

public class TweenEaseDemo implements InputProcessor {
	private ShapeRenderer sr;
	private List<Shape> shapes;
	private boolean isTween = false;
	private Shape cir1;

	private final float EASE = .1f;
	private final float DURATION = 1f;
	private Vector2 originalPosition = new Vector2();

	public TweenEaseDemo() {
		sr = new ShapeRenderer();

		cir1 = new Circle(0, 0, 5);
		originalPosition.set(cir1.getPosition());

		shapes = new ArrayList<Shape>();
		for (int i = 0; i < 1; i++) {
			Shape s = new Circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 5);
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

	public void update(float dt) {
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

	public void render() {
		cir1.render(sr);

		// for (Shape s : shapes) {
		// s.render(sr);
		// }
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
