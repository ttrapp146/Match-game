package puzzle;

public class Player {

	private int centerX = 1;
	private int centerY = 467;
	private int moveBlockX, moveBlockY;
	private int position = 12;
	private int holdBlock = 36;
	private int switchBlockPosition = 36;
	private boolean holding = false, frozen = false;

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void moveRight() {
		//TODO: limit movement after selecting block
		//use lower and upper limit variables
		if (centerX < 301) {
			centerX += 60;
			position +=1;
		}
	}

	public void moveLeft() {
		if (centerX > 1) {
			centerX -= 60;
			position -=1;
		}

	}

	public void moveUp() {
		if (centerY > 287) {
			centerY -= 60;
			position +=6;
		}
		
	}

	public void moveDown() {
		if (centerY < 587) {
			centerY += 60;
			position -=6;
		}

	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getHoldBlock() {
		return holdBlock;
	}

	public void setHoldBlock(int holdBlock) {
		this.holdBlock = holdBlock;
	}

	public boolean isHolding() {
		return holding;
	}

	public void setHolding(boolean holding) {
		this.holding = holding;
	}

	public int getSwitchBlockPosition() {
		return switchBlockPosition;
	}

	public void setSwitchBlockPosition(int switchBlockPosition) {
		this.switchBlockPosition = switchBlockPosition;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	/*public void moveBlock(int x, int y) {
		if (moveBlockX == centerX && moveBlockY == centerY) {
			moveBlockX = 0;
			moveBlockY = 0;
		} else if (moveBlockY > 1) {
			//switch blocks
			moveBlockX = 0;
			moveBlockY = 0;
		}else {
			moveBlockX = centerX;
			moveBlockY = centerY;
		}
	}*/
}
