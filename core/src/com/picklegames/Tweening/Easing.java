package com.picklegames.Tweening;

public class Easing {
	public static float easeLinear (float t,float b , float c, float d) {
		return c*t/d + b;
	}
	
	public static float  easeInQuad(float t,float b , float c, float d) {
		return c*(t/=d)*t + b;
	}
	
	public static float easeInCubic (float t,float b , float c, float d) {
		return c*(t/=d)*t*t + b;
	}
	
	public static float  easeInQuart(float t,float b , float c, float d) {
		return c*(t/=d)*t*t*t + b;
	}
	
	public static float easeInQuint (float t,float b , float c, float d) {
		return c*(t/=d)*t*t*t*t + b;
	}
	
	public static float  easeInCos(float t,float b , float c, float d) {
		return -c * (float)Math.cos(t/d * (Math.PI/2)) + c + b;
	}
	
	public static float  easeOutSin(float t,float b , float c, float d) {
		return c * (float)Math.sin(t/d * (Math.PI/2)) + b;	
	}
	
	public static float  easeInExpo(float t,float b , float c, float d) {
		return (t==0) ? b : c * (float)Math.pow(2, 10 * (t/d - 1)) + b;
	}
}