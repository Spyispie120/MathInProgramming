/*
 * Nguyen Duong
 * Math 126 G
 * Journal Entry 1
 * Polygon
 */

/**
 * Polygon class is a template for a polygon consisted of vertices.
 */
package com.picklegames.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Polygon {
	// vertices of polygon
	private float[] vertices;
	// normal vector to each edge
	private Vector2[] normals;
	// polygon color
	private Color color;

	/**
	 * Create a default polygon post: - create a polygon with 4 sides, 100px
	 * each.
	 */
	public Polygon() {
		this(new float[] { 0, 0, 100, 0, 100, 100, 0, 100 });
	}

	/**
	 * Create a polygon with given vertices pre: - vertices much be even
	 * 
	 * post: create a polygon with given vertices
	 * 
	 * @param vertices
	 *            vertices to create polygon
	 */
	public Polygon(float[] vertices) {
		this.vertices = vertices;
		this.normals = new Vector2[vertices.length / 2];
		// update normal vector edges
		updateAxes();
		color = Color.WHITE;
	}

	/**
	 * Move polygon by given value (x,y)
	 * 
	 * @param x
	 *            x translation
	 * @param y
	 *            y translation
	 */
	public void translate(float x, float y) {
		for (int i = 0; i < vertices.length; i += 2) {
			vertices[i] += x;
			vertices[i + 1] += y;
		}
		// update normal vector edges
		updateAxes();
	}

	/**
	 * Get the vertex of the polygon pre: - n much be less than polygon sides -
	 * 1 and not negative post: - return a vertex
	 * 
	 * @param n
	 *            vertex order
	 * @return Value of the vertex in (x,y)
	 * @throws IllegalArgumentException
	 *             if point order is invalid
	 */
	public Vector2 getPoint(int n) {
		if (n < 0 || n > vertices.length / 2)
			throw new IllegalArgumentException("Invalid point");
		return new Vector2(vertices[n * 2], vertices[n * 2 + 1]);
	}

	/**
	 * Get the edge vector pre: - n much be less than polygon sides - 1 and not
	 * negative 
	 * post: - return edge vector
	 * 
	 * @param n
	 *            edge order
	 * @return vector of the edge
	 * @throws IllegalArgumentException
	 *             if n is invalid
	 */
	public Vector2 getEdge(int n) {
		Vector2 edge, p1, p2;
		if (n < 0 || n >= vertices.length / 2)
			throw new IllegalArgumentException("wrong edge");
		p1 = getPoint(n);
		p2 = (n == vertices.length / 2 - 1) ? getPoint(0) : getPoint(n + 1);
		edge = new Vector2(p2.x - p1.x, p2.y - p1.y);
		return edge;
	}

	/**
	 * The meat and bone of this Separating axis theorem By apply the Separating
	 * Axis Theorem, we can find if 2 convex polygons are colliding or not.
	 * 
	 * pre: - po2 much be non-null
	 * 
	 * post: - return true if the two polygons are colliding
	 * 
	 * @param po2
	 *            Polygon
	 * @return true if 2 polygons are colliding
	 * 
	 */
	//Still bugged 
	//TODO: sometimes, switching polygons.isOverlap work...
	public boolean isOverlap(Polygon po2) {
		// Getting the normal vector of both polygon
		Vector2[] axes1 = this.getAxes();
		Vector2[] axes2 = po2.getAxes();

		// Loop over all vertices in the first polygon
		for (int i = 0; i < axes1.length; i++) {
			// Find the scalar projection of each polygons to the normal vector
			// of the first polygon
			Vector2 axis = axes1[i];
			Projection p1 = this.project(axis, this);
			Projection p2 = po2.project(axis, po2);

			// If one of the projections is not overlap
			// then we can safely assume the two polygons are not overlap
			if (!p1.isOverlap(p2)) {
				return false;
			}
		}

		// Loop over all vertices in the second polygon
		for (int i = 0; i < axes2.length; i++) {
			// Find the scalar projection of each polygons to the normal vector
			// of the second polygon
			Vector2 axis = axes2[i];
			Projection p1 = this.project(axis, this);
			Projection p2 = po2.project(axis, po2);

			// If one of the projections is not overlap
			// then we can safely assume the two polygons are not overlap
			if (!p1.isOverlap(p2)) {
				return false;
			}
		}

		// Return true if all projections are overlapped
		return true;
	}

	/**
	 * Calculate the scalar projection of a given vector to each point
	 * 
	 * pre:
	 * - axis much be a unit vector
	 * 
	 * post:
	 * - return a projection
	 * 
	 * @param axis vector to project on.
	 * @param poly polygon use to project.
	 * @return A projection {@code Projection} 
	 */
	private Projection project(Vector2 axis, Polygon poly) {
		// Calculate the dot product of a unit vector with a vector
		// Comp_a-B = (a / ||a||) . b
		// return the scalar
		float min = axis.dot(poly.getPoint(0)) / axis.len();
		float max = min;
		
		for (int i = 1; i < poly.getVertices().length / 2; i++) {
			// Calculate the second dot
			float p = axis.dot(poly.getPoint(i)) / axis.len();
			// Check the scalar value
			if (p < min) {
				// if true, replace new min
				min = p;
			} else if (p > max) {
				// if true, replace new max
				max = p;
			}
		}
		Projection proj = new Projection(min, max);
		return proj;
	}

	/**
	 * return all the normal edge vectors
	 * @return normal edge vector
	 */
	public Vector2[] getAxes() {
		return this.normals;
	}

	/**
	 * Make a new perpendicular (left) vector to the original
	 * @param v Vector
	 * @return new perpendicular vector
	 */
	private Vector2 perpL(Vector2 v) {
		return new Vector2(v.y, -v.x);
	}

	/**
	 * Make a new perpendicular (right) vector to the original
	 * @param v Vector
	 * @return new perpendicular vector
	 */
	private Vector2 perpR(Vector2 v) {
		return new Vector2(-v.y, v.x);
	}
	/**
	 * Update normal vector when vertices change
	 */
	private void updateAxes() {
		for (int i = 0; i < vertices.length / 2; i++) {
			Vector2 edge = getEdge(i);
			// normalize perpendicular vector
			normals[i] = perpL(edge);
		}
	}

	/**
	 * Render on screen using shaperenderer
	 * @param sr ShapeRenderer
	 */
	public void render(ShapeRenderer sr) {
		sr.begin(ShapeType.Line);
		sr.setColor(color);
		sr.polygon(vertices);
		sr.end();
	}
	
	/**
	 * Scale polygon
	 * pre:
	 * - c much be greater than 0
	 * @param c scalar
	 */
	public void scl(float c){
		for (int i = 0; i < vertices.length; i += 2) {
			vertices[i] *= c;
			vertices[i + 1] *= c;
		}
		// update normal vector edges
		updateAxes();
	}
	/**
	 * Set new color to the Polygon
	 * @param color color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Get vertices
	 */
	public float[] getVertices() {
		return vertices;
	}
}
