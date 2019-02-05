package com.last2424.game.mechanics.items;

public class Cell {
	
	ItemType type;
	Item item = null;
	int countItems = 0;
	
	public Cell(ItemType type) {
		this.type = type;
	}
	
	public boolean SetItem(Item item) {
		if(item == null) {
			this.item = null;
			countItems = 0;
			return true;
		}
		if(type == ItemType.ANY || type == item.GetItemType()) {
			this.item = item;
			countItems = 1;
			return true;
		}
		return false;
	}
	
	public void RemoveOneItem() {
		countItems--;
		if(countItems <= 0) {
			this.item = null;
			countItems = 0;
		}
	}
	
	public void RemoveAllItems() {
			this.item = null;
			countItems = 0;
	}
	
	public Item GetItem() {
		return this.item;
	}
	
	public enum ItemType {
		ANY,
		WEAPON,
		HELMET,
		CHESTPLATE,
		LEGGINGS,
		BOOTS,
		ARMLETS,
		RING;
	}

	public int GetCount() {
		return countItems;
	}
	
	public void SetCount(int count) {
		this.countItems = count;
	}
	
}
