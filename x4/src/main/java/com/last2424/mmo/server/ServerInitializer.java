package com.last2424.mmo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

	public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new RequestDecoder(), new ResponseDataEncoder(), new ProcessingHandler());
		channels.add(ch);
	}


}
