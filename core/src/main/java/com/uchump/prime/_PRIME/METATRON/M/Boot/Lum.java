package com.uchump.prime._PRIME.METATRON.M.Boot;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.METATRON.M.File.OroborosList;

public class Lum {

	public LuciferSprite L;
	public OroborosList<Vector3> At;

	public boolean visible = true;

	public Lum(LuciferSprite L) {
		this.L = L;
		this.At = new OroborosList<Vector3>(1, L.mem, 1);
	}

	public void update(Vector3 t) {
		this.At.add(t);
	}

	public void draw() {

		if (this.visible)
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					for (int z = -1; z <= 1; z++) {
						this.render(new Vector3(x, y, z));
					}
				}

			}
	}

	protected void render(Vector3 md) {
		
		float deltaTime = L.M.DeltaTime.I.floatValue();
		float I = L.M.iTime.I.floatValue();
		float m = L.M.Metranome.I.floatValue();
		Vector3 pos = this.L.TRANSFORM.GetLocalPosition();
		Sketcher.Drawer.setDefaultLineWidth(L.R.C.Camera.zoom);
		for (Vector3 v : this.At) {
			Vector3 V = v.cpy().scl(md);
			int i = (this.At.indexOf(v));
			Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
			if (!this.L.lensUp) {
				Sketcher.Drawer.filledCircle((L.R.CellSize/2)+V.x, (L.R.CellSize/2)+V.y,
						L.M.Metranome.I.floatValue()-((float) MathUtils.clamp(V.z / i, -0.5f, 0.5f)));	
				Sketcher.Drawer.filledCircle((L.R.CellSize/4)+V.x, (L.R.CellSize/4)+V.y,
						L.M.Metranome.I.floatValue()-((float) MathUtils.clamp(V.z / i, -0.5f, 0.5f)));
				Sketcher.Drawer.filledCircle((L.R.CellSize/4)+V.x, (L.R.CellSize/4)+V.y,
						MathUtils.clamp(V.z / i,I,L.M.Metranome.I.floatValue()*(L.R.CellSize/4)));
			} else {
				Sketcher.Drawer.circle((L.R.CellSize/2)+V.x, (L.R.CellSize/2)+V.y, (L.R.CellSize/2)*L.R.C.Camera.zoom);
				Sketcher.Drawer.circle((L.R.CellSize/4)+V.x, (L.R.CellSize/4)+V.y, (L.R.CellSize/2)*L.R.C.Camera.zoom);
			}
		}
	}
}
