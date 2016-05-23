package com.picklegames.shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Circle extends Shape{
	private float radius;
	
	public Circle(float x , float y, float radius){
		position = new Vector2(x, y);
		this.radius = radius;
		color = Color.WHITE;
	}
	
	public void update(){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			radius = (float) Math.random() + 50;
		}
	}
	
	public void render(ShapeRenderer sr){
		sr.begin(ShapeType.Filled);
		//sr.setColor(color);
		sr.circle(position.x, position.y, radius);
		sr.end();
	}
	
	public boolean circleCollision(Circle circle0, Circle circle1){
		return distance(circle0.position.x, circle0.position.y, circle1.position.x, circle1.position.y) <=  circle0.radius + circle1.radius;
	}
	
	public boolean circlePointCollision(float x, float y, Circle c){
		return distance(x, y, c.position.x, c.position.y) < c.radius;
	}

	public float distance(float x0, float y0, float x1, float y1) {
		return (float) Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
	}

	

}
