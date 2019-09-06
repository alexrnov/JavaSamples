package xml;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Класс демонстрирует запись xml-файла с помощью
 * потокового Stax-анализатора
 */
public class WriteStAX {
  public void readXML(File file) throws XMLError {
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLStreamWriter writer = null;
    try {
      //XMLStreamWriter
      writer = factory.createXMLStreamWriter(Files.newOutputStream(file.toPath()));
      writer.writeStartElement("persone");
      writer.writeCharacters("\n");
      writer.writeStartElement("name");
      writer.writeCharacters("alex");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeStartElement("id");
      writer.writeCharacters("4554");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeStartElement("pasport");
      writer.writeCharacters("\n");
      writer.writeStartElement("serial");
      writer.writeCharacters("3223");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeStartElement("number");
      writer.writeCharacters("4354");
      writer.writeEndElement();
      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeCharacters("\n");

      writer.writeEndDocument();
      //writer.writeEndDocument();
    } catch(IOException | XMLStreamException e) {
      throw new XMLError(e.getMessage());
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch(XMLStreamException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
