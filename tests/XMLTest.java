import org.junit.Before;
import org.junit.Test;
import xml.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

public class XMLTest {

  private ReadXMLWithStructure readXML;
  private ReadXPath readXPath;
  private ReadSAX readSax;
  private ReadStAX readStax;
  private WriteStAX writeStax;

  @Before
  public void up() {
    readXML = new ReadXMLWithStructure();
    readXPath = new ReadXPath();
    readSax = new ReadSAX();
    readStax = new ReadStAX();
    writeStax = new WriteStAX();
  }

  @Before
  public void down() {
    readXML = null;
    readXPath = null;
    readSax = null;
    readStax = null;
    writeStax = null;
  }

  @Test
  public void readXML() {
    String filePath = "." + separator + "input" + separator + "config.xml";
    //String filePath = "." + separator + "input" + separator + "popup.xml";
    Path p = Paths.get(filePath);
    try {
      readXML.readOfFile(p.toFile());
    } catch (XMLError e) {
      e.printStackTrace();
    }
  }

  @Test
  public void xPath() {
    String filePath = "." + separator + "input" + separator + "config.xml";
    Path p = Paths.get(filePath);
    try {
      readXPath.readOfFile(p.toFile());
    } catch(XMLError e) {
      e.printStackTrace();
    }
  }

  @Test
  public void saxTest() {
    String filePath = "." + separator + "input" + separator + "config.xml";
    Path p = Paths.get(filePath);
    try {
      readSax.readXML(p.toFile());
    } catch (XMLError e) {
      e.printStackTrace();
    }
  }

  @Test
  public void staxTest( ) {
    String filePath = "." + separator + "input" + separator + "config.xml";
    Path p = Paths.get(filePath);
    try {
      readStax.readXML(p.toFile());
    } catch (XMLError e) {
      e.printStackTrace();
    }
  }

  @Test
  public void writeStaxTest() {
    String filePath = "." + separator + "output" + separator + "stax.xml";
    Path p = Paths.get(filePath);
    try {
      writeStax.readXML(p.toFile());
    } catch(XMLError e) {
      e.printStackTrace();
    }
  }
}
