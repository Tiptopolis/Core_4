package com.uchump.prime.CORE.UI.Rendering.Drawable;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.Resources.FontAtlas;
import com.uchump.prime.CORE.Resources.Glyph;
import com.uchump.prime.CORE.Resources.GlyphSheet;
import com.uchump.prime.CORE.UI.Rendering.Drawable.iDrawable;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uLabel;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;


public class GlyphSequence implements CharSequence, iDrawable {

	protected GlyphSheet Font;
	public String Text = "";
	public Glyph[] Glyphs;
	public HashMap<CharSequence, Glyph> TEXT = new HashMap<CharSequence, Glyph>();

	public Quaternion metrics = new Quaternion(); // (char min/max size) [(minX,minY,maxX,maxY)]
	public Vector2 size = new Vector2();

	public GlyphSequence() {
		this.Font = FontAtlas.DefaultGlyphs;
		this.refresh();
	}

	public GlyphSequence(CharSequence s) {
		this.Font = FontAtlas.DefaultGlyphs;
		this.Text = (String) s;
		this.refresh();
	}

	public GlyphSequence(String s) {
		this.Font = FontAtlas.DefaultGlyphs;
		this.Text = s;
		this.refresh();
	}

	public GlyphSequence(GlyphSheet f, CharSequence s) {
		this.Font = f;
		this.Text = (String) s;
		this.refresh();
	}

	public GlyphSequence(GlyphSheet f, String s) {
		this.Font = f;
		this.Text = s;
		this.refresh();
	}

	public void setFont(GlyphSheet fnt) {
		this.Font = fnt;
		this.refresh();
	}

	public void refresh() {
		this.Glyphs = new Glyph[this.Text.length()];
		this.TEXT.clear();
		for (int i = 0; i < this.Glyphs.length; i++) {
			Glyphs[i] = this.Font.newGlyph(this.Text.charAt(i));
			this.TEXT.put("" + Glyphs[i].Char, Glyphs[i]);
		}
		this.metrics = this.charMetrics();
		this.size = new Vector2(metrics.z * (this.Text.length() - 1), metrics.w);
	}

	@Override
	public int length() {
		return this.Text.length();
	}

	@Override
	public char charAt(int index) {
		return this.Text.charAt(index);
	}

	public Glyph glyphAt(int index) {
		return this.Glyphs[index];
	}

	@Override
	public GlyphSequence subSequence(int start, int end) {

		String i = this.Text.substring(start, end);

		return new GlyphSequence(this.Font, i);
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
	
	public static uLabel getLabelFor(GlyphSequence G)
	{
		return G.toLabel();
	}

	public uLabel toLabel() {
		return new uLabel("aGlyphLabel", this.Text) {

			@Override
			public void label() {
				usingFont = Font.getUsedFont();
				Transform.SetLocalScale(new Vector3(metrics.z * (this.Label.length() - 1), metrics.w, 1));
			}
		};

	}

	protected Quaternion charMetrics() {
		int minX = 0;
		int minY = 0;
		int maxX = 0;
		int maxY = 0;
		for (Glyph G : this.Glyphs) {
			if (G.size.x < minX)
				minX = (int) G.size.x;
			if (G.size.y < minY)
				minY = (int) G.size.y;

			if (G.size.x > maxX)
				maxX = (int) G.size.x;
			if (G.size.y > maxY)
				maxY = (int) G.size.y;

		}
		return new Quaternion(minX, minY, maxX, maxY);

	}

	@Override
	public String toString() {
		String log = "";
		for (Glyph G : this.Glyphs) {
			log += G.toString();
		}

		return log;
	}

	@Override
	public String toLog() {
		String log = "[{" + this.Text + "}]\n";

		String l2 = "";
		
		for (Glyph G : this.Glyphs) {
			log += "(" + G.toString() + ")";
			l2 += "(" + G.size.x + "," + G.size.y + ")";
		}
		String l5 = "" + this.size;
		String l3 = "" + FontAtlas.SystemDefault.getScaleX() + "," + FontAtlas.SystemDefault.getScaleY();
		String l4 = "" + FontAtlas.SystemDefault.getLineHeight() + "," + FontAtlas.SystemDefault.getCapHeight();
		log += "\n{" + l5 + "}";
		log += "\n{" + l2 + "}";
		log += "\n(" + l3 + ")";
		log += "\n(" + l4 + ")";
		log += "\n(" + this.metrics + ")";
		return log;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
