package com.uchump.prime.CORE.UI.SceneGraph.Widgets;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.uchump.prime.CORE.UI.SceneGraph.uInputListener;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uWidget;

public class uButton extends uWidget implements iButton{

	
	public uButton()
	{
		super();
	}
	
	public uButton(String name)
	{
		super(name);
	}
	
	
	public void show() {
		this.setVisible(true);
		this.addListener(new uInputListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				in();
				press();
				out();
				Log("You clicked the Button [" + getZIndex() + "]" + event.getListenerActor().getName()); // Z-INDEX<=o
			}
		});
	}
	
	
	
	
	




	@Override
	public void in() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void press() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void out() {
		// TODO Auto-generated method stub
		
	}

}
