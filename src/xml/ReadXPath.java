package xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

/**
 * Поиск информации в xml-файле средствами XPath
 */
public class ReadXPath {
  public void readOfFile(File path) throws XMLError {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    Document doc;
    try {
      builder = factory.newDocumentBuilder();
      doc = builder.parse(path);
    } catch(ParserConfigurationException | SAXException | IOException e) {
      throw new XMLError(e.getMessage());
    }

    XPathFactory xpFactory = XPathFactory.newInstance();
    XPath xp = xpFactory.newXPath();
    try {
      String value = xp.evaluate("/resources/bool[2]", doc);
      System.out.println("Значение второго тега bool = " + value);
      System.out.println("-------------------------------------");
      String expression = "resources/bool";
      int count = ((Number) xp.evaluate("count(" + expression + ")",
              doc, XPathConstants.NUMBER)).intValue();
      if (count > 1) {
        NodeList nodes = (NodeList) xp.evaluate("/resources/bool", doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
          Node child = nodes.item(i);
          if (child instanceof Element) {
            Text text = (Text) child.getFirstChild();
            System.out.println(i + " тег bool: " + text.getData().trim());
          }
        }
      }
      System.out.println("------------------------------");
      expression = "resources/persone";
      count = ((Number) xp.evaluate("count(" + expression + ")",
              doc, XPathConstants.NUMBER)).intValue();
      if (count == 1) {
        String s = xp.evaluate(expression, doc);
        System.out.println("Значение тега persone = " + s);
        System.out.println("-----------------------------");
        Node node = (Node) xp.evaluate(expression, doc, XPathConstants.NODE);
        if (node instanceof Element) {
          Element element = (Element) node;
          System.out.println("название тега = " + element.getTagName());
          NodeList nodes = element.getChildNodes();
          for (int i = 0; i < nodes.getLength(); i++) {
            Node child = nodes.item(i);
            if (child instanceof Element) {
              Text text = (Text) child.getFirstChild();
              System.out.println("вложенные данные = " + text.getData().trim());
            }
          }
        }

      }
    } catch(XPathExpressionException e) {
      throw new XMLError(e.getMessage());
    }
  }
}
