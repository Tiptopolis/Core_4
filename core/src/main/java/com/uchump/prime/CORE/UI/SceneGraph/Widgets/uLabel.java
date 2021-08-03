package com.uchump.prime.CORE.UI.SceneGraph.Widgets;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.Resources.FontAtlas;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uWidget;

public class uLabel extends uWidget {

	public String Label;
	public BitmapFont usingFont;

	public uLabel(String name, String label) {
		super();
		this.setName(name);
		this.Label = label;
		
		this.label();
	}

	
	public void label() {
		this.usingFont = FontAtlas.SystemDefault;
		this.Transform.SetLocalScale(new Vector3(32*this.Label.length(), 32, 1));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(this.isVisible()) {
		Vector3 lP = this.Transform.GetLocalPosition();
		Vector3 lS = this.Transform.GetLocalScale();
		this.setBounds(lP.x, lP.y, lS.x, lS.y);
		Sketcher.setColor(this.inner);
		Sketcher.Drawer.filledRectangle(lP.x, lP.y, lS.x, lS.y);
		Sketcher.setColor(this.outter);
		Sketcher.Drawer.rectangle(lP.x, lP.y, lS.x, lS.y);
		//Sketcher.interupt();
		FontAtlas.SystemDefault.draw(Sketcher.getBatch(), this.Label, lP.x, lP.y+(lS.y));
		//Sketcher.interupt();
		}
	}
	


}
