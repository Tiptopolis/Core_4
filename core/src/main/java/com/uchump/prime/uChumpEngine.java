package com.uchump.prime;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.Device.iApplet;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.Resources.FontAtlas;
import com.uchump.prime.CORE.UI.aViewContext;
import com.uchump.prime.CORE.UI.Camera.OrthoController;
import com.uchump.prime.CORE.UI.Rendering.DrawBuffer;
import com.uchump.prime._PRIME.uAppUtils;
import com.uchump.prime._PRIME.uSketcher;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.M.MetatronConsole;
import com.uchump.prime._PRIME.METATRON.M.Boot.oLF_Renderer_3M;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME.METATRON.M.File.Rect;
import com.uchump.prime._PRIME.METATRON.M.File.TimeKey;
import com.uchump.prime._PRIME._coPRIME.META.iCypher;
import com.uchump.prime._PRIME._coPRIME.N_M.Maths;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aMatrix;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.UT_CAMERA._uCamera;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.bNode;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E.dNode;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E.qNode;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class uChumpEngine implements iApplet {
	private Texture image;

	public static uChumpEngine Engine;
	public static uSketcher Sketcher;
	public static Metatron METATRON;
	public static OrthoController CAMERA;
	public static Rect InitialConditions;
	public static uApp MainApp;
	public static FontAtlas Fonts;

	public static oLF_Renderer_3M oLF;

	public TimeKey A = new TimeKey();
	public BoundShape UiPlaceholder;
	public DrawBuffer dBuffer;

	public static void ref()
	{
		METATRON.removeProcessor(CAMERA);
		METATRON.removeProcessor(oLF);
		CAMERA.exists = false;
		oLF.dispose();
		CAMERA = new OrthoController(METATRON);
		CAMERA.init();
		oLF = new oLF_Renderer_3M(new Rect(0, 0, Width, Height), new Vector2(10, 5), CAMERA);
	}
	
	@Override
	public void create() {
		uAppUtils.update(0);
		InitialConditions = new Rect(0, 0, Width, Height);

		if (Engine == null)
			Engine = this;

		if (this.image == null)
			image = new Texture("badlogic.png");
		if (Sketcher == null)
			Sketcher = new uSketcher();
		if (Fonts == null)
			Fonts = new FontAtlas();

		if (METATRON == null) {
			METATRON = Metatron.TheMetatron;
			CAMERA = new OrthoController(METATRON);
			CAMERA.init();
		}

		if (oLF == null)
			oLF = new oLF_Renderer_3M(new Rect(0, 0, Width, Height), new Vector2(10, 5), CAMERA);

		Log(">::>\n" + CAMERA.getProjection().toString());

		this.dBuffer = DrawBuffer.unmanagedBuffer(new Rect(0, 0, Width, Height), true);

	}

	@Override
	public void render() {

		// Log(aNodeType.All.Nodes());
		// Log(aNodeType.All.Types());
		// uVector t = new uVector(new Float[] {45f,90f,45f,90f,45f});
		// Log(t);
		// Log(t.cpy().add(t.cpy()));

		// uNumber n = new uNumber(666);
		// Log(n.s());

		/*
		 * qNode N = new qNode(); eNode e = new eNode(N); Log(">>" + N);
		 * 
		 * Log("" + N.toLog());
		 * 
		 * Log(">>" + e); Log("" + e.toLog()); Log(">>" + N); Log("" + N.toLog());
		 * ArrayList<String> s = new ArrayList<String>();
		 * 
		 * for (String S : s) { Log(s); } N.exists(false); e.exists(false);
		 * 
		 * oNode o = new oNode("Object"); Log(">>" + o); Log("" + o.toLog());
		 */

		/*uVector v = new Vector4(1, 2, 3, 4);
		Log(v.toString());
		Log(v.toLog());
		uVector V = new uVector(new Float[] { 4f, 5f, 6f });
		Log(V.toString());
		Log(V.toLog());*/

		 //aMatrix M = new aMatrix("13");//werx lol; maximum is 9, problem is in
		// iCypher.Digits()
		
		//bNode b = new bNode();
		//Log(b.toString());
		//Log(b.toLog());
		//      HOLY SHIT,FKN WERX
		aMatrix M = new aMatrix(13,Maths.E(13)) {
			@Override
			public uVector unfold(Number type)
			{
				//->Maths.E(13) ~~ 5.175...
				float t = type.floatValue();
				
				t = MathUtils.sin(this.elements.size());
				
				return new uVector(t);
			}
		};		
		// Log(M.toString());
		//Log(M.toLog());
		
		//aMatrix M = new aMatrix(13,13);
		// Log(M.toString());
		//Log(M.toLog());
		//M.set(6);
		//M.mul(2); //math no work yet...
		//Log(M.toString());
		//Log(M.toLog());

		

		//Log(iCypher.Digit(123));

		/*
		 * dNode agntDoc = new dNode("Agent M"); Log(">>" + agntDoc); Log("" +
		 * agntDoc.toLog()); agntDoc.exists(false);
		 */

		uAppUtils.update(Gdx.graphics.getDeltaTime());
		METATRON.update(Gdx.graphics.getDeltaTime());
		this.CAMERA.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		// Gdx.gl.glClearColor(1, 1, 1, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// BACKGROUND
		if (oLF.isOn && oLF.dI) {
			Sketcher.begin();
			Sketcher.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Width, Height));
			Sketcher.getBatch().draw(image, (Width / 2) - (image.getWidth() / 2), (Height / 2));

			// Vector3 mAtScreenRaw = new Vector3(MouseX, MouseY, 0);
			// Vector3 mAtScreenFix = new Vector3(MouseX, Height - MouseY, 0);
			// Rect vScreen = MainApp.Monitor;
			// Rect vCamW = CAMERA.Camera.getViewRect();

			// Vector3 mPrj = CAMERA.Camera.camera.unproject(mAtScreenRaw.cpy());
			// Sketcher.Drawer.setColor(new Color(1, 0, 0, 0.25f));
			// Sketcher.Drawer.filledCircle(mAtScreenFix.x, mAtScreenFix.y, 4 -
			// (CAMERA.Camera.zoom * 4));

			Sketcher.end();
		}
		// MAIN RENDER
		if (oLF.isOn && oLF.dI) {
			Sketcher.setProjectionMatrix(CAMERA.getProjection());
			Sketcher.begin();
			Sketcher.getBatch().draw(image, (Width / 2) - (image.getWidth() / 2), (Height / 2));
			// Sketcher.Drawer.setColor(new Color(0, 0, 1, 0.25f));
			// Sketcher.Drawer.filledCircle(mPrj.x, mPrj.y, 2 - (2 / CAMERA.Camera.zoom));
			Sketcher.end();
		}
		if (oLF.isOn) {
			oLF.update();
			oLF.render();
		}

		if (MainApp != null)
			MainApp.render();
		else {
			Log("MAIN APP NULL DAMMIT");
		}

		Sketcher.Drawer.setDefaultLineWidth(16 * (Width / Height));
		this.dBuffer.begin();
		Sketcher.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Width, Height));
		Sketcher.begin();
		Sketcher.setColor(Color.TEAL);
		Sketcher.Drawer.filledRectangle(0, 0, Width / 2, Height / 2);
		Sketcher.setColor(Color.ORANGE);
		Sketcher.Drawer.rectangle(0, 0, Width / 2, Height / 2);
		Sketcher.end();
		this.dBuffer.end();

		// UI RENDER
		Sketcher.begin();
		UiPlaceholder = BoundShape.bindShape(new Vector3(24, Height - 24, 1), new Vector3(32, 32, 1),
				new Vector3(0, 0, 0), new Color(1, 0, 0, 0.25f), 4, 1);
		UiPlaceholder.drawShape();
		dBuffer.draw(new Rect(0, 0, 8, 8));
		Sketcher.end();

	}

	@Override
	public void resize(int width, int height) {
		uAppUtils.resize();
		// CAMERA.update(0);

		CAMERA.resize(width, height);
		CAMERA.update(0);
		oLF.resize();
		this.dBuffer.refresh();

		if (MainApp != null)
			MainApp.resize(width, height);

	}

	@Override
	public void dispose() {
		image.dispose();
		oLF.dispose();
		MetatronConsole.mConsole.terminate();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public aViewContext domain() {

		return METATRON.Shell.GlobalPrimeModal;
	}
}