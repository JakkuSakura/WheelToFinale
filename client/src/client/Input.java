package client;

import io.netty.channel.Channel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input extends Thread
{
    private Channel channel;
    public void setChannel(Channel channel)
    {
        this.channel = channel;
    }
    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String str = in.readLine();
                channel.writeAndFlush( str + "\r\n");
            }
            catch (NullPointerException e)
            {
                System.err.println("没有连接到Server");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}