package org.example.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 16001;

    public static void main(String[] args) throws IOException{
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Conectando ao servidor. Digite sua mensagem:");
            String input;
            while ((input = userInput.readLine()) != null){
                out.println(input);
                System.out.println("Resposta do servidor :" + in.readLine());
            }
        }
    }
}
