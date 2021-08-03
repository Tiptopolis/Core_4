package com.uchump.prime._PRIME.METATRON.M;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.w3c.dom.events.Event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime.CORE.UI.aViewContext;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime.CORE.UI.Events.aEvent;
import com.uchump.prime.CORE.UI.Events.aEventManager;
import com.uchump.prime.CORE.UI.Events.iEventProcessor;
import com.uchump.prime.CORE.UI.Events.iEventReciever;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.Agents.iControlAgent;
import com.uchump.prime._PRIME.METATRON.M.Boot.DummyInteruptApp;
import com.uchump.prime._PRIME._coPRIME.N_M._N;


public class MetatronShell extends aEventManager {

	protected static Metatron TheMetatron;
	public static MetatronShell mShell;
	public static MetatronConsole mConsole;
	
	public static AssetManager GdxAssetManager = new AssetManager();
	public static JFileChooser JavaFileChoser;
	
	
	
	public static uApp MainApp;

	public static aViewContext GlobalPrimeModal; // view context
	
	// MENU

	public MetatronShell() {
		TheMetatron = Metatron.TheMetatron;
		mShell = this;
	
		//mShellLocal = new MetaFS(".ShellRoot");
	
		mConsole.mShell = this;
		Metatron.TheMetatron.addProcessor(this);
		
	}

	@Override
	public void update(float deltaTime) {		
		if(MainApp!=GlobalPrimeModal)
			MainApp.update();
		
		GlobalPrimeModal.update(); // MainApp.CurrentView
		
	}

	public static void launch(uApp app) {
		
		
		
		if (MainApp == null || !MainApp.equals(app)) {
			if(MainApp!= null && !MainApp.equals(app))
			{
				Log("METATRON_SHELL.CLOSING: [" + MainApp.getClass().getSimpleName()+"]...");
				MainApp.dispose();
			}
			Log("METATRON_SHELL.LAUNCHING: [" + app.getClass().getSimpleName()+"]...");
			uApp bootApp = app;
			MainApp = bootApp;
			MainApp.create();
			uChumpEngine.MainApp = MainApp;
			MainApp.enter();
			MainApp.resize(Width, Height);
			mShell.setView(MainApp.CurrentView);
			MainApp.resume();
		}
		
		
	}
	
	

	@Override
	public boolean handle(aEvent event) {
		
		if (event.content.equals("SHELL:REBOOT")) {
			launch(MainApp);
			return true;
		}
		
		if (event.content.equals("SHELL:RESTART")) {
			launch(new DummyInteruptApp());
			
			return true;
		}

		return false;
	}

	// set GlobalPrimeModal
	public void setView(aViewContext view) {

		Log("Metatron.Shell.setView(" + view.getName() + ")");
		if (GlobalPrimeModal != null) {
			TheMetatron.removeProcessor(GlobalPrimeModal);
			GlobalPrimeModal.exit();
		}
		GlobalPrimeModal = view;
		TheMetatron.addProcessor(GlobalPrimeModal);
		mConsole.localPrimeModal = GlobalPrimeModal;
		GlobalPrimeModal.enter();

	}
	
	@Override
	public String toString()
	{
		return "[{(<MetatronShell>)}]";
	}

	public String toLog() {
		String log = "_________________________\n";
		log += this.getClass().getSimpleName();
		log += "\n";
		//log += "mFS: " + mShellLocal.toLog();
		//log += "\n";
		//log += JavaFileChoser;
		log += "\n";
		if (JavaFileChoser != null) {
			log += JavaFileChoser.getSelectedFile();
			log += "\n";
		}
		log += "@%" + GlobalPrimeModal.getName() + "[" + GlobalPrimeModal.listeners.size() + "]"; // current view
																									// context
		log += "\n";
		log += "$%" + mConsole.localPrimeModal.toString() + "["
				+ mConsole.localPrimeModal.listeners.size() + "]";// primary input command targets
		log += "\n";
		log += "Metatron.Multiplexer[" + TheMetatron.getProcessors().size + "]";
		log += "\n";
		for (InputProcessor E : TheMetatron.getProcessors()) {
			log += E.toString()+"\n";
		}
		log += "\n";
		log += "Shell.Listeners[" + this.listeners.size() + "]";
		log += "\n";
		for (iEventReciever E : this.listeners) {
			log += E.toString()+"\n";
		}
		log += "\n";
		log += "_________________________";
		return log;
	}

	public static void invokeJFileChoser() {

		Thread T = new Thread(new Runnable() {

			@Override
			public void run() {
				JFileChooser chooser = new JFileChooser();
				JFrame f = new JFrame();
				f.setVisible(true);
				f.toFront();
				f.setVisible(false);
				int res = chooser.showSaveDialog(f);
				f.dispose();
				MetatronShell.JavaFileChoser = chooser;
				if (res == JFileChooser.APPROVE_OPTION) {
					// Do some stuff

				}
			}

		});

		T.start();

	}

	@Override
	public iControlAgent getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public iSpace getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getExpanded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setExpanded(boolean is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object Name() {
		// TODO Auto-generated method stub
		return this.Index();
	}

	@Override
	public _N Index() {
		// TODO Auto-generated method stub
		return new Name(this.Symbol());
	}

	@Override
	public String Symbol() {
		
		return this.toString();
	}

	@Override
	public _N new_N(_N index, Object name, String symbol) {
		
		return this;
	}

	@Override
	public Object as(_N n) {
		return this;
	}

	@Override
	public Object getKey() {
		return this.Name();
	}

	@Override
	public Object getValue() {
		return this.Index();
	}

	@Override
	public Object setValue(Object value) {
		return this;
	}
	
	

}
