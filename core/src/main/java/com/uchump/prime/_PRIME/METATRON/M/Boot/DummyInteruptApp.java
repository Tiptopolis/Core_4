package com.uchump.prime._PRIME.METATRON.M.Boot;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.uchump.prime.uChumpEngine;
import com.uchump.prime.CORE.Device.uApp;
import com.uchump.prime._PRIME.METATRON.Metatron;

public class DummyInteruptApp extends uApp{

	int i = 0;
	
	
	@Override
	public void create()
	{
		i=0;
		Log("ABRAKADABRA!!!  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@Override
	public void resume()
	{
		uChumpEngine.ref();
	}
		
	
	
	
	@Override
	public void update()
	{
		Log(i);
		i++;
		if(i>=10)
			M.Shell.launch(new MetatronBoot(M));
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		
	}
}
