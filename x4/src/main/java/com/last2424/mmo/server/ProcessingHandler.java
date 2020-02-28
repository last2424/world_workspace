package com.last2424.mmo.server;

import com.last2424.mmo.RequestData;
import com.last2424.mmo.ResponseData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		RequestData requestData = (RequestData) msg;
		ResponseData responseData = new ResponseData();
		responseData.setIntValue(requestData.getIntValue()*2);
		ChannelFuture future = ctx.writeAndFlush(responseData);
		//future.addListener(ChannelFutureListener.CLOSE);
		System.out.println(requestData.getStringValue());
	}
		
}
