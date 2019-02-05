package com.last2424.game.mechanics.dialog;

public class AQElement {
	public Dialog toDialog;
	String aq = "";
	
	public boolean isShop, isNext;
	
	public AQElement(String aq, Dialog dialog, boolean isShop, boolean isNext) {
		this.toDialog = dialog;
		this.aq = aq;
		this.isShop = isShop;
		this.isNext = isNext;
	}
	
	public String GetAQ() {
		return aq;
	}
}