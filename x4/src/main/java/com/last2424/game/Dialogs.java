package com.last2424.game;

import java.util.ArrayList;
import java.util.List;

import com.last2424.game.entities.EntityNPC;
import com.last2424.game.entities.EntityPlayer;
import com.last2424.game.gui.DialogGUI;
import com.last2424.game.gui.MerchantGUI;
import com.last2424.game.mechanics.dialog.AQElement;
import com.last2424.game.mechanics.dialog.Dialog;
import com.last2424.game.mechanics.dialog.Page;
import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.GameMath;
import com.last2424.ogl.input.MouseHandler;

public class Dialogs {
	public DialogGUI dialogGUI;
	
	public static Dialog testDialog;
	public static Dialog testDialog2;
	
	public void createDialogs() {
		dialogGUI = new DialogGUI();
		testDialog = new Dialog();
		testDialog.AddPage(new Page("TEST", 0.2f));
		testDialog.AddPage(new Page("TEST2", 0.2f));
		testDialog.AddPage(new Page("TEST3", 0.2f));
		MainScene.characters.testNPC.SetDialog(testDialog);
		
		testDialog2 = new Dialog();
		testDialog2.AddPage(new Page("TEST3", 0.2f));
		testDialog2.AddPage(new Page("TEST2", 0.2f));
		testDialog2.AddPage(new Page("TEST", 0.2f));
		testDialog.SetNextDialog(testDialog2);
		testDialog2.SetNextDialog(testDialog);
		
		testDialog.AddAQElement(new AQElement("Test 1", testDialog2, true, true));
		testDialog.AddAQElement(new AQElement("Close", null, false, false));
	}
	
	public void update(EntityPlayer entity) {
		if(!dialogGUI.isStarted) {
			if(GameMath.GetDistance(entity, MainScene.characters.testNPC) < 50) {
				if(MouseHandler.isMousePressed(0)) {
					dialogGUI.StartDialog(MainScene.characters.testNPC.dialog, true, false);
					//currentMerchant = new MerchantGUI(Logic.player.GetStorage(), Logic.characters.testNPC.GetStorage());
					//Logic.player.merchantOpened = true;
				}
			}
		}
	}
}