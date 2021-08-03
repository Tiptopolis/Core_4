package com.uchump.prime.CORE.UI.SceneGraph;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.Struct.aTree;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime.CORE.UI.Camera.OrthoController;
import com.uchump.prime.CORE.UI.Camera.uCamera;
import com.uchump.prime.CORE.UI.Camera.uCameraController;
import com.uchump.prime.CORE.UI.Camera.A_I.aCameraController.CameraCollisionMode;
import com.uchump.prime.CORE.UI.Events.iEventProcessor;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uGroup;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uWidget;
import com.uchump.prime._PRIME.METATRON.Agents.iControlAgent;
import com.uchump.prime._PRIME.METATRON.Signals.Message;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNode;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;


public class uStage extends Stage implements iSpace, iEventProcessor, iMonad {

	// metric anchors
	// public HashMap<String, Vector3> anchors;
	public OrthographicCamera StageCamera;
	public uCameraController StageCam;
	public aTree<uWidget, Integer> SceneGraph;

	private uGroup rG;
	

	public uStage() {
		super(new StretchViewport(Width, Height));
		this.StageCamera = (OrthographicCamera) this.getCamera();		
		this.StageCam = new uCameraController(this) {
			@Override
			public void init()
			{
				this.Camera = new uCamera(this, StageCamera);
				this.Camera.resize(Width, Height);
				this.setCameraOrigin(Width/2, Height/2);
				//this.CollisionMode = CameraCollisionMode.CENTER_ENDGE;
			}
		};
		this.StageCam.init();
		
		
		this.SceneGraph = new SceneTree();
		this.rG = new uGroup();
		this.setRoot(rG);
		this.rG.setName("[Root]");
		rG.RootTransform = this.SceneGraph.getTransform();
		this.SceneGraph.RootNode = rG;
		StageCam.root = this.SceneGraph.RootNode.getTransform();
		StageCam.setTransform(this.SceneGraph.RootNode.getTransform());
	}

	@Override
	public void update(float deltaTime) {
		this.act();
	}

	@Override
	public void act(float deltaTime) {
		super.act(deltaTime);
	}

	@Override
	public void draw() {
		Sketcher.Drawer.setDefaultLineWidth(1);
		for (Actor a : this.getActors()) {
			a.draw(this.getBatch(), 1);
		}

	}

	@Override
	public void addActor(Actor a) {

		if (a instanceof uWidget) {
			uWidget W = (uWidget) a;
			int i = SceneGraph.size() + 1;
			this.SceneGraph.put(W, i);
			W.getTransform().SetParent(this.SceneGraph.getTransform());
			// W.getTransform()
			super.addActor(a);
		}
	}

	@Override
	public void broadcast() {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(Object at) {
		// TODO Auto-generated method stub

	}

	/**
	 * Replaces the root group. This can be useful, for example, to subclass the
	 * root group to be notified by {@link Group#childrenChanged()}.
	 */
	public void setRoot(Group root) {
		if (root.getParent() != null)
			root.getParent().removeActor(root, false);
		this.SceneGraph.clear();
		super.setRoot(root);

	}

	@Override
	public Vector3 getSize() {

		return new Vector3(this.getCamera().viewportWidth, this.getCamera().viewportHeight, this.getActors().size);
	}

	@Override
	public Vector3 getUnit() {

		return new Vector3().add(MathUtils.PI);
	}

	@Override
	public uTransform metric() {

		return null; // MathUtils.PI
	}

	@Override
	public boolean keyDown(int keycode) {
		Log("->uStage.keyDown() " + keycode);
		return super.keyDown(keycode);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public String toLog() {
		String log = "\n";
		log += " Stage: \n";
		log += this.getCamera().getClass().getSimpleName() + "\n";
		log += "[" + this.getCamera().viewportWidth + "," + this.getCamera().viewportHeight + "]";
		log += this.getViewport().getClass().getSimpleName() + "\n";
		log += this.getRoot() + "\n";
		log += this.SceneGraph.toLog();
		return log;
	}

	public void dispose() {
		this.StageCam.exists(false);
		this.StageCamera = null;
		
		for (iNode N : this.SceneGraph) {
			N.exists(false);
			this.getActors().clear();
		}
		this.exists(false);
	}

	@Override
	public uTransform getTransform() {

		return this.SceneGraph.getTransform();
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub

	}

	public class SceneTree extends aTree<uWidget, Integer> {

		public SceneTree() {
			this.RootTransform = new uTransform(this);
		}

		public class Node extends aNode {

			public Actor A;

			public Node(Actor a) {
				this.A = a;
			}
		}

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dispatchEvent(Event evt) throws EventException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public iControlAgent getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handle(Message m) {
		// TODO Auto-generated method stub
		return false;
	}

}
