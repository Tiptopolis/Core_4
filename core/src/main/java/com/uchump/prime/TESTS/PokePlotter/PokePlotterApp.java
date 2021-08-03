package com.uchump.prime.TESTS.PokePlotter;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.UI.Events.aEvent;
import com.uchump.prime.CORE.UI.Events.uEvent;
import com.uchump.prime.CORE.UI.Events.aEventManager.StdInputEvents;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.PokeBall;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uButton;
import com.uchump.prime.TESTS.MatrixBox.MatrixDrawable;
import com.uchump.prime._PRIME.METATRON.M.MetatronConsole;
import com.uchump.prime._PRIME.METATRON.M.MetatronShell;
import com.uchump.prime._PRIME.METATRON.M.Boot.MetatronBoot;
import com.uchump.prime._PRIME.METATRON.M.Boot.oLF_Renderer_3M;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME.METATRON.Signals.Message;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class PokePlotterApp extends uApp {

	int i = 0;
	private oLF_Renderer_3M Renderer;
	public boolean toCamera = false;

	@Override
	public void create() {

		this.Renderer = uChumpEngine.oLF;
		if (!this.Renderer.isOn)
			this.Renderer.isOn = true;

		Log("LET THERE BE LIGHT!!!  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Renderer.dI = false;
		super.create();
		this.buildUI();

		// this.CORE_M = new MatrixDrawable(10, Maths.E(10)) {
		/*
		 * this.CORE_M = new MatrixDrawable(10, 0.5f) {
		 * 
		 * @Override public uVector unfold(Number type) { float t = type.floatValue();
		 * //t *= ((float) Math.sin(this.elements.size())); return new uVector(t); } };
		 */

		// this.CORE_M = new MatrixDrawable(13,0.5f);
		// this.CORE_M = new MatrixDrawable(((uStage) Stage).StageCamera.combined);
	}

	@Override
	public void update() {
		// Log(backingMatrix.toLog());
		// Log(i);
		// i++;
		if (i >= 1)
			M.Shell.launch(new MetatronBoot(M));
		super.update();

		// this.CORE_M.update();

		// Log("
		// <<<<<<<<<<<<<<<<<<<===================================--------------------------------------------<<<<<<<"
		// + M.DeltaTime.I.floatValue());

		SkinLoader L;

	}

	@Override
	public void render() {

		// Log("-M_Boot.render()");

		// this.toCamera = true;
		Vector3 mouseRaw = new Vector3(MouseX, MouseY, 1);
		Vector3 mousePrj = new Vector3(MouseX, MouseY, 1);
		// Draws UI to WORLD as per oLF_Renderer3, input remains accurate
		if (this.running) {

			// Log("METATRON BOOT RDR " + M.DeltaTime.I);

			if (this.toCamera) {
				Sketcher.setProjectionMatrix(uChumpEngine.CAMERA.getProjection());
				Stage.getViewport().setCamera(uChumpEngine.CAMERA.getBaseCamera());
				uChumpEngine.CAMERA.getBaseCamera().unproject(mousePrj);

			} else // Draws to Screen-Space like a normal fukin UI
			{
				Stage.getViewport().setCamera(((uStage) Stage).StageCamera);
				Sketcher.setProjectionMatrix(((uStage) Stage).StageCamera.combined);
				((uStage) Stage).StageCamera.unproject(mousePrj);
			}

			Sketcher.begin();
			super.render();
			Sketcher.setColor(Color.MAGENTA);
			Sketcher.Drawer.rectangle(0, 0, Stage.getWidth(), Stage.getHeight());
			BoundShape s = BoundShape.bindShape(mousePrj, new Vector3(8, 8, 8), Color.ORANGE, 4);
			s.drawShape();
			// for(xIndex.Entry E : vAnchor.DefaultScreenAnchors)
			// {

			// }

			// LOD
			// if ((Monitor.width * Monitor.height) * Renderer.C.Camera.zoom <
			// (Monitor.width * Monitor.height)
			/// (((MathUtils.PI / MathUtils.PI2) * Renderer.C.Camera.zoom))) {

			// }

			// DRAW
			Sketcher.end();
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
		super.pause();
		uChumpEngine.oLF.isOn = false;
	}

	@Override
	public void resume() {

		super.resume();
		Log("Metatron ON");
		uChumpEngine.oLF.isOn = true;
		uChumpEngine.oLF.dL2 = false;
		MetatronConsole.post(this.toLog());

	}

	@Override
	public void dispose() {

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

		Vector3 v = uChumpEngine.CAMERA.getBaseCamera().unproject(new Vector3(screenX, screenY, button));
		Log(v + ">");
		Vector2 mAt = new Vector2(v.x, v.y);
		Log(mAt + ">-(" + screenX + "," + (Height - screenY) + ")");

		if (button == Input.Buttons.RIGHT) {
			Log("BUTTS " + mAt);
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				PokeBall b = new PokeBall(new Vector2(mAt.x, mAt.y));
				b.outterColor(Color.RED);
				b.innerColor(Color.LIGHT_GRAY);
				this.Stage.addActor(b);
				b.show();
			}

		}


		
		
		
		return false;
	}

	@Override
	public boolean handle(Message m) {
		// Log(m+"::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		/*
		 * if (m.content.equals("toggle")) { this.toggleLens = !this.toggleLens; return
		 * true; } if (m.content.equals("on")) { this.toggleLens = false; CORE_L.lensUp
		 * = true; return true; } if (m.content.equals("off")) { this.toggleLens =
		 * false; CORE_L.lensUp = false; return true; }
		 */

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

	public enum Cursor {
		OPEN, PLACE, GRAB_INNER, GRAB_OUTTER, JOIN;

		public static Cursor Mode = OPEN;
		public static ArrayList<iNode> Selected;

		private Cursor() {
			reg(this);
		}

		private void reg(Cursor c) {
			if (Selected == null)
				Selected = new ArrayList<iNode>();
		};

	}

}
