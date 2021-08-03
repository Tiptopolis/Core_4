package com.uchump.prime.CORE.UI.Events;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;
import java.util.ArrayList;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Queue;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME.METATRON.Agents.iControlAgent;
import com.uchump.prime._PRIME.METATRON.M.Boot.DummyInteruptApp;
import com.uchump.prime._PRIME.METATRON.Signals.Listener;
import com.uchump.prime._PRIME.METATRON.Signals.Message;
import com.uchump.prime._PRIME.METATRON.Signals.Signal;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;

public class aEventManager extends InputMultiplexer implements iEventProcessor, iMonad, iNode<eNode<aEventManager>,Signal,Listener> {

	protected aEventManager parent;
	protected int hDepth = 0;
	public boolean isProxy = false;

	protected static ArrayList<iEventReciever> listeners = new ArrayList<iEventReciever>();
	public static Queue<aEvent> pending = new Queue<aEvent>();

	public static boolean LogInput = false;

	public aEventManager() {
		Log("-" + this.getClass().getSimpleName() + " created");
	}

	public aEventManager(aEventManager parent) {
		this.parent = parent;
		this.hDepth = parent.hDepth + 1;
		this.isProxy = true;
		parent.listeners.add(this);
		Log("\n-" + this.getClass().getSimpleName() + " created");
		
	}

	public void update(float deltaTime) {
		
		for (aEvent e : pending) {
			for (iEventReciever r : listeners) {
				r.handle(e);
			}
		}
	}

	@Override
	public void broadcast() {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(Object out) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public boolean handle(Event evnt)
	{
		if(evnt instanceof aEvent)
		{
			return this.handle((aEvent)evnt);
		}
		
		return false;
	}

	@Override
	public boolean handle(Message m) {
		// TODO Auto-generated method stub
		return false;
	}

	


	////////////////
	@Override
	public boolean keyDown(int keycode) {
		// Log(this.getClass().getSimpleName() + " k->" + keycode);
		if (LogInput)
			Log(this.getClass().getSimpleName() + hDepth + StdInputEvents.KeyDown + keycode);
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		// Log(this.getClass().getSimpleName() + " k<-" + keycode);
		if (LogInput)
		Log(this.getClass().getSimpleName() + hDepth + StdInputEvents.KeyUp + keycode);
		return super.keyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		if (LogInput)
		Log(this.getClass().getSimpleName() + hDepth + StdInputEvents.KeyTyped + character);
		return super.keyTyped(character);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (LogInput)
		Log(this.getClass().getSimpleName() + hDepth + StdInputEvents.TouchDown + "[(" + screenX + "," + screenY + ")|"
				+ pointer + "|" + button + ")]");
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (LogInput)
		Log(this.getClass().getSimpleName() + hDepth + StdInputEvents.TouchUp + "[(" + screenX + "," + screenY + ")|"
				+ pointer + "|" + button + ")]");
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (LogInput)
		Log(this.getClass().getSimpleName() + hDepth + StdInputEvents.TouchDragged + "[(" + screenX + "," + screenY + ")|" + pointer
				+ ")]");
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		//if (this.logInput)
		// Log(this.getClass().getSimpleName()+hDepth+" t<->" + "[("+screenX + "," +
		// screenY+")]");
		return super.mouseMoved(screenX, screenY);
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		if (LogInput)
		Log(this.getClass().getSimpleName() + hDepth + StdInputEvents.Scrolled + "[(" + amountX + "," + amountY + ")]");
		return super.scrolled(amountX, amountY);
	}

	public static enum StdInputType {

		Touch("t"),
		Scroll("s"),
		Cursor("x"),
		Command("c"),
		Key("k");

		public final String get;

		private StdInputType(String symbol) {
			this.get = symbol;
		}
	}

	public static enum StdInputEvents {
		TouchDown(InputEvent.Type.touchDown,StdInputType.Touch, "->"),
		TouchUp(InputEvent.Type.touchUp,StdInputType.Touch, "<-"),
		TouchDragged(InputEvent.Type.touchDragged,StdInputType.Touch, "<_>"),
		KeyDown(InputEvent.Type.keyDown,StdInputType.Key, "->"),		
		KeyUp(InputEvent.Type.keyUp,StdInputType.Key, "<-"),
		KeyTyped(InputEvent.Type.keyTyped,StdInputType.Key, "<->"),
		Scrolled(InputEvent.Type.scrolled,StdInputType.Scroll, "<+>"),
		CursorMoved(InputEvent.Type.mouseMoved, StdInputType.Cursor,"<->");

		
		public final InputEvent.Type I;
		public final StdInputType T;
		public final String Symbol;

		private StdInputEvents(InputEvent.Type I,StdInputType T, String symbol) {
			this.T = T;
			this.Symbol = symbol;
			this.I = I;
		}

		@Override
		public String toString() {
			return " <[" + this.T.get + "|" + this.Symbol + "]> ";
		}



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

	public String logInput() {
		String log = "";
		return log;
	}

	@Override
	public String toLog() {
		return this.getClass().getSimpleName();
	}


	@Override
	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		
		
	}

	@Override
	public void removeEventListener(String type, EventListener listener, boolean useCapture) {
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
	public Object as(_N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getKey() {
		return this;
	}

	@Override
	public Object getValue() {
		return this;
	}

	@Override
	public Object setValue(Object value) {
		return value;
	}

	@Override
	public iSpace getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getExpanded() {
		return false;
	}

	@Override
	public void setExpanded(boolean is) {
		
	}


	

}
