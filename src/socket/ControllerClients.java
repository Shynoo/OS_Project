package socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by dengshougang on 16/5/9.
 */
public class ControllerClients {
     ArrayList<Socket> socket=new ArrayList<>();
//    ArrayList<BufferedReader> br;
    public ControllerClients() {

    }
//    public void add
    public synchronized int addClient(String url,int port){
        try{
            Socket s=new Socket(url,port);
            assert s!=null;
            socket.add(s);
//            br.add(new BufferedReader(new InputStreamReader(socket.get(socket.size()-1).getInputStream())));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            return -1;
        }
        return 0;
    }
    public synchronized int removeClient(int n){
        socket.remove(n);
//        br.remove(n);
        return 0;
    }
    public Socket getClient(int n){
        return socket.get(n);
    }
    public String read(int n){
        try {
            InputStream is = socket.get(n).getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
//                System.out.println("我是客户端："+info);
//                System.out.println(info);
                return info;
            }
            // 4.关闭所有资源
            br.close();
            isr.close();
            is.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public synchronized void write(int n,byte[] bytes){
        try {
            OutputStream os = socket.get(n).getOutputStream();// 创建输出字节流
            OutputStreamWriter osw = new OutputStreamWriter(os);//转化为字符流
            BufferedWriter bw = new BufferedWriter(osw);//创建缓冲流
            bw.write(bytes.toString());
            bw.flush();
//            socket.get(0).shutdownOutput();// 关闭输出流
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public synchronized void write(int n,String s){
        try {
            OutputStream os = socket.get(n).getOutputStream();// 创建输出字节流
            OutputStreamWriter osw = new OutputStreamWriter(os);//转化为字符流
            BufferedWriter bw = new BufferedWriter(osw);//创建缓冲流
            bw.write(s);
            bw.flush();
            socket.get(n).shutdownOutput();// 关闭输出流

//            bw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ControllerClients cc=new ControllerClients();
        cc.addClient("localhost",6699);
//        cc.write(0,"jniin");
//        cc.write(0,"jniin");
        cc.write(0,"sdad");
        System.out.println(cc.read(0));
    }
}
