package last2424.ogl.rendering;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.joml.Vector2f;

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
	
	public void draw(Texture texture, int x, int y) {
		draw(texture, x, y, texture.getWidth(), texture.getHeight());
	}
	
	public void draw(Texture texture, int x, int y, int width, int height) {
		draw(texture, x, y, width, height, 1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	public void draw(Texture texture, int x, int y, int width, int height, float red, float green, float blue, float alpha) {
		draw(new TextureRegion(texture, new Vector2f(0, 0), new Vector2f(texture.getWidth(), texture.getHeight())), x, y, width, height, red, green, blue, alpha);
	}
	
	public void draw(TextureRegion texture, int x, int y, int width, int height, float red, float green, float blue, float alpha) {
		texture.GetTexture().bind();
		glBegin(GL_QUADS);
		setColor(red, green, blue, alpha);
		glTexCoord2f(texture.regionPos.x/texture.GetTexture().getWidth(), texture.regionPos.y/texture.GetTexture().getHeight());
		glVertex2f(x, y);
		glTexCoord2f(texture.regionPos.x/texture.GetTexture().getWidth()+texture.regionSize.x/texture.GetTexture().getWidth(), texture.regionPos.y/texture.GetTexture().getHeight());
		glVertex2f(x+width, y);
		glTexCoord2f(texture.regionPos.x/texture.GetTexture().getWidth()+texture.regionSize.x/texture.GetTexture().getWidth(), texture.regionPos.y/texture.GetTexture().getHeight()+texture.regionSize.y/texture.GetTexture().getHeight());
		glVertex2f(x+width, y+height);
		glTexCoord2f(texture.regionPos.x/texture.GetTexture().getWidth(), texture.regionPos.y/texture.GetTexture().getHeight()+texture.regionSize.y/texture.GetTexture().getHeight());
		glVertex2f(x, y+height);
		glEnd();
	}
	
}
