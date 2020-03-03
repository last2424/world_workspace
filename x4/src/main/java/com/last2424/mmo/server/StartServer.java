package com.last2424.mmo.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.last2424.mmo.RequestData;
import com.last2424.mmo.ResponseData;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class StartServer {

	private int port;
	
	public StartServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
		
		new StartServer(port).run();
	}

	
	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	    try {
	    	ServerBootstrap b = new ServerBootstrap()
	    		.group(bossGroup, workerGroup)
	        	.channel(NioServerSocketChannel.class)
	        	.childHandler(new ServerInitializer());
	    	
	        ChannelFuture f = b.bind(port).sync();
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String line = in.readLine();
	        while ((line = in.readLine())!=null) {
	    		ResponseData msg = new ResponseData();
	    		msg.setIntValue(2);
	    		msg.setStringValue(line);
	            ServerInitializer.channels.writeAndFlush(msg);
	            System.out.println(ServerInitializer.channels.size());
	        }
	       
	        f.channel().closeFuture().sync();
	        
	    } finally {
	    	workerGroup.shutdownGracefully();
	        bossGroup.shutdownGracefully();
	    }
	}
	
}
