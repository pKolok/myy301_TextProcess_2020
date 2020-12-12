package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleStartWith extends AbstractRule {
	private String prefix = null;
	
	public RuleStartWith(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean isValid(LineBlock paragraph) {
		if (paragraph.startsWith(this.prefix))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Paragraphs which start with: " + this.prefix;
	}

}
