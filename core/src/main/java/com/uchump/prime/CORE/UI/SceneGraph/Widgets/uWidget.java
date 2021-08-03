package com.uchump.prime.CORE.UI.SceneGraph.Widgets;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime.CORE.UI.Rendering.Drawable.iDrawable;
import com.uchump.prime.CORE.UI.SceneGraph.uInputListener;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;


public class uWidget extends Actor implements iDrawable, iNode<uWidget, String, uTransform> {

	public String Name;
	public boolean exists = false;
	public uTransform Transform;
	// public BoundShape Appearance;

	public Color inner = Color.BLUE;
	public Color outter = Color.LIGHT_GRAY;

	// transform anchor type

	public uWidget() {

		this.Transform = new uTransform(this);
		this.Transform.of = this;
		this.setName("NewWidget");
		this.setVisible(false);
		this.build();
		this.exists = true;
	}

	public uWidget(String name) {
		this.Transform = new uTransform(this);
		this.Transform.of = this;
		this.setName(name);
		this.setVisible(false);
		this.build();
	}

	public void build() {

		this.Transform.SetRotation(new Quaternion(0, 1, 0, 0));
		this.Transform.SetScale(new Vector3(32, 32, 1));
	}

	// override me lol
	public void show() {
		this.setVisible(true);
		this.addListener(new uInputListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				// Log("You clicked the Widget [" + getZIndex() + "]" +
				// event.getListenerActor().getName()); // Z-INDEX<=o
			}
		});
	}

	public void hide() {
		this.setVisible(false);
		this.clearListeners();
	}

	public void launch() {
		this.build();
		this.show();
	}

	@Override
	public boolean remove() {
		return super.remove();

	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		return super.hit(x, y, touchable);
	}

	public void draw() {
		if (this.isVisible())
			this.draw(Sketcher.PolygonSpriteBatch(), 1);
	}

	// WorldEntity with an ActorComponent
	// | *-WorldActor
	// | /|\
	// | O-ScreenActor = scale = WorldActor.scale*camera.zoom
	// so, if its screen size is less than 1 (pixel .ie screen cell), it wont
	// register input
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (this.isVisible()) {
			Vector3 lP = this.Transform.GetLocalPosition();
			Vector3 lS = this.Transform.GetLocalScale();
			this.setBounds(lP.x, lP.y, lS.x, lS.y);
			Sketcher.setColor(this.inner);
			Sketcher.Drawer.filledRectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			Sketcher.setColor(this.outter);
			Sketcher.Drawer.rectangle(lP.x, lP.y, lS.x, lS.y);
		}
	}

	@Override
	public Stage getStage() {
		return super.getStage();
	}



	@Override
	public void sizeChanged() {
		super.sizeChanged();
		this.Transform.GetLocalScale().set(this.getWidth(), this.getHeight(), 1f);
	}

	@Override
	public void positionChanged() {
		super.positionChanged();
		this.Transform.GetLocalPosition().set(this.getX(), this.getY(), (float) this.getZIndex());
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public void setName(String name) {
		this.Name = name;
	}

	@Override
	public uTransform getTransform() {

		return this.Transform;
	}

	@Override
	public void transformUpdated() {
		
		this.positionChanged();
		this.rotationChanged();
		this.sizeChanged();

	}

	@Override
	public String toLog() {

		String log = "[" + this.getZIndex() + "]" + "M_B." + this.getStage().toString() + ".uActor.()";
		log += " [" + this.Transform.GetParent() + "::" + this.Transform.GetParent().of.getName() + "]<"
				+ this.getRootDist() + ">";
		// log+="\n"+this.getParent();

		return log;
	}

	@Override
	public String getKey() {
		return this.getName();
	}

	@Override
	public uTransform getValue() {
		return (uTransform) this.getTransform();
	}



	@Override
	public iSpace getGraph() {
		// TODO Auto-generated method stub
		return (uStage) this.getStage();
	}

	@Override
	public boolean getExpanded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setExpanded(boolean is) {

	}

	@Override
	public boolean exists() {
		return this.exists;
	}

	@Override
	public Object Name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public _N Index() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Symbol() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object as(_N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public _N new_N(_N index, Object name, String symbol) {
		// TODO Auto-generated method stub
		return null;
	}



}
