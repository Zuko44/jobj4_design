package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger ECHOSERVER = LoggerFactory.getLogger(EchoServer.class);

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                    String[] line = in.readLine().split("=");
                    String[] s = line[1].split(" ");
                    if ("Exit".equals(s[0])) {
                        server.close();
                    } else {
                        System.out.println(s[0]);
                    }
                    /**for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                     System.out.println(str);
                     }**/
                    out.flush();
                }
            }
        } catch (IOException e) {
            ECHOSERVER.error("Exception in log example", e);
        }
    }
}
