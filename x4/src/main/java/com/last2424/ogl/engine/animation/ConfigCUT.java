package com.last2424.ogl.engine.animation;

import java.awt.Color;

import com.last2424.ogl.rendering.Texture;

public class ConfigCUT implements Config {
	public int FRAME_ROWS, FRAME_COLS;
	public float delay;
	public Texture texture;
	
	public ConfigCUT(Texture texture, int ROWS, int COLS, float delay) {
		this.texture = texture;
		this.FRAME_COLS = COLS;
		this.FRAME_ROWS = ROWS;
		this.delay = delay;
	}

	@Override
	public Frame[] GenerateFrames() {
		int w = texture.getWidth()/FRAME_ROWS, h = texture.getHeight()/FRAME_COLS;
		int g = 0;
		Frame[] frames = new Frame[FRAME_ROWS*FRAME_COLS];
		for(int i = 0; i < FRAME_COLS; i++) {
			for(int j = 0; j < FRAME_ROWS; j++) {
				frames[g] = new Frame(j*w, i*h, w, h);
				g++;
			}
		}
		return frames;
	}
}
