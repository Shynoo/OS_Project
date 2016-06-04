package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by dengshougang on 16/4/17.
 */
public class SocketClient{
    public static final int PORT_=5000;
    public Socket socket;
    Reader reader;
    Writer writer;
    public SocketClient(String address){
        try{
            socket = new Socket(address, PORT_);
            reader = new InputStreamReader(socket.getInputStream());
            writer = new OutputStreamWriter(socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public String read(){
        char chars[] = new char[8];
        int len;
        StringBuilder sb = new StringBuilder();
        try{
            while ((len=reader.read(chars)) != -1) {
                sb.append(new String(chars, 0, len));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
    public String write(String data,int timeout){
        try{
            writer.write(data);
            writer.flush();

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int write(String data){
        try{
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(data);
            writer.write("eof\n");
            writer.flush();
        }
        catch (Exception e){
            return -1;
        }
        return 0;
    }

    public String writeAndRead(String data) throws IOException{
        writer.write(data);
        writer.write("eof\n");
        writer.flush();
        //写完以后进行读操作
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String temp;
        int index;
        while ((temp=br.readLine()) != null) {
            if ((index = temp.indexOf("eof")) != -1) {
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }
        br.close();
        return sb.toString();
    }


}
