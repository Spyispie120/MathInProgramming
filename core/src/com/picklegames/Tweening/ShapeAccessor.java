package com.picklegames.Tweening;

import com.badlogic.gdx.graphics.Color;
import com.picklegames.shape.Shape;

import aurelienribon.tweenengine.TweenAccessor;

public class ShapeAccessor implements TweenAccessor<Shape> {
	public static final int XY = 1;
	public static final int A = 2;

	@Override
	public int getValues(Shape target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case XY:
			returnValues[0] = target.getPosition().x;
			returnValues[1] = target.getPosition().y;
			return 2;
		case A:
			returnValues[0] = target.getColor().a;
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(Shape target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case XY:
			target.setPosition(newValues[0], newValues[1]);
			break;
		case A:
			target.setColor(new Color(target.getColor().r,
									  target.getColor().g,
									  target.getColor().b, 
									  newValues[0]));
			break;
		}
	}

}
