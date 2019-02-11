package com.last2424.game.gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.last2424.game.entities.Entity;
import com.last2424.game.mechanics.items.Cell;
import com.last2424.game.mechanics.items.Storage;
import com.last2424.game.mechanics.items.Cell.ItemType;
import com.last2424.game.mechanics.items.MouseCell;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.input.KeyboardHandler;
import com.last2424.ogl.input.MouseHandler;
import com.last2424.ogl.input.MousePositionHandler;
import com.last2424.ogl.rendering.SpriteBatch;

public class MerchantGUI {
	Storage playerStorage, merchantStorage;
	
	Sprite cellSprite;
	
	public MerchantGUI(Storage playerStorage, Storage merchantStorage) {
		this.playerStorage = playerStorage;
		this.merchantStorage = merchantStorage;
		cellSprite = new Sprite("test.png", 10);
	}
	
	public void draw(SpriteBatch batch) {
		drawInventory(batch, playerStorage, 0, true);
		drawInventory(batch, merchantStorage, 300, false);
	}
	
	private void drawInventory(SpriteBatch batch, Storage storage, float offset, boolean mouseHandle) {
		int j = 1, k = 1;
		List<Cell> inv = storage.GetInventory();
		for(int i = 0; i < inv.size(); i++) {
			
			if(i % 5 == 0) {
				j++;
				k = 1;
			}
			
			cellSprite.SetPosition(48*k+offset, 150+(48*j));
			cellSprite.SetColor(new Color(1.0f, 0.0f, 1.0f, 1.0f));
			
			if(mouseHandle) {
				if(new Rectangle((int)MousePositionHandler.getX(), (int)MousePositionHandler.getY(), 1, 1).intersects(new Rectangle((int)(48*k+offset), 150+(48*j), 32, 32))) {
				
					cellSprite.SetColor(new Color(1.0f, 0.0f, 0.0f, 1.0f));
				
					if(MouseHandler.isMousePressed(0)) {
						onItemClick(inv.get(i));
					}
				
				}
			}
			
			cellSprite.draw(batch);
			k++;
			
			if(inv.get(i).GetItem() != null) {
				batch.draw(inv.get(i).GetItem().GetIcon(), (int)cellSprite.GetPosition().x, (int)cellSprite.GetPosition().y, 32, 32, 1.0f, 1.0f, 1.0f, 1.0f, 10);					
			}
		}
	}
	
	private void onItemClick(Cell cell) {
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_LEFT_CONTROL)) {
		}
	}
}
