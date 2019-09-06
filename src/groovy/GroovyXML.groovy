package groovy

import groovy.xml.MarkupBuilder

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import static java.io.File.separator
/**
 * Класс демонстрирует работу с XML в Groovy
 */
class GroovyXML {
  static void main(String[] args) {
    //вывод структуры xml-файла на консоль:
    def writer = new StringWriter()
    def xml = new MarkupBuilder(writer)
    xml.person(id:4) {
      name 'Gweneth'
      age 1
      xml.builde(id:3, number:'a') {
        type 'domestic house'
        xml.repairs() {
          capital '2011'
          current '2017'
        }
      }
    }

    println writer.toString()

    println("Разбор xml-разметки из строки, и запись данных в объект:")
    def xmlData = new XmlParser().parseText(writer.toString())

    Data1 data1 = new Data1(id:xmlData.@id,
                            name:xmlData.name.text(),
                            age:xmlData.age.text())

    println(data1.id + ", " + data1.name + ", " + data1.age)

    //запись данных в xml-файл
    Path file = Paths.get("." + separator + "output" + separator + "groovy.xml")
    Files.deleteIfExists(file)

    def writer2 = new FileWriter(file.toString())


    def xml2 = new MarkupBuilder(writer2)
    xml2.person(id:5) {
      name 'alex'
      sex 'man'
    }

    println("чтение xml-файла, и запись данных в объект:")
    def xmlData2 = new XmlSlurper().parse("." + separator + "output" +
                  separator + "groovy.xml")

    def idText = xmlData2.@id
    def nameText = xmlData2.name
    def sexText =  xmlData2.sex

    Data2 data2 = new Data2(id: idText, name: nameText,
                            sex: sexText)
    println((String)data2.id + ", " + (String)data2.name + ", " + (String)data2.sex)

  }
}

class Data1 {
  def id; def name; def age
}

class Data2 {
  def id; def name; def sex
}