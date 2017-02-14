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
		sr.setColor(color);
		sr.circle(position.x, position.y, radius);
		sr.end();
	}
	
	@Override
	public boolean collidedShape(Shape shape) {
		return distance(this.position.x, this.position.y, shape.position.x, shape.position.y) <=  this.radius + ((Circle)shape).radius;
	}

	@Override
	public boolean collidedPoint(float x, float y) {
		return distance(x, y, this.position.x, this.position.y) < this.radius;
	}

	private float distance(float x0, float y0, float x1, float y1) {
		return (float) Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
	}

	

}
