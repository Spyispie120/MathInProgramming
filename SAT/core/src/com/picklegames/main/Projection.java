/*
 * Nguyen Duong
 * Math 126 G
 * Journal Entry
 * Projection
 */

/**
 * Projection class takes account of the max and the min scalar projection 
 */
package com.picklegames.main;

public class Projection {
	// min projection scalar
	private float min;
	// max projection scalar
	private float max;

	public Projection(float min, float max) {
		this.min = min;
		this.max = max;
	}

	/**
	 * 
	 * @param min1
	 *            other min
	 * @param max1
	 *            other max
	 * @return true if there is a overlap between this.min this.max, and min max
	 */
	//TODO: bugged
	private boolean intervalSeperate(float min1, float max1) {
		return (this.min >= max1) || (this.max >= min1);
	}

	public boolean isOverlap(Projection p2) {
		return intervalSeperate(p2.min, p2.max);
	}

}
