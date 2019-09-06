package xml;

import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

/**
 * Выводит на консоль структуру xml-файла
 */
public class ReadXMLWithStructure {
  private StringBuilder space = new StringBuilder(" ");
  public void readOfFile(File path) throws XMLError {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(true);//не влияет на поведение программы
    factory.setIgnoringElementContentWhitespace(true);//не влияет на поведение программы
    DocumentBuilder builder;
    Document doc;
    try {
      builder = factory.newDocumentBuilder();
      //не влияет не поведение программы
      builder.setErrorHandler(new ErrorHandler() {
        @Override
        public void warning(SAXParseException exception) throws SAXException {

        }

        @Override
        public void error(SAXParseException exception) throws SAXException {

        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {

        }
      });
      doc = builder.parse(path);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new XMLError(e.getMessage());
    }
    Element root = doc.getDocumentElement();
    System.out.println("<" + root.getTagName() + ">");
    readElements(root.getChildNodes());
    System.out.println("</" + root.getTagName() + ">");
  }

  private void readElements(NodeList children) {
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child instanceof Element) {
        Element childElement = (Element) child;
        System.out.print(space.toString() + "<" + childElement.getTagName());
        NamedNodeMap attributes = childElement.getAttributes();
        for (int j = 0; j < attributes.getLength(); j++) {
          Node attribute = attributes.item(j);
          System.out.print(" " + attribute.getNodeName()
                  + "= \"" + attribute.getNodeValue() + "\"");
        }
        System.out.println(">");
        Text textNode = (Text) childElement.getFirstChild();
        if (textNode != null) {
          String text = textNode.getData().trim();
          if (!text.equals("\n") && !text.equals("\r") &&
                  !text.equals("\t") && text.length() != 0) {
            System.out.println(space.toString() + text);
          }
        }
        if (childElement.getChildNodes().getLength() > 1) {
          space.append(" ");
          readElements(childElement.getChildNodes());
          space.deleteCharAt(space.length() - 1);
        }
        System.out.println(space.toString() + "</" + childElement.getTagName() + ">");
      }
    }

  }
}
