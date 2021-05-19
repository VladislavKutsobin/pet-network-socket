package com.mayateam.client;

import com.mayateam.server.MessageBoardServer;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.net.Socket;

import static com.mayateam.client.ClientUtil.*;


public class MessageBoardClient {
    private static final String SERVER_ADDRESS = MessageBoardServer.HOST;
    private static final int SERVER_PORT = MessageBoardServer.PORT;


    @SneakyThrows
    public static void main(String[] args) {
        try (BufferedReader reader = bufferedReader()) {
            String message = readMessage(reader);

            while (!message.equals("q")) {
                try (Socket socket = openSocket(SERVER_ADDRESS, SERVER_PORT)) {
                    writeMessage(message, socket);
                }
                message = readMessage(reader);
            }
        }
    }
}
