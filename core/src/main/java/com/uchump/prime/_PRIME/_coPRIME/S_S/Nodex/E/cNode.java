package com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E;

import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME.METATRON.Agents.iControlAgent;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;

public class cNode extends eNode<cNode> implements iControlAgent {

	// control node, component node
	// broadcast input to linked oNode agent

	@Override
	public iControlAgent getSource() {
		return this;
	}

	@Override
	public iAgent getAgent() {
		return this.K;
	}

}
