package com.uchump.prime.CORE.UI.Events;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.badlogic.gdx.InputProcessor;
import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME.METATRON.M.Boot.DummyInteruptApp;
import com.uchump.prime._PRIME.METATRON.Signals.Listener;
import com.uchump.prime._PRIME.METATRON.Signals.Signal;


public interface iEventReciever extends iAgent, EventTarget, EventListener, Listener<Signal<Event>>{

	
	@Override
	 public default void handleEvent(Event evt) {
		
	}
	
	public default boolean handle(Signal<Event> event) {
		Log("$&: " + event.toString());
		return false;
	}
	

	public default boolean handle(Event evnt)
	{
		if(evnt instanceof aEvent)
		{
			return this.handle((aEvent)evnt);
		}
		
		if(evnt instanceof Signal)
		{
			return this.handle((Signal)evnt);
		}
		
		return false;
	}

	
	public default boolean handle(aEvent event) {
		
		

		return false;
	}

	

	
}
