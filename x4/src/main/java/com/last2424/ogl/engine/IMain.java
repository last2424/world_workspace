package com.last2424.ogl.engine;

import java.io.IOException;

import io.netty.channel.Channel;

public interface IMain {

	public void create() throws IOException, InterruptedException;
	public void update(float delta);
	public void render();
	
	public void dispose();
	public void setChannel(Channel channel);
	
}
