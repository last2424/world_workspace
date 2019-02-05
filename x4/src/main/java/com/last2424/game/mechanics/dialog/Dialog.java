package com.last2424.game.mechanics.dialog;

import java.util.ArrayList;
import java.util.List;

import com.last2424.game.entities.EntityNPC;

public class Dialog {
	public List<Page> pages;
	public List<AQElement> aqelements;
	Dialog nextDialog;
	
	public Dialog() {
		pages = new ArrayList();
		aqelements = new ArrayList();
	}
	
	public void AddPage(Page page) {
		pages.add(page);
	}
	
	public Page GetPage(int index) {
		return pages.get(index);
	}

	public void SetNextDialog(Dialog dialog) {
		this.nextDialog = dialog;
	}

	public Dialog GetNextDialog() {
		return nextDialog;
	}
	
	public void AddAQElement(AQElement element) {
		this.aqelements.add(element);
	}
}