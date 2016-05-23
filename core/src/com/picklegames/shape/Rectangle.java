package com.picklegames.shape;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Rectangle extends Shape{

	private float width, height;
	
	public Rectangle(float x, float y, float width, float height){
		this.width = width;
		this.height = height;
		color = Color.WHITE;
		position = new Vector2(x, y);
	}
	
	@Override
	public void render(ShapeRenderer sr) {
		// TODO Auto-generated method stub
		sr.begin(ShapeType.Filled);
		//sr.setColor(color);
		sr.rect(position.x, position.y, width, height);
		sr.end();
	}
	
	public boolean pointInRange(float x, float y, Rectangle rect){
		return isInRange(x, rect.position.x, rect.position.x + rect.width) &&
				isInRange(y, rect.position.y, rect.position.y + rect.height);
	}
	
	public boolean isInRange(float value, float min, float max){
		return value >= min && value <= max;
	}
	
	public boolean rangeIntersect(float min0, float max0, float min1, float max1){
		return max0 >= min1 && min0 <= max1;
	}
	
	public boolean rectIntersect(Rectangle rect0, Rectangle rect1){
		return rangeIntersect(rect0.position.x, rect0.position.x + rect0.width, rect1.position.x, rect1.position.x + rect1.width) &&
				rangeIntersect(rect0.position.y, rect0.position.y + rect0.height, rect1.position.y, rect1.position.y + rect1.height);
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	

}
