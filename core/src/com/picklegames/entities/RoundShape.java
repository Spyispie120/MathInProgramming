package com.picklegames.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class RoundShape {

	private Circle circle;
	private Sprite sprite;

	public RoundShape(float x, float y, float radius) {
		
		circle = new Circle(x, y, radius);
		
		sprite = new Sprite(new Texture("circle.png"));
		sprite.setSize(circle.radius * 2, circle.radius * 2);
		setPosition(circle.x, circle.y);
		
	}
	
	public void update(float dt){
		setPosition(circle.x, circle.y);
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			circle.radius = 25 + (float) Math.random() * 50;
			sprite.setSize(circle.radius * 2, circle.radius * 2);
		}
	}
	
	public void render(SpriteBatch batch){
		
		sprite.draw(batch);
		
	}
	
	public boolean circleCollision(Circle circle0, Circle circle1){
		return distance(circle0.x, circle0.y, circle1.x, circle1.y) <=  circle0.radius + circle1.radius;
	}
	
	public boolean circlePointCollision(float x, float y, Circle c){
		return distance(x, y, c.x, c.y) < c.radius;
	}

	public float distance(float x0, float y0, float x1, float y1) {
		return (float) Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
	}
	
	public void setPosition(float x, float y){
		sprite.setPosition(x - circle.radius, y - circle.radius);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public Circle getCircle(){
		return circle;
	}

}
