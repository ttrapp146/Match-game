package puzzle;

import java.util.Random;

public class Block {

	Random random = new Random();
	private int centerX;
	private int centerY;
	private int speedX;
	private int speedY = 1;
	private int blockType = random.nextInt(5) + 1;
	private int blockColor, oldCenterY;
	private int matchGraphicSpeed = 1, matchGraphicX1, matchGraphicX2,
			matchGraphicX3, matchGraphicX4, matchGraphicY1, matchGraphicY2,
			matchGraphicY3, matchGraphicY4, matchGraphicY1Start,
			matchGraphicY2Start, matchGraphicY3Start, matchGraphicY4Start,
			matchGraphicY1End, matchGraphicY2End, matchGraphicY3End,
			matchGraphicY4End, matchGraphicX1Start, matchGraphicX2Start,
			matchGraphicX3Start, matchGraphicX4Start, matchGraphicX1End,
			matchGraphicX2End, matchGraphicX3End, matchGraphicX4End;
	private boolean match, xMatch, yMatch, falling, selected, playAnimation, matchGraphic;

	public void update() {
		if (centerY != oldCenterY) {
			centerY += speedY;
		} else
			falling = false;

		if (matchGraphicX1 != matchGraphicX1End && matchGraphic == true) {
			matchGraphicX1 -= matchGraphicSpeed;
			matchGraphicX2 += matchGraphicSpeed;
			matchGraphicX3 += matchGraphicSpeed;
			matchGraphicX4 -= matchGraphicSpeed;
			matchGraphicY1 += matchGraphicSpeed;
			matchGraphicY2 += matchGraphicSpeed;
			matchGraphicY3 -= matchGraphicSpeed;
			matchGraphicY4 -= matchGraphicSpeed;
		} else {
			matchGraphic = false;
			matchGraphicX1 = matchGraphicX1Start;
			matchGraphicY1 = matchGraphicY1Start;
			matchGraphicX2 = matchGraphicX2Start;
			matchGraphicY2 = matchGraphicY2Start;
			matchGraphicX3 = matchGraphicX3Start;
			matchGraphicY3 = matchGraphicY3Start;
			matchGraphicX4 = matchGraphicX4Start;
			matchGraphicY4 = matchGraphicY4Start;
		}
	}

	/*
	 * public void blockFall(int startY, int endBlockType, int startBlockType,
	 * int speed) { match = false; oldCenterY = centerY; centerY = startY;
	 * speedY = speed; blockType = endBlockType;
	 * 
	 * while (centerY != oldCenterY) { centerY += speedY; }
	 * 
	 * speedY = 0; blockType = endBlockType; }
	 */

