package com.uchump.prime._PRIME.METATRON.M.Boot;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.Rendering.DrawBuffer;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.M.File.TimeKey;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;


public class LuciferSprite implements iMonad {

	public oLF_Renderer_3M R;

	public Metatron M;
	public TimeKey TIME;
	public uTransform TRANSFORM;

	protected DrawBuffer lBuffer;
	protected Sprite lSprite;
	
	float mem;
	
	public Lum MTX;
	
	public boolean lensUp = false;
	public float lensAngle = 0;
	
	public LuciferSprite(oLF_Renderer_3M R, float mem) {
		this.R = R;
		this.M = Metatron.TheMetatron;
		this.TIME = new TimeKey();
		this.TRANSFORM = new uTransform(this);
		this.mem = mem;
		this.lBuffer = DrawBuffer.unmanagedBuffer(R.C.Camera.getViewRect(), true);
		 MTX = new Lum(this);
		 
	}

	public void update(Quaternion time) {

		float fTheta = M.DeltaTime.I.floatValue();
		int iTheta = 1;
		
		this.TRANSFORM.SetLocalPosition((new Vector3(time.x,time.y,time.z)));
		
		this.TIME.a = fTheta;
		this.TIME.t = iTheta;
		this.TIME.n = M.Metranome.I.floatValue();
		this.TIME.m = M.iTime.I.floatValue();
		
		//angle of mouse from screen-center
		this.TIME.i=time.w;
		//projected mousePosition;
		this.TIME.o=time.x;
		this.TIME.d=time.y;
		this.TIME.e = time.z;
		
		this.lensAngle = time.w;
		MTX.update(new Vector3(R.CellSize/2,R.CellSize/2,1));
		
	}

	public void render() {
		// toBuffer
		Vector3 lP = this.TRANSFORM.GetLocalPosition();
		this.lensUp = !this.lensUp;
		lBuffer.begin();
		Sketcher.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, R.CellSize, R.CellSize));
		Sketcher.Drawer.setDefaultLineWidth(1-R.C.Camera.zoom);
		Sketcher.begin();
		Sketcher.setColor(new Color(this.TIME.e.floatValue(),this.TIME.o.floatValue(),this.TIME.d.floatValue(),this.TIME.i.floatValue()));
		
		//Sketcher.Drawer.filledRectangle(0, 0, R.CellSize, R.CellSize);
		MTX.draw();
		
		Sketcher.end();
		lBuffer.end();
		this.lSprite = new Sprite(lBuffer.cache);
		this.lSprite.setFlip(false, true);
		
	}

	public void draw() {
		// toScreen
		//
		//Sketcher.getBatch().draw(lBuffer.view, this.TIME.o.floatValue(), this.TIME.d.floatValue(), R.CellSize,R.CellSize);
		//Sketcher.getBatch().draw(lBuffer.view, this.TIME.o.floatValue(), this.TIME.d.floatValue(), R.CellSize,R.CellSize);
		
		
		//this.lSprite.setOrigin(this.TIME.o.floatValue()+(R.CellSize/2),this.TIME.d.floatValue()+(R.CellSize/2));
		this.lSprite.setSize(R.CellSize, R.CellSize);
		
			
		
		//
		this.lSprite.setOrigin(R.CellSize/2, R.CellSize/2);
		this.lSprite.setPosition(this.TIME.o.floatValue()-(R.CellSize/2), this.TIME.d.floatValue()-(R.CellSize/2));
		this.lSprite.setRotation((45-((this.TIME.i.floatValue())*MathUtils.radDeg)));	
		this.lSprite.setSize(R.CellSize/2, R.CellSize/2);
		
		this.lSprite.draw(Sketcher.getBatch());
	}

	@Override
	public uTransform getTransform() {

		return this.TRANSFORM;
	}

	@Override
	public void transformUpdated() {

	}
	


	public void dispose()
	{
		MTX.At.clear();
		lBuffer.dispose();
	}
	
	@Override
	public String toLog() {
		
		String log = "\n";
		log +="[Lucifer]\n";
		log += this.TIME.toLog();
		
		return log;
	}

}
