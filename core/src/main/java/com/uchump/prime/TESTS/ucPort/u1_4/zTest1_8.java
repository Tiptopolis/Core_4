package com.uchump.prime.TESTS.ucPort.u1_4;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.UI.Camera.pOrthoAdapter;
import com.uchump.prime.CORE.UI.Camera.uFpsAdapter;
import com.uchump.prime.CORE.UI.Camera.A_I.pCameraAdapter;
import com.uchump.prime.CORE.UI.Events.aEvent;
import com.uchump.prime.CORE.UI.Events.uEvent;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uButton;
import com.uchump.prime._PRIME.METATRON.M.MetatronConsole;
import com.uchump.prime._PRIME.METATRON.M.MetatronShell;
import com.uchump.prime._PRIME.METATRON.M.Boot.MetatronBoot;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME._coPRIME.N_M.VectorUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class zTest1_8 extends uApp {
	int i = 0;
	public boolean toCamera = false;

	public pOrthoAdapter groundCam;
	public pCameraAdapter fpsAdapter;

	Texture test = new Texture(Gdx.files.internal("TST/TestInput/LandWater.png"));;

	public ArrayList<Vector3> gridCells = new ArrayList<Vector3>();

	public zTest1_8() {

	}

	@Override
	public void create() {
		super.create();
		this.buildUI();
		uChumpEngine.oLF.isOn = false;

		this.groundCam = new pOrthoAdapter(uChumpEngine.CAMERA);
		this.groundCam.init();
		this.fpsAdapter = new uFpsAdapter(uChumpEngine.CAMERA); // Standard First-Person
		this.fpsAdapter.init();

		this.genGrid(100, 100);

	}

	@Override
	public void update() {
		// Log(backingMatrix.toLog());
		// Log(i);
		// i++;
		if (i >= 1)
			M.Shell.launch(new MetatronBoot(M));
		super.update();

	}

	@Override
	public void render() {

		// Log("-M_Boot.render()");

		// this.toCamera = true;

		// Draws UI to WORLD as per oLF_Renderer3, input remains accurate
		if (this.running) {

			Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
			Vector3 pos = uChumpEngine.CAMERA.Camera.getOfficialPosition();
			Vector3 dir = uChumpEngine.CAMERA.Camera.camera.direction;
			Vector3 up = uChumpEngine.CAMERA.Camera.camera.up;
			Vector3 right = dir.cpy().nor().crs(up.cpy());
			Vector3 mouseRaw = new Vector3(MouseX, MouseY, 1);
			Vector3 mousePrj = new Vector3(MouseX, MouseY, 1);
			// Log("METATRON BOOT RDR " + M.DeltaTime.I);

			if (this.toCamera) {
				Sketcher.setProjectionMatrix(uChumpEngine.CAMERA.getProjection());
				Stage.getViewport().setCamera(uChumpEngine.CAMERA.getBaseCamera());
				uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);

			} else // Draws to Screen-Space like a normal fukin UI, <---the DefaultInitial setting
					// on startup
			{
				Stage.getViewport().setCamera(((uStage) Stage).StageCamera);
				Sketcher.setProjectionMatrix(((uStage) Stage).StageCamera.combined);
				((uStage) Stage).StageCamera.unproject(mousePrj);

			}

			Sketcher.begin();
			super.render();
			Sketcher.setColor(Color.MAGENTA);
			Sketcher.setColor(Color.YELLOW);
			Sketcher.Drawer.rectangle(0, 0, Stage.getWidth(), Stage.getHeight());

			Color d = new Color(1, 1, 1, 0.1f);
			this.drawGrid(0, d);
			this.drawGrid(1, d);
			this.drawGrid(2, d);
			// this.drawTrans(uChumpEngine.CAMERA.getTransform(), Color.WHITE, Color.WHITE);
			this.drawTrans(this.fpsAdapter.getTransform(), Color.YELLOW, Color.YELLOW);
			this.drawTrans(this.groundCam.getTransform(), Color.MAGENTA, Color.MAGENTA);
			this.drawCam(this.fpsAdapter.getBaseCamera(), Color.ORANGE, Color.ORANGE);
			this.drawCam(this.groundCam.getBaseCamera(), Color.TEAL, Color.TEAL);

			Sketcher.setProjectionMatrix(uChumpEngine.CAMERA.getProjection());
			this.drawGrid(0, Color.RED);
			// this.drawTrans(uChumpEngine.CAMERA.getTransform(), Color.DARK_GRAY,
			// Color.DARK_GRAY);
			// this.drawCam(uChumpEngine.CAMERA.getBaseCamera(), Color.DARK_GRAY,
			// Color.DARK_GRAY);
			Sketcher.setProjectionMatrix(this.groundCam.getProjection());
			this.drawGrid(1, Color.GREEN);

			Sketcher.setProjectionMatrix(this.fpsAdapter.getProjection());
			this.drawGrid(2, Color.BLUE);

			Sketcher.end();

			// LOD
			/*
			 * if ((Monitor.width * Monitor.height) * Renderer.C.Camera.zoom <
			 * (Monitor.width * Monitor.height) / (((MathUtils.PI / MathUtils.PI2) *
			 * Renderer.C.Camera.zoom))) {
			 * 
			 * }
			 */
			// t.setFromMatrix(new Matrix4().set(new Vector3(dir.x,0,0), new
			// Vector3(0,dir.y,0), new Vector3(0,0,dir.z), pos));
			// mainCam.setFromCamera(uChumpEngine.CAMERA.Camera.camera);
			Color c = new Color();

			////

		}
	}

	private void buildUI() {

		uButton ML = new uButton("oLF_MODE") {
			@Override
			public void press() {
				uEvent MainAppChangeMode = new uEvent();
				MainAppChangeMode.setSender(this);
				MainAppChangeMode.setTarget(uChumpEngine.MainApp);
				MainAppChangeMode.setContent("TOGGLE_MODE_C");

				uChumpEngine.MainApp.handle(MainAppChangeMode);
			}

		};

		Stage.addActor(ML);
		ML.setSize(32, 32);
		ML.Transform.SetPosition(new Vector3(-ML.getWidth() / 2, -ML.getHeight() / 2, 1));
		ML.inner = Color.CLEAR;
		ML.outter = Color.TEAL;

		// ML.Transform.SetLocalScale(new Vector3(64, 64, 1));
		ML.show();

		uButton mRtn = new uButton("mReturn") {
			@Override
			public void press() {
				// MetatronShell.launch(new MetatronBoot(Metatron.TheMetatron));
				uEvent MainAppChangeMode = new uEvent();
				MainAppChangeMode.setSender(this);
				MainAppChangeMode.setTarget(MetatronShell.mShell);
				MainAppChangeMode.setContent("SHELL:RESTART");

				MetatronShell.mShell.handle(MainAppChangeMode);
			}
		};
		this.Stage.addActor(mRtn);
		mRtn.setSize(32, 32);
		mRtn.Transform.SetPosition(new Vector3(0, Height, 1));
		mRtn.inner = Color.RED;
		mRtn.show(); // just the button, lol
	}

	@Override
	public void pause() {
		uChumpEngine.oLF.isOn = true;
		uChumpEngine.CAMERA.removeAdapter(this.groundCam);
		this.groundCam.isOn = false;
		uChumpEngine.CAMERA.removeAdapter(this.fpsAdapter);
		this.fpsAdapter.isOn = false;
		super.pause();
	}

	@Override
	public void resume() {

		super.resume();
		Log("Metatron ON");
		uChumpEngine.oLF.isOn = false;
		MetatronConsole.post(this.toLog());
		this.groundCam.init();
		this.fpsAdapter.init();
		uChumpEngine.CAMERA.addAdapter(this.groundCam);
		uChumpEngine.CAMERA.addAdapter(this.fpsAdapter);

	}

	@Override
	public void dispose() {
		uChumpEngine.oLF.isOn = true;
		super.dispose();

	}

	@Override
	public String toString() {
		return "[uApp:MetatronBoot]";
	}

	@Override
	public String toLog() {
		String log = "\n";
		log += "MetatronBootLog:START                                       <&%$&>";
		log += "\n";
		if (this.Stage instanceof uStage)
			log += ((uStage) this.Stage).toLog() + "\n";

		log += "\n";
		// log += qAnchor.DefaultScreenAnchors.toLog();
		log += "\n";
		// log += qAnchor.DefaultScreenAnchors.Get("SCREEN_TOP_LEF");
		log += "\n";
		// log += qAnchor.DefaultScreenAnchors.Get("SCREEN_TOP_LEFT");
		log += "\n";
		// log += qAnchor.DefaultScreenAnchors.Get("SCREEN_TOP_LEFT").getValue();
		log += "\n";
		log += "MetatronBootLog:END <&%$&>";

		return log;
	}

	private void shiftInput() {

		this.groundCam.isOn = !this.groundCam.isOn;
		this.fpsAdapter.isOn = !this.fpsAdapter.isOn;
		uChumpEngine.CAMERA.isOn = !uChumpEngine.CAMERA.isOn;

	}

	@Override
	public boolean keyDown(int keycode) {

		Log(">>oLF_Renderer3.keyDown()  " + keycode);
		if (keycode == Keys.ESCAPE) {
			return this.handle(new uEvent("TOGGLE_MODE_C"));
		}

		return false;
	}

	@Override
	public boolean handle(aEvent event) {
		if (event.content.equals("TOGGLE_MODE_C")) {
			this.toCamera = !this.toCamera;
			this.shiftInput();
			Log("::" + event.content);
			return true;
		}

		return false;
	}

	////
	//
	public void genGrid(int width, int height) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Vector3 at = new Vector3(x, y, 0);
				this.gridCells.add(at);
			}
		}

	}

	public void drawGrid(int op, Color c) {
		float cellSize = 32;

		Vector3 prj = uChumpEngine.CAMERA.Camera.getOfficialPosition();

		for (Vector3 v : gridCells) {
			Vector3 at = new Vector3(v.x * cellSize, v.y * cellSize, 64);
			Sketcher.setColor(c);
			Sketcher.Drawer.rectangle(at.x, at.y, cellSize, cellSize);
			// pCon.perspective.camera.far ->100 for now
			if (Math.abs(prj.dst(at)) < (300 * uChumpEngine.CAMERA.Camera.zoom) / 2) {
				Vector3 radius = new Vector3(cellSize / 2, cellSize / 2, 0);
				Vector3 rotation = new Vector3(0, 0, 0);
				Color color = new Color(0.5f, 0.5f, 0.5f, 0.25f);
				int n = 4;
				BoundShape rect = BoundShape.bindShape(at, radius, rotation, color, n);
				rect.drawShape();
				Vector3 last;

				switch (op) {
				case 0:
					last = rect.shape.get(0);
					Sketcher.setColor(0.6f, 0.3f, 0.6f, 0.5f);
					for (Vector3 t : rect.shape) {
						Sketcher.Drawer.filledCircle(t.x, t.y, 2);
					}

					Sketcher.Drawer.filledCircle(last.x, last.y, 4);
					break;
				case 1:

					last = rect.shape.get(0);
					Sketcher.setColor(0.3f, 0.6f, 0.3f, 0.5f);
					for (Vector3 t : rect.shape) {
						Sketcher.Drawer.filledCircle(t.x, t.y, 2);
					}
					Sketcher.Drawer.circle(last.x, last.y, 4);
					break;

				case 2:

					last = rect.shape.get(0);
					Sketcher.setColor(0.3f, 0.3f, 0.6f, 0.5f);
					for (Vector3 t : rect.shape) {
						Sketcher.Drawer.filledRectangle(t.x - 1, t.y - 1, 2, 2);
					}
					Sketcher.Drawer.rectangle(last.x - 2, last.y - 2, 4, 4);
					break;
				}

			}
		}

	}

	public void drawTrans(uTransform t, Color c, Color k) {

		Vector3 pos = t.GetLocalPosition();
		Vector3 dir = VectorUtils.downcast(t.GetLocalRotation()).nor();
		Vector3 scl = t.GetLocalScale();

		BoundShape cLine = BoundShape.bindRadius(pos, new Vector3().add(MathUtils.PI2), dir, c);
		cLine.fillColor = c;
		cLine.lineColor = c;
		cLine.drawShape();

		Sketcher.Drawer.setDefaultLineWidth(1);
		Sketcher.Drawer.circle(pos.x, pos.y, 16);
		Sketcher.setColor(k);
		Sketcher.Drawer.circle(pos.x, pos.y, scl.x + MathUtils.PI2, MathUtils.PI2);

	}

	public void drawCam(Camera C, Color c, Color k) {
		Vector3 pos = C.position.cpy();
		Vector3 dir = C.direction.cpy();
		Vector3 up = C.up.cpy();
		Vector3 right = dir.cpy().crs(up.cpy()).nor();

		Vector3 localForward = pos.cpy().add(dir.cpy().scl(32));

		BoundShape oLine = BoundShape.bindLine(pos.cpy(), localForward.cpy());
		oLine.fillColor = c.cpy().mul(k.cpy().add(c.cpy()));

		oLine.drawShape();
		BoundShape cHex = BoundShape.bindShape(C.position.cpy(), new Vector3().add(32), C.direction.cpy(), k, 2, 2);

		cHex.lineColor = c;
		// cHex.drawShape();

		Color K = new Color(1, 0, 0, 0.25f);
		Vector3 mS = new Vector3().add(32);
		Vector3 mAt = new Vector3(MouseX, MouseY, 0);
		Vector3 scrnCtr = new Vector3(Width / 2, Height / 2, 0);
		Vector3 mDir = new Vector3();
		float mAngle = 0;

		uChumpEngine.CAMERA.Camera.camera.unproject(mAt);
		uChumpEngine.CAMERA.Camera.camera.project(mS.cpy().scl(uChumpEngine.CAMERA.Camera.getDirection().cpy()));

		Vector3 ctrPrj = uChumpEngine.CAMERA.Camera.camera.unproject(scrnCtr.cpy());
		mDir = VectorUtils.dir(ctrPrj, mAt.cpy());
		mAngle = (float) Math.atan2(mDir.x * -1, mDir.y * -1);

		BoundShape mHex = BoundShape.bindShape(mAt, mS, mDir, k, 6);
		mHex.drawShape();

	}
}
