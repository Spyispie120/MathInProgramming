package com.picklegames.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.picklegames.demo.CollisionDemo;
import com.picklegames.demo.TweenEaseDemo;

public class Main extends ApplicationAdapter {
	private TweenEaseDemo TED;
	private CollisionDemo CD;

	private SpriteBatch batch;

	enum DemoState {
		TED, CD
	}

	DemoState demoState = DemoState.CD;

	@Override
	public void create() {
		batch = new SpriteBatch();

		TED = new TweenEaseDemo();
		CD = new CollisionDemo();

	}

	public void update(float dt) {

		switch (demoState) {
			case TED:
				TED.update(dt);
				break;
			case CD:
				CD.update(dt);
				break;
		}

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update(Gdx.graphics.getDeltaTime());

		switch (demoState) {
			case TED:
				TED.render(batch);
				break;
			case CD:
				CD.render();
				break;
		}

	}

	public void Tween() {

	}

}
