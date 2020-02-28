package com.last2424.mmo;

import java.io.IOException;

import com.last2424.ogl.Window;

public class Start {

	public static void main(String[] args) throws IOException, InterruptedException {
		Client client = new Client();
		new Window().connecting(client);
	}

}
