package com.uchump.prime._PRIME.METATRON.M.Boot.Apps.lBox;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.Map.Entry;

import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
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
import com.uchump.prime._PRIME.METATRON.M.MetatronConsole;
import com.uchump.prime._PRIME.METATRON.M.MetatronShell;
import com.uchump.prime._PRIME.METATRON.M.Boot.MetatronBoot;
import com.uchump.prime._PRIME.METATRON.M.Boot.oLF_Renderer_3M;


public class LuciferBoxApp extends uApp {

	int i = 0;
	private oLF_Renderer_3M Renderer;

	public LuciferObject CORE_L;

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
		this.CORE_L = new LuciferObject(new Vector3(), 360, 10, this.Renderer);
	}

	@Override
	public void update() {
		// Log(i);
		// i++;
		if (i >= 1)
			M.Shell.launch(new MetatronBoot(M));
		super.update();
		this.CORE_L.update();

		Log(" <<<<<<<<<<<<<<<<<<<===================================--------------------------------------------<<<<<<<" + M.DeltaTime.I.floatValue());
		
		SkinLoader L;
		
		
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		// Log("-M_Boot.render()");

		// this.toCamera = true;

		// Draws UI to WORLD as per oLF_Renderer3, input remains accurate
		if (this.running) {
			// Log("METATRON BOOT RDR " + M.DeltaTime.I);

			if (this.toCamera) {
				Sketcher.setProjectionMatrix(uChumpEngine.CAMERA.getProjection());
				Stage.getViewport().setCamera(uChumpEngine.CAMERA.getBaseCamera());

			} else // Draws to Screen-Space like a normal fukin UI
			{
				Stage.getViewport().setCamera(((uStage) Stage).StageCamera);
				Sketcher.setProjectionMatrix(((uStage) Stage).StageCamera.combined);
			}

			Sketcher.begin();
			super.render();
			Sketcher.setColor(Color.MAGENTA);
			Sketcher.Drawer.rectangle(0, 0, Stage.getWidth(), Stage.getHeight());

			// for(xIndex.Entry E : vAnchor.DefaultScreenAnchors)
			// {

			// }

			
			if ((Monitor.width * Monitor.height) * Renderer.C.Camera.zoom < (Monitor.width * Monitor.height)
					/ (((MathUtils.PI / MathUtils.PI2) * Renderer.C.Camera.zoom)))
				this.CORE_L.draw();
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
				//MetatronShell.launch(new MetatronBoot(Metatron.TheMetatron));
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
		this.CORE_L.clear();		
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
	public boolean handle(aEvent event) {
		if (event.content.equals("TOGGLE_MODE_C")) {
			this.toCamera = !this.toCamera;
			Log("::" + event.content);
			return true;
		}

		return false;
	}
}
