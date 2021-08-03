package com.uchump.prime._PRIME.METATRON.Agents;

import com.uchump.prime._PRIME.METATRON.Signals.Signal;

public interface iControlAgent extends iAgent{

	public default iAgent getAgent() {
		return this;
	}
	public default iAgent[] getAgents()
	{
		return new iAgent[] {this.getAgent()};
	}
	

}
