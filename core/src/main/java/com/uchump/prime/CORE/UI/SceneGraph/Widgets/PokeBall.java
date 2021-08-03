package com.uchump.prime.CORE.UI.SceneGraph.Widgets;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.artemis.utils.Bag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.uchump.prime.CORE.UI.SceneGraph.uInputListener;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME._coPRIME.N_M.VectorUtils;


public class PokeBall extends uWidget implements iButton {

	// click ball

	public Bag<Object> contents;
	public BoundShape shape;


	public boolean expanded = false;

	public PokeBall(Vector2 at) {
		super("PokeBall");
		this.Transform.SetLocalPosition(new Vector3(at.x, at.y, 1));
		this.inner = Color.RED;
		this.outter = Color.WHITE;
		
		this.shape = BoundShape.bindRadian(this.Transform.GetLocalPosition(), this.Transform.GetLocalScale(),
				VectorUtils.downcast(this.Transform.GetLocalRotation()), Color.RED);
	}

	@Override
	public void build() {
		super.build();
		this.Transform.SetLocalScale(new Vector3(MathUtils.PI, MathUtils.PI, 1));
		this.setSize(MathUtils.PI, MathUtils.PI);
	}

	@Override
	public void show() {
		this.setVisible(true);
		this.addListener(new uInputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (super.touchDown(event, x, y, pointer, button)) {
					Stage S = event.getStage();
					Actor A = event.getListenerActor();
					// Log(">"+S +"\n>" + A);
					A.remove();
					S.addActor(A);
					return true;
				} else
					return false;
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				//Log("You Poked the Ball [" + getZIndex() + "]" + event.getListenerActor().getName());
				if (this.getTapCount() == 2)
					toggleExpanded();
				else {
					
					String tk = "";
					if(expanded)
						tk = "open";
					else
						tk = "closed";
					Log("You Poked the "+tk+" Ball [" + getZIndex() + "]" + event.getListenerActor().getName());
				}
			}
		});
		this.addListener(new DragListener() {
			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				Vector3 lp = Transform.GetLocalPosition()
						.add(new Vector3(getDragX() - getDragStartX(), getDragY() - getDragStartY(), 0));

				Transform.SetLocalPosition(lp);
			}
		});

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (this.isVisible()) {
			Vector3 lP = this.Transform.GetLocalPosition();
			Vector3 lS = this.Transform.GetLocalScale();
			
			Vector3 nS = lS.cpy();
			if (this.expanded) {
				nS.scl(MathUtils.PI);
			}
			
				this.setBounds(lP.x, lP.y, nS.x, nS.y);
				
				
			Sketcher.setColor(this.inner);
			Sketcher.Drawer.filledCircle(lP.x + (nS.x / 2), lP.y + (nS.y / 2), nS.x);
			Sketcher.Drawer.circle(lP.x + (lS.x / 2), lP.y + (lS.y / 2), lS.x);
			Sketcher.setColor(this.outter);
			Sketcher.Drawer.circle(lP.x + (nS.x / 2), lP.y + (nS.y / 2), nS.x);
			Sketcher.Drawer.circle(lP.x + (lS.x / 2), lP.y + (lS.y / 2), lS.x);
			Sketcher.setColor(new Color(0, 0, 0, 0.134f));
			Sketcher.Drawer.rectangle(lP.x, lP.y, nS.x, nS.y);
			
		}
	}

	public void toggleExpanded() {
		if (this.expanded) {
			this.contract();
		} else
			this.expand();

		this.expanded = !this.expanded;
	}
	
	@Override
	public boolean getExpanded() {
		
		return this.expanded;
	}

	@Override
	public void setExpanded(boolean is) {		
		if(is)
			this.expand();
		else
			this.contract();		
	}

	public void expand() {
		Log("You Expanded the Ball [" + getZIndex() + "]" + this.getName());
	}

	public void contract() {
		Log("You Contracted the Ball [" + getZIndex() + "]" + this.getName());
		//this.broadcast
	}

	

	@Override
	public void in() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void press() {
		this.toggleExpanded();
		
	}

	@Override
	public void out() {
		// TODO Auto-generated method stub
		
	}






}
