package com.uchump.prime._PRIME.METATRON.M.Boot.Apps.lBox;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
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


public class LuciferObject implements iMonad {
	protected Metatron M;
	protected OrthoController C;

	public uTransform Transform;
	public TimeKey T;

	public OroborosList<Vector3> MTX;
	public OroborosList<Vector3> OBJ;
	public OroborosList<Vector3> CNT_RAD;
	public OroborosList<Vector3> CNT_CRS;
	public OroborosList<Vector3> Z_DiamClockwise;// RadialRight, Directional Right ({o>)
	public OroborosList<Vector3> Z_DiamAnti_Clockwise;
	public OroborosList<Vector3> R_F_outter;// Right-Forward, Right Side?, Right-Face (o{)]?
	public OroborosList<Vector3> R_F_inner;
	public OroborosList<Vector3> F;// FORWARD or UP?, |, color->Right, anti->left, so RIGHT is the positive X;
	// sends neg pulses up, pos down, inverted up/down controls lol
	public OroborosList<Vector3> U; // arc upward
	public OroborosList<Vector3> A_U;
	public OroborosList<Vector3> U_F_outter; // Up-Forward
	public OroborosList<Vector3> U_F_inner;
	public OroborosList<Vector3> H;

	public boolean lensUp = true;
	public boolean drawLinesR;

	int mem;
	public int clr;
	
	public LuciferObject(oLF_Renderer_3M R)
	{
		this(new Vector3(),360,10,R);
	}

	public LuciferObject(Vector3 at, int memory, int redraw, oLF_Renderer_3M R) {
		this.M = R.M;
		this.C = R.C;
		this.mem = memory;
		this.clr = redraw;
		this.T = new TimeKey();
		this.Transform = new uTransform();
		this.Transform.SetPosition(at);
		this.newLO(memory);
	}

	public LuciferObject(uTransform at, int memory, int redraw, oLF_Renderer_3M R) {
		this.M = R.M;
		this.C = R.C;
		this.mem = memory;
		this.clr = redraw;
		this.T = new TimeKey();
		this.Transform = at.clone(this);
		this.newLO(memory);
	}

	protected void newLO(int memory) {
		MTX = new OroborosList<Vector3>(1, (memory), 1); // rad origin
		OBJ = new OroborosList<Vector3>(1, (memory), 1); // sqr origin
		CNT_RAD = new OroborosList<Vector3>(1, (memory), 1);
		CNT_CRS = new OroborosList<Vector3>(1, (memory), 1);

		R_F_outter = new OroborosList<Vector3>(1, (memory), 1);
		R_F_inner = new OroborosList<Vector3>(1, (memory), 1);
		F = new OroborosList<Vector3>(1, (memory), 1);
		U = new OroborosList<Vector3>(1, (memory), 1);
		A_U = new OroborosList<Vector3>(1, (memory), 1);

		Z_DiamClockwise = new OroborosList<Vector3>(1, (memory), 1);
		Z_DiamAnti_Clockwise = new OroborosList<Vector3>(1, (memory), 1);

		U_F_outter = new OroborosList<Vector3>(1, (memory), 1);
		U_F_inner = new OroborosList<Vector3>(1, (memory), 1);

		H = new OroborosList<Vector3>(1, (memory), 1);
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

		MTX.add(new Vector3((float) (1), (float) (1), (float) (1)));
		OBJ.add(new Vector3((float) ((i * -1) * m), (float) ((i * -1) * m), (float) ((i))));

		CNT_RAD.add(
				new Vector3((float) (Math.sin(fTheta)), (float) (Math.cos(fTheta)), (float) (Math.tan(fTheta))).nor());
		CNT_CRS.add(
				new Vector3((float) (Math.cos(fTheta)), (float) (Math.sin(fTheta)), (float) (Math.tan(iTheta))).nor());

		R_F_outter.add(new Vector3((float) (Math.sin(1)), (float) (Math.cos(fTheta)), (float) (Math.tan(1))));
		R_F_inner.add(new Vector3((float) (Math.cos(1)), (float) (Math.sin(fTheta)), (float) (Math.tan(fTheta))));

		F.add(new Vector3((float) (Math.sin(1 - i) * Math.sin(fTheta)), (float) (Math.cos(fTheta) * Math.cos(fTheta)),
				(float) (Math.tan(1 - i) * Math.tan(fTheta))));
		U.add(new Vector3((float) (Math.cos(1 - i) * Math.cos(fTheta)), (float) (Math.cos(fTheta) * Math.cos(fTheta)),
				(float) (Math.tan(iTheta - i) * Math.tan(fTheta))));
		A_U.add(new Vector3((float) (Math.cos(1 - i) * Math.cos(fTheta)), (float) (Math.sin(fTheta) * Math.cos(fTheta)),
				(float) (Math.tan(fTheta - i) * Math.tan(1))));

		U_F_outter.add(new Vector3((float) (Math.cos(fTheta)), (float) (Math.sin(iTheta)), (float) (Math.tan(iTheta))));
		U_F_inner.add(new Vector3((float) (Math.sin(fTheta)), (float) (Math.cos(iTheta)), (float) (Math.tan(fTheta))));

		Z_DiamClockwise
				.add(new Vector3((float) (Math.sin(fTheta)), (float) (Math.cos(fTheta)), (float) (Math.tan(fTheta))));
		Z_DiamAnti_Clockwise
				.add(new Vector3((float) (Math.cos(fTheta)), (float) (Math.sin(fTheta)), (float) (Math.tan(iTheta))));

		H.add(new Vector3(MathUtils.sin(1), MathUtils.cos(1), (1 - (M.Metranome.I.floatValue() * i))));
		
		
	}

