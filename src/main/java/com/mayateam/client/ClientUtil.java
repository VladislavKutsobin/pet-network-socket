package com.mayateam.client;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

public class ClientUtil {

    public ClientUtil() {

    }


    public static BufferedReader bufferedReader() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        return new BufferedReader(inputStreamReader);
    }

    @SneakyThrows
    public static  String readMessage(BufferedReader bufferedReader) {
        System.out.println("Enter message (q - quitt):");
        return bufferedReader.readLine();
    }

    @SneakyThrows
    public static Socket openSocket(String host, int port) {
        return new Socket(host, port);
    }

    @SneakyThrows
    public static void writeMessage(String message, Socket socket) {
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(message);
        bufferedWriter.flush();
    }
}
