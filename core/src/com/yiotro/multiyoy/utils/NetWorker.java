package com.yiotro.multiyoy.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class NetWorker implements Runnable {
    public boolean running = true;
    public float[] myCarPos = {0f,0f,0f};
    public float[] otherCarPos = {0f,0f,0f};
    public Socket socket;

    SocketHints hints;

    private void auth() {
        try {
            if (socket.getInputStream().available() > 0) {
                String response = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();

                if (!response.equals("multiyoy-server v0.1a")) {
                    running = false;
                }
            }

        } catch (IOException e) {
            Gdx.app.log("NetWorker", "an error occured", e);
            running = false;
        }
    }

    public static byte[] floatToByte(float[] input) {
        StringBuilder ret = new StringBuilder();
        for (float v : input) {
            ret.append(v);
            ret.append(";");
        }
        ret.setLength(ret.length() - 1);
        ret.append("\n");
        System.out.println("pushed:"+ ret.toString());
//        System.out.println("pushed:"+ Arrays.toString(ret.toString().getBytes()));
        return ret.toString().getBytes();
    }

    public static float[] byteToFloat(byte[] input) {
        String ret = new String(input);
//        System.out.println("got: "+ret);
        String[] parts = ret.split(";");

        return new float[] {Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2])};
    }

    public void run(){

        hints = new SocketHints();
        hints.connectTimeout = 11000;

        socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "192.168.0.102", 9999, hints);

        Gdx.app.log("NetWorker", "connected");
        auth();
        if (running) Gdx.app.log("NetWorker", "authed");

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("HERE");
            socket.getOutputStream().write(floatToByte(myCarPos));

        } catch (IOException e) {
            Gdx.app.log("NetWorker", "an error occured", e);
            running = false;
        }

        while(running && socket.isConnected())
        {
            try {
                if (socket.getInputStream().available() > 0) {
                    byte[] response = new byte[1024];
                    int l = socket.getInputStream().read(response);
                    while (l==-1) {
                        response = new byte[1044];
                        l = socket.getInputStream().read(response);
                    }
//                    System.out.println(Arrays.toString(floatToByte(myCarPos)));
//                    System.out.println(Arrays.toString(response));
//                    System.out.println();
                    otherCarPos = byteToFloat(response);

                    try {
                        socket.getOutputStream().write(floatToByte(myCarPos));

                    } catch (IOException e) {
                        Gdx.app.log("NetWorker", "an error occured", e);
                        running = false;
                    }


                }

            } catch (IOException | NumberFormatException e) {
                Gdx.app.log("NetWorker", "an error occured", e);
                running = false;
            }
        }
        socket.dispose();
        running = false;
        Gdx.app.log("NetWorker", "closed");
    }
}
