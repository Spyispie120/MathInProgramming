package com.picklegames.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Box{
	
	private Rectangle rect;
	private Sprite sprite;
	
	public Box(float x, float y, float width, float height){
		rect = new Rectangle(x,y,width, height);
		sprite = new Sprite(new Texture("rect.png"));
		sprite.setSize(rect.width, rect.height);
		sprite.setPosition(rect.x, rect.y);
		

	}
	
	public void update(float dt){
		
		sprite.setPosition(rect.x, rect.y);
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			rect.width = 40 + (float) Math.random() * 65;
			rect.height = 40 + (float) Math.random() * 65;
			sprite.setSize(rect.width, rect.height);
		}
	}
	
	public void render(SpriteBatch batch){
		sprite.draw(batch);
	}
	
	public boolean pointInRange(float x, float y, Rectangle rect){
		return isInRange(x, rect.x, rect.x + rect.width) &&
				isInRange(y, rect.y, rect.y + rect.height);
	}
	
	public boolean isInRange(float value, float min, float max){
		return value >= min && value <= max;
	}
	
	public boolean rangeIntersect(float min0, float max0, float min1, float max1){
		return max0 >= min1 && min0 <= max1;
	}
	
	public boolean rectIntersect(Rectangle rect0, Rectangle rect1){
		return rangeIntersect(rect0.x, rect0.x + rect0.width, rect1.x, rect1.x + rect1.width) &&
				rangeIntersect(rect0.y, rect0.y + rect0.height, rect1.y, rect1.y + rect1.height);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public Rectangle getRect(){
		return rect;
	}
	
}
