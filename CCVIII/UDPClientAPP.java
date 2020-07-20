package CCVIII;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class UDPClientAPP {
    public static SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");
            String input;
            do {
                byte[] buffer = new byte[256];
                System.out.print("Ingrese cadena: ");
                input = scanner.nextLine();
                System.out.println();
                DatagramPacket request = new DatagramPacket(input.getBytes(), 0,input.length(), address, 4870);
                socket.send(request);
                TCPClientAPP.outputFormat(input, true, false);
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response); // Blocks until a datagram is received
                String upperCase = new String(buffer, 0, response.getLength());
                TCPClientAPP.outputFormat(upperCase, false, false);
            }while(!input.equals("EXIT"));
            Calendar cal = Calendar.getInstance();
            System.out.printf( "[%s] Client Stop\n", sdf.format(cal.getTime()));
        }catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
