package gui;

import socket.ServerTest;

/**
 * Created by dengshougang on 16/5/9.
 */
public class MainGUI {
    public static void main(String[] args) {
        ServerTest st=new ServerTest(6699);
        System.out.println(1);
        Thread th=new Thread(st);
        th.run();
        System.out.println(2);
    }

}
