package com.last2424.game.gui;

import java.awt.Font;

import com.last2424.ogl.Start;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.TrueTypeFont;

public class GameInformationGUI {

	Font font;
	TrueTypeFont trueFont;
	
	public GameInformationGUI() {
		font = new Font("Times New Roman", Font.BOLD, 21);
		trueFont = new TrueTypeFont(font, true);
	}
	
	public void draw() {
		trueFont.drawString(10, 10, "FPS: " + Start.timer.getFPS(), 1, 1);
	}
}
