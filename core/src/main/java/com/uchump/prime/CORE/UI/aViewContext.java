package com.uchump.prime.CORE.UI;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uchump.prime.CORE.UI.Events.aEventManager;
import com.uchump.prime.CORE.UI.Rendering.DrawBuffer;
import com.uchump.prime.CORE.UI.Rendering.FrameBufferManager;
import com.uchump.prime.CORE.UI.Rendering.Drawable.iDrawable;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.M.File.OroborosList;
import com.uchump.prime._PRIME.METATRON.M.File.TimeKey;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public abstract class aViewContext extends aEventManager implements iMonad, iSpace {

	// Top-1Level Modal Window interface, consumes gdx window
	// transmits input from InputMultiplexer to stage & camera controller

	public FrameBufferManager Buffers;
	public Environment Environment;
	public uStage Stage;
	public TimeKey Time;
	// public aCamera[] staticCameras;
	public Vector3 Unit; // cell size

	// MenuWidget
	public Camera View;
	public DrawBuffer DrawBuffer;
	public OroborosList<iDrawable> toDraw;
	// protected static final Vector3 Unit = new Vector3(1,1,1);

	public aViewContext() {
		super();
		Log(".aViewContext()");
		this.Stage = new uStage();
		this.View = this.Stage.getCamera();
		this.Buffers = new FrameBufferManager();
		this.Time = new TimeKey();
		this.isProxy = true;
		this.Environment = new Environment();
	}

	public aViewContext(aEventManager manager) {
		super(manager);
		this.Stage = new uStage();
		this.View = this.Stage.getCamera();
		this.Buffers = new FrameBufferManager();
		this.Time = new TimeKey();
		this.isProxy = true;
		this.Environment = new Environment();

	}

	public void create() {

		this.Stage = new uStage();

	}

	public void update() {
		this.Time.a = Metatron.TheMetatron.DeltaTime.I.floatValue();
		this.Time.i = Metatron.TheMetatron.iTime.I.floatValue();
		this.update(this.Time.a.floatValue());

	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		this.Buffers.update(deltaTime);
		this.Stage.act(deltaTime);
	}

	public void render() {
		// stage.render
		// Log(""+this.getClass().getSimpleName()+".draw()");

		this.Stage.draw();
	}

	public void enter() {
		Metatron.TheMetatron.addProcessor(this);
		this.Time = new TimeKey();
		Log("{=>" + this.getClass().getSimpleName());
	}

	public void exit() {
		Metatron.TheMetatron.removeProcessor(this);
		Log("<=}" + this.getClass().getSimpleName());
		this.exists(false);
	}

	@Override
	public uTransform getTransform() {
		return super.getTransform();
	}

	@Override
	public void transformUpdated() {
		super.transformUpdated();
	}

	@Override
	public String toLog() {
		return super.toLog();
	}

	@Override
	public Vector3 getSize() {
		return new Vector3();
	}

	@Override
	public Vector3 getUnit() {
		return this.Unit;
	}

	@Override
	public uTransform metric() {
		return this.getTransform();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
