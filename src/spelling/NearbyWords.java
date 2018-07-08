/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author andreea teodor
 */

public class NearbyWords implements SpellingSuggest
{
  // THRESHOLD - how many words to look through when looking for spelling suggestions 
  private static final int THRESHOLD = 1000;
  Dictionary dict;

  public NearbyWords(Dictionary dict)
  {
    this.dict = dict;
  }

  public List<String> distanceOne(String s, boolean wordsOnly)
  {
    List<String> retList = new ArrayList<String>();
    insertions(s, retList, wordsOnly);
    substitution(s, retList, wordsOnly);
    deletions(s, retList, wordsOnly);
    return retList;
  }

  public void substitution(String s, List<String> currentList, boolean wordsOnly)
  {
    // for each letter in the s and for all possible replacement characters
    for (int index = 0; index < s.length(); index++)
    {
      for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++)
      {
        StringBuffer sb = new StringBuffer(s);
        sb.setCharAt(index, (char) charCode);

        // if the item isn't in the list, isn't the original string, and
        // (if wordsOnly is true) is a real word, add to the list
        
        if (!currentList.contains(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))
            && !s.equals(sb.toString()))
        {
          currentList.add(sb.toString());
        }
      }
    }
  }

  public void insertions(String s, List<String> currentList, boolean wordsOnly)
  {
    for (int index = 0; index <= s.length(); index++)
    {
      for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++)
      {
        StringBuffer sb = new StringBuffer(s);
        sb.insert(index, (char) charCode);
        if (!currentList.contains(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))
            && !s.equals(sb.toString()))
        {
          currentList.add(sb.toString());
        }
      }
    }
  }

  public void deletions(String s, List<String> currentList, boolean wordsOnly)
  {
    for (int index = 0; index < s.length(); index++)
    {
      StringBuffer sb = new StringBuffer(s);
      sb.deleteCharAt(index);
      if (!currentList.contains(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))
          && !s.equals(sb.toString()))
      {
        currentList.add(sb.toString());
      }
    }
  }

  @Override
  public List<String> suggestions(String word, int numSuggestions)
  {

    // initial variables
    List<String> queue = new LinkedList<String>(); // String to explore
    HashSet<String> visited = new HashSet<String>(); // to avoid exploring the same
    // string multiple times
    List<String> retList = new LinkedList<String>(); // words to return

    // insert first node
    queue.add(word);
    visited.add(word);

    while(queue.size()>0 && retList.size()<=numSuggestions)
    {
       String curr = queue.remove(0);
       List<String> neighbours = distanceOne(curr, true);
       for(String n : neighbours)
       {
         if(!visited.contains(n))
         {
           visited.add(n);
           queue.add(n);
           if(dict.isWord(n))
           {
             retList.add(n);
           }
         }
       }
    }
    return retList;
  }
}
