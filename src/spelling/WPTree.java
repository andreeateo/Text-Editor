/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a
 * Breadth First Search of Nearby words to create a path between two words.
 * 
 * @author andreea teodor
 *
 */
public class WPTree implements WordPath
{

  // root node of the WPTree
  private WPTreeNode root;
  private NearbyWords nw;

  public WPTree()
  {
    this.root = null;
    Dictionary d = new DictionaryHashSet();
    DictionaryLoader.loadDictionary(d, "data/dict.txt");
    this.nw = new NearbyWords(d);
  }

  public WPTree(NearbyWords nw)
  {
    this.root = null;
    this.nw = nw;
  }

  public List<String> findPath(String word1, String word2)
  {
    List<WPTreeNode> queue = new LinkedList<WPTreeNode>();
    HashSet<String> visited = new HashSet<String>();
    root = new WPTreeNode(word1, null);
    visited.add(root.getWord());
    queue.add(root);

    while (queue.size() > 0 && !visited.contains(word2))
    {
      WPTreeNode curr = queue.remove(0);
      List<String> neighbours = this.nw.distanceOne(curr.getWord(), true);
      for (String n : neighbours)
      {
        if (!visited.contains(n))
        {
          curr.addChild(n);
          visited.add(n);
          WPTreeNode nodeN = new WPTreeNode(n, curr);
          queue.add(nodeN);          
          if (n.equals(word2))
          {
            return nodeN.buildPathToRoot();
          }
        }
      }
    }
    return new LinkedList<String>();
  }

  private String printQueue(List<WPTreeNode> list)
  {
    String ret = "[ ";

    for (WPTreeNode w : list)
    {
      ret += w.getWord() + ", ";
    }
    ret += "]";
    return ret;
  }
}

/*
 * Tree Node in a WordPath Tree. This is a standard tree with each node having
 * any number of possible children. Each node should only contain a word in the
 * dictionary and the relationship between nodes is that a child is one
 * character mutation (deletion, insertion, or substitution) away from its
 * parent
 */
class WPTreeNode
{

  private String word;
  private List<WPTreeNode> children;
  private WPTreeNode parent;

  public WPTreeNode(String w, WPTreeNode p)
  {
    this.word = w;
    this.parent = p;
    this.children = new LinkedList<WPTreeNode>();
  }

  public WPTreeNode addChild(String s)
  {
    WPTreeNode child = new WPTreeNode(s, this);
    this.children.add(child);
    return child;
  }

  public List<WPTreeNode> getChildren()
  {
    return this.children;
  }

  public List<String> buildPathToRoot()
  {
    WPTreeNode curr = this;
    List<String> path = new LinkedList<String>();
    while (curr != null)
    {
      path.add(0, curr.getWord());
      curr = curr.parent;
    }
    return path;
  }

  public String getWord()
  {
    return this.word;
  }

  public String toString()
  {
    String ret = "Word: " + word + ", parent = ";
    if (this.parent == null)
    {
      ret += "null.\n";
    }
    else
    {
      ret += this.parent.getWord() + "\n";
    }
    ret += "[ ";
    for (WPTreeNode curr : children)
    {
      ret += curr.getWord() + ", ";
    }
    ret += (" ]\n");
    return ret;
  }

}
