package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME._coPRIME.N_M._N;

public interface iNode<N extends iNode, O extends Object, V> extends iMonad, _N, Cloneable {

	public iSpace getGraph();

	public boolean getExpanded();

	public void setExpanded(boolean is);

	public default void toggleExpanded() {
		this.setExpanded(!this.getExpanded());
	}

	public default void expand() {
		if (!this.getExpanded()) {
			this.toggleExpanded();
		}
	}

	public default void contract() {
		if (this.getExpanded()) {
			this.toggleExpanded();
		}
	}

	public default boolean isConnected(iNode other) {
		return false;
	}

	public default iNode connectTo(N other) {
		return this;
	}

	public default iNode connectTo(N other, CharSequence how) {
		return this.connectTo(other);
	}


	public default iNode connectTo(N other, Object with) {
		return this.connectTo(other);
	}

	public default void disconnectFrom(iNode other) {
		other.disconnect(this);
	}

	public default void disconnect(iNode other) {
		other.disconnectFrom(this);
	}

	public default void disconnect() {
		this.setExpanded(false);
		this.exists(false);
	}

	public default float getRootDist() {
		// distance from 'root'
		if (this.getGraph() == null)
			return -1;
		return this.getTransform().GetDepth();
	}

	public default int getDepth() {
		// z-depth
		if (this.getTransform() == null)
			return -1;
		return this.getTransform().GetDepth();
	}

	public default Map<Object, iNode> getLinkedNodes()
	{
		return new HashMap<Object,iNode>();
	}
	
	@Override
	public default String toLog() {
		String log = "<" + this.getName() + ">";
		log += "([" + this.getKey() + "]<" + this.getKey().getClass().getSimpleName() + ">,";
		log += "[" + this.getValue() + "]<" + this.getValue().getClass().getSimpleName() + ">)" + this.getRootDist();

		return log;
	}
}