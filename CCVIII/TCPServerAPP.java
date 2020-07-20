package CCVIII;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class TCPServerAPP {
    public static SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    public static void main(String[] args) {
        System.out.println("TCP Server");
        //Using port 4870
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
                Thread.sleep(1000); // Added Sleeps to test time logging
                TCPClientAPP.outputFormat(message, true, true);
                Thread.sleep(1000); // Added Sleeps to test time logging
                String upperCase = message.toUpperCase();
                writer.println("Server: " + upperCase);
                TCPClientAPP.outputFormat(upperCase, false, true);

            }while(!message.equals("EXIT"));
            Calendar cal = Calendar.getInstance();
            System.out.printf( "[%s] Server Stop\n", sdf.format(cal.getTime()));
        } catch(Exception error) {
            System.out.println("IO Exception" + error.getMessage());
            error.printStackTrace();
        }
    }
}
