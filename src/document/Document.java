package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 * @author andreea teodor
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document
{
  private String text;

  protected Document(String text)
  {
    this.text = text;
  }
  
  protected List<String> getTokens(String pattern)
  {
    ArrayList<String> tokens = new ArrayList<String>();
    Pattern tokSplitter = Pattern.compile(pattern);
    Matcher m = tokSplitter.matcher(text);

    while (m.find())
    {
      tokens.add(m.group());
    }

    return tokens;
  }

  /**
   * This is a helper function that returns the number of syllables in a word.
   */
  protected int countSyllables(String word)
  {
    int numSyl = 0;
    boolean newSyl = true;
    char[] letters = word.toCharArray();
    String vowels = "aeiouy";
    
    for(int i = 0; i<letters.length; i++)
    {
      if(i == letters.length-1 && Character.toLowerCase(letters[i]) == 'e' && newSyl && numSyl > 0)//subtract count of last lone 'e'
      {
        numSyl--;
      }
      if(newSyl && vowels.indexOf(Character.toLowerCase(letters[i])) >= 0)//char is vowel, count a new syllable
      {
        newSyl = false;
        numSyl++;
      }else if (vowels.indexOf(Character.toLowerCase(letters[i])) < 0) //char is not a vowel, next vowel will be the next syllable
      { 
        newSyl = true;
      }
    
    }
    return numSyl;
  }

  /** Return the number of words in this document */
  public abstract int getNumWords();

  /** Return the number of sentences in this document */
  public abstract int getNumSentences();

  /** Return the number of syllables in this document */
  public abstract int getNumSyllables();

  /** Return the entire text of this document */
  public String getText()
  {
    return this.text;
  }

  /** return the Flesch readability score of this document */
  public double getFleschScore()
  {
    double fScore = 206.835 - (1.015*(((double)getNumWords())/(getNumSentences()))) - (84.6*(((double)getNumSyllables())/(getNumWords())));
    return fScore;
  }

}
