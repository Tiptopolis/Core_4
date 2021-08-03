package com.uchump.prime.CORE.UI.SceneGraph.Widgets;

import com.uchump.prime.CORE.Resources.Glyph;
import com.uchump.prime.CORE.UI.Rendering.Drawable.GlyphSequence;

public class GlyphButton extends uButton{

	private GlyphSequence G;
	public uLabel Label;
	
	public GlyphButton(String G)
	{
		super(G);
		this.G = new GlyphSequence(G);		
		this.Label = this.G.toLabel();
	}
	
}
