package com.uchump.prime._PRIME._coPRIME.N_M.Prototype.UnityTransform;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME._coPRIME.N_M.VectorUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.UnityTransform.Transform;

public class gdxTransform extends Transform{

	public String name;
	public iMonad of;

	public static ArrayList<gdxTransform> All = new ArrayList<gdxTransform>();

	protected Vector3 _localDirection;
	protected Vector3 _localUp;
	protected Vector3 _localRight;

	public gdxTransform() {
		super();
		// All.add(this);

	}

	public gdxTransform(Vector3 position, Quaternion rotation, Vector3 scale) {
		_localPosition = position;
		_localRotation = rotation;
		_localScale = scale;

		_parent = null;
		_children = new Array<Transform>();
		_localToWorldMatrix = new Matrix4(_localPosition, _localRotation, _localScale);
	}

	public gdxTransform(iMonad of) {
		super();
		this.of = of;
		All.add(this);
	}

	public gdxTransform(iMonad of, Transform parent) {
		this(of);
		this.SetParent(parent);

	}

	public void attach(iMonad of, Transform parent) {
		this.of = of;
		this.SetParent(parent);
	}

	public void attach(iMonad of) {
		this.of = of;
	}

	@Override
	protected void UpdateMatricies() {
		super.UpdateMatricies();
		this.transformUpdated(this);
	}
	
	public void transformUpdated(Object source)
	{
		
	}

	public int GetDepth() {
		return this.depth;
	}

	public void setDepth(int d)
	{
		this.depth = d;
		this.UpdateMatricies();
	}
	////
	//

	public void setName(String name) {
		this.name = name;
	}

	public gdxTransform setFromMatrix(Matrix4 mat4) {
		this.SetLocalPosition(mat4.getTranslation(new Vector3()));
		this.SetRotation(mat4.getRotation(new Quaternion()));
		this.SetScale(mat4.getScale(new Vector3()));
		return this;
	}

	public gdxTransform setFromCamera(Camera camera) {
		Vector3 dir = camera.direction.cpy();
		Vector3 pos = camera.position.cpy();
		Matrix4 mat4 = new Matrix4().set(new Vector3(dir.x, 0, 0), new Vector3(0, dir.y, 0), new Vector3(0, 0, dir.z),
				pos);
		this.SetPosition(mat4.getTranslation(new Vector3()));
		this.SetRotation(mat4.getRotation(new Quaternion()));
		this.SetScale(mat4.getScale(new Vector3()));
		return this;
	}

	public gdxTransform setFromCameraCombined(Camera camera) {
		Matrix4 mat4 = camera.combined.cpy();
		this.SetLocalPosition(mat4.getTranslation(new Vector3()));
		this.SetRotation(mat4.getRotation(new Quaternion()));
		this.SetScale(mat4.getScale(new Vector3()));
		return this;
	}

	public gdxTransform setFromCameraProjection(Camera camera) {
		Matrix4 mat4 = camera.projection.cpy();
		this.SetLocalPosition(mat4.getTranslation(new Vector3()));
		this.SetRotation(mat4.getRotation(new Quaternion()));
		this.SetScale(mat4.getScale(new Vector3()));
		return this;
	}

	public Vector3 getMagnitude() {
		Vector3 s = this.GetScale();
		Vector3 l = this.GetLocalScale();
		Vector3 size = new Vector3(s.x / l.x, s.y / l.y, s.z / l.z);

		return size;
	}

	// you probably want getLocalDirection()...
	public Vector3 getDirection() {

		Quaternion q = this.GetRotation();
		Vector3 z = z = VectorUtils.mul4x3(q, new Vector3(0, -1, 0));
		Vector3 correctedR = new Vector3(z.x, z.z, z.y);

		return correctedR;
	}

	public Vector3 getLocalDirection() {

		Quaternion q = this.GetLocalRotation();
		Vector3 z = z = VectorUtils.mul4x3(q, new Vector3(0, -1, 0));
		Vector3 correctedR = new Vector3(z.x, z.z, z.y);

		return correctedR;
	}

	public void setLookDirection(Vector3 dir) {

		// this._localRotation = VectorUtils.upcast(dir);
		this.SetLocalRotation(VectorUtils.upcast(dir));
	}

	public Vector3 getUnitDirection() {
		return this.unitDir();
	}

	public Vector3 unitDir() {
		return this.GetLocalPosition().cpy().add(getLocalDirection().nor().scl(this.GetLocalScale()));
	}

	public Vector3 unitDir(Vector3 scale) {
		return this.GetLocalPosition().cpy().add(getLocalDirection().nor().scl(scale));
	}

	public Vector3 unitDir(float scale) {
		return this.GetLocalPosition().cpy().add(getLocalDirection().nor().scl(scale));
	}
	
	@Override
	public gdxTransform GetParent()
	{
		return (gdxTransform)super.GetParent();
	}

	/////
	//
	public boolean hasParent() {
		if (this.GetParent() != null)
			return true;
		else
			return false;
	}

	public boolean isRoot() {
		return !this.hasParent();
	}

	public void destroy() {
		// this will have problems in the long-run
		this.of = null;
		this.RemoveParent();
		All.remove(this);
	}

	////

	public Transform copyTo(Transform other) {
		other._localPosition.set(this.GetLocalPosition().cpy());
		other._localScale.set(this.GetLocalScale().cpy());
		other._localRotation.set(this.GetLocalRotation().cpy());
		other.UpdateMatricies();

		return other;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public String toLog() {
		// Transform is mathematically equivalent to Gdx camera's Inverse Projection
		// Matrix?
		String log = "";
		log += "Transform: " + this.toString();
		if (this.of != null) {
			log += " | of " + this.of.getClass().getSimpleName();
		}

		log += "\n";
		log += "Position:     :@:" + this.GetPosition() + "     :#: " + this.GetLocalPosition();
		log += "\n";
		log += "Rotation:     :@:" + this.GetRotation() + "     :#: " + this.GetLocalRotation();
		log += "\n";
		log += "Scale:        :@:" + this.GetScale() + "        :#: " + this.GetLocalScale();
		log += "\n";
		log += "uMagnitude:   :@:" + this.getMagnitude();
		log += "\n";
		log += "uDirection:   :@:" + this.getDirection() + "    :#: " + this.getLocalDirection();
		log += "\n";
		log += "Matrix:\n" + this.GetLocalToWorldMatrix();
		log += "\n";
		log += "mPosition:   " + this.GetLocalToWorldMatrix().getTranslation(new Vector3());
		log += "\n";
		log += "mRotation:   " + this.GetLocalToWorldMatrix().getRotation(new Quaternion());
		log += "\n";
		log += "mScale:   " + this.GetLocalToWorldMatrix().getScale(new Vector3());
		log += "\n";
		return log;
	}

	public static void logAll() {
		for (gdxTransform t : gdxTransform.All) {
			String op = "";
			String pr = "";
			if (t.of != null)
				op = t.of.toString();

			if (t.hasParent())
				pr = t.GetParent().getClass().getSimpleName();
			Log(gdxTransform.All.indexOf(t) + ": " + t.toString() + " " + op + " | " + pr);
		}
	}





}
