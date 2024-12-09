package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Sebastian Manza
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The depth of the bit tree. */
  int size;

  /** The root of the tree */
  BitNode root;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new Bit Tree of size n.
   */
  public BitTree(int n) {
    this.size = n;
    this.root = new BitNode();
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+
  void dumpHelper(PrintWriter pen, BitNode node, StringBuilder path) {
    if (node == null) {
      return;
    } // if

    if (node.getVal() != null) {
      pen.printf("%s,%s\n", path.toString(), node.getVal());
    } // if

    /* Recursively search the children nodes */
    dumpHelper(pen, node.left, path.append('0'));
    dumpHelper(pen, node.right, path.append('1'));
  } //dumpHelper(PrintWriter, BitNode, StringBuilder)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Follow a path and replace the value at the end with value.
   * 
   * @param bits  The path to take represented by bits.
   * @param value The value to assign at the end.
   * @throws IndexOutOfBoundsException if the path is the improper length or not
   *                                   1's and 0's
   */
  public void set(String bits, String value) throws IndexOutOfBoundsException {
    if (bits.length() != this.size) {
      throw new IndexOutOfBoundsException("Improper number of bits.");
    } // if
    /* Assign a current node beginning at the root. */
    BitNode curNode = this.root;
    for (int i = 0; i < bits.length(); i++) {
      if (bits.charAt(i) == '0') {
        if (curNode.left == null) {
          curNode.left = new BitNode();
        } // if
        curNode = curNode.left;
      } else if (bits.charAt(i) == '1') {
        if (curNode.right == null) {
          curNode.right = new BitNode();
        } // if
        curNode = curNode.right;
      } else {
        throw new IndexOutOfBoundsException("Input is not 1's and 0's");
      } // if/else
    } // for
    curNode.setVal(value);
  } // set(String, String)

  /**
   * Get a value returned by a path.
   * 
   * @param bits The path to follow
   * @return a string representation of the value
   */
  public String get(String bits) {
    if (bits.length() != this.size) {
      throw new IndexOutOfBoundsException("Improper number of bits");
    } // if

    BitNode curNode = this.root;
    for (int i = 0; i < size; i++) {
      if (bits.charAt(i) == '0') {
        if (curNode.left == null) {
          throw new IndexOutOfBoundsException("No such path");
        } // if
        curNode = curNode.left;
      } else if (bits.charAt(i) == '1') {
        if (curNode.right == null) {
          throw new IndexOutOfBoundsException("No such path");
        } // if
        curNode = curNode.right;
      } else {
        throw new IndexOutOfBoundsException("Input is not 1's and 0's");
      } // if/else
    } // for
    String val = curNode.getVal();
    if (val == null) {
      throw new IndexOutOfBoundsException("No assigned value");
    } // if

    return val;
  } // get(String, String)

  /**
   * Prints the tree in csv format.
   * 
   * @param pen the printwriter object to print with.
   */
  public void dump(PrintWriter pen) {
    dumpHelper(pen, this.root, new StringBuilder());
  } // dump(PrintWriter)

  /**
   *
   */
  public void load(InputStream source) {
    BufferedReader eyes = new BufferedReader(new InputStreamReader(source));
    try {
      String line;
      while ((line = eyes.readLine()) != null) {

        /* Split the lines up to read them. */
        String[] strs = line.split(",");

        if (strs.length != 2) {
          throw new IndexOutOfBoundsException("Input improper length");
        } // if
        String path = strs[0].trim();
        String val = strs[1].trim();
        this.set(path, val);
      } // while

    } catch (Exception e) {
      throw new RuntimeException("Failed to read source.");
    } // try/catch
  } // load(InputStream)

} // class BitTree
