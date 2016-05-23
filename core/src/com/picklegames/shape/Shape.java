package com.picklegames.shape;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Shape {
	protected Vector2 position;
	protected Color color;

	public abstract void render(ShapeRenderer sr);

	public void translate(float x, float y) {
		position.x += x;
		position.y += y;
	}

	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
