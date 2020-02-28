package com.last2424.mmo.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new RequestDataEncoder(), new ResponseDataDecoder(), new ClientHandler());
	}

}
