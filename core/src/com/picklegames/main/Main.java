package com.picklegames.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.picklegames.demo.TweenEaseDemo;
import com.picklegames.entities.Box;
import com.picklegames.entities.RoundShape;

public class Main extends ApplicationAdapter {
	private TweenEaseDemo TED;
	private SpriteBatch batch;
	private Box rectangle0, rectangle1;
	private RoundShape circle0, circle1;

	private enum TestState {
		CirclePoint, CircleCircle, RectanglePoint, RectangleRectangle, TweenDemo
	}

	private TestState testState;

	@Override
	public void create() {
		TED = new TweenEaseDemo();

		batch = new SpriteBatch();
		
		rectangle0 = new Box(250, 200, 100, 100);
		rectangle1 = new Box(400, 400, 100, 100);

		circle0 = new RoundShape(250, 250, 75);
		circle1 = new RoundShape(250, 250, 75);

		testState = TestState.TweenDemo;
	}

	public void update(float dt) {

		if (testState.equals(TestState.RectanglePoint)) {

			if (rectangle0.pointInRange(Gdx.input.getX(), -Gdx.input.getY() + Gdx.graphics.getHeight(),
					rectangle0.getRect())) {
				rectangle0.getSprite().setColor(Color.RED);
			} else {
				rectangle0.getSprite().setColor(Color.SKY);
			}
		} else if (testState.equals(TestState.RectangleRectangle)) {
			rectangle1.getRect().setPosition(Gdx.input.getX() - rectangle1.getRect().width / 2,
					-Gdx.input.getY() + Gdx.graphics.getHeight() - rectangle1.getRect().height / 2);

			rectangle1.update(dt);

			if (rectangle0.rectIntersect(rectangle0.getRect(), rectangle1.getRect())) {
				rectangle0.getSprite().setColor(Color.RED);
				rectangle1.getSprite().setColor(Color.RED);
			} else {
				rectangle0.getSprite().setColor(Color.SKY);
				rectangle1.getSprite().setColor(Color.CYAN);
			}
		} else if (testState.equals(TestState.CirclePoint)) {

			if (circle0.circlePointCollision(Gdx.input.getX(), Gdx.input.getY() + Gdx.graphics.getHeight(),
					circle0.getCircle())) {
				circle0.getSprite().setColor(Color.RED);
			} else {
				circle0.getSprite().setColor(Color.SKY);
			}
		} else {
			circle1.getCircle().setPosition(Gdx.input.getX(),
					-Gdx.input.getY() + Gdx.graphics.getHeight());

			circle1.update(dt);

			if (circle0.circleCollision(circle0.getCircle(), circle1.getCircle())) {
				circle0.getSprite().setColor(Color.RED);
				circle1.getSprite().setColor(Color.RED);
			} else {
				circle0.getSprite().setColor(Color.SKY);
				circle1.getSprite().setColor(Color.CYAN);
			}
		}

//		System.out.println("CIRCLE_X: " + circle0.getCircle().x + " CIRCLE_Y: " + circle0.getCircle().y);
//		System.out.println("CIRCLE_RADIUS: " + circle0.getCircle().radius);
//		System.out.println(
//				"MOUSE_X: " + Gdx.input.getX() + " MOUSE_Y: " + (-Gdx.input.getY() + Gdx.graphics.getHeight()));

	}

	@Override
	public void render() {

		if (testState.equals(TestState.TweenDemo)) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
		} else {
			Gdx.gl.glClearColor(1, 1, 1, 1);
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(Gdx.graphics.getDeltaTime());

		batch.begin();
		if (testState.equals(TestState.RectanglePoint)) {
			rectangle0.render(batch);
		} else if (testState.equals(TestState.RectangleRectangle)) {
			rectangle0.render(batch);
			rectangle1.render(batch);
		} else if (testState.equals(TestState.CirclePoint)) {
			circle0.render(batch);
		} else if (testState.equals(TestState.CircleCircle)) {
			circle0.render(batch);
			circle1.render(batch);
		}
		batch.end();
	
		if (testState.equals(TestState.TweenDemo)) {
			TED.update(Gdx.graphics.getDeltaTime());
			TED.render(batch);
		}
	}

	public void Tween() {

	}

}
