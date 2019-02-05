package com.last2424.game.mechanics.items;

import org.joml.Vector2f;

import com.last2424.game.mechanics.items.Cell.ItemType;
import com.last2424.ogl.engine.animation.Frame;
import com.last2424.ogl.rendering.Texture;
import com.last2424.ogl.rendering.TextureRegion;

public class Item {
	String itemName;
	ItemType type;
	TextureRegion icon;
	
	int costBuy, costSell;
	public boolean isNotSelled = false;
	
	public Item(String path, String itemName, ItemType type, int costBuy, int costSell) {
		this(Texture.loadTexture(path), itemName, type, costBuy, costSell);
	}
	
	public Item(Texture icon, String itemName, ItemType type, int costBuy, int costSell) {
		this(new TextureRegion(icon, new Vector2f(0, 0), new Vector2f(icon.getWidth(), icon.getHeight())), itemName, type, costBuy, costSell);
	}
	
	public Item(TextureRegion icon, String itemName, ItemType type, int costBuy, int costSell) {
		this.itemName = itemName;
		this.type = type;
		this.icon = icon;
		this.costBuy = costBuy;
		this.costSell = costSell;
	}
	
	public void SetFrame(Frame frame) {
		this.icon.SetRegionPos(frame.GetPosition());
		this.icon.SetRegionSize(frame.GetSize());
	}

	public ItemType GetItemType() {
		return type;
	}
	
	public TextureRegion GetIcon() {
		return icon;
	}
}
