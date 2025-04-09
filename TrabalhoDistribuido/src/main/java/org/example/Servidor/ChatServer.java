package org.example.Servidor;

import javax.naming.ldap.SortKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private static final int PORT = 16001;
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    private static int clientCount = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor TCP na porta" + PORT);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                synchronized (ChatServer.class) {
                    clientCount++;
                }

                System.out.println("Novo Cliente conectado:" + clienteSocket.getRemoteSocketAddress());
                pool.execute(new ClientHandler(clienteSocket));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    public static synchronized int getClientCount() {
        return clientCount;
    }

    public static synchronized void decrementCLientCount() {
        clientCount--;
    }


}


