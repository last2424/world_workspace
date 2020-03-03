package com.last2424.mmo;

import java.io.IOException;

import com.last2424.mmo.client.ClientChannelInitializer;
import com.last2424.ogl.Window;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Start {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Client client = new Client();
		new Window().connecting(client);
	}

}
