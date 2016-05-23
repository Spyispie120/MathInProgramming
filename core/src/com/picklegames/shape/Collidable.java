package com.picklegames.shape;

public interface Collidable {
	boolean collidedShape(Shape shape);
	boolean collidedPoint(float x, float y);
}
