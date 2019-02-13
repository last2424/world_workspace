package com.last2424.game.scenes;

import java.awt.Color;

import org.joml.Vector2f;

import com.last2424.game.Characters;
import com.last2424.game.Dialogs;
import com.last2424.game.KeyboardSettings;
import com.last2424.game.entities.EntityPlayer;
import com.last2424.game.entities.Entity.EntityType;
import com.last2424.game.gui.GameInformationGUI;
import com.last2424.game.gui.InventoryGUI;
import com.last2424.game.gui.MerchantGUI;
import com.last2424.game.gui.PlayerInfoGUI;
import com.last2424.game.mechanics.items.Item;
import com.last2424.game.world.Tree;
import com.last2424.game.world.Tree.TreeType;
import com.last2424.game.world.environment.Pillars;
import com.last2424.game.world.houses.HouseSimple;
import com.last2424.game.world.houses.MayorHouse;
import com.last2424.game.mechanics.items.Cell.ItemType;
import com.last2424.ogl.Start;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.animation.Animation;
import com.last2424.ogl.engine.animation.ConfigCUT;
import com.last2424.ogl.engine.animation.ConfigJSON;
import com.last2424.ogl.engine.scene.Scene;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.ogl.rendering.Texture;
import com.last2424.ogl.rendering.TextureRegion;

public class MainScene implements Scene {
	SpriteBatch batch;
	
	public static EntityPlayer player;
	public static Characters characters;
	public static Dialogs dialogSystem;
	public static MerchantGUI currentMerchant;
	public static boolean allStoped = false;
	
	InventoryGUI invGUI;
	PlayerInfoGUI infoGUI;
	GameInformationGUI gameInfoGui;
	
	Item testItem;
	Sprite testItemIcon;
	KeyboardSettings settingsKeys;
	
	Animation testPlayerAnimation;
	
	Tree testTree;
	public static MayorHouse testHouse;
	public static Pillars testPillars;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		batch.setWindowColor(0.5f, 0.7f, 0.95f, 1.0f);
		System.out.println(Start.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		testItem = new Item(new TextureRegion("swords.png", new Vector2f(0, 32), new Vector2f(32, 32)), "Test", ItemType.WEAPON, 10, 5);
		player = new EntityPlayer();
		player.GetStorage().AddItem(testItem);
		invGUI = new InventoryGUI(player.GetStorage());
		infoGUI = new PlayerInfoGUI(player);
		gameInfoGui = new GameInformationGUI();
		characters = new Characters();
		characters.initializeNPC();
		dialogSystem = new Dialogs();
		dialogSystem.createDialogs();
		settingsKeys = new KeyboardSettings();
		
		testHouse = new MayorHouse(new Vector2f(300, 100));
		testPillars = new Pillars("Pillars", new Vector2f(testHouse.position.x+4, testHouse.position.y+156));
		testPlayerAnimation = new Animation(new ConfigCUT(Texture.loadTexture("char/Pen/down-move.png"), 4, 1, 1/5f));
		
		testTree = new Tree(TreeType.NORMAL, new Vector2f(100, 150));
	}

	@Override
	public void update(float delta) {
		if(!allStoped) {
			characters.update(delta);
			dialogSystem.update(player);
			player.GetSprite(0).SetFrame(testPlayerAnimation.GetFrame());
			testPillars.update();
		}
		testPlayerAnimation.update(delta);
		
		settingsKeys.update();
	}

	@Override
	public void render() {
		batch.clearWindow();
		testTree.draw(batch);
		testHouse.draw(batch);
		testPillars.draw(batch);
		characters.draw(batch);
		player.draw(batch);
		
		if(!player.merchantOpened) dialogSystem.dialogGUI.draw(batch);
		if(player.inventoryOpened) invGUI.draw(batch);
		if(player.infoOpened) infoGUI.draw(batch);
		if(player.merchantOpened) currentMerchant.draw(batch);
		gameInfoGui.draw();
	}

}
