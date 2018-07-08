package spelling;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WPTreeTest
{

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void test()
  {
    String word1 = "this";
  String word2 = "there";
  Dictionary d = new DictionaryHashSet();
  DictionaryLoader.loadDictionary(d, "data/dict.txt");
  NearbyWords nw = new NearbyWords(d);
  WPTree tree = new WPTree(nw);
  List<String> path = tree.findPath(word1, word2);
  System.out.println("the path from " + word1 + " to "+ word2 + " is: ");
  System.out.println(path + "\n");
  }

}
