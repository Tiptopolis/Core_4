package com.uchump.prime._PRIME.METATRON;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.UI.Camera.OrthoController;
import com.uchump.prime.CORE.UI.Events.aEvent;
import com.uchump.prime.CORE.UI.Events.aEventManager;
import com.uchump.prime.CORE.UI.Events.iEventReciever;
import com.uchump.prime.CORE.UI.Events.iEventSender;
import com.uchump.prime.CORE.UI.Events.uEventManager;
import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME.METATRON.Agents.iControlAgent;
import com.uchump.prime._PRIME.METATRON.Agents.iControlGroup;
import com.uchump.prime._PRIME.METATRON.M.MetatronConsole;
import com.uchump.prime._PRIME.METATRON.M.MetatronShell;
import com.uchump.prime._PRIME.METATRON.M.Boot.DummyInteruptApp;
import com.uchump.prime._PRIME.METATRON.M.Boot.MetatronBoot;
import com.uchump.prime._PRIME.METATRON.M.File.TimeKey;
import com.uchump.prime._PRIME.METATRON.Signals.Signal;
import com.uchump.prime._PRIME._coPRIME.N_M.Maths;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class Metatron extends InputMultiplexer implements iMonad, iEventSender, iEventReciever, iControlGroup {

	// CLI ICA central time-handler
	// Command Line Interface & Interpreter Compiler Assembler

	public final static Metatron TheMetatron;
	public static CoreTypeRegistry CORE = CoreTypeRegistry.TYPE;
	public MetatronShell Shell;
	public MetatronConsole Console;
	public static CoreTypeRegistry R;

	public TimeKey Module = new TimeKey(); // PULSER

	public TimeKey Metranome = new TimeKey(); // SCANNER
	public TimeKey DeltaTime = new TimeKey(); // FREQUENCY
	public TimeKey RealSecond = new TimeKey(); // COUNTER

	public TimeKey iTime = new TimeKey(); // COUNTER

	static {

		TheMetatron = new Metatron();
		TheMetatron.Shell = new MetatronShell();
		TheMetatron.Console = new MetatronConsole();
		MetatronConsole.mShell = TheMetatron.Shell;
		Gdx.input.setInputProcessor(TheMetatron);
	}

	public Metatron() {

	}

	public void update(Number deltaTime) {
		// this.is();
		// Log(this.toLog());
		if (uChumpEngine.MainApp == null) {
			// uChumpEngine.MainApp = new MetatronBoot(TheMetatron);
			// this.Shell.setView(uChumpEngine.MainApp);
			this.Shell.launch(new MetatronBoot(TheMetatron));
		}
		
		if (deltaTime.floatValue() > 0.999f)
			MetatronShell.launch(new DummyInteruptApp());

		DeltaTime.I = this.DeltaTime.I.byteValue() + deltaTime.floatValue();
		DeltaTime.i = 1;
		DeltaTime.t = deltaTime.floatValue();
		RealSecond.I = (this.RealSecond.I.floatValue() + deltaTime.floatValue()) % 360;
		//////
		Module.n = Byte.MIN_VALUE;
		Module.m = Byte.MAX_VALUE;
		Module.i = 1;

		Module.a = Module.a.byteValue() + this.Module.i.byteValue();
		Module.t = this.Module.I;
		Module.I = Maths.map(this.Module.a.floatValue(), this.Module.n.floatValue(), this.Module.m.floatValue(), 0f,
				1f);
		//////

		//////
		Metranome.n = Byte.MIN_VALUE;
		Metranome.m = Byte.MAX_VALUE;
		Metranome.i = 1;

		Metranome.a = Metranome.a.byteValue() + Metranome.i.byteValue();
		Metranome.t = Maths.map(Metranome.a.floatValue(), Metranome.n.floatValue(), Metranome.m.floatValue(), -1, 1);
		Metranome.I = Math.abs(Metranome.t.floatValue());
		//////

		iTime.I = ((RealSecond.I.floatValue() / (RealSecond.I.intValue() * DeltaTime.I.floatValue()))
				* DeltaTime.I.floatValue());
		if (iTime.I.floatValue() == Float.POSITIVE_INFINITY)
			iTime.I = 1f;
		// Log(this.toLog());


		this.Shell.update(deltaTime.floatValue());

	}

	@Override
	public void addProcessor(InputProcessor processor) {
		if (processor == null)
			throw new NullPointerException("processor cannot be null");

		Log(processor.getClass().getSimpleName() + "                     <- MTRN Multiplexer ADDED");
		if (!this.getProcessors().contains(processor, true) && !this.getProcessors().contains(processor, false))
			getProcessors().add(processor);
		Console.post("!METATRON:_:InputProcessor Added:_[" + processor + "]");
	}

	public String toLog() {
		String log = "";

		// K V T
		log += 0 / 1;
		log += "\n";

		log += "\n";
		log += "[METATRON]\n";
		log += "{\n";
		log += "|>{DeltaTime [" + DeltaTime + "] " + DeltaTime.toLog() + "}\n"; // Constant N
		log += "|>{RealSecond [" + RealSecond + "] " + RealSecond.toLog() + "}\n"; // Accumulator 0->Inf, counter
		log += "|>{Module [" + Module + "] " + Module.toLog() + "}\n"; // Interpolator 0->N-^0 : 0->1-^0, returns to 0
																		// after reaching N
		log += "|>{Metranome [" + Metranome + "] " + Metranome.toLog() + "}\n"; // Cycle -N-N:-1<->1
		log += "|>{iTime} [" + iTime + "]" + iTime.toLog();
		log += "}\n";

		// log += ((Module.i.floatValue() - Module.Raw().byteValue() %
		// (RealSecond.I.byteValue() + 1)));// RNG?
		// log += "\n";

		// Log("ProjectedSecond: " + ((RealSecond.I.floatValue() /
		// (RealSecond.I.intValue() * DeltaTime.I.floatValue()))
		// * DeltaTime.I.floatValue()));

		return log;
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
	public void broadcast() {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(Object at) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEventListener(String type, EventListener listener, boolean useCapture) {
		// TODO Auto-generated method stub

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
		return this;
	}

	@Override
	public iAgent getAgent() {
		return this;
	}

}
