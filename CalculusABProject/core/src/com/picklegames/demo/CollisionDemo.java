package com.picklegames.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.picklegames.shape.Circle;
import com.picklegames.shape.Rectangle;

public class CollisionDemo {
	private ShapeRenderer sr;
	private Circle circle0, circle1;
	private Rectangle rectangle0, rectangle1;

	private enum TestState {
		CirclePoint, CircleCircle, RectanglePoint, RectangleRectangle
	}

	private TestState testState;

	public CollisionDemo() {

		sr = new ShapeRenderer();

		circle0 = new Circle(250, 250, 75);
		circle1 = new Circle(250, 250, 75);

		rectangle0 = new Rectangle(250, 200, 100, 100);
		rectangle1 = new Rectangle(400, 400, 100, 100);

		testState = TestState.CircleCircle;
	}

	public void update(float dt) {
		if (testState.equals(TestState.RectanglePoint)) {
			if (rectangle0.collidedPoint(Gdx.input.getX(), -Gdx.input.getY() + Gdx.graphics.getHeight())) {
				rectangle0.setColor(Color.RED);
			} else {
				rectangle0.setColor(Color.SKY);
			}
		} else if (testState.equals(TestState.RectangleRectangle)) {
			rectangle1.setPosition(Gdx.input.getX() - rectangle1.getWidth() / 2,
					-Gdx.input.getY() + Gdx.graphics.getHeight() - rectangle1.getHeight() / 2);

			if (rectangle0.collidedShape(rectangle1)) {
				rectangle0.setColor(Color.RED);
				rectangle1.setColor(Color.RED);
			} else {
				rectangle0.setColor(Color.CYAN);
				rectangle1.setColor(Color.CYAN);
			}
		} else if (testState.equals(TestState.CirclePoint)) {
			System.out.println(circle0.getColor().toString());
			if (circle0.collidedPoint(Gdx.input.getX(), -Gdx.input.getY() + Gdx.graphics.getHeight())) {
				circle0.setColor(Color.RED);
			} else {
				circle0.setColor(Color.SKY);
			}
		} else {
			circle1.setPosition(Gdx.input.getX(), -Gdx.input.getY() + Gdx.graphics.getHeight());
			circle1.update();
			if (circle0.collidedShape(circle1)) {
				circle0.setColor(Color.RED);
				circle1.setColor(Color.RED);
			} else {
				circle0.setColor(Color.CYAN);
				circle1.setColor(Color.CYAN);
			}
		}
	}

	public void render() {

		if (testState.equals(TestState.RectanglePoint)) {
			rectangle0.render(sr);
		} else if (testState.equals(TestState.RectangleRectangle)) {
			rectangle0.render(sr);
			rectangle1.render(sr);
		} else if (testState.equals(TestState.CirclePoint)) {
			circle0.render(sr);
		} else if (testState.equals(TestState.CircleCircle)) {
			circle0.render(sr);
			circle1.render(sr);
		}

	}

}
