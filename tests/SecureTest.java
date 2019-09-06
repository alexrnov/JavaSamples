import cipher.Crypt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;
import static org.junit.Assert.assertEquals;

/**
 * Тест шифрования текстового файла, который предусматривает следующие этапы:
 * 1. Читается входной текстовый файл
 * 2. Для него создается секретный ключ шифрования
 * 3. Входной текстовый файл зашифровывается с помощью этого ключа
 * 4. Зашифрованный текстовый файл расшифровывается с помощью созданного ключа
 */
public class SecureTest {

  private Crypt crypt;

  @Before
  public void up() {
    crypt = new Crypt();
  }

  @After
  public void down() {
    crypt = null;
  }

  @Test
  public void encryptTest() throws Exception {
    Path keyFile = Paths.get("." + separator + "output" + separator + "key");
    Files.deleteIfExists(keyFile);
    crypt.generateKey(keyFile);//создать ключ шифрования

    Path fileForCrypt = Paths.get("." + separator + "input" + separator + "fileForCrypt.txt");
    Path cipherFile = Paths.get("." + separator + "output" + separator + "cipherFile.txt");
    Files.deleteIfExists(cipherFile);
    crypt.encrypt(fileForCrypt, cipherFile);//зашифровать файл

    Path decryptFile = Paths.get("." + separator + "output" + separator + "decryptFile.txt");
    Files.deleteIfExists(decryptFile);

    crypt.decrypt(cipherFile, decryptFile);//расшифровать файл
    //проверить равенство количества байт файла для шифрования и
    //расшифрованного файла
    assertEquals(fileForCrypt.toFile().length(), decryptFile.toFile().length());
  }
}
