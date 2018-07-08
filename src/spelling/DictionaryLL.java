package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *@author andreea teodor
 */

public class DictionaryLL implements Dictionary
{

  private LinkedList<String> dict;

  public DictionaryLL()
  {
    dict = new LinkedList<String>();
  }

  public boolean addWord(String word)
  {
    if (!dict.contains(word.toLowerCase()))
    {
      dict.add(word.toLowerCase());
      return true;
    }else {
      return false;
    }
  }

  public int size()
  {
    return dict.size();
  }

  public boolean isWord(String s)
  {
    return dict.contains(s.toLowerCase());
  }

}
