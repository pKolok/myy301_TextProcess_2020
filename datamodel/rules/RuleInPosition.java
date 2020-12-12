package datamodel.rules;

import java.util.ArrayList;
import java.util.List;

import datamodel.buildingblocks.LineBlock;

public class RuleInPosition extends AbstractRule {
	private List<LineBlock> pLineblocks = new ArrayList<LineBlock>();
	private List<Integer> pPositions = new ArrayList<Integer>();
	
	public RuleInPosition(List<LineBlock> pLineblocks, List<Integer> pPositions) {
		this.pLineblocks = pLineblocks;
		this.pPositions = pPositions;
	}

	@Override
	public boolean isValid(LineBlock paragraph) {
		for (int pos = 0; pos < pPositions.size(); pos++)
			if(pLineblocks.get(pPositions.get(pos)).equals(paragraph))
				return true;
		return false;
	}

	@Override
	public String toString() {
		// TODO Check
		String msg = "Paragraphs in positions: ";
		for (int i=0; i<pPositions.size(); i++)
			msg += pPositions.get(i) + " ";
		return msg;
	}

}
