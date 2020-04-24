package com.last2424.ogl.rendering;

import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

import com.last2424.game.entity.Entity;
import com.last2424.ogl.Window;

public class Camera {
	class Fade{

		public int Sign = 1;
		public boolean On = false;
		public Vector3f colors = new Vector3f(0,0,0);
		public float maxDuration = 1.0f;
		public float duration = 1.0f;
		float opt = 1;
		public void FadeIn(float seconds,Vector3f color) {
			colors = color;
			duration = seconds;
			maxDuration = seconds;
			On = true;
			Sign = 1;
			opt = 1.0f;
		}
		public void FadeOut(float seconds,Vector3f color) {
			colors = color;
			duration = seconds;
			maxDuration = seconds;
			On = true;
			Sign = -1;
			opt = -1.0f;
		}
		public Vector4f getColor() {
			return new Vector4f(colors.x,colors.y,colors.z,opt);
		}
		public void Update(float delta) {
			/*
			 * 
    if (this._fadeDuration > 0) {
        var d = this._fadeDuration;
        if (this._fadeSign > 0) {
            this._fadeSprite.opacity -= this._fadeSprite.opacity / d;
        } else {
            this._fadeSprite.opacity += (255 - this._fadeSprite.opacity) / d;
        }
        this._fadeDuration--;
    }
			 * */
			if(delta>=1) return;
			if(!On) return;
			if(duration > 0) {
				if(Sign>0) opt=(duration/maxDuration);
				else opt=1.0f-(duration/maxDuration);
				duration -=delta;
				if(duration<=0) {
						duration = 0;
						On = false;
						if(Sign==1) {
							opt = 0.0f;
						}else
							opt = 1.0f;
				}
			}
		}
		
	}
	Fade fade = new Fade();
	public Entity target;
	public float speed = 0.5f;
	public Vector2f position;
	public Vector2f size;
	public boolean smooth = false;
	public Camera(){
		this.position = new Vector2f(0, 0);
		this.size = new Vector2f(Window.width/2, Window.height/2);
	}
	Vector2f max = new Vector2f(0,0);
	public void SetMaxCamera(Vector2f max) {
		this.max = max;
	}
	public void FadeIn(float seconds) {
		FadeIn(seconds,new Vector3f(0,0,0));
	}
	public void FadeOut(float seconds) {
		FadeOut(seconds,new Vector3f(0,0,0));
	}
	public void FadeIn(float seconds,Vector3f color) {
		fade.FadeIn(seconds, color);
	}
	public void FadeOut(float seconds,Vector3f color) {
		fade.FadeOut(seconds, color);
	}
	public void update(float delta) {
		fade.Update(delta);
		this.size = new Vector2f(Window.width/2, Window.height/2);
		if (target != null) {
			Vector2f newPos = new Vector2f(target.getHalf().x -(this.size.x/2),target.getHalf().y-(this.size.y/2));
			if(newPos.x <=0) newPos.x = 0;
			if(newPos.y<=0) newPos.y = 0;
			if(max.x>=1 && newPos.x+this.size.x>=max.x) newPos.x = max.x-this.size.x;
			if(max.y>=1 && newPos.y+this.size.y>=max.y) newPos.y = max.y-this.size.y;
			Vector2f dir = new Vector2f(0,0);
			newPos.sub(this.position,dir);
			if(smooth){
				Vector2f direction = new Vector2f(dir.x,dir.y);
				direction = direction.normalize();
				if(dir.x<=0.01f) 
					this.position.x = newPos.x;
				else
					this.position.x+=direction.x*speed;
				if(dir.y<=0.01f) 
					this.position.y = newPos.y;
				else
					this.position.y+=direction.y*speed;
			}
			else this.position = newPos;
		}
	}
	
	public void render(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(position.x, size.x+position.x, size.y+position.y, position.y, -50, 50);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	public void postRender() {

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, size.x, size.y, 0, -50, 50);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		glDisable(GL11.GL_TEXTURE_2D);
		Vector4f cf = fade.getColor();
		GL11.glColor4f(cf.x,cf.y,cf.z,cf.w);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(size.x, 0);
		GL11.glVertex2f(size.x, size.y);
		GL11.glVertex2f(0, size.y);
		GL11.glEnd();
		glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1,1,1,1);
	}
	public void renderUI() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, size.x, size.y, 0, -50, 50);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}
}