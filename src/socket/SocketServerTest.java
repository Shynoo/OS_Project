package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dengshougang on 16/4/17.
 */
public class SocketServerTest implements Runnable{
    static final int PORT_=5000;
    ServerSocket server;

    SocketServerTest() throws IOException{
        server=new ServerSocket(5000);
    }

    public static void main(String[] args){

    }

    @Override
    public void run(){
        try{
            System.out.println("监听在端口号:"+PORT_);
            while (true){
                //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
                Socket socket=server.accept();
                //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
                Reader reader=new InputStreamReader(socket.getInputStream());
                char chars[]=new char[64];
                int len;
                StringBuilder sb=new StringBuilder();
                String temp;
                int index;
                while ((len=reader.read(chars))!=-1){
                    temp=new String(chars, 0, len);
                    if ((index=temp.indexOf("eof"))!=-1){//遇到eof时就结束接收
                        sb.append(temp.substring(0, index));
                        break;
                    }
                    sb.append(temp);
                }
                System.out.println("from client: "+sb);
                //读完后写一句
                Writer writer=new OutputStreamWriter(socket.getOutputStream());
                writer.write("Hello Client.");
                writer.flush();
                writer.close();
                reader.close();
                socket.close();
            }

        } catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
//            ss.close();

        }
    }
}
