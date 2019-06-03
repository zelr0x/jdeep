package io.github.zelr0x.jdeep;

import java.io.*;

public class Copier {
    public static <T> T deepCopy(final DeepCopyable<? extends T> obj) {
        return obj.deepCopy();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(final T obj) {
        Object res = null;
        try (var byteArrayStream = new UnsyncByteOutputStream();
             var out = new ObjectOutputStream(byteArrayStream)) {
            out.writeObject(obj);
            res = readObject(byteArrayStream);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) res;
    }

    private static Object readObject(final UnsyncByteOutputStream out)
            throws IOException, ClassNotFoundException {
        try (var in = new ObjectInputStream(out.toInputStream())) {
            return in.readObject();
        }
    }

    /**
     * UnsyncByteOutputStream is basically a ByteArrayOutputStream without
     * synchronization and with bigger default buffer size.
     * UnsyncByteInputStream can be obtained via
     * <code>UnsyncByteOutputStream#toInputStream</code>.
     * <p>
     * These byte array output and input streams are meant to be passed to
     * constructors of ObjectOutputStream and ObjectInputStream respectively.
     * The data is read to a buffer which is then reused by an anonymous
     * InputStream - a non-synchronized version of ByteArrayInputStream.
     * <p>
     * All credit for the idea goes to Philip Isenhour at <a href="http://javatechniques.com/blog/faster-deep-copies-of-java-objects/" target="_top">javatechniques.com</a>
     */
    private static final class UnsyncByteOutputStream extends OutputStream {
        private static final int DEFAULT_BUFFER_SIZE = 8192;

        private byte[] buf;
        private int size;

        private UnsyncByteOutputStream() {
            this(DEFAULT_BUFFER_SIZE);
        }

        private UnsyncByteOutputStream(int initialCapacity) {
            buf = new byte[initialCapacity];
        }

        @Override
        public void write(final int b) {
            ensureCapacity(size + 1);
            buf[size++] = (byte) b;
        }

        @Override
        public void write(final byte[] b) {
            final var len = b.length;
            ensureCapacity(size + len);
            System.arraycopy(b, 0, buf, size, len);
            size += len;
        }

        @Override
        public void write(final byte[] b, final int off, final int len) {
            ensureCapacity(size + len);
            System.arraycopy(b, off, buf, size, len);
            size += len;
        }

        private void ensureCapacity(int size) {
            if (size > buf.length) {
                byte[] oldBuf = buf;
                buf = new byte[Math.max(size, 2 * buf.length)];
                System.arraycopy(oldBuf, 0, buf, 0, oldBuf.length);
            }
        }

        private InputStream toInputStream() {
            return new InputStream() {
                private int pos = 0;

                @Override
                public final int available() {
                    return size - pos;
                }

                /**
                 * Reads the next byte of data from the input stream.
                 * The value byte is returned as an <code>int</code>
                 * in the range <code>0</code> to <code>255</code>.
                 * @return the next byte or -1 if no byte is available
                 */
                @Override
                public final int read() {
                    return (pos < size) ? buf[pos++] : -1;
                }

                @Override
                public final int read(final byte[] b, final int off, int len) {
                    if (pos >= size) return -1;

                    if (pos + len > size) {
                        len = (size - pos);
                    }
                    System.arraycopy(buf, pos, b, off, len);
                    pos += len;
                    return len;
                }

                @Override
                public final long skip(long n) {
                    if (pos + n > size) {
                        n = size - pos;
                    }
                    if (n < 0) return 0;

                    pos += n;
                    return n;
                }
            };
        }
    }
}
