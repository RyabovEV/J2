package HW6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer2 {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new EchoServer2().start();
    }

    private void start() {
        try {
            openConnection();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                sendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openConnection() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Ждем подключения клиента");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        final String message = in.readUTF();
                        if ("/end".equalsIgnoreCase(message)) {
                            out.writeUTF("/end");
                            break;
                        }
                        System.out.println("Сообщенеи от клиента: " + message);
                        //out.writeUTF(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        }
    }

    private void closeConnection() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
