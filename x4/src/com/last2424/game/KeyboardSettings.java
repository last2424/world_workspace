package last2424.game;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import org.lwjgl.glfw.GLFW;

import last2424.game.scenes.MainScene;
import last2424.ogl.Start;
import last2424.ogl.input.KeyboardHandler;

public class KeyboardSettings {

	public void update() {
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			glfwSetWindowShouldClose(Start.window, true);
		}
		
		if(KeyboardHandler.isKeyPressed(GLFW.GLFW_KEY_I) && !MainScene.allStoped) {
			MainScene.player.inventoryOpened = !MainScene.player.inventoryOpened;
		}
		
		if(KeyboardHandler.isKeyPressed(GLFW.GLFW_KEY_B) && !MainScene.allStoped) {
			MainScene.player.infoOpened = !MainScene.player.infoOpened;
		}
		
		if(KeyboardHandler.isKeyPressed(GLFW.GLFW_KEY_C)) {
			MainScene.player.merchantOpened = false;
		}
		
		if(MainScene.player.infoOpened || MainScene.player.inventoryOpened || MainScene.player.merchantOpened || MainScene.dialogSystem.dialogGUI.isStarted) {
			MainScene.allStoped = true;
		}else {
			MainScene.allStoped = false;
		}
	}
	
}
