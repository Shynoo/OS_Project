package controller;

import socket.SocketClient;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dengshougang on 16/5/9.
 */
public class ClientController implements DeviceRotater{
    final SocketClient client;
    public ClientController(String host){
        client=new SocketClient(host);
        service();
    }

    private int deReturn(String s){
        if (s.equals("finish")){
            return 0;
        }
        if (s.equals("ok")){
            return 0;
        }
        return -1;
    }

    @Override
    public int leftRotate(String angle) throws IOException{
        return deReturn(client.writeAndRead("l"+angle));
    }

    @Override
    public int rightRotate(String angle) throws IOException{
        return deReturn(client.writeAndRead("r"+angle));
    }

    @Override
    public int upRotate(String angle) throws IOException{
        return deReturn(client.writeAndRead("u"+angle));
    }

    @Override
    public int downRotate(String angle) throws IOException{
        return deReturn(client.writeAndRead("d"+angle));
    }

    @Override
    public int forwardRotate(String angle) throws IOException{
        return deReturn(client.writeAndRead("f"+angle));
    }

    @Override
    public int backRotate(String angle) throws IOException{
        return deReturn(client.writeAndRead("b"+angle));

    }

    private int sendControl(String s) throws IOException{
        String[] ss=s.split(" ");
        if (ss.length!=2){
            return -5;
        }
        int m=Integer.valueOf(ss[1]);
        String arc;
        if (m<=99){
            arc="0"+m;
        }
        else {
            arc=""+m;
        }

        if (ss[0].equals("back")){
            return backRotate(arc);
        }
        if (ss[0].equals("forward")){
            return forwardRotate(arc);
        }
        if (ss[0].equals("left")){
            return leftRotate(arc);
        }
        if (ss[0].equals("right")){
            return rightRotate(arc);
        }
        if (ss[0].equals("down")){
            return downRotate(arc);
        }
        if (ss[0].equals("up")){
            return upRotate(arc);
        }
        return -10;
    }
    public void service(){
        Scanner in=new Scanner(System.in);
        while (true){
            String s=in.nextLine();
            try{
                int i=sendControl(s);
                if (i!=0){
                    System.out.println("Something UnExpected Happened. The Error Code is "+i);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
