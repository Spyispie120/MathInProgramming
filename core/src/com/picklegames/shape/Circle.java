package com.picklegames.shape;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Circle extends Shape{
	private float size;
	
	public Circle(float x , float y, float radius){
		position = new Vector2(x, y);
		this.size = radius;
		color = Color.WHITE;
	}
	
	public void render(ShapeRenderer sr){
		sr.begin(ShapeType.Filled);
		sr.setColor(color);
		sr.circle(position.x, position.y, size);
		sr.end();
	}
	

}
