package com.last2424.ogl.rendering;

import org.joml.Vector2f;

import com.last2424.ogl.Start;

import static org.lwjgl.opengl.GL11.*;

public class SpriteBatch {
	
	public void clearWindow() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void setWindowColor(float red, float green, float blue, float alpha) {
		glClearColor(red, green, blue, alpha);
	}
	
	public void setColor(float red, float green, float blue, float alpha) {
		glColor4f(red, green, blue, alpha);
	}
	
	public void draw(Texture texture, int x, int y, int layer) {
		draw(texture, x, y, texture.getWidth(), texture.getHeight(), layer);
	}
	
	public void draw(Texture texture, int x, int y, int width, int height, int layer) {
		draw(texture, x, y, width, height, 1.0f, 1.0f, 1.0f, 1.0f, layer);
	}
	
	public void draw(Texture texture, int x, int y, int width, int height, float red, float green, float blue, float alpha, int layer) {
		draw(new TextureRegion(texture, new Vector2f(0, 0), new Vector2f(texture.getWidth(), texture.getHeight())), x, y, width, height, red, green, blue, alpha, layer);
	}
	
	public void draw(TextureRegion texture, int x, int y, int width, int height, float red, float green, float blue, float alpha, int layer) {
		float widthTexture = texture.GetTexture().getWidth(), heightTexture = texture.GetTexture().getHeight();
		Vector2f regionPos = texture.regionPos, regionSize = texture.regionSize;
		//glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
		texture.GetTexture().bind();
		// Draw stuff

		glPushMatrix();
		glTranslatef(0, 0, layer);
		glBegin(GL_QUADS);
		setColor(red, green, blue, alpha);
		glTexCoord3f(regionPos.x/widthTexture, regionPos.y/heightTexture, layer);
		glVertex2f(x, y);
		glTexCoord3f(regionPos.x/widthTexture+regionSize.x/widthTexture, regionPos.y/heightTexture, layer);
		glVertex2f(x+width, y);
		glTexCoord3f(regionPos.x/widthTexture+regionSize.x/widthTexture, regionPos.y/heightTexture+regionSize.y/heightTexture, layer);
		glVertex2f(x+width, y+height);
		glTexCoord3f(regionPos.x/widthTexture, regionPos.y/heightTexture+regionSize.y/heightTexture, layer);
		glVertex2f(x, y+height);
		glEnd();
		glFlush();
		glPopMatrix();
	}
	
}
