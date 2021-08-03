package com.uchump.prime._PRIME.METATRON.M.Boot;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.UI.Events.aEvent;
import com.uchump.prime.CORE.UI.Events.uEvent;
import com.uchump.prime.CORE.UI.Rendering.Drawable.GlyphSequence;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.PokeBall;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uButton;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uLabel;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uPanel;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uWidget;
import com.uchump.prime.TESTS.LuciferCube.LuciferCubeApp;
import com.uchump.prime.TESTS.MatrixBox.MatrixBoxApp;
import com.uchump.prime.TESTS.PokePlotter.PokePlotterApp;
import com.uchump.prime.TESTS.Pseudo3D.oL_3D;
import com.uchump.prime.TESTS.Raymarching.oL_2D;
import com.uchump.prime.TESTS.ucPort.u1_4.zTest1_8;
import com.uchump.prime._PRIME.GdxFileUtils;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.M.MetatronConsole;
import com.uchump.prime._PRIME.METATRON.M.MetatronShell;
import com.uchump.prime._PRIME.METATRON.M.Boot.Apps.lBox.LuciferBoxApp;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E.qNode;

public class MetatronBoot extends uApp {

	private FileHandle RootDirectory = Gdx.files.internal("./assets");
	// SKIN LOADER
	private String defaultInternalDir = "./assets/data/skins/";
	private String skinPath = "orange/skin/";
	public FileHandle SkinsDirectory = Gdx.files.internal(defaultInternalDir + skinPath);
	public FileHandle SkinFile;

	private ArrayList<FileHandle> fileList = new ArrayList<FileHandle>();

	Metatron M;
	Skin Metaorange;

	public boolean toCamera = false;

	public uButton launchDummyApp;

	public MetatronBoot(Metatron M) {
		super();
		Log("{<[(?!?])>}");
		this.M = M;
		uChumpEngine.CAMERA.init();
		// Log(GdxFileUtils.listDirectory(defaultInternalDir + skinPath));
		// this.fileList = GdxFileUtils.fillFilesFrom(fileList,
		// SkinsDirectory.toString());
		this.fileList = GdxFileUtils.fillFilesFrom(fileList, defaultInternalDir + skinPath);
		this.fileList = GdxFileUtils.fillFilesFrom(fileList, "./assets/ui");
		for (FileHandle F : fileList) {
			String apnd = "";
			if (F.name().equals("uiskin.json")) {
				// apnd = "*";
				SkinFile = F;
			}

			// Log("[" + fileList.indexOf(F) + "]<"+F.type()+">" + F.name() + F.path() + " "
			// + apnd);
		}
		// Log("" + SkinFile.exists() + "{" + SkinFile.path() + " {{" +
		// SkinFile.type());

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		uChumpEngine.MainApp = this;
		super.create();
		// this.LogInput = true;
		Metaorange = new Skin(SkinFile);
		Log("{<[(I AM THE METATRON!])>}");
		/*
		 * final TextButton button = new TextButton("Click me", Metaorange, "default");
		 * 
		 * button.setWidth(200f); button.setHeight(20f);
		 * button.setPosition(Gdx.graphics.getWidth() /2 - 100f,
		 * Gdx.graphics.getHeight()/2 - 10f);
		 * 
		 * button.addListener(new ClickListener(){
		 * 
		 * @Override public void clicked(InputEvent event, float x, float y){
		 * button.setText("You clicked the button"); } });
		 * 
		 * this.Stage.addActor(button);
		 */
		this.loadUiTestDummies();

	}

