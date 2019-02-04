package last2424.game.mechanics.dialog;

public class Page {
	String text;
	public float delay;
	public Page(String text, float delay) {
		this.text = text;
		this.delay = delay;
	}
	
	public String GetText() {
		return text;
	}
	
}