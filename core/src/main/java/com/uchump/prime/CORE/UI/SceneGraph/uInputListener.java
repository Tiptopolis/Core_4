package com.uchump.prime.CORE.UI.SceneGraph;

import java.util.EventListener;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uchump.prime.CORE.UI.Events.iEventReciever;
import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME.METATRON.Agents.iControlAgent;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class uInputListener extends ClickListener implements iEventReciever, EventListener, iAgent{

	

	@Override
	public void addEventListener(String type, org.w3c.dom.events.EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEventListener(String type, org.w3c.dom.events.EventListener listener, boolean useCapture) {
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
	public uTransform getTransform() {
		// TODO Auto-generated method stub
		return null;
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

}
