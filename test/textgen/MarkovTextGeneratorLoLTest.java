package textgen;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MarkovTextGeneratorLoLTest
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
    MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
    
    String textString1 = "hi there hi Leo";
    gen.train(textString1);
    
    assertEquals(5, gen.generateText(5).split(" +").length);
    assertTrue(gen.generateText(5).contains("hi"));
    
    String textString2 = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
    gen.retrain(textString2);
    
    assertEquals(20, gen.generateText(20).split(" +").length);
    assertTrue(gen.generateText(20).contains("Hello"));
    
    String textString3 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
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
    gen.retrain(textString3);
    
    assertEquals(84, gen.generateText(84).split(" +").length);
    assertTrue(gen.generateText(100).contains("goodbye"));
    assertTrue(gen.generateText(100).contains("no"));
  
  }

}
