package com.uchump.prime.TESTS.Pseudo3D;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.UI.Camera.uFpsAdapter;
import com.uchump.prime.CORE.UI.Events.aEvent;
import com.uchump.prime.CORE.UI.Events.uEvent;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uButton;
import com.uchump.prime.TESTS.Raymarching.SDF_Env;
import com.uchump.prime.TESTS.Raymarching.UselesssNonsense.SDF_BS;
import com.uchump.prime._PRIME.METATRON.M.MetatronShell;
import com.uchump.prime._PRIME.METATRON.M.Boot.oLF_Renderer_3M;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME.METATRON.Signals.Message;
import com.uchump.prime._PRIME._coPRIME.N_M.Maths;

public class oL_3D extends uApp {

	protected DecalBatch dBatch;
	protected boolean toggleLens = false;

	public Environment Space;
	public uFpsAdapter Explorer;
	DirectionalLight ExLight;
	ModelBatch modelBatch = new ModelBatch();
	ModelInstance instance;
	Model model;

	public boolean toCamera = false;
	private oLF_Renderer_3M Renderer;
	// Render LuciferObject in 3-d

	//public LuciferObject3D L;

	ArrayList<Vector3> grd = new ArrayList<Vector3>();

	@Override
	public void create() {
		SDF_Env.Caster = uChumpEngine.CAMERA.getTransform();
		this.Explorer = new uFpsAdapter(uChumpEngine.CAMERA);
		this.Space = new Environment();
		this.Space.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		ExLight = new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f);
		this.Space.add(ExLight);

		this.Renderer = uChumpEngine.oLF;
		if (!this.Renderer.isOn)
			this.Renderer.isOn = true;

		this.Renderer.dI = false;
		this.Renderer.dL2 = false;
		//this.L = new LuciferObject3D(Renderer);
		//this.L.clr = 8;
		recombTestMdl();

		super.create();
		this.buildUI();

	}

	@Override
	public void resize(int width, int height) {

		super.resize(width, height);
		this.modelBatch.dispose();
		this.modelBatch = new ModelBatch();

	}

	@Override
	public void update() {
		//this.L.update();

		super.update();

	}

	private void recombTestMdl() {
		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(MathUtils.PI2, MathUtils.PI2, MathUtils.PI2,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);
		instance = new ModelInstance(model);
		//instance.transform.set(L.getTransform().GetLocalToWorldMatrix());
	}

	@Override
	public void render() {
		if (this.running) {
			// Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

			modelBatch.begin(Explorer.perspective.camera);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			modelBatch.render(instance, Space);
			modelBatch.end();

			Sketcher.begin();
			Sketcher.setProjectionMatrix(Explorer.getProjection());
			this.drawTestField();
			for (BoundShape s : SDF_BS.ShapesInSpace)
				s.drawShape();
			super.render();
			// this.L.draw();
			Sketcher.end();

			if (this.toCamera) {
				Sketcher.setProjectionMatrix(uChumpEngine.CAMERA.getProjection());
				Stage.getViewport().setCamera(uChumpEngine.CAMERA.getBaseCamera());

			} else // Draws to Screen-Space like a normal fukin UI
			{
				Stage.getViewport().setCamera(((uStage) Stage).StageCam.getBaseCamera());
				Sketcher.setProjectionMatrix(((uStage) Stage).StageCam.getBaseCamera().combined);
			}

			// c=Explorer.perspective.camera;

			//if (this.toggleLens)
				//this.L.lensUp = !this.L.lensUp;

			modelBatch.begin(Explorer.perspective.camera);
			modelBatch.render(instance, Space);
			// L.draw(modelBatch, Space); //WORKS, BUT EATS MEMORY!!!
			modelBatch.end();

			Sketcher.begin();
			// this.drawTestField();
			super.render();
			//Sketcher.setProjectionMatrix(Explorer.getProjection());
			
			// this.L.draw();
			Sketcher.end();

		}
	}

	private void genTestField() {
		Sketcher.Drawer.filledRectangle(0, 0, Width, Height);
		for (int x = 0; x < Width / MathUtils.PI; x++) {
			for (int y = 0; y < Width / MathUtils.PI; y++) {
				this.grd.add(new Vector3(x * MathUtils.PI, y * MathUtils.PI, MathUtils.PI));

			}
		}
	}

	private void drawTestField() {
		Sketcher.setColor(new Color(0, 0, 0, 0.5f));
		Sketcher.Drawer.filledRectangle(0, 0, Width, Height);
		for (int x = 0; x < Width / MathUtils.PI; x++) {
			for (int y = 0; y < Width / MathUtils.PI; y++) {
				if (Maths.isEven(x + y)) {
					Sketcher.setColor(new Color(.25f, .25f, .25f, 0.5f));
				} else
					Sketcher.setColor(new Color(.75f, .75f, .75f, 0.5f));
				Sketcher.Drawer.filledRectangle(x * MathUtils.PI, y * MathUtils.PI, MathUtils.PI, MathUtils.PI);

			}
		}
	}

	@Override
	public void resume() {
		super.resume();
		Log("Metatron ON");
		uChumpEngine.oLF.isOn = true;
		uChumpEngine.oLF.dL2 = false;
		uChumpEngine.oLF.dI = false;
		this.Explorer.init();
		uChumpEngine.CAMERA.addAdapter(this.Explorer);

	}

	@Override
	public void pause() {
		super.pause();
		uChumpEngine.CAMERA.removeAdapter(this.Explorer);
		this.Explorer.isOn = false;
	}

	@Override
	public void dispose() {

		Explorer.exists(false);
		uChumpEngine.CAMERA.removeAdapter(this.Explorer);
		M.removeProcessor(this.Explorer);
		//L.clear();
		model.dispose();
		modelBatch.dispose();
		super.dispose();

		// dBatch.dispose();
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

		uButton lnchArchFileTest = new uButton("mReturn") {
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
		this.Stage.addActor(lnchArchFileTest);
		lnchArchFileTest.setSize(32, 32);
		lnchArchFileTest.Transform.SetPosition(new Vector3(0, Height, 1));
		lnchArchFileTest.inner = Color.RED;
		lnchArchFileTest.show(); // just the button, lol
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
			//L.lensUp = true;
			return true;
		}
		if (m.content.equals("off")) {
			this.toggleLens = false;
			//L.lensUp = false;
			return true;
		}

		return false;

	}

	@Override
	public boolean handle(aEvent event) {
		if (event.content.equals("TOGGLE_MODE_C")) {
			//this.L.lensUp = this.toCamera;
			this.toCamera = !this.toCamera;
			Log("::" + event.content);
			return true;
		}

		return false;
	}
}