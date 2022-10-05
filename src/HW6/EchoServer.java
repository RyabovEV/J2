package HW6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Ждем подключения клиента");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true){
                String message = in.readUTF();
                if ("/end".equalsIgnoreCase(message)){
                    out.writeUTF("/end");
                    break;
                }
                System.out.println("Сообщенеи от клиента: " + message);
                //out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
