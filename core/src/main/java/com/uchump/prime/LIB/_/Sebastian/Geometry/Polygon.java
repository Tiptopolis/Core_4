package com.uchump.prime.LIB._.Sebastian.Geometry;

import com.badlogic.gdx.math.Vector2;

public class Polygon {

	public final Vector2[] points;
	public final int numPoints;

	public final int numHullPoints;

	public final int[] numPointsPerHole;
	public final int numHoles;

	final int[] holeStartIndices;

	public Polygon(Vector2[] hull) {
		this(hull, new Vector2[0][]);
	}

	public Polygon(Vector2[] hull, Vector2[][] holes) {
		numHullPoints = hull.length;
		numHoles = holes.length;
		// holes.length is equivalent to C#.getLength(0);
		// holes.length[] would be C#.getLength(1);, etc

		numPointsPerHole = new int[numHoles];
		holeStartIndices = new int[numHoles];
		int numHolePointsSum = 0;

		for (int i = 0; i < holes.length; i++) {
			numPointsPerHole[i] = holes[i].length;

			holeStartIndices[i] = numHullPoints + numHolePointsSum;
			numHolePointsSum += numPointsPerHole[i];
		}

		numPoints = numHullPoints + numHolePointsSum;
		points = new Vector2[numPoints];

		// add hull points, ensuring they wind in counterclockwise order
		boolean reverseHullPointsOrder = !PointsAreCounterClockwise(hull);
		for (int i = 0; i < numHullPoints; i++) {
			points[i] = hull[(reverseHullPointsOrder) ? numHullPoints - 1 - i : i];
		}

		// add hole points, ensuring they wind in clockwise order
		for (int i = 0; i < numHoles; i++) {
			boolean reverseHolePointsOrder = PointsAreCounterClockwise(holes[i]);
			for (int j = 0; j < holes[i].length; j++) {
				points[IndexOfPointInHole(j, i)] = holes[i][(reverseHolePointsOrder) ? holes[i].length - j - 1 : j];
			}
		}
	}

	boolean PointsAreCounterClockwise(Vector2[] testPoints) {
		float signedArea = 0;
		for (int i = 0; i < testPoints.length; i++) {
			int nextIndex = (i + 1) % testPoints.length;
			signedArea += (testPoints[nextIndex].x - testPoints[i].x) * (testPoints[nextIndex].y + testPoints[i].y);
		}

		return signedArea < 0;
	}

	public int IndexOfFirstPointInHole(int holeIndex) {
		return holeStartIndices[holeIndex];
	}

	public int IndexOfPointInHole(int index, int holeIndex) {
		return holeStartIndices[holeIndex] + index;
	}

	public Vector2 GetHolePoint(int index, int holeIndex) {
		return points[holeStartIndices[holeIndex] + index];
	}

}
