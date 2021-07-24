package com.yiotro.multiyoy.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.IOException;

public class NetWorker implements Runnable {
    public boolean running = true;
    public int data = 1;
    public Socket socket;

    SocketHints hints;

    public void run(){

        hints = new SocketHints();
        hints.connectTimeout = 11000;

        socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 9999, hints);

        boolean state = true;

        while(running && socket.isConnected())
        {
            try {
                if (socket.getInputStream().available() > 0) {
                    String response = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
                    if (response != null) {
                        Gdx.app.log("NetWorker", response);
                    }
                    if (state) {
                        socket.getOutputStream().write("Hi server".getBytes());
                        state = false;
                    }
                }

            } catch (IOException e) {
                Gdx.app.log("NetWorker", "an error occured", e);
                socket.dispose();
                running = false;
            }
        }
        socket.dispose();
        running = false;
        Gdx.app.log("NetWorker", "closed");
    }
}

// netWorker = new NetWorker();
//        new Thread(netWorker).start();
// NetWorker netWorker;