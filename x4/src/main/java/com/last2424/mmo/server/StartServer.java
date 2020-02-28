package com.last2424.mmo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
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

	        b.bind(port).sync().channel().closeFuture().sync();
	    } finally {
	    	workerGroup.shutdownGracefully();
	        bossGroup.shutdownGracefully();
	    }
	}
	
}
