package com.uchump.prime.CORE.UI.SceneGraph.Widgets;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime.CORE.UI.Rendering.Drawable.iDrawable;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;


public class uGroup extends Group implements iDrawable, iNode {

	public uTransform RootTransform;

	public uGroup() {
		super();
		this.RootTransform = new uTransform();
		this.RootTransform.SetScale(new Vector3(1, 1, 1));
		this.RootTransform.setDepth(0);
	}

	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
		if (actor instanceof uWidget) {
			uWidget W = (uWidget) actor;
			W.Transform.SetParent(this.RootTransform);
		}
	}

	@Override
	public boolean removeActor(Actor actor) {

		if (actor instanceof uWidget) {
			uWidget W = (uWidget) actor;
			W.Transform.RemoveParent();
			
		}
		return super.removeActor(actor);
	}

	public void show() {
		for (Actor A : this.getChildren()) {
			if (A instanceof uWidget) {
				uWidget W = (uWidget) A;
				W.show();
			} else
				A.setVisible(true);
		}
	}

	public void hide() {
		for (Actor A : this.getChildren()) {
			if (A instanceof uWidget) {
				uWidget W = (uWidget) A;
				W.hide();
			} else
				A.setVisible(false);

		}
	}

	@Override
	public uTransform getTransform() {
		// TODO Auto-generated method stub
		return this.RootTransform;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setValue(Object value) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	/** Returns a description of the actor hierarchy, recursively. */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(128);
		toString(buffer, 1);
		buffer.setLength(buffer.length() - 1);
		return buffer.toString();
	}

	void toString(StringBuilder buffer, int indent) {
		// buffer.append(""+super.toString());
		buffer.append('\n');

		String tab = "";
		String post = "";
		for (int j = 0; j <= this.getDepth(); j++)
			tab += "_";
		for (int u = 0; u <= this.getChildren().size; u++)
			post += "_";

		buffer.append(tab + this.getName() + ":<uGroup.toLog()>" + post + "\n");

		Actor[] actors = getChildren().begin();
		String xDst = "";

		for (int i = 0, n = getChildren().size; i < n; i++) {
			xDst = "";
			if (actors[i] instanceof uWidget)
				xDst = "[" + ((uWidget) actors[i]).getRootDist() + "]";
			for (int ii = 0; ii < indent; ii++)
				buffer.append("|[" + actors[i].getZIndex() + "]:_:" + xDst + "|  ");
			Actor actor = actors[i];
			if (actor instanceof Group)
				((uGroup) actor).toString(buffer, indent + 1);
			else {
				buffer.append(actor);
				buffer.append('\n');
			}
		}
		getChildren().end();
	}

	@Override
	public Object as(_N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getBits() {
		// TODO Auto-generated method stub
		return null;
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
	public _N new_N(_N index, Object name, String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}


	
	

}
