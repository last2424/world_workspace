package com.last2424.ogl.engine.animation;

import org.joml.Vector2f;


public class Animation {
	
	float delay, currentDelay;
	Frame[] frames;
	int currentPos;
	public Animation(Config config) {
		if(config instanceof ConfigCUT) {
			delay = ((ConfigCUT) config).delay;
		}
		
		if(config instanceof ConfigJSON) {
			String str = (String) ((ConfigJSON) config).json.get("delay");
			delay = 1f/new Float(str);
		}
		currentDelay = delay;
		frames = config.GenerateFrames();
	}
	
	public void update(float delta) {
		if(currentDelay <= 0) {
			currentPos++;
			currentDelay = delta;
		}
		
		if(currentPos >= frames.length) {
			currentPos = 0;
		}
		currentDelay -= delta;
	}
	
	public Frame GetFrame() {
		return frames[currentPos];
	}
	
	public enum AnimationType {
		JSON(true),
		CUT(false);
		
		private boolean type;
		
		AnimationType(boolean type){
			this.type = type;
		}
		
		public boolean getMultiplier() {
	        return this.type;
	    }
		
		public void setMultiplier(boolean type) {
			this.type = type;
		}
	}

	
}
