package com.uchump.prime.TESTS.MatrixBox;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.github.czyzby.kiwi.util.tuple.immutable.Pair;
import com.uchump.prime.CORE.UI.Rendering.Drawable.GlyphSequence;
import com.uchump.prime.CORE.UI.Rendering.Drawable.iDrawable;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uLabel;
import com.uchump.prime.CORE.UI.SceneGraph.Widgets.uPanel;
import com.uchump.prime.LIB.MAP.A_I.aMap;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME.METATRON.M.File.Rect;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aMatrix;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uMatrix;

public class MatrixDrawable extends uMatrix implements iDrawable {
	// its a map lolol

	public Color innerM = Color.BLACK;
	public Color outterM = Color.ORANGE; // lil transparency detection fns
	public Rect Mtx = new Rect(0, 0, 32, 32);

	public Color innerC = Color.WHITE;
	public Color outterC = Color.BLACK;

	public Vector2 cSize = new Vector2(MathUtils.PI,MathUtils.PI);
	
	public ArrayList<Vector3> Cells = new ArrayList<Vector3>();
	public ArrayList<Pair<uLabel, uPanel>> a;
	public HashMap<Vector3, Number> CellData = new HashMap<Vector3, Number>(); // RGB

	
	protected Vector2 mSize;
	private Vector3 mAt = new Vector3(-1, -1, -1);
	public boolean mOver = false;
	// Vector2 cellSize

	public MatrixDrawable(Number n) {
		this(n, 0);
		this.mSize = new Vector2(this.i, this.i);
	}

	// array[width * row + col] = value;
	public MatrixDrawable(Number n, Number m) {
		super(n, m);
		this.mSize = new Vector2(this.i, this.i); // W/H

		int I = this.i;
		int x = 0;
		int y = 0;

		int i = -1;
		for (int w = (int) mSize.x; w > 0; w--) {
			for (int h = 0; h < mSize.y; h++) {
				i++;
				x = w;
				y = h;
				Vector3 v = new Vector3(x, y, 1);
				this.Cells.add(v);
				CellData.put(v, this.elements.get(i).floatValue());

			}
		}
	}

	public MatrixDrawable(Matrix4 M) {
		super(M);

	}

	public void update() {

		Log(super.toLog());
		for (Vector3 V : this.Cells) {
			// Log(V.toString());
		}
		this.mAt = new Vector3(-1, -1, -1);
		this.updateState(mAt);
	}
	
	public void updateCellSize(Vector2 size)
	{
		this.cSize = size.cpy();
	}

	public void updateState(Vector3 mouseAt) {
		this.mOver = false;
		this.mAt = mouseAt.cpy();

		if (this.Mtx.contains(new Vector2(mAt.x, mAt.y)))
			this.mOver = true;

	}
	
	public void updateValues(aMatrix M)
	{
		this.elements = (ArrayList<Number>) M.elements.clone();
	}

	@Override
	public void draw() {
		Sketcher.Drawer.setDefaultLineWidth(0.1f - (1 / this.i));
		for (Vector3 v : this.Cells) {
			int i = this.Cells.indexOf(v);
			float f = 0;
			// f = this.elements.get(i).floatValue();// same as below
			f = this.CellData.get(v).floatValue();// same as above
			this.outterC = new Color(f, f, f, 1);
			this.innerC = new Color(1, 1, 1, 1 - f);
			Sketcher.setColor(innerC);
			Rect dst = new Rect((v.x - 1) * cSize.x, (v.y) * cSize.y, cSize.x, cSize.y);
			Sketcher.Drawer.filledRectangle(dst.getRectangle());
			Sketcher.setColor(this.outterC);
			Sketcher.Drawer.rectangle(dst.getRectangle());
		}
	}

	public void clear() {
		this.CellData.clear();
		this.Cells.clear();
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
	public String toLog() {
		// TODO Auto-generated method stub
		return null;
	}

}
