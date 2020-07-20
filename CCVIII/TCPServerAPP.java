package CCVIII;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class TCPServerAPP {
    public static SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(4870)) {
           // System.out.println(serverSocket.getLocalPort());
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
            Scanner reader = new Scanner(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String message;
            do {
                message = reader.nextLine();
                Thread.sleep(1500);
                TCPClientAPP.outputFormat(message, true);
                Thread.sleep(1500);
                String upperCase = message.toUpperCase();
                writer.println("Server: " + upperCase);
                TCPClientAPP.outputFormat(upperCase, false);
            }while(!message.equals("EXIT"));
            Calendar cal = Calendar.getInstance();
            System.out.printf( "[%s] Server Stop", sdf.format(cal.getTime()));
        } catch(Exception error) {
            System.out.println("IO Exception" + error.getMessage());
            error.printStackTrace();
        }
    }
}
