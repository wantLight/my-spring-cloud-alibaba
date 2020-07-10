package com.itmuch.usercenter.BIO;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8079);
            String message = null;
            Scanner sc = new Scanner(System.in);
            message = sc.next();
            socket.getOutputStream().write(message.getBytes());
            socket.close();
            sc.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
