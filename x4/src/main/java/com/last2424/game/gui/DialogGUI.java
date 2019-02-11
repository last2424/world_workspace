package com.last2424.game.gui;

import java.awt.Font;
import java.awt.Rectangle;

import com.last2424.game.Logic;
import com.last2424.game.mechanics.dialog.Dialog;
import com.last2424.game.mechanics.dialog.Page;
import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.input.MouseHandler;
import com.last2424.ogl.input.MousePositionHandler;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.ogl.rendering.TrueTypeFont;

public class DialogGUI {

	public boolean isStarted = false, dAQElements = false;
	Dialog currentDialog = null;
	Sprite dialogBackground;
	Font font;
	TrueTypeFont trueFont;
	
	int currentPage = 0;
	int symbol = 0;
	String text = "";
	float delay = 0;
	
	boolean startShop;
	
	public DialogGUI() {
		dialogBackground = new Sprite("dialogBox.png", 10);
		dialogBackground.SetPosition(100, 350);
		font = new Font("Times New Roman", Font.BOLD, 21);
		trueFont = new TrueTypeFont(font, true);
	}
	
	public void StartDialog(Dialog dialog, boolean nextDialog, boolean startShop) {
		if(dialog != null) {
			currentDialog = dialog;
			isStarted = true;
			currentPage = 0;
			dAQElements = false;
			this.startShop = startShop;
			if(nextDialog) MainScene.characters.testNPC.dialog = MainScene.characters.testNPC.dialog.GetNextDialog();
		}else {
			zeroingGUI();
		}
	}
	
	public void draw(SpriteBatch batch) {
		if(isStarted) {
			dialogBackground.draw(batch);
			if(currentDialog != null) {
				Page page = currentDialog.GetPage(currentPage);
				if(delay <= 0) {
					delay = currentDialog.GetPage(currentPage).delay;
					if(!text.equals(page.GetText())) {
						if(symbol < page.GetText().length()) {
							text += page.GetText().substring(symbol, symbol+1);
							symbol += 1;
						}
						if(MouseHandler.isMousePressed(0)) {
							text = page.GetText();
						}
					}else {
						if(MouseHandler.isMousePressed(0)) {
							text = "";
							if(currentPage < currentDialog.pages.size()-1) { currentPage += 1; } else {
								if(startShop) {
									MainScene.currentMerchant = new MerchantGUI(MainScene.player.GetStorage(), MainScene.characters.testNPC.GetStorage());
									MainScene.player.merchantOpened = true;
								}
								if(currentDialog.aqelements.size() <= 0) {
									zeroingGUI();
								}else {
									dAQElements = true;
								}
							}
							symbol = 0;
						}
					}
				}else {
					delay -= 1/30f;
				}
				if(!dAQElements) trueFont.drawString(dialogBackground.GetPosition().x+5, dialogBackground.GetPosition().y+5, text, 1, 1);
				else drawAQElements(batch);
			}
		}
	}
	
	private void zeroingGUI() {
		currentPage = 0;
		currentDialog = null;
		isStarted = false;
		text = "";
		symbol = 0;
		delay = 0;
		dAQElements = false;
		startShop = false;
	}
	
	private void drawAQElements(SpriteBatch batch) {
			for(int i = 0; i < currentDialog.aqelements.size(); i++) {
				batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
				if(MousePositionHandler.Intersects(new Rectangle((int)dialogBackground.GetPosition().x+5, (int)dialogBackground.GetPosition().y+5+(32*i), trueFont.getWidth(currentDialog.aqelements.get(i).GetAQ()), trueFont.getHeight(currentDialog.aqelements.get(i).GetAQ())))) {
					batch.setColor(1.0f, 0.0f, 0.0f, 1.0f);
					if(MouseHandler.isMousePressed(0)) {
						StartDialog(currentDialog.aqelements.get(i).toDialog, currentDialog.aqelements.get(i).isNext, currentDialog.aqelements.get(i).isShop);
						break;
					}
				}
				trueFont.drawString(dialogBackground.GetPosition().x+5, dialogBackground.GetPosition().y+5+(32*i), currentDialog.aqelements.get(i).GetAQ(), 1, 1);
			}
	}

}
