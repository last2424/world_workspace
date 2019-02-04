package last2424.ogl.rendering;

import org.joml.Vector2f;

public class TextureRegion {
	Texture texture;
	
	Vector2f regionPos;
	Vector2f regionSize;
	
	public TextureRegion(String path, Vector2f regionPos, Vector2f regionSize) {
		this(Texture.loadTexture(path), regionPos, regionSize);
	}
	
	public TextureRegion(Texture texture, Vector2f regionPos, Vector2f regionSize) {
		this.texture = texture;
		this.regionPos = regionPos;
		this.regionSize = regionSize;
	}
	
	
	public void SetRegionPos(Vector2f regionPos) {
		this.regionPos = regionPos;
	}
	
	public void SetRegionSize(Vector2f regionSize) {
		this.regionSize = regionSize;
	}
	
	public Vector2f GetRegionPos() {
		return this.regionPos;
	}
	
	public Vector2f GetRegionSize() {
		return this.regionSize;
	}
	
	public Texture GetTexture() {
		return this.texture;
	}
}
