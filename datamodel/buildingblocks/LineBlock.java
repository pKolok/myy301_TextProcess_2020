package datamodel.buildingblocks;

import java.util.ArrayList;

public class LineBlock {
	String curLineBlock;
	StyleEnum pStyle = null;
	FormatEnum pFormat = null;
	int noWords = -1;
	ArrayList<String> lines = new ArrayList<String>();

	public LineBlock(String paragraph) {
		this.curLineBlock = paragraph;
		splitLinesWords();
	}

	// get method
	public String getLineBlock() {
		return curLineBlock;
	}
	
	public String getStatsAsString() {
		return "Lines: " + this.lines.size() + "\tWords: " + this.getNumWords();
	}

	public void setStyle(StyleEnum determineHeadingStatus) {
		this.pStyle = determineHeadingStatus;
	}

	// get method
	public StyleEnum getStyle() {
		return pStyle;
	}
	
	public void setFormat(FormatEnum determineFormatStatus) {
		this.pFormat = determineFormatStatus;
	}
	
	// TODO - Test this
	/**
	 * Method to determine if a paragraph (LineBlock) starts with a given prefix
	 * 
	 * @param prefix 
	 * @return true if it does, false otherwise
	 */
	public boolean startsWith(String prefix) {
		if (prefix.length() > curLineBlock.length())
			return false;
		if (curLineBlock.substring(0, prefix.length()).equals(prefix))
			return true;
		return false;
	}
	
	// TODO - Test this
	/**
	 * equals method for LinkBlock objects
	 * 
	 * compares current LineBlock with given LineBlock
	 * 
	 * @param tLineBlock is a different LineBlock Object
	 * @return true if equal, false otherwise
	 */
	public boolean equals(LineBlock tLineBlock) {
		if (curLineBlock.equals(tLineBlock.getLineBlock()))
			return true;
		return false;
	}
	
	// TODO - Test this
	/**
	 * Method to check if all letters of paragraph are Capital 
	 * 
	 * @return true is all letters are capital, false otherwise
	 */
	public boolean allCaps() {
		for (int i=0; i<curLineBlock.length(); i++)
			if(Character.isLowerCase(curLineBlock.charAt(i)))
				return false;
		return true;
	}

	/**
	 * Method to return the number of words in a paragraph
	 * 
	 * @return the number of words in the paragraph (integer) 
	 */
	public int getNumWords() {
		return this.noWords;
	}
	
	/**
	 * Method to return the collection of lines in a paragraph
	 * 
	 * @return the collection of line strings in a paragraph
	 */
	public ArrayList<String> getLines() {
		return this.lines;
	}
	
	// TODO - Test this
	/**
	 * Private method to split the paragraph into lines and words
	 * 
	 * It puts lines into ArrayList lines and counts the number of words
	 */
	private void splitLinesWords() {
		int count = 0; 
		String[] tmpLines = curLineBlock.split("\n");
		for (String line : tmpLines) {
			this.lines.add(line);
			String[] words = line.split(" ");
			for (int i = 0; i < words.length; i++)
				count ++;
		}
		this.noWords = count;
	}


	
}
