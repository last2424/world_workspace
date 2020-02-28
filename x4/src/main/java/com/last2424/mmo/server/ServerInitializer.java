package com.last2424.mmo.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new RequestDecoder(), new ResponseDataEncoder(), new ProcessingHandler());
	}


}
