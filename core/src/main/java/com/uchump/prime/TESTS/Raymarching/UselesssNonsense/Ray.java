package com.uchump.prime.TESTS.Raymarching.UselesssNonsense;

import com.badlogic.gdx.math.Intersector;
import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME.METATRON.Signals.Listener;
import com.uchump.prime._PRIME.METATRON.Signals.Signal;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class Ray<T extends aNumber> implements Listener{
	public Intersector a;
	
	/** The starting point of this ray. */
	public T start;

	/** The ending point of this ray. */
	public T end;

	/** Creates a {@code Ray} with the given {@code start} and {@code end} points.
	 * @param start the starting point of this ray
	 * @param end the starting point of this ray */
	public Ray (T start, T end) {
		this.start = start;
		this.end = end;
	}

	/** Sets this ray from the given ray.
	 * @param ray The ray
	 * @return this ray for chaining. */
	public Ray<T> set (Ray<T> ray) {
		this.start.set(ray.start);
		end.set(ray.end);
		return this;
	}

	/** Sets this Ray from the given start and end points.
	 * @param start the starting point of this ray
	 * @param end the starting point of this ray
	 * @return this ray for chaining. */
	public Ray<T> set (T start, T end) {
		this.start.set(start);
		this.end.set(end);
		return this;
	}

	@Override
	public iAgent getSource() {
		// TODO Auto-generated method stub
		return null;
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
