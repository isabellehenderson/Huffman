# Huffman Encoding

This project implements a Huffman coding system for text file compression and decompression. It provides a command-line interface for users to interact with the system, allowing them to create Huffman codes, compress files, and decompress files.

## Features

- **Create Huffman Codes**: Generate Huffman codes based on character frequencies in a text file.
- **Compress Files**: Compress text files using generated Huffman codes.
- **Decompress Files**: Decompress previously compressed files to recover the original text.
- **Round-Trip Compression**: Perform compression followed by decompression to verify the integrity of the process.
- **Debug Mode**: Option to output debug information during compression.

## Project Structure

The project consists of four main Java classes:

1. `HuffmanCode.java`: Implements the core Huffman coding algorithm, including tree construction and code generation.
2. `BitOutputStream.java`: Provides functionality to write individual bits to an output stream.
3. `BitInputStream.java`: Allows reading of individual bits from an input stream.
4. `HuffmanCompressor.java`: Serves as the main interface for the compression and decompression processes.

## Getting Started

To use the Huffman Coding system:

1. Compile all Java files in the project.
2. Run the `HuffmanCompressor` class.

```bash
javac *.java
java HuffmanCompressor
```

## Usage

Upon running the program, you will be prompted to:

1. Select a text file to work with.
2. Choose an operation:
   - Make a Huffman code
   - Compress a file
   - Decompress a file
   - Perform a round-trip compression and decompression

Follow the on-screen prompts to complete your desired operation.

## File Outputs

- `.code`: Contains the Huffman code for a given text file.
- `.short`: The compressed version of the input file.
- `.new`: The decompressed version of a compressed file (when not printing to console).
- `.debug`: (Optional) Debug output showing the binary representation of the compressed file.

## Implementation Details

- The `HuffmanCode` class uses a priority queue to build the Huffman tree efficiently.
- Bit-level I/O operations are handled by `BitOutputStream` and `BitInputStream` classes for compact file representation.
- The `HuffmanCompressor` class orchestrates the entire process, from reading input files to writing compressed or decompressed output.
