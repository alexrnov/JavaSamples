package international;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class Localise {
  public void method1() {
    System.out.println("Региональные настройки в системе по умолчанию: " + Locale.getDefault());
    Locale rus = new Locale("ru", "RU");
    NumberFormat nf = NumberFormat.getCurrencyInstance(rus);
    double d = 32332232.434;
    String s = nf.format(d);
    System.out.println("числовой формат: " + s);
    try {
      Number n = nf.parse("32 332 232,43 ₽");
      double i = n.doubleValue();
      System.out.println("строка в региональном формате - в число: " + i);
    } catch(ParseException e) {
      System.out.println("parse error: " + e.getMessage());
    }
    System.out.println("Существующие форматы чисел: ");
    Locale[] locales = NumberFormat.getAvailableLocales().clone();
    Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
    for(Locale loc: locales) { //региональные настройки для различных стран
      System.out.println(loc.getDisplayName());
      nf = NumberFormat.getCurrencyInstance(loc);
      System.out.println(nf.format(d));
    }
    System.out.println("-------------------------");
    String s2 = MessageFormat.format("в аудитории №{0} было {1} человек", 5, 10);
    System.out.println(s2);
  }
}