	////////////////////
	///////////////////
	public void draw() {
		float d = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue() - 1;
		float m = M.Metranome.I.floatValue();

		// BASE-WHITE
				Sketcher.setColor(1, 1, 1, 1);
				Sketcher.Drawer.filledRectangle((Width / 2) - 1f, (Height / 2) -1f, 2, 2);			
		
		//// NORMAL-SPACE
		// RIGHT-RED
		Sketcher.setColor(0.25f, 0, 0, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f) + 1, ((Height / 2) - 0.5f), 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f) + 1, ((Height / 2) - 0.5f), 1, 1);
		// UP-GREEN
		Sketcher.setColor(0, 0.25f, 0, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f), ((Height / 2) - 0.5f) + 1, 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f), ((Height / 2) - 0.5f) + 1, 1, 1);
		// RIGHT-UP-BLUE
		Sketcher.setColor(0, 0, 0.25f, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f) + 1, ((Height / 2) - 0.5f) + 1, 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f) + 1, ((Height / 2) - 0.5f) + 1, 1, 1);
		//// ANTI-NORMAL-SPACE
		// LEFT-ANTIRED
		Sketcher.setColor(1 - 0.25f, 0, 0, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f) - 1, ((Height / 2) - 0.5f), 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f) - 1, ((Height / 2) - 0.5f), 1, 1);
		// DOWN-ANTIGREEN
		Sketcher.setColor(0, 1 - 0.25f, 0, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f), ((Height / 2) - 0.5f) - 1, 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f), ((Height / 2) - 0.5f) - 1, 1, 1);
		// LEFT-DOWN-ANTIBLUE
		Sketcher.setColor(0, 0, 1 - 0.25f, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f) - 1, ((Height / 2) - 0.5f) - 1, 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f) - 1, ((Height / 2) - 0.5f) - 1, 1, 1);

		//// EDGE
		// RIGHT-DOWN
		Sketcher.setColor(0.25f, 1 - 0.25f, 0, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f) + 1, ((Height / 2) - 0.5f)-1, 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f) + 1, ((Height / 2) - 0.5f)-1, 1, 1);
		// LEFT-UP
		Sketcher.setColor(1 - 0.25f, 0.25f, 0, 0.25f);
		Sketcher.Drawer.filledRectangle(((Width / 2) - 0.5f) - 1, ((Height / 2) - 0.5f)+1, 1, 1);
		Sketcher.Drawer.rectangle(((Width / 2) - 0.5f) - 1, ((Height / 2) - 0.5f)+1, 1, 1);

		// AT-BLACK
		Sketcher.setColor(0, 0, 0, 0.25f);
		Sketcher.Drawer.filledRectangle(Width / 2 - 0.5f, (Height / 2) - 0.5f, 1, 1);
		Sketcher.setColor(0, 0, 0, 0.25f);
		Sketcher.Drawer.rectangle((Width / 2) - 0.5f, (Height / 2) - 0.5f, 1, 1);

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					Sketcher.Drawer.setDefaultLineWidth(C.Camera.zoom);
					this.drawHalo(false);
					Sketcher.interupt();

					// Sketcher.Drawer.setDefaultLineWidth(C.Camera.zoom + (i * d));
					Sketcher.Drawer.setDefaultLineWidth(C.Camera.zoom * 1 - (m * i * d));
					this.drawLinesA_0(new Vector3(x, y, z));
					this.drawLinesR(new Vector3(x, y, z));
					this.drawLinesG(new Vector3(x, y, z));
					this.drawLinesB(new Vector3(x, y, z));
					this.drawLinesA_1(new Vector3(x, y, z));
					Sketcher.interupt();

					Sketcher.Drawer.setDefaultLineWidth(C.Camera.zoom);
					this.drawHalo(true);
					this.render(new Vector3(x, y, z));
					Sketcher.interupt();

				}
			}
		}
		//if (M.RealSecond.Raw().intValue() % this.clr <=1) {
		//this.clr = 10;
		if (M.RealSecond.Raw().intValue() % this.clr > -1 && M.RealSecond.Raw().intValue() % this.clr <= 1) {
			this.clear();
			// Log("CLR RND_ " + this.clr + ":" + M.RealSecond.Raw().intValue() % this.clr);
		}
	}

	boolean dM = true;
	boolean dO = true;

	boolean dC_R = true;
	boolean dC_C = true;

	boolean dR_F_outter = true;
	boolean dR_F_inner = true;
	boolean dF = true;
	boolean dU = true;
	boolean dA_U = true;

	boolean dCW = true;
	boolean dA_CL = true;

	boolean dU_F_inner = true;
	boolean dU_F_outter = true;

	private void render(Vector3 md) {

		float deltaTime = M.DeltaTime.I.floatValue();

		if (this.dM)
			for (Vector3 v : MTX) {
				Vector3 V = v.cpy().scl(md);
				int i = (MTX.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.filledCircle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % v.z, -0.5f, 0.5f));
			}

		if (this.dO)
			for (Vector3 v : OBJ) {
				Vector3 V = v.cpy().scl(md);
				int i = (OBJ.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.filledCircle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
			}

		if (this.dC_R)
			for (Vector3 v : CNT_RAD) {
				Vector3 V = v.cpy().scl(md);
				int i = (CNT_RAD.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.filledCircle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
			}

		if (this.dC_C)
			for (Vector3 v : CNT_CRS) {
				Vector3 V = v.cpy().scl(md);
				int i = (CNT_CRS.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.filledCircle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
			}

		if (this.dCW)
			for (Vector3 v : Z_DiamClockwise) {
				Vector3 V = v.cpy().scl(md);
				int i = (Z_DiamClockwise.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
			}

		if (this.dA_CL)
			for (Vector3 v : Z_DiamAnti_Clockwise) {
				Vector3 V = v.cpy().scl(md);
				int i = (Z_DiamAnti_Clockwise.indexOf(v));
				Sketcher.Drawer.setColor(((v.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle(Width / 2 + (V.x), Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.circle(Width / 2 + (V.x), Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
			}

		if (this.dF)
			for (Vector3 v : F) {
				Vector3 V = v.cpy().scl(md);
				int i = (F.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
			}

		if (this.dU)
			for (Vector3 v : U) {
				Vector3 V = v.cpy().scl(md);
				int i = (U.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle((Width / 2) + V.x, (Height / 2) + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.circle((Width / 2) + V.x, (Height / 2) + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % v.z, -0.5f, 0.5f));
			}

		if (this.dA_U)
			for (Vector3 v : A_U) {
				Vector3 V = v.cpy().scl(md);
				int i = (A_U.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp)
					Sketcher.Drawer.circle((Width / 2) + V.x, (Height / 2) + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				else
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
			}

		if (this.dR_F_outter)
			for (Vector3 v : R_F_outter) {
				Vector3 V = v.cpy().scl(md);
				int i = (R_F_outter.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp) {
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				} else {
					Sketcher.Drawer.circle(Width / 2 + v.x, Height / 2 + v.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % v.z, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + v.x, Height / 2 + v.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % v.z, -0.5f, 0.5f));
				}
			}

		if (this.dR_F_inner)
			for (Vector3 v : R_F_inner) {
				Vector3 V = v.cpy().scl(md);
				int i = (R_F_inner.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp) {
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				} else {
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
				}
			}

		if (this.dU_F_inner)
			for (Vector3 v : U_F_outter) {
				Vector3 V = v.cpy().scl(md);
				int i = (U_F_outter.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp) {
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				} else {
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
				}
			}

		if (this.dU_F_outter)
			for (Vector3 v : U_F_inner) {
				Vector3 V = v.cpy().scl(md);
				int i = (U_F_inner.indexOf(v));
				Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z * -1) % 256f) * .25f);
				if (lensUp) {
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(V.z / i, -0.5f, 0.5f));
				} else {
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
					Sketcher.Drawer.circle(Width / 2 + V.x, Height / 2 + V.y,
							(M.Metranome.I.floatValue() * deltaTime) * (float) MathUtils.clamp(i % V.z, -0.5f, 0.5f));
				}
			}
	}

	private void drawLinesR(Vector3 mod) {

		float d = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue() - 1;
		float m = M.Metranome.I.floatValue();
		Vector2 O = new Vector2(Width / 2, Height / 2);

		Vector2 tmp1;
		Vector2 tmp2;

		// LINES 0->(SIZE-1)
		
		Color R = (new Color(1, d * i, d * i, (m - (i * d)) / 2f));
		if(!this.MTX.isEmpty()) {
		tmp1 = new Vector2(this.MTX.get(0).x * mod.x, this.MTX.get(0).y * mod.y);
		tmp2 = new Vector2(this.MTX.get(this.MTX.size() - 1).x * mod.x, this.MTX.get(this.MTX.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);
		

		tmp1 = new Vector2(this.OBJ.get(0).x * mod.x, this.OBJ.get(0).y * mod.y);
		tmp2 = new Vector2(this.OBJ.get(this.OBJ.size() - 1).x * mod.x, this.OBJ.get(this.OBJ.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.CNT_RAD.get(0).x * mod.x, this.CNT_RAD.get(0).y * mod.y);
		tmp2 = new Vector2(this.CNT_RAD.get(this.CNT_RAD.size() - 1).x * mod.x,
				this.CNT_RAD.get(this.CNT_RAD.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.CNT_CRS.get(0).x * mod.x, this.CNT_CRS.get(0).y * mod.y);
		tmp2 = new Vector2(this.CNT_CRS.get(this.CNT_CRS.size() - 1).x * mod.x,
				this.CNT_CRS.get(this.CNT_CRS.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.R_F_outter.get(0).x * mod.x, this.R_F_outter.get(0).y * mod.y);
		tmp2 = new Vector2(this.R_F_outter.get(this.R_F_outter.size() - 1).x * mod.x,
				this.R_F_outter.get(this.R_F_outter.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.R_F_inner.get(0).x * mod.x, this.R_F_inner.get(0).y * mod.y);
		tmp2 = new Vector2(this.R_F_inner.get(this.R_F_inner.size() - 1).x * mod.x,
				this.R_F_inner.get(this.R_F_inner.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.F.get(0).x * mod.x, this.F.get(0).y * mod.y);
		tmp2 = new Vector2(this.F.get(this.F.size() - 1).x * mod.x, this.F.get(this.F.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.U.get(0).x * mod.x, this.U.get(0).y * mod.y);
		tmp2 = new Vector2(this.U.get(this.U.size() - 1).x * mod.x, this.U.get(this.U.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.A_U.get(0).x * mod.x, this.A_U.get(0).y * mod.y);
		tmp2 = new Vector2(this.A_U.get(this.A_U.size() - 1).x * mod.x, this.A_U.get(this.A_U.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.Z_DiamClockwise.get(0).x * mod.x, this.Z_DiamClockwise.get(0).y * mod.y);
		tmp2 = new Vector2(this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).x * mod.x,
				this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.Z_DiamAnti_Clockwise.get(0).x * mod.x, this.Z_DiamAnti_Clockwise.get(0).y * mod.y);
		tmp2 = new Vector2(this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).x * mod.x,
				this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.U_F_outter.get(0).x * mod.x, this.U_F_outter.get(0).y * mod.y);
		tmp2 = new Vector2(this.U_F_outter.get(this.U_F_outter.size() - 1).x * mod.x,
				this.U_F_outter.get(this.U_F_outter.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);

		tmp1 = new Vector2(this.U_F_inner.get(0).x * mod.x, this.U_F_inner.get(0).y * mod.y);
		tmp2 = new Vector2(this.U_F_inner.get(this.U_F_inner.size() - 1).x * mod.x,
				this.U_F_inner.get(this.U_F_inner.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), R);
		}
	}

	private void drawLinesG(Vector3 mod) {
		float d = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue() - 1;
		float m = M.Metranome.I.floatValue();
		Vector2 O = new Vector2(Width / 2, Height / 2);

		Vector2 tmp1;
		Vector2 tmp2;

		// LINES 0->(SIZE-1)
		Color G = (new Color(d * i, 1, d * i, (m - (i * d)) / 2f));
		if(!MTX.isEmpty()) {
		tmp1 = new Vector2(this.MTX.get(0).x * mod.x, this.MTX.get(0).y * mod.y);
		tmp2 = new Vector2(this.OBJ.get(0).x * mod.x, this.OBJ.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.OBJ.get(0).x * mod.x, this.OBJ.get(0).y * mod.y);
		tmp2 = new Vector2(this.CNT_RAD.get(0).x * mod.x, this.CNT_RAD.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.CNT_RAD.get(0).x * mod.x, this.CNT_RAD.get(0).y * mod.y);
		tmp2 = new Vector2(this.CNT_CRS.get(0).x * mod.x, this.CNT_CRS.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.CNT_CRS.get(0).x * mod.x, this.CNT_CRS.get(0).y * mod.y);
		tmp2 = new Vector2(this.R_F_outter.get(0).x * mod.x, this.R_F_outter.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.R_F_outter.get(0).x * mod.x, this.R_F_outter.get(0).y * mod.y);
		tmp2 = new Vector2(this.R_F_inner.get(0).x * mod.x, this.R_F_inner.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.R_F_inner.get(0).x * mod.x, this.R_F_inner.get(0).y * mod.y);
		tmp2 = new Vector2(this.F.get(0).x * mod.x, this.F.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.F.get(0).x * mod.x, this.F.get(0).y * mod.y);
		tmp2 = new Vector2(this.U.get(0).x * mod.x, this.U.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.U.get(0).x * mod.x, this.U.get(0).y * mod.y);
		tmp2 = new Vector2(this.A_U.get(0).x * mod.x, this.A_U.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.A_U.get(0).x * mod.x, this.A_U.get(0).y * mod.y);
		tmp2 = new Vector2(this.Z_DiamClockwise.get(0).x * mod.x, this.Z_DiamClockwise.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.Z_DiamClockwise.get(0).x * mod.x, this.Z_DiamClockwise.get(0).y * mod.y);
		tmp2 = new Vector2(this.Z_DiamAnti_Clockwise.get(0).x * mod.x, this.Z_DiamAnti_Clockwise.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.Z_DiamAnti_Clockwise.get(0).x * mod.x, this.Z_DiamAnti_Clockwise.get(0).y * mod.y);
		tmp2 = new Vector2(this.U_F_outter.get(0).x * mod.x, this.U_F_outter.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.U_F_outter.get(0).x * mod.x, this.U_F_outter.get(0).y * mod.y);
		tmp2 = new Vector2(this.U_F_inner.get(0).x * mod.x, this.U_F_inner.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);

		tmp1 = new Vector2(this.U_F_inner.get(0).x * mod.x, this.U_F_inner.get(0).y * mod.y);
		tmp2 = new Vector2(this.MTX.get(0).x * mod.x, this.MTX.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), G);
		}
	}

	private void drawLinesB(Vector3 mod) {
		float d = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue() - 1;
		float m = M.Metranome.I.floatValue();
		Vector2 O = new Vector2(Width / 2, Height / 2);

		Vector2 tmp1;
		Vector2 tmp2;

		// LINES 0->(SIZE-1)
		Color B = (new Color(d * i, d * i, 1, (m - (i * d)) / 2f));
		if(!MTX.isEmpty()) {
		tmp1 = new Vector2(this.MTX.get(this.MTX.size() - 1).x * mod.x, this.MTX.get(this.MTX.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.OBJ.get(this.OBJ.size() - 1).x * mod.x, this.OBJ.get(this.OBJ.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.OBJ.get(this.OBJ.size() - 1).x * mod.x, this.OBJ.get(this.OBJ.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.CNT_RAD.get(this.CNT_RAD.size() - 1).x * mod.x,
				this.CNT_RAD.get(this.CNT_RAD.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.CNT_RAD.get(this.CNT_RAD.size() - 1).x * mod.x,
				this.CNT_RAD.get(this.CNT_RAD.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.CNT_CRS.get(this.CNT_CRS.size() - 1).x * mod.x,
				this.CNT_CRS.get(this.CNT_CRS.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.CNT_CRS.get(this.CNT_CRS.size() - 1).x * mod.x,
				this.CNT_CRS.get(this.CNT_CRS.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.R_F_outter.get(this.R_F_outter.size() - 1).x * mod.x,
				this.R_F_outter.get(this.R_F_outter.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.R_F_outter.get(this.R_F_outter.size() - 1).x * mod.x,
				this.R_F_outter.get(this.R_F_outter.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.R_F_inner.get(this.R_F_inner.size() - 1).x * mod.x,
				this.R_F_inner.get(this.R_F_inner.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.R_F_inner.get(this.R_F_inner.size() - 1).x * mod.x,
				this.R_F_inner.get(this.R_F_inner.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.F.get(this.F.size() - 1).x * mod.x, this.F.get(this.F.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.F.get(this.F.size() - 1).x * mod.x, this.F.get(this.F.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.U.get(this.U.size() - 1).x * mod.x, this.U.get(this.U.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.U.get(this.U.size() - 1).x * mod.x, this.U.get(this.U.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.A_U.get(this.A_U.size() - 1).x * mod.x, this.A_U.get(this.A_U.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.A_U.get(this.A_U.size() - 1).x * mod.x, this.A_U.get(this.A_U.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).x * mod.x,
				this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).x * mod.x,
				this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).x * mod.x,
				this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).x * mod.x,
				this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.U_F_outter.get(this.U_F_outter.size() - 1).x * mod.x,
				this.U_F_outter.get(this.U_F_outter.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.U_F_outter.get(this.U_F_outter.size() - 1).x * mod.x,
				this.U_F_outter.get(this.U_F_outter.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.U_F_inner.get(this.U_F_inner.size() - 1).x * mod.x,
				this.U_F_inner.get(this.U_F_inner.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);

		tmp1 = new Vector2(this.U_F_inner.get(this.U_F_inner.size() - 1).x * mod.x,
				this.U_F_inner.get(this.U_F_inner.size() - 1).y * mod.y);
		tmp2 = new Vector2(this.MTX.get(this.MTX.size() - 1).x * mod.x, this.MTX.get(this.MTX.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy().add(tmp1.cpy()), O.cpy().add(tmp2.cpy()), B);
		}
	}

	private void drawLinesA_1(Vector3 mod) {
		float d = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue() - 1;
		float m = M.Metranome.I.floatValue();
		Vector2 O = new Vector2(Width / 2, Height / 2);

		Vector2 tmp1;
		Vector2 tmp2;

		// LINES 0->(SIZE-1)
		Color A = (new Color(i * 0.5f, i * 0.5f, i * 0.5f, i * 0.5f));
		if(!MTX.isEmpty()) {
		tmp2 = new Vector2(this.MTX.get(this.MTX.size() - 1).x * mod.x, this.MTX.get(this.MTX.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.OBJ.get(this.OBJ.size() - 1).x * mod.x, this.OBJ.get(this.OBJ.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.CNT_RAD.get(this.CNT_RAD.size() - 1).x * mod.x,
				this.CNT_RAD.get(this.CNT_RAD.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.CNT_CRS.get(this.CNT_CRS.size() - 1).x * mod.x,
				this.CNT_CRS.get(this.CNT_CRS.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.R_F_outter.get(this.R_F_outter.size() - 1).x * mod.x,
				this.R_F_outter.get(this.R_F_outter.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.R_F_inner.get(this.R_F_inner.size() - 1).x * mod.x,
				this.R_F_inner.get(this.R_F_inner.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.F.get(this.F.size() - 1).x * mod.x, this.F.get(this.F.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.U.get(this.U.size() - 1).x * mod.x, this.U.get(this.U.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.A_U.get(this.A_U.size() - 1).x * mod.x, this.A_U.get(this.A_U.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).x * mod.x,
				this.Z_DiamClockwise.get(this.Z_DiamClockwise.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).x * mod.x,
				this.Z_DiamAnti_Clockwise.get(this.Z_DiamAnti_Clockwise.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.U_F_outter.get(this.U_F_outter.size() - 1).x * mod.x,
				this.U_F_outter.get(this.U_F_outter.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.U_F_inner.get(this.U_F_inner.size() - 1).x * mod.x,
				this.U_F_inner.get(this.U_F_inner.size() - 1).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);
		}
	}

	private void drawLinesA_0(Vector3 mod) {
		float d = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue() - 1;
		float m = M.Metranome.I.floatValue();
		Vector2 O = new Vector2(Width / 2, Height / 2);

		Vector2 tmp1;
		Vector2 tmp2;

		// LINES 0->(SIZE-1)
		
		Color A = (new Color(i * 0.5f, i * 0.5f, i * 0.5f, i * 0.5f));
		if(!this.MTX.isEmpty()) {
		tmp2 = new Vector2(this.MTX.get(0).x * mod.x, this.MTX.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);
		

		tmp2 = new Vector2(this.OBJ.get(0).x * mod.x, this.OBJ.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.CNT_RAD.get(0).x * mod.x, this.CNT_RAD.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.CNT_CRS.get(0).x * mod.x, this.CNT_CRS.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.R_F_outter.get(0).x * mod.x, this.R_F_outter.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.R_F_inner.get(0).x * mod.x, this.R_F_inner.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.F.get(0).x * mod.x, this.F.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.U.get(0).x * mod.x, this.U.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.A_U.get(0).x * mod.x, this.A_U.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.Z_DiamClockwise.get(0).x * mod.x, this.Z_DiamClockwise.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.Z_DiamAnti_Clockwise.get(0).x * mod.x, this.Z_DiamAnti_Clockwise.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.U_F_outter.get(0).x * mod.x, this.U_F_outter.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);

		tmp2 = new Vector2(this.U_F_inner.get(0).x * mod.x, this.U_F_inner.get(0).y * mod.y);
		Sketcher.Drawer.line(O.cpy(), O.cpy().add(tmp2.cpy()), A);
		}
	}

	public void drawHalo(boolean up) {
		Sketcher.setColor(1, 1, 1, 0.2f);
		for (Vector3 v : this.H) {

			int i = (H.indexOf(v));

			float g = M.iTime.I.floatValue() * M.DeltaTime.I.floatValue();
			g = M.iTime.I.floatValue() - (M.iTime.I.floatValue() * M.DeltaTime.I.floatValue());
			Sketcher.Drawer.setColor(g, g, g, (M.iTime.I.floatValue() * M.DeltaTime.I.floatValue()) * .75f);
			Sketcher.Drawer.circle(Width / 2, Height / 2, v.x * v.y * v.z);

			if (up == false) {
				Sketcher.Drawer.setColor(((v.x) % 256f), ((v.y) % 256f), (v.z % 256f),
						((M.iTime.I.floatValue() - (M.DeltaTime.I.floatValue() * i) * M.Metranome.I.floatValue())
								* M.DeltaTime.I.floatValue()) * 0.5f);
				Sketcher.Drawer.circle(Width / 2, Height / 2,
						MathUtils.clamp((M.Metranome.I.floatValue() - (M.DeltaTime.I.floatValue() * i)), 0, 1));
			}
			if (up == true) {
				Sketcher.Drawer.setColor(((v.x) % 256f), ((v.y) % 256f), ((v.z / i) % 256f),
						((M.iTime.I.floatValue() - (M.DeltaTime.I.floatValue() * i) * M.Metranome.I.floatValue())
								* M.DeltaTime.I.floatValue()) * 0.5f);

				Sketcher.Drawer.circle(Width / 2, Height / 2, MathUtils.clamp(v.z, 0, 1));
			}
		}

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
