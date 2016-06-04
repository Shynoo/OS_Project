package test;

import controller.ClientController;

import java.io.IOException;

/**
 * Created by dengshougang on 16/6/4.
 */
public class ClientControllerTest{
    public static void main(String[] args){
        ClientController cc=new ClientController("127.0.0.1");
        try{
            System.out.println(cc.downRotate("12"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
