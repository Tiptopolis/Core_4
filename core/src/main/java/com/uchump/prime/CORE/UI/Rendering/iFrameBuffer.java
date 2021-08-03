package com.uchump.prime.CORE.UI.Rendering;

public interface iFrameBuffer {

	// bufferes from source
	
	
	
	public void begin();
	
	public void draw(); //actual draw instructions
	
	public void end();

	public void refresh();

	public void buffer();
	
	public void dispose();

}
