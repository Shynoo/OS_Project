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
    public int leftRotate(int angle) throws IOException{
        return deReturn(client.writeAndRead("left,"+angle));
    }

    @Override
    public int rightRotate(int angle) throws IOException{
        return leftRotate(-angle);
    }

    @Override
    public int upRotate(int angle) throws IOException{
        return deReturn(client.writeAndRead("up,"+angle));
    }

    @Override
    public int downRotate(int angle) throws IOException{
        return upRotate(-angle);
    }

    @Override
    public int forwardRotate(int angle) throws IOException{
        return backRotate(angle);
    }

    @Override
    public int backRotate(int angle) throws IOException{
        return deReturn(client.writeAndRead("back,"+angle));

    }

    private int sendControl(String s) throws IOException{
        String[] ss=s.split(" ");
        if (ss.length!=2){
            return -5;
        }
        if (ss[0].equals("back")){
            return backRotate(Integer.valueOf(ss[1]));
        }
        if (ss[0].equals("forward")){
            return forwardRotate(Integer.valueOf(ss[1]));
        }
        if (ss[0].equals("left")){
            return leftRotate(Integer.valueOf(ss[1]));
        }
        if (ss[0].equals("right")){
            return rightRotate(Integer.valueOf(ss[1]));
        }
        if (ss[0].equals("down")){
            return downRotate(Integer.valueOf(ss[1]));
        }
        if (ss[0].equals("up")){
            return upRotate(Integer.valueOf(ss[1]));
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
