package ua.ithillel.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyNonBlockingServer implements AutoCloseable {
    private int port;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private boolean isOpen;

    public MyNonBlockingServer(int port) throws IOException {
        this.port = port;
        this.isOpen = true;
        this.serverSocketChannel = ServerSocketChannel.open();
        this.selector = Selector.open();
    }

    public void start() throws IOException {
        // bind port to process
        serverSocketChannel.bind(new InetSocketAddress(port));
        // make channel non-blocking
        serverSocketChannel.configureBlocking(false);
        // register selector for a channel
        serverSocketChannel.register(selector, serverSocketChannel.validOps());

        while (isOpen) {
            int select = selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                // new connection - accept
                if (key.isAcceptable()) {
                    SocketChannel socket = serverSocketChannel.accept();
                    socket.configureBlocking(false);
                    socket.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }
                // ready to read
                else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(200);

                    int read;
                    String request = "";
                    channel.read(buffer);
                    byte[] array = buffer.array();
                    System.out.println(new String(array));
                }
                // ready to write
                else if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();

                    String response = "HTTP/1.1 200 OK\r\n";
                    response += "Content-Type: text/html\r\n";
                    response += "\r\n";
                    response += "<p>Hello</p>\r\n";

                    byte[] bytes = response.getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(bytes);

                    channel.write(buffer);
                    channel.close();
                }

                iterator.remove();
            }


        }
    }

    @Override
    public void close() throws Exception {
        isOpen = false;
        if (serverSocketChannel != null) {
            serverSocketChannel.close();
        }

        if (selector != null) {
            selector.close();
        }
    }
}
