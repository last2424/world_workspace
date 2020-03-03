package com.last2424.mmo;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.last2424.game.Animations;
import com.last2424.mmo.client.ClientChannelInitializer;
import com.last2424.mmo.client.ClientHandler;
import com.last2424.mmo.client.RequestDataEncoder;
import com.last2424.mmo.client.ResponseDataDecoder;
import com.last2424.ogl.engine.IMain;
import com.last2424.ogl.input.KeyboardHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client implements IMain {
	Channel channel;
	
	@Override
	public void create() throws IOException, InterruptedException {
		
	}

	@Override
	public void update(float delta) {
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_W)) {
	        RequestData msg = new RequestData();
	        msg.setIntValue(123);
	        msg.setStringValue("test");
			channel.writeAndFlush(msg);
		} 
	}

	@Override
	public void render() {

	}
	@Override
	public void dispose() {
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
