package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datamodel.ruleset.RuleSet;
import engine.Engine;

public class UI {
	
	// TODO - UI
	public static void main(String[] args) throws IOException {
		
		String filePath = "C:/Users/Panousias/Dropbox/02 Education/01 Panepisthmio/06 CS - UoI/"
				+ "04 Ma8hmata/MYY301 -- Anapty3h Logismikou/Project/4914_TextProcessor/"
				+ "4914_TextProcessor/Resources/SampleDocs/hippocratesOath.html";
		String alias = "hippocratesOath";
		String pInputType = "ANNOTATED"; 		// or "RAW"
		List<List<String>> docInputSpec = new ArrayList<List<String>>();
		List<String> tmpList = null;
		RuleSet docRuleSet = null;
		List<String> docPrefixes = null;
		List<String> docReport = null;
		int noLineBlocks = -1;
		
		// RAW - give some temporary rules; one for each (OMIT, H1, H2, <B>, <I>):
//		tmpList = new ArrayList<String>(3);	
//		tmpList.add("OMIT"); tmpList.add("POSITIONS"); tmpList.add("0,3");
//		docInputSpec.add(tmpList);
//		tmpList = new ArrayList<String>(3);
//		tmpList.add("H1"); tmpList.add("STARTS_WITH"); tmpList.add("OATH AND");
//		docInputSpec.add(tmpList);		
//		tmpList = new ArrayList<String>(3);
//		tmpList.add("H2"); tmpList.add("POSITIONS"); tmpList.add("4");
//		docInputSpec.add(tmpList);
//		tmpList = new ArrayList<String>(3);
//		tmpList.add("<B>"); tmpList.add("ALL_CAPS");
//		docInputSpec.add(tmpList);				
//		tmpList = new ArrayList<String>(3);
//		tmpList.add("<I>"); tmpList.add("POSITIONS"); tmpList.add("2");
//		docInputSpec.add(tmpList);
		
		// ANNOTATED - rules for each one of (OMIT, H1, H2, <B>, <I>):
		tmpList = new ArrayList<String>(3);	
		tmpList.add("OMIT"); tmpList.add("STARTS_WITH"); tmpList.add("");
		docInputSpec.add(tmpList);
		tmpList = new ArrayList<String>(3);	
		tmpList.add("H1"); tmpList.add("STARTS_WITH"); tmpList.add("<H1>");
		docInputSpec.add(tmpList);		
		tmpList = new ArrayList<String>(3);	
		tmpList.add("H2"); tmpList.add("STARTS_WITH"); tmpList.add("<H2>");
		docInputSpec.add(tmpList);
		tmpList = new ArrayList<String>(3);	
		tmpList.add("<B>"); tmpList.add("STARTS_WITH"); tmpList.add("<b>");
		docInputSpec.add(tmpList);
		tmpList = new ArrayList<String>(3);	
		tmpList.add("<I>"); tmpList.add("STARTS_WITH"); tmpList.add("<i>");
		docInputSpec.add(tmpList);		
		
		// Step 1 - Create an Engine for current document
		Engine docEngine = new Engine(filePath, pInputType, alias);
		
		// Step 2 - Set-up Rule Set - TODO: Annotated Files
		switch (pInputType.strip().toUpperCase()) {
		case "RAW":			docRuleSet = docEngine.registerInputRuleSetForPlainFiles(docInputSpec);
							break;
		case "ANNOTATED":	docRuleSet = docEngine.registerInputRuleSetForAnnotatedFiles(docInputSpec,
								docPrefixes);
							break;
		default:			System.out.println("Wrong Input Type. Exiting.\n");
							System.exit(-100);
		}
		
		// Step 3 - Characterize LineBlock as per Rule Set
		noLineBlocks = docEngine.loadFileAndCharacterizeBlocks();
		System.out.println(noLineBlocks + " paragraphs loaded and characterised");
		
		// Step 4 - Print report statistics
		docReport = docEngine.reportWithStats();
		for (int i=0; i<docReport.size(); i++)
			System.out.print(docReport.get(i));
		
		// Step 5 - Export to Markdown
		
		// Step 6 - Export to Pdf
		
	}

}
