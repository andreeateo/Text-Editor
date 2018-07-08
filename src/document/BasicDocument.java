package document;

import java.util.List;

/**
 * implementation for the Document abstract class.
 * @author andreea teodor
 */
public class BasicDocument extends Document
{
  public BasicDocument(String text)
  {
    super(text);
  }

  /**
   * get the number of words in the document
   */
  @Override
  public int getNumWords()
  {
    List<String> tokens = getTokens("[a-zA-Z]+");
    return tokens.size();
  }

  /**
   * get the number of sentences in the document
   */
  @Override
  public int getNumSentences()
  {
    List<String> tokens = getTokens("[^.!?]+");
    return tokens.size();
  }

  /**
   * get the total number of syllables in the document (the stored text)
   */
  @Override
  public int getNumSyllables()
  {
    List<String> words = getTokens("[a-zA-Z]+");
    int count = 0;
    for (String word : words)
    {
      int sylNum = countSyllables(word);
      count += sylNum;
    }
    return count;
  }
}