	public void generateBlockType() {
		blockType = random.nextInt(5) + 1;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getBlockType() {
		return blockType;
	}

	public int getBlockColor() {
		return blockColor;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}

	public void setBlockColor(int blockColor) {
		this.blockColor = blockColor;
	}

	public boolean getMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	public int getOldCenterY() {
		return oldCenterY;
	}

	public void setOldCenterY(int oldCenterY) {
		this.oldCenterY = oldCenterY;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isPlayAnimation() {
		return playAnimation;
	}

	public void setPlayAnimation(boolean playAnimation) {
		this.playAnimation = playAnimation;
	}

	public int getMatchGraphicSpeed() {
		return matchGraphicSpeed;
	}

	public int getMatchGraphicX1() {
		return matchGraphicX1;
	}

	public int getMatchGraphicX2() {
		return matchGraphicX2;
	}

	public int getMatchGraphicX3() {
		return matchGraphicX3;
	}

	public int getMatchGraphicX4() {
		return matchGraphicX4;
	}

	public int getMatchGraphicY1() {
		return matchGraphicY1;
	}

	public int getMatchGraphicY2() {
		return matchGraphicY2;
	}

	public int getMatchGraphicY3() {
		return matchGraphicY3;
	}

	public int getMatchGraphicY4() {
		return matchGraphicY4;
	}

	public int getMatchGraphicY1Start() {
		return matchGraphicY1Start;
	}

	public int getMatchGraphicY2Start() {
		return matchGraphicY2Start;
	}

	public int getMatchGraphicY3Start() {
		return matchGraphicY3Start;
	}

	public int getMatchGraphicY4Start() {
		return matchGraphicY4Start;
	}

	public int getMatchGraphicY1End() {
		return matchGraphicY1End;
	}

	public int getMatchGraphicY2End() {
		return matchGraphicY2End;
	}

	public int getMatchGraphicY3End() {
		return matchGraphicY3End;
	}

	public int getMatchGraphicY4End() {
		return matchGraphicY4End;
	}

	public int getMatchGraphicX1Start() {
		return matchGraphicX1Start;
	}

	public int getMatchGraphicX2Start() {
		return matchGraphicX2Start;
	}

	public int getMatchGraphicX3Start() {
		return matchGraphicX3Start;
	}

	public int getMatchGraphicX4Start() {
		return matchGraphicX4Start;
	}

	public int getMatchGraphicX1End() {
		return matchGraphicX1End;
	}

	public int getMatchGraphicX2End() {
		return matchGraphicX2End;
	}

	public int getMatchGraphicX3End() {
		return matchGraphicX3End;
	}

	public int getMatchGraphicX4End() {
		return matchGraphicX4End;
	}

	public boolean isMatchGraphic() {
		return matchGraphic;
	}

	public void setMatchGraphicSpeed(int matchGraphicSpeed) {
		this.matchGraphicSpeed = matchGraphicSpeed;
	}

	public void setMatchGraphicX1(int matchGraphicX1) {
		this.matchGraphicX1 = matchGraphicX1;
	}

	public void setMatchGraphicX2(int matchGraphicX2) {
		this.matchGraphicX2 = matchGraphicX2;
	}

	public void setMatchGraphicX3(int matchGraphicX3) {
		this.matchGraphicX3 = matchGraphicX3;
	}

	public void setMatchGraphicX4(int matchGraphicX4) {
		this.matchGraphicX4 = matchGraphicX4;
	}

	public void setMatchGraphicY1(int matchGraphicY1) {
		this.matchGraphicY1 = matchGraphicY1;
	}

	public void setMatchGraphicY2(int matchGraphicY2) {
		this.matchGraphicY2 = matchGraphicY2;
	}

	public void setMatchGraphicY3(int matchGraphicY3) {
		this.matchGraphicY3 = matchGraphicY3;
	}

	public void setMatchGraphicY4(int matchGraphicY4) {
		this.matchGraphicY4 = matchGraphicY4;
	}

	public void setMatchGraphicY1Start(int matchGraphicY1Start) {
		this.matchGraphicY1Start = matchGraphicY1Start;
	}

	public void setMatchGraphicY2Start(int matchGraphicY2Start) {
		this.matchGraphicY2Start = matchGraphicY2Start;
	}

	public void setMatchGraphicY3Start(int matchGraphicY3Start) {
		this.matchGraphicY3Start = matchGraphicY3Start;
	}

	public void setMatchGraphicY4Start(int matchGraphicY4Start) {
		this.matchGraphicY4Start = matchGraphicY4Start;
	}

	public void setMatchGraphicY1End(int matchGraphicY1End) {
		this.matchGraphicY1End = matchGraphicY1End;
	}

	public void setMatchGraphicY2End(int matchGraphicY2End) {
		this.matchGraphicY2End = matchGraphicY2End;
	}

	public void setMatchGraphicY3End(int matchGraphicY3End) {
		this.matchGraphicY3End = matchGraphicY3End;
	}

	public void setMatchGraphicY4End(int matchGraphicY4End) {
		this.matchGraphicY4End = matchGraphicY4End;
	}

	public void setMatchGraphicX1Start(int matchGraphicX1Start) {
		this.matchGraphicX1Start = matchGraphicX1Start;
	}

	public void setMatchGraphicX2Start(int matchGraphicX2Start) {
		this.matchGraphicX2Start = matchGraphicX2Start;
	}

	public void setMatchGraphicX3Start(int matchGraphicX3Start) {
		this.matchGraphicX3Start = matchGraphicX3Start;
	}

	public void setMatchGraphicX4Start(int matchGraphicX4Start) {
		this.matchGraphicX4Start = matchGraphicX4Start;
	}

	public void setMatchGraphicX1End(int matchGraphicX1End) {
		this.matchGraphicX1End = matchGraphicX1End;
	}

	public void setMatchGraphicX2End(int matchGraphicX2End) {
		this.matchGraphicX2End = matchGraphicX2End;
	}

	public void setMatchGraphicX3End(int matchGraphicX3End) {
		this.matchGraphicX3End = matchGraphicX3End;
	}

	public void setMatchGraphicX4End(int matchGraphicX4End) {
		this.matchGraphicX4End = matchGraphicX4End;
	}

	public void setMatchGraphic(boolean matchGraphic) {
		this.matchGraphic = matchGraphic;
	}

	public boolean isxMatch() {
		return xMatch;
	}

	public boolean isyMatch() {
		return yMatch;
	}

	public void setxMatch(boolean xMatch) {
		this.xMatch = xMatch;
	}

	public void setyMatch(boolean yMatch) {
		this.yMatch = yMatch;
	}
}
