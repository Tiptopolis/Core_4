package com.uchump.prime.CORE.UI.Camera.A_I;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME.METATRON.M.File.Rect;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public abstract class aCamera implements iAgent{

	public aCameraController controller;

	public Camera camera;
	public Viewport viewport;

	public Rect view;
	public float zoom = 1f;
	public Vector3 right = new Vector3(1,0,0);		

	public aCamera()
	{
		
	}

	public aCamera(aCameraController controller) {
		this.controller = controller;

	}

	public void update() {
		// Override
		
		this.camera.update();		
		this.right = this.camera.direction.cpy().crs(this.camera.up.cpy()).nor();	
		this.view.set(this.getOrigin().x, this.getOrigin().y, this.camera.viewportWidth,
				this.camera.viewportHeight);
	}

	public void resize(int width, int height) {
		
		this.viewport.update(width, height);
		this.update();
		
		
	}

	// WorldPosition

	public Vector3 getSize() {
		return new Vector3((int) this.camera.viewportWidth, (int) this.camera.viewportHeight,0);
	}

	// CENTER
	public Vector3 getPosition() {
		return this.camera.position;
		
	}

	public Vector3 getOfficialPosition() {
		return this.camera.position;
	}
	
	public Vector3 getOrigin()
	{
		return new Vector3(this.getPosition().x-(this.camera.viewportWidth/2), this.getPosition().y-(this.camera.viewportHeight/2),0);
		
	}

	// CENTER
	public void setPosition(Vector2 newPos) {
		this.camera.position.set(new Vector3(newPos.x, newPos.y, 0));
	}

	public void setPosition(float x, float y) {
		this.camera.position.set(new Vector3(x, y, 0));
	}

	public void setPosition(Vector3 newPos) {
		this.camera.position.set(newPos);
	}

	public void setPosition(float x, float y, float z) {
		this.camera.position.set(new Vector3(x, y, z));
	}
	
	public void setPosition(float x, float y, float z, float w) {
		Vector3 v = new Vector3(x, y, z);
		this.camera.rotate(v, w*MathUtils.degRad);
		this.camera.position.set(v);
	}
	
	public void setPosition(Quaternion q) {
		Vector3 v = new Vector3(q.x,q.y, q.z);
		this.camera.rotate(v, q.w*MathUtils.degRad);
		this.camera.position.set(v);
	}

	public Vector3 getDirection()
	{
		if(this.camera== null)
			return new Vector3(0,-1f,0);
		
		return this.camera.direction;
	}
	
	//
	public float getZoom() {
		return 1f;
	}

	public Rect getViewRect() {
		if (this.view == null) {
			this.view = new Rect(this.getOrigin().x, this.getOrigin().y, this.camera.viewportWidth,
					this.camera.viewportHeight);
			return this.view;
		} else

			return this.view;
	}

	public Matrix4 getProjection() {
		return this.camera.combined;
	}
	
	public Camera getBaseCamera()
	{
		return this.camera;
	}

	public void setController(aCameraController controller) {
		this.controller = controller;
	}
	
	@Override
	public iAgent getSource() {
		return this.controller;
	}
	

	
	@Override
	public uTransform getTransform() {
		// TODO Auto-generated method stub
		return this.controller.getTransform();
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toLog() {
		// TODO Auto-generated method stub
		return this.getName();
	}

}
