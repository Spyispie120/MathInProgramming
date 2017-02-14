package com.picklegames.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private SATtest satTest;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		satTest = new SATtest();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		satTest.update(Gdx.graphics.getDeltaTime());
		satTest.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
