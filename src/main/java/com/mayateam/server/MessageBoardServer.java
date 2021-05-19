package com.mayateam.server;

import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;

import static com.mayateam.server.ServerUtil.*;


public class MessageBoardServer {
    public static final String HOST = ServerUtil.getLocalHost();
    public static final int PORT = 8899;

    @SneakyThrows
    public static void main(String[] args)  {
        try (ServerSocket serverSocket = createServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = acceptClientSocket(serverSocket)) {
                    String message = readMessageFromSocket(clientSocket);
                    printMessage(clientSocket, message);
                }
            }
        }
    }
}
