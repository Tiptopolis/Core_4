package com.uchump.prime.CORE.UI.Events;

import com.uchump.prime._PRIME.METATRON.Agents.iAgent;

public interface iEventSender extends iAgent{

	
	
	public void broadcast();
	public void broadcast(Object at);
}
