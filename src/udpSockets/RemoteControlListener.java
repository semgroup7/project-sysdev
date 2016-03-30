package udpSockets;

import SmartCarInterface.TestClass;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class RemoteControlListener {
    private InputStream in;

    public static void main(String[] args) {
        try {
            System.out.println("Listening");
            RemoteControlListener rcl = new RemoteControlListener(1234);//change this port number
        } catch (Exception e) {
            System.out.println("Failed " + e.getMessage());
        }
    }

    public RemoteControlListener(int port) throws IOException {
        ServerSocket listener = new ServerSocket(port);
        TestClass sc = new TestClass();

        Socket socket = listener.accept();
        while (true) {
            try {
                in = socket.getInputStream();
                String buffer = "";

                while (in.available() > 0) {
                    buffer += in.read();
                    char first;
                    first = buffer.charAt(0);
                    //TODO
                    // Remember to change the method inside to make it work
                    if (first == 's') {
                        sc.setSpeed(Integer.parseInt(buffer.substring(1)));
                        System.out.println("testing!"+Integer.parseInt(buffer.substring(1)));
                    } else if (first == 'a') {
                        sc.setSpeed(Integer.parseInt(buffer.substring(1)));
                    } else if (first == 'r') {
                        sc.setSpeed(Integer.parseInt(buffer.substring(1)));
                    }
                }
            }catch(Exception e){}
        }

    }

//    public void listen(InputStream in) throws IOException {
//
//        TestClass sc = new TestClass();
//        String buffer = "";
//        while (in.available() > 0) {
//            buffer += in.read();
//            char first;
//            first = buffer.charAt(0);
//            if (first == 's') {
//                sc.setSpeed(Integer.valueOf(buffer.substring(1)));
//            } else if (first == 'a') {
//                sc.setSpeed(Integer.valueOf(buffer.substring(1)));
//            } else if (first == 'r') {
//                sc.setSpeed(Integer.valueOf(buffer.substring(1)));
//            }
//        }
//    }
}
