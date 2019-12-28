package com.last2424.editor;

import java.io.IOException;

import com.last2424.ogl.Window;

public class Start {

	public static void main(String[] args) throws IOException {
		Editor editor = new Editor();
		new Window().run(editor);
	}
	
}
