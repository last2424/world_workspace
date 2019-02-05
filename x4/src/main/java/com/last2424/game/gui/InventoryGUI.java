package com.last2424.game.gui;

import java.awt.Color;
import java.awt.Rectangle;

import com.last2424.game.mechanics.items.Cell;
import com.last2424.game.mechanics.items.Item;
import com.last2424.game.mechanics.items.Storage;
import com.last2424.game.mechanics.items.Cell.ItemType;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.input.MouseHandler;
import com.last2424.ogl.input.MousePositionHandler;
import com.last2424.ogl.rendering.SpriteBatch;

public class InventoryGUI {
	Storage storage;
	Sprite cellSprite;
	Cell selectedItem = new Cell(ItemType.ANY);
	
	public InventoryGUI(Storage storage) {
		this.storage = storage;
		cellSprite = new Sprite("test.png");
	}
	
	public void draw(SpriteBatch batch) {
		drawEquipment(batch);
		
		drawInventory(batch);
		
		if(selectedItem.GetItem() != null) {
			batch.draw(selectedItem.GetItem().GetIcon(), (int)MousePositionHandler.getX(), (int)MousePositionHandler.getY(), 32, 32, 1.0f, 1.0f, 1.0f, 1.0f);
		}
	}
	
	private void drawInventory(SpriteBatch batch) {
		int j = 1, k = 1;
		for(int i = 0; i < storage.GetInventory().size(); i++) {
			
			if(i % 5 == 0) {
				j++;
				k = 1;
			}
			
			cellSprite.SetPosition(48*k, 150+(48*j));
			cellSprite.SetColor(new Color(1.0f, 0.0f, 1.0f, 1.0f));
			
			if(new Rectangle((int)MousePositionHandler.getX(), (int)MousePositionHandler.getY(), 1, 1).intersects(new Rectangle(48*k, 150+(48*j), 32, 32))) {
				
				cellSprite.SetColor(new Color(1.0f, 0.0f, 0.0f, 1.0f));
				
				if(MouseHandler.isMousePressed(0)) {
					onItemClick(storage.GetInventory().get(i));
				}
				
			}
			
			cellSprite.draw(batch);
			k++;
			
			if(storage.GetInventory().get(i).GetItem() != null) {
				batch.draw(storage.GetInventory().get(i).GetItem().GetIcon(), (int)cellSprite.GetPosition().x, (int)cellSprite.GetPosition().y, 32, 32, 1.0f, 1.0f, 1.0f, 1.0f);					
			}
		}
	}
	
	private void drawEquipment(SpriteBatch batch) {
		for(int i = 0; i < storage.GetEquipment().length; i++) {
			cellSprite.SetPosition(48*(i+1), 50);
			cellSprite.SetColor(new Color(0.0f, 0.0f, 1.0f, 1.0f));
			if(new Rectangle((int)MousePositionHandler.getX(), (int)MousePositionHandler.getY(), 1, 1).intersects(new Rectangle(48*(i+1), 50, 32, 32))) {
				cellSprite.SetColor(new Color(0.0f, 0.5f, 1.0f, 1.0f));
				if(MouseHandler.isMousePressed(0)) {
					onItemClick(storage.GetEquipment()[i]);
				}
			}
			cellSprite.draw(batch);
			if(storage.GetEquipment()[i].GetItem() != null) {
				batch.draw(storage.GetEquipment()[i].GetItem().GetIcon(), (int)cellSprite.GetPosition().x, (int)cellSprite.GetPosition().y, 32, 32, 1.0f, 1.0f, 1.0f, 1.0f);
			}
			
		}
	}

	private void onItemClick(Cell cell) {
		if(selectedItem.GetItem() == null) {
			if(cell.GetItem() != null) {
				if(selectedItem.SetItem(cell.GetItem())) {
					selectedItem.SetCount(cell.GetCount());
					cell.SetItem(null);
				}
			}
		}else{
			if(cell.GetItem() == null) {
				if(cell.SetItem(selectedItem.GetItem())) {
					cell.SetCount(selectedItem.GetCount());
					selectedItem.SetItem(null);
				}
			}else if(cell.GetItem() == selectedItem.GetItem()) {
				cell.SetCount(cell.GetCount()+selectedItem.GetCount());
				selectedItem.SetItem(null);
			}
		}
	}
}
