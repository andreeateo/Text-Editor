package spelling;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A trie data structure that implements the Dictionary and the AutoComplete ADT
 * 
 * @author Andreea Teodor
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete
{

  private TrieNode root;
  private int size;

  public AutoCompleteDictionaryTrie()
  {
    root = new TrieNode();
  }

  public boolean addWord(String word)
  {
    char[] w = word.toLowerCase().toCharArray();

    TrieNode curr = root;
    for (int i = 0; i < w.length; i++)
    {
      if (curr.getChild(w[i]) != null)
      {
        curr = curr.getChild(w[i]);
      }
      else
      {
        curr = curr.insert(w[i]);
      }
      if (i == w.length - 1 && !curr.endsWord())
      {
        curr.setEndsWord(true);
        size++;
        return true;
      }
    }
    return false;
  }

  public int size()
  {
    return size;
  }

  /**
   * returns whether the string is a word in the trie
   */
  @Override
  public boolean isWord(String s)
  {
    char[] w = s.toLowerCase().toCharArray();
    TrieNode curr = root;
    for (char c : w)
    {
      if (curr.getChild(c) != null)
      {
        curr = curr.getChild(c);
      }
      else
      {
        return false;
      }
    }
    return curr.endsWord();
  }

  /**
   * return a list, in order of increasing (non-decreasing) word length,
   * containing the numCompletions shortest legal completions of the prefix
   * string. All legal completions must be valid words in the dictionary. If the
   * prefix itself is a valid word, it is included in the list of returned words.
   */
  @Override
  public List<String> predictCompletions(String prefix, int numCompletions)
  {
    char[] prefixArr = prefix.toLowerCase().toCharArray();
    TrieNode curr = root;
    for (char c : prefixArr)
      if (curr.getChild(c) != null)
      {
        curr = curr.getChild(c);
      }
      else
      {
        return new ArrayList<>();
      }
    LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    for (char c : curr.getValidNextCharacters())
    {
      queue.addLast(curr.getChild(c));
    }
    List<String> completions = new ArrayList<>();

    while (!queue.isEmpty() && completions.size() < numCompletions)
    {
      TrieNode firstNode = queue.removeFirst();
      if (firstNode != null)
      {
        if (firstNode.endsWord())
          completions.add(firstNode.getText());
        for (char a : firstNode.getValidNextCharacters())
        {
          queue.addLast(firstNode.getChild(a));
        }
      }

    }
    printTree();
    return completions;
  }

  public void printTree()
  {
    printNode(root);
  }

  /** a pre-order traversal print method from this node down */
  public void printNode(TrieNode curr)
  {
    if (curr == null)
      return;

    System.out.println(curr.getText());

    TrieNode next = null;
    for (Character c : curr.getValidNextCharacters())
    {
      next = curr.getChild(c);
      printNode(next);
    }
  }
}