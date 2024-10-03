import java.util.*;
import java.io.*;

// This class represents a Huffman encoding and decoding system. It allows users to create
// Huffman codes based on character frequencies and input files, save and load the codes, and 
// translate compressed messages back to their original form.
public class HuffmanCode {
    
    // This class represents a node in the Huffman tree. Each node contains is characterized by
    // a character and its frequency in a message.
    private class HuffmanNode implements Comparable<HuffmanNode>{
        public final char character;
        public final int frequency;
        public HuffmanNode left;
        public HuffmanNode right;

        // Constructs a HuffmanNode with the given character and frequency.
        // Parameters:
        // - character: The character represented by this node
        // - frequency: The frequency of the character in the source data
        public HuffmanNode(char character, int frequency){
            this.character = character;
            this.frequency = frequency;
        }
        
        // Compares this HuffmanNode to another based on their frequency values.
        // Parameters:
        // - other: The HuffmanNode to compare to
        // Returns:
        // - int: 1 if this node has higher frequency than the other, -1 if lower, 0 if equal
        public int compareTo(HuffmanNode other){
            if(this.frequency > other.frequency){
                return 1;
            } else if(other.frequency > this.frequency){
                return -1;
            }
            return 0;
        }
    }

    private HuffmanNode root;

    // Constructs a HuffmanCode using an array of character frequencies. If there exists a 
    // character with a frequency <= 0,it will not be included in the HuffmanCode
    // Parameters:
    // - frequencies: An array representing the frequency of each character
    public HuffmanCode(int[] frequencies){
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (int i = 0; i < frequencies.length; i++){
            if (frequencies[i] > 0) {
                HuffmanNode node = new HuffmanNode((char) i, frequencies[i]);
                pq.add(node);
            }
        }

        while(pq.size() > 1){
            HuffmanNode left = pq.remove();
            HuffmanNode right = pq.remove();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        root = pq.remove();
    }

    // Constructs a HuffmanCode using an input file of ASCII values and codes.
    // Parameters:
    // - input: The Scanner object containing ASCII values and corresponding Huffman codes from
    // a file
    public HuffmanCode(Scanner input){
        root = null;

        while(input.hasNextLine()){
            int asciiValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            root = buildTree(root, asciiValue, code);
        }
    }

    // Saves the Huffman code to an output file in standard ASCII value and code format.
    // Parameters:
    // - output: The PrintStream to write the Huffman code to
    public void save(PrintStream output){
        save(output, root, "");
    }

    // Recursively saves the Huffman code to an output file in standard ASCII value and code 
    // format.
    // Parameters:
    // - output: The PrintStream to write the Huffman code to
    // - node: The current node in the Huffman tree
    // - code: The Huffman code for the current node
    private void save(PrintStream output, HuffmanNode node, String code){
        if(node.left == null && node.right == null){
            output.println((int) node.character);
            output.println(code);
        } else {
            save(output, node.left, code + "0");
            save(output, node.right, code + "1");
        }
    }

    // Translates a compressed message and writes the decompressed message to a file.
    // Parameters:
    // - input: The BitInputStream containing the compressed message
    // - output: The PrintStream to write the original message to
    public void translate(BitInputStream input, PrintStream output){
        HuffmanNode current = root;

        while(input.hasNextBit()){
            int bit = input.nextBit();

            if(bit == 0){
                current = current.left;
            } else {
                current = current.right;
            }

            if(current.left == null && current.right == null){
                output.write((int) current.character);
                current = root;
            }
        }
    }

    // Recursively builds a Huffman tree based on ASCII values and codes.
    // Parameters:
    // - node: The current node in the Huffman tree
    // - asciiValue: The ASCII value of the character
    // - code: The Huffman code for the character
    // Returns:
    // - HuffmanNode: The updated HuffmanNode after building the tree
    private HuffmanNode buildTree(HuffmanNode node, int asciiValue, String code){
        if (node == null) {
            node = new HuffmanNode('\0', 0); 
        }

        if (code.length() == 0) {
            return new HuffmanNode((char) asciiValue, -1);
        } else {
            char direction = code.charAt(0);
            if (direction == '0') {
                node.left = buildTree(node.left, asciiValue, code.substring(1));
            } else if (direction == '1') {
                node.right = buildTree(node.right, asciiValue, code.substring(1));
            }
        }

        return node;
    }
}