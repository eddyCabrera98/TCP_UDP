package CCVIII;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class TCPClientAPP {

    public static SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        try(Socket socket = new Socket("localhost", 4870)) {
            //Input and output Streams
            InputStream inputStream = socket.getInputStream();
            Scanner reader = new Scanner(new InputStreamReader(inputStream));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            //Sending data
            String input;
            do {
                System.out.print("Ingrese cadena: ");
                input = scanner.nextLine();
                System.out.println();
                Thread.sleep(1500);
                outputFormat(input, true);
                writer.println(input);
                Thread.sleep(1500);
                outputFormat(reader.nextLine(), false);
            }while( !input.equals("EXIT"));
            Calendar cal = Calendar.getInstance();
            System.out.printf( "[%s] Client Stop", sdf.format(cal.getTime()));
        }catch (Exception error) {
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }

    /**
     *
     * @param message, sent to server or received from server
     * @param client_server, true -> client, false -> server
     */
    public static void outputFormat(String message, boolean client_server) {
        Calendar cal = Calendar.getInstance();
        System.out.printf("%s %s %s [%s] \n",
                (client_server)? ">" : "<",
                (client_server)? "100.120.1.1": "127.100.2.9",
                (client_server)? "client": "server",
                sdf.format(cal.getTime()));
        System.out.printf("TCP: %s \n", message);
    }
}
