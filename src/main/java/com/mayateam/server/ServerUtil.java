package com.mayateam.server;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerUtil {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ServerUtil() {

    }


    @SneakyThrows
    public static String getLocalHost() {
        return InetAddress.getLocalHost().getHostAddress();
    }

    @SneakyThrows
    public static ServerSocket createServerSocket(int port) {
        return new ServerSocket(port);
    }

    @SneakyThrows
    public static Socket acceptClientSocket(ServerSocket serverSocket) {
        return serverSocket.accept();
    }

    @SneakyThrows
    public static String readMessageFromSocket(Socket socket) {
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine();
    }

    public static void printMessage(Socket socket, String message) {
        InetAddress clientAddress = socket.getInetAddress();
        System.out.print(LocalDateTime.now().format(TIME_FORMATTER) + " ");
        System.out.printf("[%s]", clientAddress.getHostAddress());
        System.out.println(" -- " + message);
    }
}
