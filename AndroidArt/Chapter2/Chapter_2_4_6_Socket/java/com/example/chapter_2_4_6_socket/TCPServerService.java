package com.example.chapter_2_4_6_socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by Guure on 2017/5/15.
 */

public class TCPServerService extends Service {

    private boolean isServiceDestroyed = false;
    private String[] definedMessages = new String[] {
            "Cleveland, this is for you!",
            "Soft, try me.",
            "This is my house!",
            "I decided to bring my talent to Miami.",
            "Merry fucking Christmas."
    };

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        isServiceDestroyed = true;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TcpServer implements Runnable {
        @SuppressWarnings("resource")
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                // 监听本地8688端口
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.err.println("establish tcp server failed, port: 8688");
                e.printStackTrace();
                return;
            }

            while (!isServiceDestroyed) {
                try {
                    // 接收客户端请求
                    final Socket client = serverSocket.accept();
                    System.out.print("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // 用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室！");
        while (!isServiceDestroyed) {
            String str = in.readLine();
            System.out.println("msg from client: " + str);
            if (str == null) {
                // 客户端断开连接
                break;
            }
            int i = new Random().nextInt(definedMessages.length);
            String msg = definedMessages[i];
            // 由于客户端是一行行读取，因此这里必须一行行输出
            out.println(msg);
            System.out.println("send: " + msg);
        }
        System.out.println("client quit.");
        // 关闭流
        out.close();
        in.close();
        client.close();
    }

}
