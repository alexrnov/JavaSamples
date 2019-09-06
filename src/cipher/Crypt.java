package cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Path;
import java.security.*;

/**
 * Класс демонстрирует процедуру симметричного шифрования
 */
public class Crypt {
  private int mode = 0;
  private String keyFile = "";//секретный ключ шифрования

  /**
   * Генерация ключа шифрования по алгоритму AES
   * @param keyFile путь для файла шифрования
   */
  public void generateKey(Path keyFile) {
    try {
      this.keyFile = keyFile.toString();
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      SecureRandom random = new SecureRandom();
      keygen.init(random);
      SecretKey key = keygen.generateKey();
      try (ObjectOutputStream out = new ObjectOutputStream(
              new FileOutputStream(this.keyFile))){
        out.writeObject(key);
      }
    } catch(NoSuchAlgorithmException | IOException e) {
      e.printStackTrace();
    }
  }

  public void encrypt(Path fileEncrypt, Path fileDecrypt) {
    mode = Cipher.ENCRYPT_MODE;//режим шифрования - зашифровка
    cipher(fileEncrypt.toString(), fileDecrypt.toString());
  }

  public void decrypt(Path fileDecrypt, Path fileEncrypt) {
    mode = Cipher.DECRYPT_MODE;//режим шифрования - расшифровка
    cipher(fileDecrypt.toString(), fileEncrypt.toString());
  }

  private void cipher(String file1, String file2) {
    try {
      try (ObjectInputStream keyIn = new ObjectInputStream(
              new FileInputStream(keyFile))) {
        InputStream in = new FileInputStream(file1);
        OutputStream out = new FileOutputStream(file2);
        Key key = (Key) keyIn.readObject();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(mode, key);
        Util.crypt(in, out, cipher);
      }
    } catch(IOException | ClassNotFoundException | GeneralSecurityException e) {
      e.printStackTrace();
    }
  }
}
