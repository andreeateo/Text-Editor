package document;

/**
 * @author andreea teodor
 */

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EfficientDocumentTest
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
  public void testGetNumWords()
  {
    EfficientDocument doc = new EfficientDocument("This is a test.");
    assertEquals(4, doc.getNumWords());
  }

  @Test
  public void testGetNumSentences()
  {
    EfficientDocument doc = new EfficientDocument("This is a test. Is it? Do. Do you know?how!");
    assertEquals(5, doc.getNumSentences());
  }

  @Test
  public void testGetNumSyllables()
  {
    EfficientDocument doc = new EfficientDocument("Alohamora");
    assertEquals(5, doc.getNumSyllables());
  }

  // Parameterised Tests
  private EfficientDocument doc;
  private int syl;
  private int words;
  private int sentences;

  @Parameters
  public static List<Object[]> data()
  {
    return Arrays.asList(new Object[][] { { new EfficientDocument(
        "This is a test.  How many???  " + "Senteeeeeeeeeences are here... there should be 5!  Right?"), 16, 13, 5 },
        { new EfficientDocument(""), 0, 0, 0 },
        { new EfficientDocument(
            "sentence, with, lots, of, commas.!  " + "(And some poaren)).  The output is: 7.5."), 15, 11, 4 },
        { new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2 },
        { new EfficientDocument("Here is a series of test sentences. Your program should "
            + "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
            + "the correct amount of syllables (example, for example), " + "but most of them will."), 49, 33, 3 },
        { new EfficientDocument("Segue"), 2, 1, 1 }, { new EfficientDocument("Sentence"), 2, 1, 1 },
        { new EfficientDocument("Sentences?!"), 3, 1, 1 },
        { new EfficientDocument(
            "Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."), 32, 15,
            1 } });
  }

  public EfficientDocumentTest(EfficientDocument doc, int syl, int words, int sentences)
  {
    this.doc = doc;
    this.syl = syl;
    this.words = words;
    this.sentences = sentences;
  }

  @Test
  public void test()
  {
    assertEquals(syl, doc.getNumSyllables());
    assertEquals(words, doc.getNumWords());
    assertEquals(sentences, doc.getNumSentences());
  }
}
