package com.uchump.prime.TESTS.LuciferCube;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.SphereShapeBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.Camera.OrthoController;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.M.Boot.oLF_Renderer_3M;
import com.uchump.prime._PRIME.METATRON.M.File.OroborosList;
import com.uchump.prime._PRIME.METATRON.M.File.TimeKey;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.bNode;

public class LuciferObject3D implements iMonad {
	protected Metatron M;
	protected OrthoController C;
	protected Environment E;

	public uTransform Transform;
	public TimeKey T;

	public OroborosList<bNode<Vector3, Model>> MTX;
	public OroborosList<bNode<Vector3, Model>> OBJ;
	public OroborosList<bNode<Vector3, Model>> CNT_RAD;
	public OroborosList<bNode<Vector3, Model>> CNT_CRS;
	public OroborosList<bNode<Vector3, Model>> Z_DiamClockwise;// RadialRight, Directional Right ({o>)
	public OroborosList<bNode<Vector3, Model>> Z_DiamAnti_Clockwise;
	public OroborosList<bNode<Vector3, Model>> R_F_outter;// Right-Forward, Right Side?, Right-Face (o{)]?
	public OroborosList<bNode<Vector3, Model>> R_F_inner;
	public OroborosList<bNode<Vector3, Model>> F;// FORWARD or UP?, |, color->Right, anti->left, so RIGHT is the
													// positive X;
	// sends neg pulses up, pos down, inverted up/down controls lol
	public OroborosList<bNode<Vector3, Model>> U; // arc upward
	public OroborosList<bNode<Vector3, Model>> A_U;
	public OroborosList<bNode<Vector3, Model>> U_F_outter; // Up-Forward
	public OroborosList<bNode<Vector3, Model>> U_F_inner;
	public OroborosList<bNode<Vector3, Model>> H;

	public boolean lensUp = true;
	public boolean drawLinesR;

	int mem;
	public int clr;

	ModelBuilder lModelBuilder = new ModelBuilder();
	//public Model lModel;
	//public ModelInstance lInst;

	public LuciferObject3D(oLF_Renderer_3M R) {
		this(new Vector3(), 12, 1, R);
	}

	public LuciferObject3D(Vector3 at, int memory, int redraw, oLF_Renderer_3M R) {
		this.M = R.M;
		this.C = R.C;
		this.mem = memory;
		this.clr = redraw;
		this.T = new TimeKey();
		this.Transform = new uTransform();
		this.Transform.SetPosition(at);
		this.newLO(memory);
	
	}

	public LuciferObject3D(uTransform at, int memory, int redraw, oLF_Renderer_3M R) {
		this.M = R.M;
		this.C = R.C;
		this.mem = memory;
		this.clr = redraw;
		this.T = new TimeKey();
		this.Transform = at.clone(this);
		this.newLO(memory);
	}

