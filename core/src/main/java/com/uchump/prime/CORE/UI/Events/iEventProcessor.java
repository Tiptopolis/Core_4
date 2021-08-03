package com.uchump.prime.CORE.UI.Events;

import com.badlogic.gdx.InputProcessor;
import com.uchump.prime._PRIME.METATRON.Signals.Message;

public interface iEventProcessor extends iEventReciever,iEventSender, InputProcessor{

	public boolean handle(Message m);
	
}
