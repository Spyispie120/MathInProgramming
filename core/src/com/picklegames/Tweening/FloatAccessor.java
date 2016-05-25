package com.picklegames.Tweening;

import aurelienribon.tweenengine.TweenAccessor;

public class FloatAccessor implements TweenAccessor<Float>{

	public static final int X = 1;
	
	@Override
	public int getValues(Float target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case X:
			returnValues[0] = target.floatValue();
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(Float target, int tweenType, float[] newValues) {
		switch (tweenType) {	
		case X:
			target = Float.valueOf(newValues[0]);
		break;
		}
	}

}