	protected void newLO(int memory) {
		MTX = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1); // rad origin
		OBJ = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1); // sqr origin
		CNT_RAD = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		CNT_CRS = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);

		R_F_outter = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		R_F_inner = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		F = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		U = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		A_U = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);

		Z_DiamClockwise = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		Z_DiamAnti_Clockwise = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);

		U_F_outter = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		U_F_inner = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);

		H = new OroborosList<bNode<Vector3, Model>>(1, (memory), 1);
		lModelBuilder = new ModelBuilder();
	}

	public void update() {

		float fTheta = 0;
		float iTheta = 0;

		float deltaTime = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue();
		float m = M.Metranome.I.floatValue();

		fTheta = M.RealSecond.I.floatValue();
		iTheta = 1;

		// Log(i + " : " + fTheta + " @ " + deltaTime);

		MTX.add(new bNode(new Vector3((float) (1), (float) (1), (float) (1))));

		OBJ.add(new bNode(new Vector3((float) ((i * -1) * m), (float) ((i * -1) * m), (float) ((i)))));

		CNT_RAD.add(new bNode(
				new Vector3((float) (Math.sin(fTheta)), (float) (Math.cos(fTheta)), (float) (Math.tan(fTheta))).nor()));
		CNT_CRS.add(new bNode(
				new Vector3((float) (Math.cos(fTheta)), (float) (Math.sin(fTheta)), (float) (Math.tan(iTheta))).nor()));

		R_F_outter.add(new bNode(new Vector3((float) (Math.sin(1)), (float) (Math.cos(fTheta)), (float) (Math.tan(1)))));
		R_F_inner.add(new bNode(new Vector3((float) (Math.cos(1)), (float) (Math.sin(fTheta)), (float) (Math.tan(fTheta)))));
		F.add(new bNode(new Vector3((float) (Math.sin(1 - i) * Math.sin(fTheta)), (float) (Math.cos(fTheta) * Math.cos(fTheta)),
				(float) (Math.tan(1 - i) * Math.tan(fTheta)))));
		U.add(new bNode(new Vector3((float) (Math.cos(1 - i) * Math.cos(fTheta)), (float) (Math.cos(fTheta) * Math.cos(fTheta)),
				(float) (Math.tan(iTheta - i) * Math.tan(fTheta)))));
		A_U.add(new bNode(new Vector3((float) (Math.cos(1 - i) * Math.cos(fTheta)), (float) (Math.sin(fTheta) * Math.cos(fTheta)),
				(float) (Math.tan(fTheta - i) * Math.tan(1)))));

		U_F_outter.add(new bNode(new Vector3((float) (Math.cos(fTheta)), (float) (Math.sin(iTheta)), (float) (Math.tan(iTheta)))));
		U_F_inner.add(new bNode(new Vector3((float) (Math.sin(fTheta)), (float) (Math.cos(iTheta)), (float) (Math.tan(fTheta)))));

		Z_DiamClockwise
				.add(new bNode(new Vector3((float) (Math.sin(fTheta)), (float) (Math.cos(fTheta)), (float) (Math.tan(fTheta)))));
		Z_DiamAnti_Clockwise
				.add(new bNode(new Vector3((float) (Math.cos(fTheta)), (float) (Math.sin(fTheta)), (float) (Math.tan(iTheta)))));

		H.add(new bNode(new Vector3(MathUtils.sin(1), MathUtils.cos(1), (1 - (M.Metranome.I.floatValue() * i)))));
	}

	////////////////////
	///////////////////
	public void draw() {

	}

	boolean dM = true;
	boolean dO = true;

	boolean dC_R = false;
	boolean dC_C = false;

	boolean dR_F_outter = false;
	boolean dR_F_inner = false;
	boolean dF = false;
	boolean dU = false;
	boolean dA_U = false;

	boolean dCW = false;
	boolean dA_CL = false;

	boolean dU_F_inner = false;
	boolean dU_F_outter = false;

	public void draw(ModelBatch batch, Environment environment) {
		/*
		 * ModelBuilder modelBuilder = new ModelBuilder(); Model model =
		 * modelBuilder.createBox(MathUtils.PI2, MathUtils.PI2, MathUtils.PI2, new
		 * Material(ColorAttribute.createDiffuse(new Color(1,0,0,0.5f))), Usage.Position
		 * | Usage.Normal); ModelInstance instance = new ModelInstance(model);
		 * this.Transform.SetLocalPosition(new Vector3(Width/2,Height/2,MathUtils.PI2));
		 * instance.transform.set(this.getTransform().GetLocalToWorldMatrix());
		 * 
		 * batch.render(instance,environment);
		 */

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {

					this.render(new Vector3(x, y, z), batch, environment);
				}
			}
		}

		if (M.RealSecond.Raw().intValue() % this.clr > -1 && M.RealSecond.Raw().intValue() % this.clr <= 1) {
			this.clear();
		}
	}

	private void render(Vector3 md, ModelBatch batch, Environment environment) {
		int P = (int) (MathUtils.PI2 * MathUtils.PI);
		float DeltaTime = Metatron.TheMetatron.DeltaTime.I.floatValue();

		int i = 0;
		float objSz = 0;
		Color C = Color.CLEAR;
		ModelBuilder modelBuilder = lModelBuilder;

		if (this.dM)
			for (bNode<Vector3, Model> v : this.MTX) {
				Vector3 V = v.A.cpy().scl(md);
				i = (MTX.indexOf(v));

				if (this.lensUp) {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp((V.z) / i, -0.5f, 0.25f);
				} else {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * DeltaTime) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp(i % (V.z), -0.5f, 0.25f);
				}
				objSz = objSz + 0.25f;
				Model model = modelBuilder.createSphere(objSz, objSz, objSz, 8, 8,
						new Material(ColorAttribute.createDiffuse(C)), Usage.Position | Usage.Normal);

				ModelInstance instance = new ModelInstance(model,
						new Vector3(Width / 2 + V.x, Height / 2 + V.y, MathUtils.PI2 + V.z));
				instance.materials.get(0).set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				batch.render(instance, environment);
				v.setB(model); // i think this makes the model dispose properly, lol?
				// instance = null;
				
			}

		if (this.dO)
			for (bNode<Vector3, Model> v : this.OBJ) {
				Vector3 V = v.A.cpy().scl(md);
				i = (OBJ.indexOf(v));

				if (this.lensUp) {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp((V.z) / i, -0.5f, 0.25f);
				} else {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * DeltaTime) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp(i % (V.z), -0.5f, 0.25f);
				}
				objSz = objSz + 0.25f;
				Model model = modelBuilder.createSphere(objSz, objSz, objSz, 8, 8,
						new Material(ColorAttribute.createDiffuse(C)), Usage.Position | Usage.Normal);
				ModelInstance instance = new ModelInstance(model,
						new Vector3(Width / 2 + V.x, Height / 2 + V.y, MathUtils.PI2 + V.z));
				instance.materials.get(0).set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				batch.render(instance, environment);
				v.setB(model);
				// instance = null;
			}

		if (this.dC_R)
			for (bNode<Vector3, Model> v : this.CNT_RAD) {
				Vector3 V = v.A.cpy().scl(md);
				i = (CNT_RAD.indexOf(v));

				if (this.lensUp) {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp((V.z) / i, -0.5f, 0.25f);
				} else {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * DeltaTime) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp(i % (V.z), -0.5f, 0.25f);
				}
				objSz = objSz + 0.25f;
				Model model = modelBuilder.createSphere(objSz, objSz, objSz, 8, 8,
						new Material(ColorAttribute.createDiffuse(C)), Usage.Position | Usage.Normal);
				ModelInstance instance = new ModelInstance(model,
						new Vector3(Width / 2 + V.x, Height / 2 + V.y, MathUtils.PI2 + V.z));
				instance.materials.get(0).set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				batch.render(instance, environment);
				v.setB(model);
				// instance = null;
			}

		if (this.dC_C)
			for (bNode<Vector3, Model> v : this.CNT_CRS) {
				Vector3 V = v.A.cpy().scl(md);
				i = (CNT_CRS.indexOf(v));

				if (this.lensUp) {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp((V.z) / i, -0.5f, 0.25f);
				} else {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * DeltaTime) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp(i % (V.z), -0.5f, 0.25f);
				}
				objSz = objSz + 0.25f;
				Model model = modelBuilder.createSphere(objSz, objSz, objSz, 8, 8,
						new Material(ColorAttribute.createDiffuse(C)), Usage.Position | Usage.Normal);
				ModelInstance instance = new ModelInstance(model,
						new Vector3(Width / 2 + V.x, Height / 2 + V.y, MathUtils.PI2 + V.z));
				instance.materials.get(0).set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				batch.render(instance, environment);
				v.setB(model);
				// instance = null;
			}

		if (this.dCW)
			for (bNode<Vector3, Model> v : this.Z_DiamClockwise) {
				Vector3 V = v.A.cpy().scl(md);
				i = (Z_DiamClockwise.indexOf(v));

				if (this.lensUp) {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp((V.z) / i, -0.5f, 0.25f);
				} else {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * DeltaTime) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp(i % (V.z), -0.5f, 0.25f);
				}
				objSz = objSz + 0.25f;
				Model model = modelBuilder.createSphere(objSz, objSz, objSz, 8, 8,
						new Material(ColorAttribute.createDiffuse(C)), Usage.Position | Usage.Normal);
				ModelInstance instance = new ModelInstance(model,
						new Vector3(Width / 2 + V.x, Height / 2 + V.y, MathUtils.PI2 + V.z));
				instance.materials.get(0).set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				batch.render(instance, environment);
				v.B = model;
				// instance = null;
			}

		if (this.dA_CL)
			for (bNode<Vector3, Model> v : this.Z_DiamAnti_Clockwise) {
				Vector3 V = v.A.cpy().scl(md);
				i = (Z_DiamAnti_Clockwise.indexOf(v));

				if (this.lensUp) {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp((V.z) / i, -0.5f, 0.25f);
				} else {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * DeltaTime) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp(i % (V.z), -0.5f, 0.25f);
				}
				objSz = objSz + 0.25f;
				Model model = modelBuilder.createSphere(objSz, objSz, objSz, 8, 8,
						new Material(ColorAttribute.createDiffuse(C)), Usage.Position | Usage.Normal);
				ModelInstance instance = new ModelInstance(model,
						new Vector3(Width / 2 + V.x, Height / 2 + V.y, MathUtils.PI2 + V.z));
				instance.materials.get(0).set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				batch.render(instance, environment);
				v.setB(model);
				// instance = null;
			}

		if (this.dF)
			for (bNode<Vector3, Model> v : this.F) {
				Vector3 V = v.A.cpy().scl(md);
				i = (F.indexOf(v));

				if (this.lensUp) {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp((V.z) / i, -0.5f, 0.25f);
				} else {
					C = new Color((V.x) % 256f, ((V.y) % 256f), (V.z % 256f), ((V.z * DeltaTime) % 256f) * .25f);
					objSz = (M.Metranome.I.floatValue() * DeltaTime) * (float) MathUtils.clamp(i % (V.z), -0.5f, 0.25f);
				}
				objSz = objSz + 0.25f;
				Model model = modelBuilder.createSphere(objSz, objSz, objSz, 8, 8,
						new Material(ColorAttribute.createDiffuse(C)), Usage.Position | Usage.Normal);
				ModelInstance instance = new ModelInstance(model,
						new Vector3(Width / 2 + V.x, Height / 2 + V.y, MathUtils.PI2 + V.z));
				instance.materials.get(0).set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				batch.render(instance, environment);
				v.setB(model);
				// instance = null;
			}
		
		
		batch.flush();
		
	}

	public void clear() {

		this.MTX.clear();
		this.OBJ.clear();
		this.CNT_RAD.clear();
		this.CNT_CRS.clear();
		this.Z_DiamClockwise.clear();
		this.Z_DiamAnti_Clockwise.clear();
		this.R_F_outter.clear();
		this.R_F_inner.clear();
		this.F.clear();
		this.U.clear();
		this.A_U.clear();
		this.U_F_outter.clear();
		this.U_F_inner.clear();
		this.H.clear();
		this.lModelBuilder = null;
		this.lModelBuilder = new ModelBuilder();

	}


	
	//////////

	@Override
	public uTransform getTransform() {
		return this.Transform;
	}

	@Override
	public void transformUpdated() {

	}

	@Override
	public String toLog() {

		return this.toLog();
	}

	public static float cInner(Number N) {

		return N.floatValue() % 180;
	}

	public static float cOutter(Number N) {

		return N.floatValue() % 360;
	}

}
