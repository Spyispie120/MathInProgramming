package com.picklegames.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.picklegames.demo.TweenEaseDemo;

public class Main extends ApplicationAdapter{
	private TweenEaseDemo TED;
	
	@Override
	public void create () {		
		TED = new TweenEaseDemo();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		TED.update(Gdx.graphics.getDeltaTime());
		TED.render();
	}

	public void Tween(){

	}
	
	
	
}
