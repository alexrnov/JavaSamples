package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Класс демонстрирует работу потокового SAX-анализатора.
 * В xml-файле производится поиск значений тегов name с атрибутом
 * maximum_size
 */
public class ReadSAX {
  public ReadSAX() {

  }
  public void readXML(File file) throws XMLError{

    DefaultHandler handler = new DefaultHandler() {

      //обработчик для открывающего тега
      public void startElement(String namespaceURI, String lname,
                               String qname, Attributes attrs) {
        if (lname.equals("name") && attrs != null) {
          for (int i = 0; i < attrs.getLength(); i++) {
            String aName = attrs.getLocalName(i);
            if (aName.equals("maximum_size")) {
              System.out.println("value of attribute \"maximum_size\" of " +
                      "tag \"name\" = " + attrs.getValue(i));
            }
          }
        }
      }

      //обработчик для замыкающего тега
      public void endElement(String namespaceURI, String lname,
                             String qname) {
      }
    };

    String url = "";
    try {
      url = file.toURL().toString();
      System.out.println("url = " + url);
    } catch(Exception e) {
      e.printStackTrace();
    }

    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setNamespaceAware(true);
    try {
      factory.setFeature(
              "http://apache.org/xml/features/nonvalidating/load-external-dtd",
              false);
      SAXParser saxParser = factory.newSAXParser();
      InputStream in = new URL(url).openStream();
      saxParser.parse(in, handler);
    } catch(ParserConfigurationException | SAXException | IOException e) {
      throw new XMLError(e.getMessage());
    }
  }
}
