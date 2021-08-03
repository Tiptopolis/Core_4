package com.uchump.prime._PRIME.METATRON.Signals;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

public class Message extends Signal<Event>{

	
	public String content = "";
	
	public Message(String content)
	{
		this.content = content;
	}
	
	@Override
	public String toString()
	{
		return this.content;
	}

}
