package datamodel.buildingblocks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Document {

	String curFilePath;
	DocumentRawType curType;
	String curAlias;
	List<LineBlock> curLineBlocks = new ArrayList<LineBlock>();
	
	public enum DocumentRawType {
		RAW, ANNOTATED
	}

	/**
	 * Constructs a new Document type to serve the file chosen
	 * 
	 * @param pFilePath: the String with the path of the input file
	 * @param docType: the String characterizing the document as raw (DocumentRawType.RAW) unless 
	 * "ANNOTATED" is given as input, in which case, the input file is characterized as annotated 
	 * (DocumentRawType.ANNOTATED)
	 * @param alias: a String with a short name, i.e., an alias for the file
	 */
	public Document(String pFilePath, DocumentRawType docType, String alias) {
		this.curFilePath = pFilePath;
		this.curType = docType;
		this.curAlias = alias;
	}

	/**
	 * Returns document's paragraphs in a List of Strings
	 * 
	 * Each paragraph is an element of the list. Blank paragraphs ('\n') are disregarded.
	 * Reads text line by line and concatenates lines into paragraphs until "\n" is found.
	 * Terminates reading when line is null.
	 * @return List of String with each paragraph of the text
	 */
	public List<LineBlock> getLineblocks() {
		String line; 
		String paragraph = "";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(curFilePath));
			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty()) {
					paragraph += line + "\n";
				// tackles case of 2 consecutive empty lines
				} else if (paragraph != "") {
					curLineBlocks.add(new LineBlock(paragraph));
					paragraph = "";				
				}
			}
			// Write last paragraph if it wasn't written
			if (paragraph != "") {
				curLineBlocks.add(new LineBlock(paragraph));
			}
			reader.close();
			return curLineBlocks;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getInputFileType() {
		return this.curType;
	}


}
