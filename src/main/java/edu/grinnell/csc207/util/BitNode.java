package edu.grinnell.csc207.util;

/**
 * A node for a bit tree
 * 
 * @author Sebastian Manza
 */
public class BitNode {
    String val;
    BitNode left;
    BitNode right;

    /**
     * Create a new BitNode with values set to null.
     */
    public BitNode() {
        val = null;
        left = null;
        right = null;
    } //BitNode()

    /**
     * Create a new BitNode with an initialized value.
     * @param val The value to assign to the node.
     */
    public BitNode(String val) {
        this.val = val;
        left = null;
        right = null;
    } //BitNode(String)

    /**
     * Set/Reset a new value in a node.
     * @param val The value to assign to the node.
     */
    public void setVal(String val) {
        this.val = val;
    } //setVal(String)

    /**
     * Get a value from the node.
     * @return a string representation of the value
     */ 
    public String getVal() {
        return this.val;
    } //getVal

    
}
