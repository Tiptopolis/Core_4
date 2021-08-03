package com.uchump.prime.CORE.UI.SceneGraph.Widgets;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.uchump.prime.CORE.UI.SceneGraph.uInputListener;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uWidget;

public class uPanel extends uWidget {

	@Override
	public void build() {
		super.build();
		this.Transform.SetLocalScale(new Vector3(100, 64, 1));
	}

	@Override
	public void show() {
		this.setVisible(true);
		this.addListener(new uInputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(super.touchDown(event, x, y, pointer, button))
				{
					Stage S = event.getStage();
					Actor A = event.getListenerActor();
					//Log(">"+S +"\n>" + A);
					A.remove();
					S.addActor(A);
					return true;
				}
				else
					return false;
			}
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				
				
				//Log("You clicked the Panel [" +getZIndex()+"]"+ event.getListenerActor().getName());
			
				
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

}
