package com.last2424.ogl.engine.animation;


import com.last2424.ogl.rendering.Texture;


public class Animation {
	
	float delay, currentDelay;
	Frame[] frames;
	int currentPos = 0;
	public Texture texture;
	public boolean isDebug = false;
	
	public Animation(Config config) {
		if(config instanceof ConfigJSON) {
			Long d = (Long)((ConfigJSON) config).json.get("delay");
			delay = 1f/new Float(d);
			texture = ((ConfigJSON) config).texture;
		}
		currentDelay = delay;
		frames = config.GenerateFrames();
	}
	
	public void update(float delta) {
		if (isDebug) {
			System.out.println(frames.length);
			System.out.println(currentPos);
		}
		
		if(currentDelay <= 0) {
			currentPos++;
			currentDelay = delay;
		}
		
		if(currentPos >= frames.length) {
			currentPos = 0;
			currentDelay = delay;
		}
		currentDelay -= delta;
	}
	
	public Frame GetFrame() {
		return frames[currentPos];
	}
	
	public Frame GetFrame(int pos) {
		currentPos = pos;
		return frames[pos];
	}
	
	public enum AnimationType {
		JSON(true),
		CUT(false);
		
		private boolean type;
		
		AnimationType(boolean type){
			this.type = type;
		}
		
		public boolean getType() {
	        return this.type;
	    }
		
		public void setType(boolean type) {
			this.type = type;
		}
	}

	
}
