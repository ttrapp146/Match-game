package puzzle;

public class Background {

	private int bgX;
	private int bgY;

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public Background(int x, int y) {

		bgX = x;
		bgY = y;
	}
}