	@Override
	public void resize(int width, int height) {

		super.resize(width, height);
		MetatronConsole.post(this.toLog());
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
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

			Sketcher.end();
		}
	}

	@Override
	public void pause() {
		super.pause();
		uChumpEngine.oLF.isOn = false;
		uChumpEngine.oLF.dL2 = false;
		uChumpEngine.oLF.dI = false;
	}

	@Override
	public void resume() {

		super.resume();
		Log("Metatron ON");
		uChumpEngine.oLF.isOn = true;
		uChumpEngine.oLF.dL2 = true;
		uChumpEngine.oLF.dI = true;
		MetatronShell.listeners.clear();
		MetatronConsole.post(this.toLog());

	}

	@Override
	public void dispose() {
		super.dispose();
		this.Metaorange.dispose();

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

	private void loadUiTestDummies() {
		uWidget ML = new uWidget("LOG") {
			@Override
			public void show() {
				addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						int idA = getStage().getActors().indexOf(event.getListenerActor(), false);
						int idB = getStage().getActors().indexOf(event.getListenerActor(), true);

						// Log("You clicked the Widget [" + idA + "|" + idB + "]" +
						// event.getListenerActor().getName()
						// + " " + getZIndex());
						Log(">>>>>>>>>>> {{{PRINT LOG}}}");
						// mConsoleLogger.logOut();
					}
				});
			}
		};

		// final Menu
		uWidget W = new uWidget();
		uPanel P = new uPanel();
		uLabel L = new uLabel("mLabel", "METATRON");
		L.Transform.SetLocalPosition(new Vector3(0, Height - 32, 1));
		// FileBucketTest F = new FileBucketTest();

		// this.Stage.addActor(W);
		this.Stage.addActor(ML);
		this.Stage.addActor(L);
		this.Stage.addActor(P);
		// this.Stage.addActor(F);

		ML.show();
		L.show();
		P.show();
		// F.show();

		// .launch() == (.build()&.show())
		P = new uPanel();
		P.inner = Color.RED;
		this.Stage.addActor(P);
		P.show();

		P = new uPanel();
		P.inner = Color.GREEN;
		this.Stage.addActor(P);
		P.show();

		GlyphSequence G = new GlyphSequence("METATRON");
		String Scrl = "";
		for (int i = 0; i < G.length(); i++)
			Scrl += "=";
		Log("<<" + Scrl + ">>");
		Log(G.toLog());
		Log("<<" + Scrl + ">>");

		G = new GlyphSequence("AEIOU");
		Scrl = "";
		for (int i = 0; i < G.length(); i++)
			Scrl += "=";
		Log("<<" + Scrl + ">>");
		Log(G.toLog());
		Log("<<" + Scrl + ">>");
		uLabel GL = G.toLabel();
		this.Stage.addActor(GL);
		GL.show();

		PokeBall Bkk = new PokeBall(new Vector2(90, 90));
		this.Stage.addActor(Bkk);
		Bkk.show();

		// uTable tab = new uTable(new Vector2(6,6));
		// this.Stage.addActor(tab);
		this.launchDummyApp = new uButton("LaunchInterupt") {

			@Override
			public void press() {
				MetatronShell.launch(new DummyInteruptApp());
			}
		};
		this.launchDummyApp.Transform.SetLocalPosition(new Vector3(0,32,1));
		this.launchDummyApp.setSize(32, 32);
		this.Stage.addActor(launchDummyApp);
		this.launchDummyApp.show(); // just the button, lol
		
		uButton TGL = new uButton("oLF_MODE") {
			@Override
			public void press() {	

				handle(new uEvent("TOGGLE_MODE_C"));
			}

		};

		Stage.addActor(TGL);
		TGL.setSize(32, 32);
		TGL.Transform.SetPosition(new Vector3(-ML.getWidth() / 2, -ML.getHeight() / 2, 1));
		TGL.inner = Color.CLEAR;
		TGL.outter = Color.TEAL;

		// ML.Transform.SetLocalScale(new Vector3(64, 64, 1));
		TGL.show();

		/////////////////////////////////////////////////////
		////// LUCIFER BOX APP
		uButton LcfrApp = new uButton("lBox_LuciferBoxApp") {

			@Override
			public void press() {
				MetatronShell.launch(new LuciferBoxApp());
			}
		};
		this.Stage.addActor(LcfrApp);
		LcfrApp.Transform.SetPosition(new Vector3(0, Height - (this.launchDummyApp.getHeight() * 2), 1));
		LcfrApp.inner = Color.RED;
		LcfrApp.show(); // just the button, lol

		////// LUCIFER BOX APP
		/////////////////////////////////////////////////////
		/////////////////////////////////////////////////////
		////// POKE PLOTTER APP
		uButton PkPlt = new uButton("lBox_PokePlotterApp") {

			@Override
			public void press() {
				MetatronShell.launch(new PokePlotterApp());
			}
		};
		this.Stage.addActor(PkPlt);
		PkPlt.Transform.SetPosition(new Vector3(32, Height - (this.launchDummyApp.getHeight() * 2), 1));
		PkPlt.inner = Color.ORANGE;
		PkPlt.show(); // just the button, lol

		////// POKE PLOTTER APP
		/////////////////////////////////////////////////////

		/////////////////////////////////////////////////////
		////// LUCIFER BOX APP
		uButton Lcfr3App = new uButton("lBox_LuciferCubeApp") {

			@Override
			public void press() {
				MetatronShell.launch(new LuciferCubeApp());
			}
		};
		this.Stage.addActor(Lcfr3App);
		Lcfr3App.Transform.SetPosition(new Vector3(64, Height - (this.launchDummyApp.getHeight() * 2), 1));
		Lcfr3App.inner = Color.TEAL;
		Lcfr3App.show(); // just the button, lol

		////// LUCIFER BOX APP
		/////////////////////////////////////////////////////

