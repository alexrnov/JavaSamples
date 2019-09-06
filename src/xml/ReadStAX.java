package xml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Класс демонстрирует работу потокового Stax-анализатора. Вместо
 * обработчика событий (как в sax-анализаторе) используется простой
 * перебор событий.
 * Выводится структура xml-файла.
 */
public class ReadStAX {
  public void readXML(File file) throws XMLError {
    URL url = null;
    try {
      url = file.toURI().toURL();
      InputStream in = url.openStream();
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLStreamReader parser = factory.createXMLStreamReader(in);
      while (parser.hasNext()) {
        int event = parser.next();
        if (event == XMLStreamConstants.START_ELEMENT) {
          System.out.println("<" + parser.getLocalName() + ">");
          if (parser.getLocalName().equals("name")) {
            System.out.println("attr = " + parser.getAttributeValue(null, "type"));
          }
        } else if (event == XMLStreamConstants.END_ELEMENT) {
          System.out.println("</" + parser.getLocalName() + ">");
        } else if (event == XMLStreamConstants.CHARACTERS) {
          String p = parser.getText().trim();
          if (p.length() != 0 && !p.equals("\n") && !p.equals("\r")
                  && !p.equals("\t")) {
            System.out.println(p);
          }
        }
      }
    } catch(IOException | XMLStreamException e) {
      throw new XMLError(e.getMessage());
    }


  }
}
