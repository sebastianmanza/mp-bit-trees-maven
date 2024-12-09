package edu.grinnell.csc207.main;
import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;
/**
 * The main class that converts braille/ascii/unicode.
 *
 * @author Sebastian Manza
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * The main function.
   *
   * @param args the args to take in
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String type = args[0];
    switch (type) {
      case "braille":
        for (int i = 0; i < args[1].length(); i++) {
          pen.print(BrailleAsciiTables.toBraille(args[1].charAt(i)));
        } // for
        break;
      case "ascii":
        if (args[1].length() % 6 != 0) {
          System.err.println("Not valid length.");
          return;
        } // if
        for (int i = 0; i < args[1].length(); i += 6) {
          pen.print(BrailleAsciiTables.toAscii(args[1].substring(i, i + 6)));
        } // for
        break;
      case "unicode":
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < args[1].length(); i++) {
          str.append(BrailleAsciiTables.toBraille(args[1].charAt(i)));
        } // for
        for (int i = 0; i < str.length(); i += 6) {
          String unicode = BrailleAsciiTables.toUnicode(str.substring(i, i + 6));
          char uni = (char) Integer.parseInt(unicode, 16);
          pen.print(uni);
        } // for
        break;
      default:
        System.err.println("Incorrect input.");
    } // main
    pen.close();
  } // main(String[]
} // class BrailleASCII
