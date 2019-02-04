package last2424.game;

import last2424.ogl.engine.scene.Scene;

public class Logic {
	public static Scene scene;
	
	public void create() {
		scene.create();
	}

	public void update(float delta) {
		scene.update(delta);
	}

	public void render() {
		scene.render();
	}

}
