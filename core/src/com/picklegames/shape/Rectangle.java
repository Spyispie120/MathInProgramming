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
	
	private boolean isInRange(float value, float min, float max){
		return value >= min && value <= max;
	}
	
	private boolean rangeIntersect(float min0, float max0, float min1, float max1){
		return max0 >= min1 && min0 <= max1;
	}
	

	@Override
	public boolean collidedShape(Shape shape) {
		return rangeIntersect(this.position.x, this.position.x + this.width, shape.position.x, shape.position.x + ((Rectangle)shape).width) &&
			   rangeIntersect(this.position.y, this.position.y + this.height, shape.position.y, shape.position.y + ((Rectangle)shape).height);
	}

	@Override
	public boolean collidedPoint(float x, float y) {
		return isInRange(x, this.position.x, this.position.x + this.width) &&
			   isInRange(y, this.position.y, this.position.y + this.height);
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
