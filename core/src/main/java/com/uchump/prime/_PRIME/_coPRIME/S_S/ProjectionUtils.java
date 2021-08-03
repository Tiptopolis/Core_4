package com.uchump.prime._PRIME._coPRIME.S_S;



import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.METATRON.M.File.Rect;
public class ProjectionUtils {

	// Screen->World
	public static Vector3 uProject(Vector3 point, Rect projector, Matrix4 projection, Rect screen) {
		Vector3 vSize = new Vector3();
		Vector3 vPosition = new Vector3(0, 0, 0);
		float x = point.x;
		float y = point.y;
		vPosition.set(projector.minX, projector.minY, 0);
		vSize.set(projector.width, projector.height, 0);
		x = x - vPosition.x;

		y = screen.height - y; // screen.height
		y = y - vPosition.y;

		point.x = (2 * x) / screen.width - 1;// screen.height
		point.y = (2 * y) / screen.height - 1;// screen.height
		point.z = (2 * point.z - 1);

		point.prj(projection.inv());

		Vector3 pointMod = point.cpy();
		pointMod.x = pointMod.x + vPosition.x + 1;
		pointMod.y = pointMod.y + vPosition.y + 1;
		return pointMod;
	}
	
	public static Vector3 uProjectRelative(Vector3 point, Rect space) {
	
		point.x = (point.x/space.width);
		point.y = (point.y/space.height);
		
		return point;
	}

	public static Vector3 uProjectRelative(Vector3 point, Rect space, float unitSize) {
		if(unitSize == 0)
			unitSize = 1;
		point.x = (point.x/space.width)/unitSize;
		point.y = (point.y/space.height/unitSize);
		
		return point;
	}

}
