package com.itmuch.usercenter.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket(8079);
            System.out.println("服务器已启动并监听8080端口");
            // BIO会产生两次阻塞，第一次在等待连接时阻塞，第二次在等待数据时阻塞
            while (true) {
                System.out.println();
                System.out.println("服务器正在等待连接...");
                Socket socket = serverSocket.accept();
                System.out.println("服务器已接收到连接请求...");
                System.out.println();
                System.out.println("服务器正在等待数据...");
                socket.getInputStream().read(buffer);
                System.out.println("服务器已经接收到数据");
                System.out.println();
                String content = new String(buffer);
                System.out.println("接收到的数据:" + content);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
