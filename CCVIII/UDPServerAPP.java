package CCVIII;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UDPServerAPP {
    public static SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(4870)) {
            byte[] exit = {69, 88, 73, 84};
            byte[] input_exit = new byte[4];
            String compare;
            String exit_str = new String(exit);
            do {
                byte[] buffer = new byte[256];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request); //Blocks until a datagram is received
                String input;
                input = new String(request.getData());
                TCPClientAPP.outputFormat(input, true, false);
                Thread.sleep(2000); // Adding an sleep to test loggin feature
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                String upperCase = input.toUpperCase();
                DatagramPacket response = new DatagramPacket(upperCase.getBytes(), upperCase.getBytes().length, clientAddress, clientPort);
                socket.send(response);
                TCPClientAPP.outputFormat(upperCase, false, false);
                byte[] array = input.getBytes();
                System.arraycopy(array, 0, input_exit,0, 4);
                compare = new String(input_exit);
            }while(!compare.equals(exit_str));
            Calendar cal = Calendar.getInstance();
            System.out.printf( "[%s] Server Stop\n", sdf.format(cal.getTime()));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
