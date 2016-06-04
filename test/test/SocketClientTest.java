package test;

import socket.SocketClient;

import java.io.IOException;

/**
 * Created by dengshougang on 16/6/4.
 */
public class SocketClientTest{
    public static void main(String[] args) throws IOException{
        SocketClient client=new SocketClient("127.0.0.1");

        System.out.println(client.writeAndRead("212"));
    }
}
