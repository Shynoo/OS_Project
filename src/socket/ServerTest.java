package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dengshougang on 16/5/9.
 */
public class ServerTest implements Runnable {
    ServerSocket ss;
    public ServerTest(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            System.out.println("---服务器即将启动，等待客户端连接---");
            while (true) {
                // 2. 调用 accept()方法监听客户端请求
                Socket socket = ss.accept();
                // 3. 利用输入流读取客户端信息
                InputStream is = socket.getInputStream();//字节输入流
                InputStreamReader isr = new InputStreamReader(is);//将字节流转化为字符流
                BufferedReader br = new BufferedReader(isr);//为输入字符流创建缓冲
                String info = null;
                while ((info = br.readLine()) != null) {//循环读取客户端的信息
                    System.out.println("我是服务器，客户端说" + info);
                }
                socket.shutdownInput();// 关闭输入流
                // 4.创建输出流，响应客户端的请求
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);//包装为打印流(与输出字符流类似)
                pw.write("服务器端说，欢迎你！");
                pw.flush();//调用 flush()方法刷新缓存
                pw.close();
                // 5.关闭所有资源
//                socket.close();
//                ss.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        pw.close();
//        os.close();
//        br.close();
//        isr.close();
//        is.close();
    }

}
