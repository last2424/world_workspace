package com.last2424.game.gui;

import java.awt.Font;

import com.last2424.game.entities.EntityPlayer;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.ogl.rendering.TrueTypeFont;

public class PlayerInfoGUI {
	
	EntityPlayer player;
	Sprite background;
	Font font;
	TrueTypeFont trueFont;
	
	public PlayerInfoGUI(EntityPlayer player) {
		this.player = player;
		//background = new Sprite("statsBackground.png");
		//background.SetPosition(0, 0);
		font = new Font("Times New Roman", Font.BOLD, 21);
		trueFont = new TrueTypeFont(font, true);
	}
	
	public void draw(SpriteBatch batch) {
		trueFont.drawString(120, 120, "Name: " + this.player.GetName(), 1, 1);
	}
	
}
