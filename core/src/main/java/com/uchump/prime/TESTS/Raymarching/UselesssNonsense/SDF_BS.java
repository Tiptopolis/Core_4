package com.uchump.prime.TESTS.Raymarching.UselesssNonsense;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.uchump.prime.TESTS.Raymarching.SDF_2d;
import com.uchump.prime.TESTS.Raymarching.UselesssNonsense.SDF_BS.SDF.Primitive2D;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector2;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector3;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class SDF_BS {
//BoundShape.BindRadius

	// contextless
	// assign a transform to tie the ray-origin to- boundshape.bindradians
	// arbitrarily large distance
	public static uTransform RaycasterMaster;
	public static Vector3 CasterAt;
	public static ArrayList<BoundShape> ShapesInSpace = new ArrayList<BoundShape>(); // all shapes in space
	public static HashMap<Integer, SDF> ShapeTypeIndex = new HashMap<Integer, SDF>();
	public static float maxDst = 3000;
	public static Vector3 Unit = new Vector3().add(MathUtils.PI).V3();
	com.badlogic.gdx.math.Vector3 TMP = new com.badlogic.gdx.math.Vector3();

	public static void setRaycastOrigin(uTransform t) {
		RaycasterMaster = t;
	}

	public static void setRaycastOrigin(Vector3 v) {
		RaycasterMaster = new uTransform();
		RaycasterMaster.SetLocalPosition(v.gdxV3());
		CasterAt = v;
	}

	public static float sigDstToScene() {
		float dstToScene = maxDst;
		if (RaycasterMaster != null)
			CasterAt = new Vector3(RaycasterMaster.GetLocalPosition());

		for (int i = 0; i < ShapesInSpace.size() - 1; i++) {
			SDF n = ShapeTypeIndex.get(i);
						
			dstToScene = Math.min(dstToScene, n.dst(new Vector2(CasterAt.x, CasterAt.y), n));
		}

		Log(dstToScene + "  <<<<<<<<<<<<>>>");
		return dstToScene;
	}

	public Vector3 setCasterPos(Vector3 v) {
		CasterAt = v;
		return CasterAt;
	}

	public static void addNewShape(BoundShape shape) {
		ShapesInSpace.add(shape); // try detect fn, default to circle
		int i = ShapesInSpace.indexOf(shape);
		ShapeTypeIndex.put(i, new SDF(shape.getTransform(), Primitive2D.CIRCLE));
	}

	/////////////////////////////////////////////////////////////////

	public static class SDF implements iSDFx {

		public iSDFx TYPE = Primitive2D.VERTEX;
		public uTransform center;

		public SDF(uTransform center, iSDFx type) {
			this.center = center;
		}

		public float dst(aVector p) {
			return this.TYPE.dst(p, this.center(), this.TYPE);
		}

		public float dst(aVector reference, iSDFx type) {
			return this.TYPE.dst(reference, this.center(), type);
		}

		@Override
		public float dst(aVector reference, aVector center, iSDFx type) {
			return this.TYPE.dst(reference, center, type);
		}

		@Override
		public <T extends iSDFx> Class<T> type() {

			return this.TYPE.type();
		}

		@Override
		public aVector center() {
			return new uVector(new Float[] { center.GetLocalPosition().x, center.GetLocalPosition().y,
					center.GetLocalPosition().z });
		}

		@Override
		public iSDFx center(aVector v) {
			float x = (float) v.elements.get(0);
			float y = (float) v.elements.get(1);
			float z = (float) v.elements.get(2);
			this.center.SetLocalPosition(new Vector3(x, y, z).gdxV3());
			return this;
		}

		public enum Primitive2D implements iSDFx {
			VERTEX, LINE, CIRCLE, RECT;

			@Override
			public float dst(aVector reference, aVector center, iSDFx type) {
				if (type == CIRCLE)
					return SDF_2d.sigSDCircle(reference.V2(), center.V2(), 1f);

				return 1;
			}

			@Override
			public <T extends iSDFx> Class<T> type() {
				return (Class<T>) Primitive2D.class;
			}

			@Override
			public aVector center() {
				return null;
			}

			@Override
			public iSDFx center(aVector v) {
				// returns any shape AT^v
				float x = (float) v.elements.get(0);
				float y = (float) v.elements.get(1);
				float z = (float) v.elements.get(2);
				Vector3 d = new Vector3(x, y, z);
				Vector3 c = new Vector3();
				for (SDF s : ShapeTypeIndex.values()) {
					c = new Vector3(s.center.GetLocalPosition().cpy());
					Vector3 r = c.sub(d.cpy().V3()).V3();
					if (r.len() < 0.01f)
						return s;
				}

				return null;
			}

		}

	}

}
