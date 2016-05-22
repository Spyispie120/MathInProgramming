package com.picklegames.demo;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.picklegames.shape.Circle;
import com.picklegames.shape.Shape;

public class TweenEaseDemo implements InputProcessor {
	private ShapeRenderer sr;
	private Shape cir1;
	private List<Shape> shapes;

	private final float EASE = .1f;


	public TweenEaseDemo() {
		sr = new ShapeRenderer();
		cir1 = new Circle(100, 100, 5);

		shapes = new ArrayList<Shape>();
		shapes.add(cir1);
		for (int i = 0; i < 100; i++) {
			Shape s = new Circle(100, 100, 5);
			shapes.add(s);
		}

		Gdx.input.setInputProcessor(this);
	}

	private Vector2 mousePosition = new Vector2();
	private Vector2 mouseClick = new Vector2();
	private Vector2 leader = new Vector2();

	public void update(float dt) {
		mousePosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		leader = mouseClick;

		for (int i = 0; i < shapes.size(); i++) {
			easeTo(shapes.get(i), leader);
			leader = shapes.get(i).getPosition();
		}

	}

	public void render() {
		for (Shape s : shapes) {
			s.render(sr);
		}
		cir1.render(sr);
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
			leader = mouseClick.set(screenX, Gdx.graphics.getHeight() - screenY);

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
