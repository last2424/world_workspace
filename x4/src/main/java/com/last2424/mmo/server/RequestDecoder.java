package com.last2424.mmo.server;

import java.nio.charset.Charset;
import java.util.List;

import com.last2424.mmo.RequestData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class RequestDecoder extends ReplayingDecoder<RequestData> {

	private final Charset charset = Charset.forName("UTF-8");
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		RequestData data = new RequestData();
		data.setIntValue(in.readInt());
		int strLen = in.readInt();
		data.setStringValue(in.readCharSequence(strLen, charset).toString());
		out.add(data);
	}

}
