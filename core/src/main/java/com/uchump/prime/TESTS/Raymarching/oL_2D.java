package com.uchump.prime.TESTS.Raymarching;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.UI.Events.aEvent;
import com.uchump.prime.CORE.UI.Events.uEvent;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uButton;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uWidget;
import com.uchump.prime.TESTS.Raymarching.UselesssNonsense.SDF_BS;
import com.uchump.prime.TESTS.Raymarching.UselesssNonsense.SDF_Maths_F.Box;
import com.uchump.prime.TESTS.Raymarching.UselesssNonsense.SDF_Maths_F.Circle;
import com.uchump.prime.TESTS.Raymarching.iSDF.SDF_2D;
import com.uchump.prime._PRIME.METATRON.M.MetatronConsole;
import com.uchump.prime._PRIME.METATRON.M.MetatronShell;
import com.uchump.prime._PRIME.METATRON.M.Boot.MetatronBoot;
import com.uchump.prime._PRIME.METATRON.M.Boot.oLF_Renderer_3M;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME.METATRON.Signals.Message;
import com.uchump.prime._PRIME._coPRIME.N_M.VectorUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class oL_2D extends uApp {

	int i = 0;
	private oLF_Renderer_3M Renderer;

	public boolean toggleLens;
	public boolean toCamera = false;

	private Vector3 viewPrj = new Vector3(Width / 2, Height / 2, 1);
	private Vector3 mousePrj = new Vector3(MouseX, MouseY, 1);
	@Override
	public void create() {
		if (SDF_Env.Caster == null)
			SDF_Env.Caster = uChumpEngine.CAMERA.getTransform();
		this.Renderer = uChumpEngine.oLF;
		if (!this.Renderer.isOn)
			this.Renderer.isOn = true;

		Log("LET THERE BE LIGHT!!!  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Renderer.dI = false;
		super.create();
		this.buildUI();
	}

	@Override
	public void update() {
		// Log(i);
		// i++;
		if (i >= 1)
			M.Shell.launch(new MetatronBoot(M));
		super.update();
		// this.CORE_L.update();

		// Log("
		// <<<<<<<<<<<<<<<<<<<===================================--------------------------------------------<<<<<<<"
		// + M.DeltaTime.I.floatValue());

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		// Log("-M_Boot.render()");
		
		// Draws UI to WORLD as per oLF_Renderer3, input remains accurate
		Vector3 mouseRaw = new Vector3(MouseX, MouseY, 1);
		this.mousePrj = new Vector3(MouseX, MouseY, 1);
		Vector3 viewRaw = new Vector3(Width / 2, Height / 2, 1);
		this.viewPrj = new Vector3(Width / 2, Height / 2, 1);
		// this.toCamera = true;
	


		// Draws UI to WORLD as per oLF_Renderer3, input remains accurate
		if (this.running) {
			// Log("METATRON BOOT RDR " + M.DeltaTime.I);

			if (this.toCamera) {
				Sketcher.setProjectionMatrix(uChumpEngine.CAMERA.getProjection());
				Stage.getViewport().setCamera(uChumpEngine.CAMERA.getBaseCamera());
				uChumpEngine.CAMERA.getBaseCamera().unproject(this.mousePrj);
				uChumpEngine.CAMERA.getBaseCamera().unproject(this.viewPrj);
				// Log("OOGABOOGA");

			} else // Draws to Screen-Space like a normal fukin UI
			{
				Stage.getViewport().setCamera(((uStage) Stage).StageCamera);
				Sketcher.setProjectionMatrix(((uStage) Stage).StageCamera.combined);
				// ((uStage) Stage).StageCamera.unproject(mousePrj);
				uChumpEngine.CAMERA.getBaseCamera().unproject(this.mousePrj);
				((uStage) Stage).StageCamera.unproject(this.viewPrj);
			}

			Vector3 mDir = VectorUtils.dir(this.mousePrj.cpy(), this.viewPrj.cpy()).scl(-1);
			float mAngle = (float) Math.atan2(mDir.x * -1, mDir.y * -1);

			if(SDF_Env._All!= null)
			{
				for(SDFObject s : SDF_Env._All)
				{
					//Log(s.toString());
					//Log("RAW_"+mouseRaw);
					//Log("PRJ_"+mousePrj);
					Log("#: "+s.dst(new uVector(new Float[] {mousePrj.x,mousePrj.y,mousePrj.z})));
				}
			}
			
			
			
			Sketcher.begin();
			super.render();
			Sketcher.setColor(Color.MAGENTA);
			Sketcher.Drawer.rectangle(0, 0, Stage.getWidth(), Stage.getHeight());

			Sketcher.setProjectionMatrix(uChumpEngine.CAMERA.getProjection());

			// float dst = SDF_BS.sigDstToScene(); // Set Ray length here
			float dst = 500;
			BoundShape P_M = BoundShape.bindRadius(this.viewPrj,
					new Vector3(dst, dst, MathUtils.PI * uChumpEngine.CAMERA.Camera.zoom), mDir, Color.RED);

			if ((Monitor.width * Monitor.height) * Renderer.C.Camera.zoom < 5 * (Monitor.width * Monitor.height)
					/ (((MathUtils.PI / MathUtils.PI2) * Renderer.C.Camera.zoom)))
				RayRay(P_M); // using viewRaw to lock it in place, using viewPrj inverts the perspective
			
			Sketcher.end();
		}
		// if (Sketcher.isDrawing) // ?? stops it crashing tho
		// Sketcher.end();

	}

	private void RayRay(BoundShape P_M) {
		for (BoundShape b : SDF_Env._All) {
			b.setColor(Color.TEAL);
			b.drawShape();
		}

		P_M.drawShape();
	}

	public void spawnShape(Vector3 v) {
		mousePrj = new Vector3(MouseX, MouseY, 1);
		uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);
		v=mousePrj.cpy();
		if (this.toCamera) {
			uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);
			uChumpEngine.CAMERA.getBaseCamera().unproject(viewPrj);

		} else {
			uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);
			((uStage) Stage).StageCamera.unproject(viewPrj);
		}
		Vector3 mDir = VectorUtils.dir(mousePrj.cpy(), viewPrj.cpy());

		// BoundShape ns = BoundShape.bindShape(v.cpy(), new Vector3(MathUtils.PI2, 5,
		// MathUtils.PI2), new Vector3(1,0,0).crs(mDir.cpy().nor()),Color.BLUE, 12);
		// SDF_BS.ShapesInSpace.add(BoundShape.bindShape(v.cpy(), new
		// Vector3(MathUtils.PI2, MathUtils.PI2, MathUtils.PI2), new
		// Vector3(1,0,0).crs(mDir.cpy().nor()),
		// Color.BLUE, 3));
		// SDF_BS.addNewShape(ns);

		SDFObject sdf = new SDFObject(new uVector(v.cpy()).V3(), SDF_2D.CIRCLE);
		SDF_Env.addNewSDF(sdf, sdf.Collider);
	}

	public void deleteShape(Vector3 v) {
		mousePrj = new Vector3(MouseX, MouseY, 1);
		uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);
		v=mousePrj.cpy();
		if (this.toCamera) {
			uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);
			uChumpEngine.CAMERA.getBaseCamera().unproject(viewPrj);

		} else {
			uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);
			((uStage) Stage).StageCamera.unproject(viewPrj);
		}
		uVector vM = new uVector(new Float[] {mousePrj.x,mousePrj.y,mousePrj.z});
		Vector3 mDir = VectorUtils.dir(mousePrj.cpy(), viewPrj.cpy());
		
		for (BoundShape S : SDF_Env._All) {
			SDFObject s = (SDFObject)S;
			Log(":::::");
			Log("#: "+s.dst(new uVector(new Float[] {v.x,v.y,v.z})));
			//vM.gdxV3()
			Log(s.position);
			if (s.vertexNum >= 3) {
				if (s.contains(new Vector2(v.x, v.y))) {
					int i = SDF_Env._All.indexOf(s);
					SDF_Env.Index_All.remove(i);
					SDF_Env._All.remove(s);
					return;

				}
			//} else if (s.dst(new uVector(new Float[] {v.x,v.y,v.z}))<(MathUtils.PI)) {
			} else if (s.dst(new uVector(new Float[] {v.x,v.y,1f}))<(1)) {
				int i = SDF_Env._All.indexOf(s);
				SDF_Env.Index_All.remove(i);
				SDF_Env._All.remove(s);
				Log(";;;;");
				Log(s.getType());
				return;
			}
		}
		return;
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
		super.pause();
		uChumpEngine.oLF.isOn = false;
	}

	@Override
	public void resume() {

		super.resume();
		// SDF_BS.setRaycastOrigin(uChumpEngine.CAMERA.getTransform());
		Log("Metatron ON");
		uChumpEngine.oLF.isOn = true;
		uChumpEngine.oLF.dL2 = false;
		MetatronConsole.post(this.toLog());

	}

	@Override
	public void dispose() {
		// SDF_Maths_F.boxes.clear();
		// SDF_Maths_F.circles.clear();
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

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		Vector3 v = uChumpEngine.CAMERA.getBaseCamera().unproject(new Vector3(screenX, screenY, 1));
		Log(v + ">");
		Vector2 mAt = new Vector2(v.x, v.y);

		Log("BUTTS " + mAt);
		if (button == Input.Buttons.LEFT && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {

			spawnShape(v);
		}
		if (button == Input.Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {

			deleteShape(v);
		}
		return false;
	}

	@Override
	public boolean handle(Message m) {
		// Log(m+"::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		if (m.content.equals("toggle")) {
			this.toggleLens = !this.toggleLens;
			return true;
		}
		if (m.content.equals("on")) {
			this.toggleLens = false;
			return true;
		}
		if (m.content.equals("off")) {
			this.toggleLens = false;
			return true;
		}

		return false;
	}

	@Override
	public boolean handle(aEvent event) {
		if (event.content.equals("TOGGLE_MODE_C")) {
			this.toCamera = !this.toCamera;
			Log("::" + event.content);
			return true;
		}

		return false;
	}
}
