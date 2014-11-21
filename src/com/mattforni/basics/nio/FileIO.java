package com.mattforni.basics.nio;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* General notes on NIO
 * .flip() sets the buffer for reading by updating the 'limit' variable to be the
 * 'position' and setting 'position' to 0. 'limit' now indicates how many bytes
 * there are to read and 'position' indicates which bytes have already been read.
 *
 * When .hasRemaining() returns false the 'position' is equal to the limit which
 * indicates everything has been read out of the buffer. In this case, clear the
 * buffer for the next use, resetting all pointers.
 */

public class FileIO {
    private static final int BUFFER_CAPACITY = 1024;

    public static final void copy(final File input, final File output)
            throws FileNotFoundException, IOException {
        write(output, read(input));
    }

    public static final void copy(final String input, final String output)
            throws FileNotFoundException, IOException {
        write(output, read(input));
    }

    public static final String read(final File input)
            throws FileNotFoundException, IOException {
        final FileInputStream stream = new FileInputStream(input);
        final FileChannel channel = stream.getChannel();
        final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        final StringBuilder string = new StringBuilder();
        while (channel.read(buffer) > -1) {
            buffer.flip(); // Flip to prepare the buffer for reading.
            while (buffer.hasRemaining()) { string.append((char)buffer.get()); }
            buffer.clear(); // Clear the buffer to receive more data.
        }
        return string.toString();
    }

    public static final String read(final String input)
            throws FileNotFoundException, IOException {
        return read(new File(input));
    }

    public static final void write(final File output, final String contents)
            throws FileNotFoundException, IOException {
        final FileOutputStream stream = new FileOutputStream(output);
        final FileChannel channel = stream.getChannel();
        final byte[] bytes = contents.getBytes();
        // Allocate a buffer that is the size of the message.
        final ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip(); // Flip to prepare the buffer for writing.
        channel.write(buffer);
    }

    public static final void write(final String output, final String contents)
            throws FileNotFoundException, IOException {
        write(new File(output), contents);
    }
}
