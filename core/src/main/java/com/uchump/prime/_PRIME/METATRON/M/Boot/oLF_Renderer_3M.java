package com.uchump.prime._PRIME.METATRON.M.Boot;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.UI.Camera.OrthoController;
import com.uchump.prime.CORE.UI.Camera.pOrthoAdapter;
import com.uchump.prime.CORE.UI.Camera.uFpsAdapter;
import com.uchump.prime.CORE.UI.Camera.A_I.pCameraAdapter;
import com.uchump.prime.CORE.UI.Events.uEvent;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.M.File.OroborosList;
import com.uchump.prime._PRIME.METATRON.M.File.Rect;
import com.uchump.prime._PRIME._coPRIME.N_M.VectorUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class oLF_Renderer_3M extends InputAdapter {

	public boolean isOn = false;
	public boolean lensUp = true;
	public boolean dL2 = true;
	public boolean dI = true;

	public Rect Monitor;
	public Vector2 graphScale; // graph area is an absolute rect 0-1
	public Metatron M;
	public OrthoController C;

	public LuciferSprite L2;
	uFpsAdapter gCam;

	uTransform g;
	uTransform o;

	private OroborosList<Vector3> wav;
	private OroborosList<Vector3> wav2;
	// int cInner = %360
	// int cOutter = %180

	public static float CellSize = (float) Math.PI * 2;

	public oLF_Renderer_3M(Rect M, Vector2 scl, OrthoController c) {

		this.Monitor = M;
		this.C = c;
		this.M = Metatron.TheMetatron;
		// this.M.Shell.addProcessor(this);

		wav = new OroborosList<Vector3>(1, Width, 1);
		wav2 = new OroborosList<Vector3>(1, Width, 1);
		this.L2 = new LuciferSprite(this, 360);
		C.addAdapter(this);
		// gCam = new uFpsAdapter(this.C);
		// gCam.init();
		this.isOn = true;

	}

	public void update() {
		// this.gCam.update();

		Vector3 mAt = new Vector3(MouseX, MouseY, 0);
		Vector3 scrnCtr = new Vector3(Width / 2, Height / 2, 0);
		Vector3 mAtPrj = C.Camera.camera.unproject(mAt.cpy());
		Vector3 ctrPrj = C.Camera.camera.unproject(scrnCtr.cpy());
		Vector3 mDir = VectorUtils.dir(scrnCtr.cpy(), new Vector3(MouseX, Height - MouseY, 0));

		float mAngle = (float) Math.atan2(mDir.x * -1, mDir.y * -1);

		// L.TRANSFORM.SetLocalPosition(mAtPrj.cpy());

		// Log(".{([UPDATE])}:");
		if (dL2) {
			this.L2.TRANSFORM.SetLocalPosition(mAtPrj);
			this.L2.TRANSFORM.SetLocalRotation(VectorUtils.upcast(mDir.cpy()));
			this.L2.TRANSFORM.SetScale(new Vector3(1, 1, 1));
			this.L2.update(new Quaternion(mAtPrj.x, mAtPrj.y, mAtPrj.z, mAngle));
		}
		// Log(":{([UPDATED])}*");

		float f = M.TheMetatron.RealSecond.I.floatValue() % ((float) (Math.PI) * 2);
		float d = M.TheMetatron.DeltaTime.I.floatValue();
		wav.add(new Vector3(f - (CellSize), ((Monitor.height / 2)) + (MathUtils.sin(f)) - (CellSize), d * -1));

		// Monitor.maxX-(Monitor.width/2),Monitor.maxY-(Monitor.height/2)
		f = M.TheMetatron.RealSecond.I.floatValue() % ((float) (Math.PI));
		d = M.TheMetatron.DeltaTime.I.floatValue();
		wav2.add(new Vector3(f - (Monitor.maxX + (Monitor.width / 2)),
				(MathUtils.sin(f)) - ((Monitor.height / 2) + (Monitor.maxY)), d * -1));
		// Log(L2.toLog());
		// Log(C.toLog());
		// if(gCam != null)
		// Log(gCam.toLog());
	}

	public void render() {
		// Log(".{([RENDER])}_");
		Sketcher.setProjectionMatrix(C.getProjection());
		Sketcher.Drawer.setDefaultLineWidth(C.Camera.zoom);
		Sketcher.begin();
		Sketcher.setColor(Color.BLACK);
		Sketcher.Drawer.filledRectangle(Monitor.getRectangle());
		Sketcher.setColor(Color.MAGENTA);
		Sketcher.Drawer.rectangle(Monitor.getRectangle());
		Sketcher.setColor(Color.TEAL);
		Sketcher.Drawer.rectangle(0, 0, Width, Height);// WorldUnits

		Sketcher.setColor(new Color(0.5f, 0.1f, 0.1f, 0.1f));
		Vector2 v = new Vector2(MathUtils.PI, MathUtils.PI);
		Sketcher.Drawer.rectangle(0, 0, v.x, v.y);
		Sketcher.setColor(new Color(0.25f, 0.1f, 0.1f, 0.25f));
		v = new Vector2(this.CellSize / 2, this.CellSize / 2);
		Sketcher.Drawer.filledRectangle(0, 0, v.x, v.y);

		Sketcher.setColor(new Color(0.1f, 0.25f, 0.1f, 0.25f));
		v = new Vector2(MathUtils.PI, MathUtils.PI);
		Sketcher.Drawer.rectangle(Monitor.maxX - (Monitor.width / 2), Monitor.maxY - (Monitor.height / 2), v.x, v.y);

		Rect C_view = C.Camera.getViewRect();
		C_view.set(new Vector2(0, 0), C_view.getSize());
		Color cc = Color.ORANGE;
		cc.a = 0.1f;
		Sketcher.setColor(cc);
		Sketcher.Drawer.rectangle(C_view.getRectangle());

		float d = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue() - 1;
		float m = M.Metranome.I.floatValue();

		Sketcher.end();

		// Log("_{([RENDERED])}*");

		if (dL2)
			this.L2.render();
		Color c = new Color(0.1f, 0.1f, 0.1f, 0.5f);// fills to 1 lol

		Sketcher.setProjectionMatrix(C.getProjection());
		Sketcher.Drawer.setDefaultLineWidth(C.Camera.zoom);
		Sketcher.begin();

		// BoundShape cHex = BoundShape.bindShape(L2.TRANSFORM, c, 3, 2 *
		// C.Camera.zoom);
		// cHex.lineWidth = C.Camera.zoom;
		// cHex.drawShape();

		if ((Monitor.width * Monitor.height) * C.Camera.zoom < (Monitor.width * Monitor.height)
				/ (((MathUtils.PI / MathUtils.PI2) * C.Camera.zoom)))
			for (Vector3 V : wav) {
				int k = wav.indexOf(V);
				// Sketcher.Drawer.setColor(((V.x) % 256f), ((V.y) % 256f), (V.z % 256f), ((V.z)
				// % 256f) * .25f);
				Sketcher.Drawer.setColor(((k / V.x) % 1 - k), ((k / V.y) % 1 - k), (k / V.z % 1 - k),
						C.Camera.zoom - M.DeltaTime.I.floatValue());
				Sketcher.Drawer.circle(V.x, V.y, 0.1f);
			}
		// else Log("NAR NAR NAR"); visibility lod

		if ((Monitor.width * Monitor.height) * C.Camera.zoom < (Monitor.width * Monitor.height)
				/ (((MathUtils.PI2 / MathUtils.PI) * C.Camera.zoom)))
			for (Vector3 V : wav2) {
				int k = wav.indexOf(V);
				Sketcher.Drawer.setColor((float) (Math.PI / (V.x) % 256f), (float) (Math.PI / (V.y) % 256f),
						(float) (Math.PI / (V.z % 256f)), (float) (Math.PI / (V.z) % 256f) * .25f);
				Sketcher.Drawer.circle(V.x, V.y, 0.1f);
			}

		if (dL2)
			this.L2.draw();

		// Rect bspO = new Rect(MouseX,MouseY,MathUtils.PI*10, MathUtils.PI*5);
		// Rect bspI = new
		// Rect(bspO.width/2,bspO.height/2,MathUtils.PI*BSP.Nodes.size(),MathUtils.PI*BSP.Nodes.size());

		Sketcher.end();

	}

	public void resize() {
		// this.Monitor = new Rect(0, 0, Width, Height);

		this.Monitor = new Rect(-CellSize, -CellSize, CellSize, CellSize);
		this.keyDown(Keys.ALT_LEFT);

	}

	////
	// INPUT_PROCESSOR
	@Override
	public boolean keyDown(int keycode) {
		if (!this.isOn)
			return false;
		Log(">>oLF_Renderer3.keyDown()  " + keycode);
		if (keycode == Keys.ESCAPE) {
			uEvent MainAppChangeMode = new uEvent();
			MainAppChangeMode.setSender(this);
			MainAppChangeMode.setTarget(uChumpEngine.MainApp);
			MainAppChangeMode.setContent("TOGGLE_MODE_C");

			return uChumpEngine.MainApp.handle(MainAppChangeMode);
		}

		if (keycode == Keys.CONTROL_LEFT && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			this.C.Camera.setPosition((Width / 2) - (CellSize / 2), (Height / 2) - (CellSize / 2));
			return true;
		}
		if (keycode == Keys.ALT_LEFT && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			this.C.Camera.setPosition((Monitor.width / 2) - (CellSize / 2), (Monitor.height / 2) - (CellSize / 2));
			return true;
		}

		return false;

	}

	@Override
	public boolean keyUp(int keycode) {
		if (!this.isOn)
			return false;
		if (keycode == Keys.CONTROL_LEFT && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			this.C.Camera.zoom = 1f;
			this.C.Camera.setPosition((Width / 2) - (CellSize / 2), (Height / 2) - (CellSize / 2));
			return true;

		}

		if (keycode == Keys.ALT_LEFT && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			this.C.Camera.zoom = 1f;
			this.C.Camera.setPosition((Monitor.width / 2) - (CellSize / 2), (Monitor.height / 2) - (CellSize / 2));
			return true;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {


		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	public void dispose() {
		C.removeAdapter(this);
		L2.dispose();
		this.isOn = false;

	}

}
