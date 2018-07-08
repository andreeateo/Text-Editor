package textgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author andreea teodor
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator
{

  // The list of words with their next words
  private List<ListNode> wordList;

  // The starting "word"
  private String starter;

  // The random number generator
  private Random rnGenerator;

  public MarkovTextGeneratorLoL(Random generator)
  {
    wordList = new LinkedList<ListNode>();
    starter = "";
    rnGenerator = generator;
  }

  /** Train the generator by adding the sourceText */
  @Override
  public void train(String sourceText)
  {
    if(wordList.size()>1)
      return;
    
    String[] allWords = sourceText.split(" +");
    if (allWords.length < 1)
      return;
    String[] words = Arrays.copyOfRange(allWords, 1, allWords.length);
    starter = allWords[0];
    String prevWord = starter;

    for (String w : words)
    {
      ListNode prevNode = new ListNode(prevWord);
      if (wordList.contains(prevNode)) // "prevWord" is already a node in the list
      {
        wordList.get(wordList.indexOf(prevNode)).addNextWord(w);
      }
      else // add "prevWord" as a node in the list
      {
        ListNode newNode = new ListNode(prevWord);
        wordList.add(newNode);
        newNode.addNextWord(w);
      }
      prevWord = w;
    }
    ListNode prevNode = new ListNode(prevWord);
    if (!wordList.contains(prevNode))
    {
      ListNode newNode = new ListNode(prevWord);
      wordList.add(newNode);
      newNode.addNextWord(starter);
    }
  }

  /**
   * Generate the number of words requested.
   */
  @Override
  public String generateText(int numWords)
  {
    if(wordList.isEmpty() || numWords == 0)
      return "";

    String currWord = starter;
    String output = "";
    output += currWord + " ";
    int count = 1;
    while (count < numWords)
    {
      ListNode a = wordList.get(wordList.indexOf(new ListNode(currWord)));
      String w = a.getRandomNextWord(rnGenerator);
      output += w + " ";
      currWord = w;
      count++;
    }
    return output;
  }

  @Override
  public String toString()
  {
    String toReturn = "";
    for (ListNode n : wordList)
    {
      toReturn += n.toString() + " ";
    }

    return toReturn;
  }

  /** Retrain the generator from scratch on the source text */
  @Override
  public void retrain(String sourceText)
  {
    wordList = new LinkedList<ListNode>();
    starter = "";
    
    String[] allWords = sourceText.split(" +");
    if (allWords.length < 1)
      return;
    String[] words = Arrays.copyOfRange(allWords, 1, allWords.length);
    starter = allWords[0];
    String prevWord = starter;

    for (String w : words)
    {
      ListNode prevNode = new ListNode(prevWord);
      if (wordList.contains(prevNode)) // "prevWord" is already a node in the list
      {
        wordList.get(wordList.indexOf(prevNode)).addNextWord(w);
      }
      else // add "prevWord" as a node in the list
      {
        ListNode newNode = new ListNode(prevWord);
        wordList.add(newNode);
        newNode.addNextWord(w);
      }
      prevWord = w;
    }
    ListNode prevNode = new ListNode(prevWord);
    if (!wordList.contains(prevNode))
    {
      ListNode newNode = new ListNode(prevWord);
      wordList.add(newNode);
      newNode.addNextWord(starter);
    }
  }


  /**
   * This is a minimal set of tests. Note that it can be difficult to test
   * methods/classes with randomized behavior.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    // feed the generator a fixed random value for repeatable behavior
    MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
    String textString1 = "hi there hi Leo";
    System.out.println(textString1);
    gen.train(textString1);
    System.out.println(gen);
    System.out.println(gen.generateText(4));
    String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
    System.out.println(textString);
    gen.retrain(textString);
    System.out.println(gen);
    System.out.println(gen.generateText(20));
    String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
        + "Oh no. You say goodbye and I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
        + "You say why, and I say I don't know. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, " + "Do you say goodbye. "
        + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
        + "You say stop and I say go, go, go. " + "Oh, oh no. " + "You say goodbye and I say hello, hello, hello. "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello, hello, hello,";
    System.out.println(textString2);
    gen.retrain(textString2);
    System.out.println(gen);
    System.out.println(gen.generateText(20));
  }

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode
{
  // The word that is linking to the next words
  private String word;

  // The next words that could follow it
  private List<String> nextWords;

  ListNode(String word)
  {
    this.word = word;
    nextWords = new LinkedList<String>();
  }

  public String getWord()
  {
    return word;
  }

  public void addNextWord(String nextWord)
  {
    nextWords.add(nextWord);
  }

  public String getRandomNextWord(Random generator)
  {
    int rNum = generator.nextInt(nextWords.size());
    return nextWords.get(rNum);
  }

  public String toString()
  {
    String toReturn = word + ": ";
    for (String s : nextWords)
    {
      toReturn += s + "->";
    }
    toReturn += "\n";
    return toReturn;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof ListNode)
    {
      ListNode otherNode = (ListNode) obj;
      return word.equals(otherNode.word);
    }
    return false;
  }

}
