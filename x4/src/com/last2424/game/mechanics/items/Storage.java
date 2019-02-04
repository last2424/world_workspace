package last2424.game.mechanics.items;

import java.util.ArrayList;
import java.util.List;

import last2424.game.mechanics.items.Cell.ItemType;

public class Storage {
	Cell[] equipment = new Cell[8];
	List<Cell> inventory = new ArrayList<Cell>();
	
	public Storage() {
		equipment[0] = new Cell(ItemType.WEAPON);
		equipment[1] = new Cell(ItemType.HELMET);
		equipment[2] = new Cell(ItemType.CHESTPLATE);
		equipment[3] = new Cell(ItemType.ARMLETS);
		equipment[4] = new Cell(ItemType.LEGGINGS);
		equipment[5] = new Cell(ItemType.BOOTS);
		equipment[6] = new Cell(ItemType.RING);
		equipment[7] = new Cell(ItemType.RING);
		
		for(int i = 0; i < 25; i++) {
				inventory.add(new Cell(ItemType.ANY));
		}
	}
	
	public List<Cell> GetInventory() {
		return inventory;
	}
	
	public Cell[] GetEquipment() {
		return equipment;
	}
	
	public boolean AddItem(Item item) {
		return AddItem(item, 1);
	}
	
	public boolean AddItem(Item item, int count) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).GetItem() == null) {
				return inventory.get(i).SetItem(item);
			}else{
				if(inventory.get(i).GetItem() == item) {
					inventory.get(i).countItems+=count;
					return true;
				}
			}
		}
		Cell temp = new Cell(ItemType.ANY);
		temp.item = item;
		temp.countItems = count;
		inventory.add(temp);
		return true;
	}
}
