package com.uchump.prime.CORE.UI.Rendering.Drawable;

import com.badlogic.gdx.graphics.Color;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;

public interface iDrawable extends iMonad {

	public default Color innerColor() {
		return Color.WHITE;
	}

	public default void innerColor(Color c) {

	}

	public default Color outterColor() {
		return Color.BLACK;
	}

	public default void outterColor(Color c) {

	}

	public void draw();
	
}
