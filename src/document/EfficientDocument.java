package document;

import java.util.List;

/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 * 
 * @author andreea teodor
 */
public class EfficientDocument extends Document {

	private int numWords;  // The number of words in the document
	private int numSentences;  // The number of sentences in the document
	private int numSyllables;  // The number of syllables in the document
	
	public EfficientDocument(String text)
	{
		super(text);
		processText();
	}
	
	
	/** 
	 * Take a string that either contains only alphabetic characters,
	 * or only sentence-ending punctuation.  
	 * Return true if the string contains only alphabetic characters
	 * Return false if it contains end of sentence punctuation.  
	 */
	private boolean isWord(String tok)
	{
	    // check whether a string is a word
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
	private void processText()
	{
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		for (String token : tokens)
		{
		  if (isWord(token))
		  {
		    numWords++;
		    int syl = countSyllables(token);
		    numSyllables += syl;
		  }else {
		    numSentences++;
		  }
		}
		if(tokens.size()>0 && isWord(tokens.get(tokens.size()-1)))
		{
		  numSentences++;
		}
	}

	@Override
	public int getNumSentences() {
		return numSentences;
	}

	@Override
	public int getNumWords() {
	    return numWords;
	}

	@Override
	public int getNumSyllables() {
        return numSyllables;
	}
}
