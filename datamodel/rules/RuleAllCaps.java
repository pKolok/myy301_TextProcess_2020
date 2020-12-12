package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleAllCaps extends AbstractRule {

	
	@Override
	public boolean isValid(LineBlock paragraph) {
		return paragraph.allCaps();
	}
	
	@Override
	public String toString() {
		return "Paragraphs with all letters upper cases";
	}


}
