package spelling;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NearbyWordsTest
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
    String word = "i";
    Dictionary d = new DictionaryHashSet();
    DictionaryLoader.loadDictionary(d, "data/dict.txt");
    NearbyWords w = new NearbyWords(d);
    List<String> l = w.distanceOne(word, true);
    System.out.println("One away word Strings for for \"" + word + "\" are:");
    System.out.println(l + "\n");

    word = "tailo";
    List<String> suggest = w.suggestions(word, 10);
    System.out.println("Spelling Suggestions for \"" + word + "\" are:");
    System.out.println(suggest);
  }

}
