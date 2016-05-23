package com.picklegames.Tweening;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import aurelienribon.tweenengine.TweenAccessor;

public class FontAccessor implements TweenAccessor<BitmapFont> {

	public static final int ALPHA = 1;
	public static final int SCALE = 2;
	public static final int COLOR = 3;

	@Override
	public int getValues(BitmapFont target, int tweenType, float[] returnValues) {
		switch(tweenType){
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		case SCALE:
			returnValues[0] = target.getData().scaleX;
			returnValues[1] = target.getData().scaleY;
			return 2;
		case COLOR:
			returnValues[0] = target.getColor().r;
			returnValues[1] = target.getColor().g;
			returnValues[2] = target.getColor().b;
			return 3;
		}
		
		assert false;return-1;
	}

	@Override
	public void setValues(BitmapFont target, int tweenType, float[] newValues) {
		switch(tweenType){
		case ALPHA:
			target.setColor(target.getColor().b, target.getColor().g, target.getColor().r, newValues[0]);
			break;
		case SCALE:
			//System.out.println(newValues[0] + " "+ newValues[1]);
			target.getData().setScale(newValues[0], newValues[1]);
			break;
		case COLOR:
			//System.out.println(newValues[0] + " "+ newValues[1]);
			target.setColor(newValues[0], newValues[1], newValues[2], 1);;
			break;
		}
		
	}

}