/////////////////////////////////////////////////////
////// MATRIX BOX APP
		uButton MtxBx = new uButton("lBox_MatrixBoxApp") {

			@Override
			public void press() {
				MetatronShell.launch(new MatrixBoxApp());
			}
		};
		this.Stage.addActor(MtxBx);
		MtxBx.Transform.SetPosition(new Vector3(96, Height - (this.launchDummyApp.getHeight() * 2), 1));
		MtxBx.inner = Color.GREEN;
		MtxBx.show(); // just the button, lol

////// MATRIX BOX APP
/////////////////////////////////////////////////////

/////////////////////////////////////////////////////
//////Raymarching2d BOX APP
		uButton o2d = new uButton("lBox_Raymarching2dBoxApp") {

			@Override
			public void press() {
				MetatronShell.launch(new oL_2D());
			}
		};
		this.Stage.addActor(o2d);
		o2d.Transform.SetPosition(new Vector3(96+32, Height - (this.launchDummyApp.getHeight() * 2), 1));
		o2d.inner = Color.PURPLE;
		o2d.show(); // just the button, lol

//////Raymarching2d BOX APP
/////////////////////////////////////////////////////
		
/////////////////////////////////////////////////////
//////Raymarching3d BOX APP
uButton o3d = new uButton("lBox_Raymarching3dBoxApp") {

@Override
public void press() {
MetatronShell.launch(new oL_3D());
}
};
this.Stage.addActor(o3d);
o3d.Transform.SetPosition(new Vector3(96+64, Height - (this.launchDummyApp.getHeight() * 2), 1));
o3d.inner = Color.PINK;
o3d.show(); // just the button, lol

//////Raymarching3d BOX APP
/////////////////////////////////////////////////////

/////////////////////////////////////////////////////
//////zTest1_8 BOX APP
uButton z1_8 = new uButton("lBox_Raymarching3dBoxApp") {

@Override
public void press() {
MetatronShell.launch(new zTest1_8());
}
};
this.Stage.addActor(z1_8);
z1_8.Transform.SetPosition(new Vector3((96*2), Height - (this.launchDummyApp.getHeight() * 2), 1));
z1_8.inner = Color.BLACK;
z1_8.show(); // just the button, lol

//////zTest1_8 BOX APP
/////////////////////////////////////////////////////

	}

}
