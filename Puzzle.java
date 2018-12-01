package puzzle;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.imageio.ImageIO;

import puzzle.framework.Animation;

public class Puzzle extends Applet implements Runnable, KeyListener {

	enum Gamestate {
		Running, MainMenu, Win, Lose
	}

	enum Gamelevel {
		L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, L13, L14, L15, L16, L17, L18, L19, L20, L21, L22, L23, L24, L25, L26, L27, L28, L29, L30, L31, L32, L33, L34, L35, L36, L37, L38, L39, L40, L41, L42, L43, L44, L45, L46, L47, L48, L49, L50, Practice, VS
	}

	Gamestate state = Gamestate.Running;
	Gamelevel level = Gamelevel.Practice;

	private static Background bgVs;
	Random random = new Random();
	public Image image, vsImage, playerImage2, block1, block2,
			block3, block4, block5, block1P, block2P, block3P, block4P,
			block5P, block1S, block2S, block3S, block4S, block5S, block1B,
			block2B, block3B, block4B, block5B, block1R, block2R, block3R,
			block4R, block5R, blackNote, blueNote, greenNote, redNote,
			orangeNote, yellowNote, pinkNote, pinkNote0, pinkNote1, pinkNote2,
			pinkNote3, pinkNote4, pinkNote5, pinkNote6, pinkNote7, pinkNote8,
			pinkNote9, pinkNote10, pinkNote11;
	private Image playerImage;
	private Graphics second;
	private Animation comboText, comboCounter, pinkNoteAnimate;
	private Player player;
	private Performance performance;
	private Boost boost1, boost2;
	private Block[] blocks = new Block[36];
	private int x = 0, y = 0, z = 0, p = 0, q = 0, s = 0, color = 1,
			matchCount = 0, blockCount = 0, comboCount = 0,
			blockOrderComboCount = 0, blockOrderMiss = 0, switchBlock1,
			switchBlock2, switchSpot1, switchSpot2, lastBlockType = 0,
			turnCount = 0, lastBlockOrder = 0,
			blockOrder1 = random.nextInt(5) + 1, blockOrder2 = random
					.nextInt(5) + 1, blockOrder3 = random.nextInt(5) + 1;

	private boolean xMatch = true, yMatch = true;

	@Override
	public void init() {

		setSize(365, 650);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		// Initialize blocks

		while (blockOrder2 == blockOrder1)
			blockOrder2 = random.nextInt(5) + 1;

		while (blockOrder3 == blockOrder1 || blockOrder3 == blockOrder2)
			blockOrder3 = random.nextInt(5) + 1;

		for (x = 0; x < 36; x++)
			blocks[x] = new Block();

		for (x = 2, y = 586, z = 0; z < 36; z++) {

			blocks[z].setCenterX(x);
			blocks[z].setCenterY(y);
			blocks[z].setOldCenterY(y);

			blocks[z].setMatchGraphicX1(x + 8);
			blocks[z].setMatchGraphicY1(y + 19);
			blocks[z].setMatchGraphicX2(x + 18);
			blocks[z].setMatchGraphicY2(y + 19);
			blocks[z].setMatchGraphicX3(x + 18);
			blocks[z].setMatchGraphicY3(y + 9);
			blocks[z].setMatchGraphicX4(x + 8);
			blocks[z].setMatchGraphicY4(y + 9);

			blocks[z].setMatchGraphicX1Start(x + 8);
			blocks[z].setMatchGraphicY1Start(y + 19);
			blocks[z].setMatchGraphicX2Start(x + 18);
			blocks[z].setMatchGraphicY2Start(y + 19);
			blocks[z].setMatchGraphicX3Start(x + 18);
			blocks[z].setMatchGraphicY3Start(y + 9);
			blocks[z].setMatchGraphicX4Start(x + 8);
			blocks[z].setMatchGraphicY4Start(y + 9);

			blocks[z].setMatchGraphicX1End(x - 8);
			blocks[z].setMatchGraphicY1End(y - 1);
			blocks[z].setMatchGraphicX2End(x + 38);
			blocks[z].setMatchGraphicY2End(y - 1);
			blocks[z].setMatchGraphicX3End(x + 38);
			blocks[z].setMatchGraphicY3End(y + 33);
			blocks[z].setMatchGraphicX4End(x - 8);
			blocks[z].setMatchGraphicY4End(y + 33);

			if ((z + 1) % 6 == 0) {
				x = 2;
				y -= 60;
			} else
				x += 60;
		}

		// Image imports

			try {
				playerImage2 = ImageIO.read(getClass().getResource("pictures/characters/playertest2.png"));
				
				playerImage = ImageIO.read(getClass().getResource("pictures/characters/playertest.png"));

				block1 = ImageIO.read(getClass().getResource(
						"pictures/blocks/yuri test2.jpeg"));

				block2 = ImageIO.read(getClass().getResource(
						"pictures/blocks/kyo test.jpeg"));

				block3 = ImageIO.read(getClass().getResource(
						"pictures/blocks/iori test.jpeg"));

				block4 = ImageIO.read(getClass().getResource(
						"pictures/blocks/leona test.jpeg"));

				block5 = ImageIO.read(getClass().getResource(
						"pictures/blocks/mai test.jpeg"));

				block1P = ImageIO.read(getClass().getResource(
						"pictures/blocks/yuri phased.jpeg"));

				block2P = ImageIO.read(getClass().getResource(
						"pictures/blocks/kyo phased.jpeg"));

				block3P = ImageIO.read(getClass().getResource(
						"pictures/blocks/iori phased.jpeg"));

				block4P = ImageIO.read(getClass().getResource(
						"pictures/blocks/leona phased.jpeg"));

				block5P = ImageIO.read(getClass().getResource(
						"pictures/blocks/mai phased.jpeg"));

				// red block images

				block1R = ImageIO.read(getClass().getResource(
						"pictures/blocks/yuri red.jpeg"));

				block2R = ImageIO.read(getClass().getResource(
						"pictures/blocks/kyo red.jpeg"));

				block3R = ImageIO.read(getClass().getResource(
						"pictures/blocks/iori red.jpeg"));

				block4R = ImageIO.read(getClass().getResource(
						"pictures/blocks/leona red.jpeg"));

				block5R = ImageIO.read(getClass().getResource(
						"pictures/blocks/mai red.jpeg"));

				// note images

				blackNote = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/blacknote.png"));

				blueNote = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/bluenote.png"));

				greenNote = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/greennote.png"));

				orangeNote = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/orangenote.png"));

				yellowNote = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/yellownote.png"));

				pinkNote = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/pinknote.png"));

				redNote = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/rednote.png"));

				pinkNote0 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note0-2test.jpg"));

				pinkNote1 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note1-2test.jpg"));

				pinkNote2 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note2-2test.jpg"));

				pinkNote3 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note3-2test.jpg"));

				pinkNote4 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note4-2test.jpg"));

				pinkNote5 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note5-2test.jpg"));

				pinkNote6 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note6-2test.jpg"));

				pinkNote7 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note6-7test.jpg"));

				pinkNote8 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note6-8test.jpg"));

				pinkNote9 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note6-9test.jpg"));

				pinkNote10 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note6-10test.jpg"));

				pinkNote11 = ImageIO.read(getClass().getResource(
						"pictures/blocks/Notes/Pink/pink note6-11test.jpg"));
				
				// add selection border to selection images

				block1S = ImageIO.read(getClass().getResource(
						"pictures/blocks/yuri select.jpeg"));

				block2S = ImageIO.read(getClass().getResource(
						"pictures/blocks/kyo select.jpeg"));

				block3S = ImageIO.read(getClass().getResource(
						"pictures/blocks/iori select.jpeg"));

				block4S = ImageIO.read(getClass().getResource(
						"pictures/blocks/leona select.jpeg"));

				block5S = ImageIO.read(getClass().getResource(
						"pictures/blocks/mai select.jpeg"));

				block1B = ImageIO.read(getClass().getResource(
						"pictures/blocks/yuri border.jpeg"));

				block2B = ImageIO.read(getClass().getResource(
						"pictures/blocks/kyo border.jpeg"));

				block3B = ImageIO.read(getClass().getResource(
						"pictures/blocks/iori border.jpeg"));

				block4B = ImageIO.read(getClass().getResource(
						"pictures/blocks/leona border.jpeg"));

				block5B = ImageIO.read(getClass().getResource(
						"pictures/blocks/mai border.jpeg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		// add note animation

		pinkNoteAnimate = new Animation();
		pinkNoteAnimate.addFrame(pinkNote0, 10);
		pinkNoteAnimate.addFrame(pinkNote1, 10);
		pinkNoteAnimate.addFrame(pinkNote2, 10);
		pinkNoteAnimate.addFrame(pinkNote3, 10);
		pinkNoteAnimate.addFrame(pinkNote4, 10);
		pinkNoteAnimate.addFrame(pinkNote5, 10);
		pinkNoteAnimate.addFrame(pinkNote6, 10);
		pinkNoteAnimate.addFrame(pinkNote7, 10);
		pinkNoteAnimate.addFrame(pinkNote8, 10);
		pinkNoteAnimate.addFrame(pinkNote9, 10);
		pinkNoteAnimate.addFrame(pinkNote10, 10);
		pinkNoteAnimate.addFrame(pinkNote11, 10);

		// add selection border to selection images

		// Animation testing

		comboCounter = new Animation();

	}

	@Override
	public void start() {
		bgVs = new Background(0, 0);
		player = new Player();

		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	public void paint(Graphics g) {
		g.drawImage(vsImage, bgVs.getBgX(), bgVs.getBgY(), this);

		// draw phased blocks. add in animation before phased shows.
		// change a value when block starts falling to signal animation starts

		for (p = 35; p > -1; p--)

			if (blocks[p].isFalling() == true) {
				if (blocks[p].getBlockType() == 1)
					g.drawImage(block1P, blocks[p].getCenterX(),
							blocks[p].getOldCenterY(), this);

				if (blocks[p].getBlockType() == 2)
					g.drawImage(block2P, blocks[p].getCenterX(),
							blocks[p].getOldCenterY(), this);

				if (blocks[p].getBlockType() == 3)
					g.drawImage(block3P, blocks[p].getCenterX(),
							blocks[p].getOldCenterY(), this);

				if (blocks[p].getBlockType() == 4)
					g.drawImage(block4P, blocks[p].getCenterX(),
							blocks[p].getOldCenterY(), this);

				if (blocks[p].getBlockType() == 5)
					g.drawImage(block5P, blocks[p].getCenterX(),
							blocks[p].getOldCenterY(), this);
			}

		for (p = 0; p < 36; p++) {
			if (blocks[p].getBlockType() == 1)
				if (blocks[p].isSelected() == true)
					g.drawImage(block1S, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else if (blocks[p].getBlockType() == blockOrder1)
					g.drawImage(block1B, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else
					g.drawImage(block1, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);

			if (blocks[p].getBlockType() == 2)
				if (blocks[p].isSelected() == true)
					g.drawImage(block2S, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else if (blocks[p].getBlockType() == blockOrder1)
					g.drawImage(block2B, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else
					g.drawImage(block2, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
			if (blocks[p].getBlockType() == 3)
				if (blocks[p].isSelected() == true)
					g.drawImage(block3S, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else if (blocks[p].getBlockType() == blockOrder1)
					g.drawImage(block3B, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else
					g.drawImage(block3, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);

			if (blocks[p].getBlockType() == 4)
				if (blocks[p].isSelected() == true)
					g.drawImage(block4S, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else if (blocks[p].getBlockType() == blockOrder1)
					g.drawImage(block4B, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else
					g.drawImage(block4, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);

			if (blocks[p].getBlockType() == 5)
				if (blocks[p].isSelected() == true)
					g.drawImage(block5S, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else if (blocks[p].getBlockType() == blockOrder1)
					g.drawImage(block5B, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
				else
					g.drawImage(block5, blocks[p].getCenterX(),
							blocks[p].getCenterY(), this);
		}

		// Player Image

		if (state != Gamestate.Win) {
			if (player.getHoldBlock() != 36) {
				g.drawImage(playerImage, player.getCenterX(),
						player.getCenterY(), this);
			} else
				g.drawImage(playerImage2, player.getCenterX(),
						player.getCenterY(), this);
		}
		// Play Match Graphic

		for (p = 0; p < 36; p++) {
			if (blocks[p].isMatchGraphic())
				if (comboCount > 49) {
					g.drawImage(pinkNote, blocks[p].getMatchGraphicX1(),
							blocks[p].getMatchGraphicY1(), this);
					g.drawImage(pinkNote, blocks[p].getMatchGraphicX2(),
							blocks[p].getMatchGraphicY2(), this);
					g.drawImage(pinkNote, blocks[p].getMatchGraphicX3(),
							blocks[p].getMatchGraphicY3(), this);
					g.drawImage(pinkNote, blocks[p].getMatchGraphicX4(),
							blocks[p].getMatchGraphicY4(), this);
				} else if (comboCount > 39) {
					g.drawImage(redNote, blocks[p].getMatchGraphicX1(),
							blocks[p].getMatchGraphicY1(), this);
					g.drawImage(redNote, blocks[p].getMatchGraphicX2(),
							blocks[p].getMatchGraphicY2(), this);
					g.drawImage(redNote, blocks[p].getMatchGraphicX3(),
							blocks[p].getMatchGraphicY3(), this);
					g.drawImage(redNote, blocks[p].getMatchGraphicX4(),
							blocks[p].getMatchGraphicY4(), this);
				} else if (comboCount > 29) {
					g.drawImage(orangeNote, blocks[p].getMatchGraphicX1(),
							blocks[p].getMatchGraphicY1(), this);
					g.drawImage(orangeNote, blocks[p].getMatchGraphicX2(),
							blocks[p].getMatchGraphicY2(), this);
					g.drawImage(orangeNote, blocks[p].getMatchGraphicX3(),
							blocks[p].getMatchGraphicY3(), this);
					g.drawImage(orangeNote, blocks[p].getMatchGraphicX4(),
							blocks[p].getMatchGraphicY4(), this);
				} else if (comboCount > 19) {
					g.drawImage(yellowNote, blocks[p].getMatchGraphicX1(),
							blocks[p].getMatchGraphicY1(), this);
					g.drawImage(yellowNote, blocks[p].getMatchGraphicX2(),
							blocks[p].getMatchGraphicY2(), this);
					g.drawImage(yellowNote, blocks[p].getMatchGraphicX3(),
							blocks[p].getMatchGraphicY3(), this);
					g.drawImage(yellowNote, blocks[p].getMatchGraphicX4(),
							blocks[p].getMatchGraphicY4(), this);
				} else if (comboCount > 9) {
					g.drawImage(greenNote, blocks[p].getMatchGraphicX1(),
							blocks[p].getMatchGraphicY1(), this);
					g.drawImage(greenNote, blocks[p].getMatchGraphicX2(),
							blocks[p].getMatchGraphicY2(), this);
					g.drawImage(greenNote, blocks[p].getMatchGraphicX3(),
							blocks[p].getMatchGraphicY3(), this);
					g.drawImage(greenNote, blocks[p].getMatchGraphicX4(),
							blocks[p].getMatchGraphicY4(), this);
				} else if (comboCount > 5) {
					g.drawImage(blueNote, blocks[p].getMatchGraphicX1(),
							blocks[p].getMatchGraphicY1(), this);
					g.drawImage(blueNote, blocks[p].getMatchGraphicX2(),
							blocks[p].getMatchGraphicY2(), this);
					g.drawImage(blueNote, blocks[p].getMatchGraphicX3(),
							blocks[p].getMatchGraphicY3(), this);
					g.drawImage(blueNote, blocks[p].getMatchGraphicX4(),
							blocks[p].getMatchGraphicY4(), this);
				} else {
					g.drawImage(blackNote, blocks[p].getMatchGraphicX1(),
							blocks[p].getMatchGraphicY1(), this);
					g.drawImage(blackNote, blocks[p].getMatchGraphicX2(),
							blocks[p].getMatchGraphicY2(), this);
					g.drawImage(blackNote, blocks[p].getMatchGraphicX3(),
							blocks[p].getMatchGraphicY3(), this);
					g.drawImage(blackNote, blocks[p].getMatchGraphicX4(),
							blocks[p].getMatchGraphicY4(), this);
				}

		}

		// Combo text blue > green > yellow > orange > red > pink

		if (comboCount > 49) {
			g.setColor(Color.MAGENTA);
			g.drawString(comboCount + " notes!!!!!!!", 270, 200);
		} else if (comboCount > 39) {
			g.setColor(Color.RED);
			g.drawString(comboCount + " notes!!!!!", 270, 200);
		} else if (comboCount > 29) {
			g.setColor(Color.ORANGE);
			g.drawString(comboCount + " notes!!!", 270, 200);
		} else if (comboCount > 19) {
			g.setColor(Color.YELLOW);
			g.drawString(comboCount + " notes!!", 270, 200);
		} else if (comboCount > 9) {
			g.setColor(Color.GREEN);
			g.drawString(comboCount + " notes!", 270, 200);
		} else if (comboCount > 1) {
			g.setColor(Color.BLUE);
			g.drawString(comboCount + " notes", 270, 200);
		} else
			g.clearRect(300, 200, 100, 20);

		// Block order display
		if (blockOrder1 == 1)
			g.drawImage(block1, 300, 50, this);
		else if (blockOrder1 == 2)
			g.drawImage(block2, 300, 50, this);
		else if (blockOrder1 == 3)
			g.drawImage(block3, 300, 50, this);
		else if (blockOrder1 == 4)
			g.drawImage(block4, 300, 50, this);
		else if (blockOrder1 == 5)
			g.drawImage(block5, 300, 50, this);

		if (blockOrder2 == 1)
			g.drawImage(block1, 250, 50, this);
		else if (blockOrder2 == 2)
			g.drawImage(block2, 250, 50, this);
		else if (blockOrder2 == 3)
			g.drawImage(block3, 250, 50, this);
		else if (blockOrder2 == 4)
			g.drawImage(block4, 250, 50, this);
		else if (blockOrder2 == 5)
			g.drawImage(block5, 250, 50, this);

		if (blockOrder3 == 1)
			g.drawImage(block1, 200, 50, this);
		else if (blockOrder3 == 2)
			g.drawImage(block2, 200, 50, this);
		else if (blockOrder3 == 3)
			g.drawImage(block3, 200, 50, this);
		else if (blockOrder3 == 4)
			g.drawImage(block4, 200, 50, this);
		else if (blockOrder3 == 5)
			g.drawImage(block5, 200, 50, this);

	}

	public void animate() {
		pinkNoteAnimate.update(10);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:
			if (player.isFrozen() == false)
				player.moveUp();
			break;

		case KeyEvent.VK_DOWN:
			if (player.isFrozen() == false)
				player.moveDown();
			break;

		case KeyEvent.VK_LEFT:
			if (player.isFrozen() == false)
				player.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			if (player.isFrozen() == false)
				player.moveRight();
			break;

		case KeyEvent.VK_SPACE:
			if (player.isFrozen() == false) {
				if (blocks[player.getPosition()].isFalling() == true) {
					// play error sound
					break;
				}

				if (player.getHoldBlock() == player.getPosition()) {
					player.setHoldBlock(36);
					blocks[player.getPosition()].setSelected(false);
					for (q = 36; q > 0; q--) {
						if (q % 6 == 0 && q != 36)
							System.out.print("\n");

						System.out.print(blocks[q - 1].getBlockColor() + " | ");
						if (q == 1)
							System.out.println("\n" + turnCount);

					}

				} else if (player.getHoldBlock() != player.getPosition()
						&& player.getHoldBlock() != 36) {
					switchBlock1 = blocks[player.getSwitchBlockPosition()]
							.getBlockType();
					switchBlock2 = blocks[player.getPosition()].getBlockType();
					switchSpot1 = player.getSwitchBlockPosition();
					switchSpot2 = player.getPosition();
					blocks[player.getHoldBlock()].setBlockType(blocks[player
							.getPosition()].getBlockType());
					blocks[player.getPosition()].setBlockType(switchBlock1);
					player.setHoldBlock(36);
					blocks[player.getSwitchBlockPosition()].setSelected(false);
					turnCount++;
					// what color are blocks changing to?
					if (turnCount % 3 == 0 && turnCount != 0) {
						if (color == 1)
							color = 2;
						else
							color = 1;
					}
				} else {
					player.setHoldBlock(player.getPosition());
					blocks[player.getPosition()].setSelected(true);
					player.setSwitchBlockPosition(player.getPosition());
				}

				break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		if (state == Gamestate.Running) {
			while (true) {

				for (x = 0; x < 36; x++) {
					blocks[x].update();
					// update lifebar
					if (turnCount % 3 == 0 && turnCount != 0) {
						// update performance.deltaPerformance with blockcombo
						// performance.update();
						//boost1.setDeltaBoost(comboCount);
						//boost1.update();
						// change comboCount to double
						// add block count - combocount* *3 to boost
						// change block count to double?
						// create new block count that resets.  active count
						// add extra blocks to boost
						//separate some updates by game mode

						blocks[x].setBlockColor(0);
					}
				}

				// prevents grabbing a falling block
				if (player.getHoldBlock() != 36
						&& blocks[player.getSwitchBlockPosition()].isFalling()) {
					player.setHoldBlock(36);
					blocks[player.getSwitchBlockPosition()].setSelected(false);
				}

				// 3 turn freeze
				if (level == Gamelevel.VS) {

					if (turnCount % 3 == 0) {
						for (x = 0; x < 36; x++) {
							if (blocks[x].isFalling()) {
								player.setFrozen(true);
								s = x;
								// works but may need to look again. may allow
								// early moves?
							}

							if (blocks[s].isFalling() == false)
								player.setFrozen(false);
						}

					}

				}
				// Check vertical match
				for (x = 0; x < 24; x++) {
					// Level Condition: Cannot match same type
					if (level == Gamelevel.L2) {
						if (blocks[x].getBlockType() != lastBlockType
								&& yMatch == true
								&& blocks[x].isFalling() == false
								&& blocks[x + 6].isFalling() == false
								&& blocks[x + 12].isFalling() == false
								&& blocks[x].getBlockType() == blocks[x + 6]
										.getBlockType()
								&& blocks[x + 6].getBlockType() == blocks[x + 12]
										.getBlockType()) {

							if (blocks[x].getMatch() == true
									|| blocks[x + 6].getMatch() == true
									|| blocks[x + 12].getMatch() == true) {

								matchCount -= 1;
								comboCount -= 1;
								blockCount -= 3;

								if (blocks[x].getMatch() == false)
									blockCount++;
								if (blocks[x + 6].getMatch() == false)
									blockCount++;
								if (blocks[x + 12].getMatch() == false)
									blockCount++;

							}

							blocks[x].setMatch(true);
							blocks[x + 6].setMatch(true);
							blocks[x + 12].setMatch(true);
							blocks[x].setMatchGraphic(true);
							blocks[x + 6].setMatchGraphic(true);
							blocks[x + 12].setMatchGraphic(true);
							blocks[x].setyMatch(true);
							blocks[x + 6].setyMatch(true);
							blocks[x + 12].setyMatch(true);

							matchCount += 1;
							comboCount += 1;
							blockCount += 3;

							if (switchSpot1 != 36 && switchSpot2 != 36
									&& blocks[switchSpot1].getMatch()
									&& blocks[switchSpot2].getMatch())
								if (switchBlock1 == blockOrder1
										&& switchBlock2 == blockOrder2
										&& switchSpot2 > switchSpot1
										|| switchBlock1 == blockOrder2
										&& switchBlock2 == blockOrder1
										&& switchSpot1 > switchSpot2) {
									System.out.println("test2");
									blockOrderComboCount = blockOrderComboCount + 2;

									blockOrder1 = blockOrder3;
									blockOrder2 = random.nextInt(5) + 1;
									blockOrder3 = random.nextInt(5) + 1;
									while (blockOrder2 == blockOrder1)
										blockOrder2 = random.nextInt(5) + 1;
									while (blockOrder3 == blockOrder1
											|| blockOrder3 == blockOrder2)
										blockOrder3 = random.nextInt(5) + 1;

									switchSpot1 = 36;
									switchSpot2 = 36;
								}

							if (blocks[x].getBlockType() == blockOrder1) {
								lastBlockOrder = blockOrder1;
								blockOrder1 = blockOrder2;
								blockOrder2 = blockOrder3;
								blockOrder3 = random.nextInt(5) + 1;
								while (blockOrder3 == blockOrder1
										|| blockOrder3 == blockOrder2)
									blockOrder3 = random.nextInt(5) + 1;
								blockOrderComboCount += 1;
								System.out.println("blockorder count ="
										+ blockOrderComboCount);
								blockOrderMiss = 0;
							} else
								blockOrderMiss += 1;

							if (blockOrderMiss == 5)
								blockOrderComboCount = 0;

							System.out.println("combo count =" + comboCount);
							// System.out.println("block count =" + blockCount);

							lastBlockType = blocks[x].getBlockType();
						}
					}

					else if (yMatch == true
							&& blocks[x].isFalling() == false
							&& blocks[x + 6].isFalling() == false
							&& blocks[x + 12].isFalling() == false
							&& blocks[x].getBlockType() == blocks[x + 6]
									.getBlockType()
							&& blocks[x + 6].getBlockType() == blocks[x + 12]
									.getBlockType()) {

						if (blocks[x].getMatch() == true
								|| blocks[x + 6].getMatch() == true
								|| blocks[x + 12].getMatch() == true) {

							matchCount -= 1;
							comboCount -= 1;
							blockCount -= 3;

							if (blocks[x].getMatch() == false)
								blockCount++;
							if (blocks[x + 6].getMatch() == false)
								blockCount++;
							if (blocks[x + 12].getMatch() == false)
								blockCount++;

						}

						blocks[x].setMatch(true);
						blocks[x + 6].setMatch(true);
						blocks[x + 12].setMatch(true);
						blocks[x].setMatchGraphic(true);
						blocks[x + 6].setMatchGraphic(true);
						blocks[x + 12].setMatchGraphic(true);
						blocks[x].setyMatch(true);
						blocks[x + 6].setyMatch(true);
						blocks[x + 12].setyMatch(true);
						matchCount += 1;
						comboCount += 1;
						blockCount += 3;

						if (switchSpot1 != 36 && switchSpot2 != 36
								&& blocks[switchSpot1].getMatch()
								&& blocks[switchSpot2].getMatch())
							if (switchBlock1 == blockOrder1
									&& switchBlock2 == blockOrder2
									&& switchSpot2 > switchSpot1
									|| switchBlock1 == blockOrder2
									&& switchBlock2 == blockOrder1
									&& switchSpot1 > switchSpot2) {
								System.out.println("test2");
								blockOrderComboCount = blockOrderComboCount + 2;

								blockOrder1 = blockOrder3;
								blockOrder2 = random.nextInt(5) + 1;
								blockOrder3 = random.nextInt(5) + 1;
								while (blockOrder2 == blockOrder1)
									blockOrder2 = random.nextInt(5) + 1;
								while (blockOrder3 == blockOrder1
										|| blockOrder3 == blockOrder2)
									blockOrder3 = random.nextInt(5) + 1;

								switchSpot1 = 36;
								switchSpot2 = 36;
							}

						if (blocks[x].getBlockType() == blockOrder1) {
							lastBlockOrder = blockOrder1;
							blockOrder1 = blockOrder2;
							blockOrder2 = blockOrder3;
							blockOrder3 = random.nextInt(5) + 1;
							while (blockOrder3 == blockOrder1
									|| blockOrder3 == blockOrder2)
								blockOrder3 = random.nextInt(5) + 1;
							blockOrderComboCount += 1;
							System.out.println("blockorder count ="
									+ blockOrderComboCount);
							blockOrderMiss = 0;
						} else
							blockOrderMiss += 1;

						if (blockOrderMiss == 5)
							blockOrderComboCount = 0;

						System.out.println("combo count =" + comboCount);
						// System.out.println("block count =" + blockCount);

						// Level Condition: Alternate match
						if (level == Gamelevel.L6) {
							yMatch = false;
							xMatch = true;
						}
						lastBlockType = blocks[x].getBlockType();
					}
				}

				// Check horizontal match

				for (x = 0; x < 34; x++) {
					// Level Condition: Cannot match same type
					if (level == Gamelevel.L2) {
						if (blocks[x].getBlockType() != lastBlockType
								&& xMatch == true
								&& blocks[x].isFalling() == false
								&& blocks[x + 1].isFalling() == false
								&& blocks[x + 2].isFalling() == false
								&& x != 4
								&& x != 5
								&& x != 10
								&& x != 11
								&& x != 16
								&& x != 17
								&& x != 22
								&& x != 23
								&& x != 28
								&& x != 29
								&& x != 34
								&& x != 35
								&& blocks[x].getBlockType() == blocks[x + 1]
										.getBlockType()
								&& blocks[x + 1].getBlockType() == blocks[x + 2]
										.getBlockType()) {

							if (blocks[x].getMatch() == true
									|| blocks[x + 1].getMatch() == true
									|| blocks[x + 2].getMatch() == true) {

								matchCount -= 1;
								comboCount -= 1;
								blockCount -= 3;

								if (blocks[x].getMatch() == false)
									blockCount++;
								if (blocks[x + 1].getMatch() == false)
									blockCount++;
								if (blocks[x + 2].getMatch() == false)
									blockCount++;

							}

							blocks[x].setMatch(true);
							blocks[x + 1].setMatch(true);
							blocks[x + 2].setMatch(true);
							blocks[x].setMatchGraphic(true);
							blocks[x + 1].setMatchGraphic(true);
							blocks[x + 2].setMatchGraphic(true);
							blocks[x].setxMatch(true);
							blocks[x + 1].setxMatch(true);
							blocks[x + 2].setxMatch(true);
							matchCount += 1;
							comboCount += 1;
							blockCount += 3;

							if (switchSpot1 != 36 && switchSpot2 != 36
									&& blocks[switchSpot1].getMatch()
									&& blocks[switchSpot2].getMatch()) {
								if (switchBlock1 == blockOrder1
										&& switchBlock2 == blockOrder2
										&& switchSpot2 > switchSpot1
										|| switchBlock1 == blockOrder2
										&& switchBlock2 == blockOrder1
										&& switchSpot1 > switchSpot2) {
									System.out.println("test2");
									blockOrderComboCount = blockOrderComboCount + 2;

									blockOrder1 = blockOrder3;
									blockOrder2 = random.nextInt(5) + 1;
									blockOrder3 = random.nextInt(5) + 1;
									while (blockOrder2 == blockOrder1)
										blockOrder2 = random.nextInt(5) + 1;
									while (blockOrder3 == blockOrder1
											|| blockOrder3 == blockOrder2)
										blockOrder3 = random.nextInt(5) + 1;

									switchSpot1 = 36;
									switchSpot2 = 36;
								}

								else if (blocks[switchSpot1].isyMatch()
										&& blocks[switchSpot2].isxMatch()
										&& switchBlock1 == blockOrder1
										&& switchSpot1 > switchSpot2
										&& switchBlock2 == blockOrder2
										|| blocks[switchSpot2].isyMatch()
										&& blocks[switchSpot1].isxMatch()
										&& switchBlock2 == blockOrder1
										&& switchSpot2 > switchSpot1
										&& switchBlock1 == blockOrder2) {
									System.out.println("test3");
									blockOrderComboCount = blockOrderComboCount + 2;

									blockOrder1 = blockOrder3;
									blockOrder2 = random.nextInt(5) + 1;
									blockOrder3 = random.nextInt(5) + 1;
									while (blockOrder2 == blockOrder1)
										blockOrder2 = random.nextInt(5) + 1;
									while (blockOrder3 == blockOrder1
											|| blockOrder3 == blockOrder2)
										blockOrder3 = random.nextInt(5) + 1;

									switchSpot1 = 36;
									switchSpot2 = 36;
								}
							}

							if (blocks[x].getBlockType() == blockOrder1) {
								lastBlockOrder = blockOrder1;
								blockOrder1 = blockOrder2;
								blockOrder2 = blockOrder3;
								blockOrder3 = random.nextInt(5) + 1;
								while (blockOrder3 == blockOrder1
										|| blockOrder3 == blockOrder2)
									blockOrder3 = random.nextInt(5) + 1;
								blockOrderComboCount += 1;
								System.out.println("blockorder count ="
										+ blockOrderComboCount);
								blockOrderMiss = 0;
							} else
								blockOrderMiss += 1;

							if (blockOrderMiss == 5)
								blockOrderComboCount = 0;

							System.out.println("combo count =" + comboCount);
							// System.out.println("block count =" + blockCount);

							lastBlockType = blocks[x].getBlockType();
						}
					}

					else if (xMatch == true
							&& blocks[x].isFalling() == false
							&& blocks[x + 1].isFalling() == false
							&& blocks[x + 2].isFalling() == false
							&& x != 4
							&& x != 5
							&& x != 10
							&& x != 11
							&& x != 16
							&& x != 17
							&& x != 22
							&& x != 23
							&& x != 28
							&& x != 29
							&& x != 34
							&& x != 35
							&& blocks[x].getBlockType() == blocks[x + 1]
									.getBlockType()
							&& blocks[x + 1].getBlockType() == blocks[x + 2]
									.getBlockType()) {

						if (blocks[x].getMatch() == true
								|| blocks[x + 1].getMatch() == true
								|| blocks[x + 2].getMatch() == true) {

							matchCount -= 1;
							comboCount -= 1;
							blockCount -= 3;

							if (blocks[x].getMatch() == false)
								blockCount++;
							if (blocks[x + 1].getMatch() == false)
								blockCount++;
							if (blocks[x + 2].getMatch() == false)
								blockCount++;

						}

						blocks[x].setMatch(true);
						blocks[x + 1].setMatch(true);
						blocks[x + 2].setMatch(true);
						blocks[x].setMatchGraphic(true);
						blocks[x + 1].setMatchGraphic(true);
						blocks[x + 2].setMatchGraphic(true);
						blocks[x].setxMatch(true);
						blocks[x + 1].setxMatch(true);
						blocks[x + 2].setxMatch(true);
						matchCount += 1;
						comboCount += 1;
						blockCount += 3;

						if (switchSpot1 != 36 && switchSpot2 != 36
								&& blocks[switchSpot1].getMatch()
								&& blocks[switchSpot2].getMatch()) {
							if (switchBlock1 == blockOrder1
									&& switchBlock2 == blockOrder2
									&& switchSpot2 > switchSpot1
									|| switchBlock1 == blockOrder2
									&& switchBlock2 == blockOrder1
									&& switchSpot1 > switchSpot2) {
								System.out.println("test2");
								blockOrderComboCount = blockOrderComboCount + 2;

								blockOrder1 = blockOrder3;
								blockOrder2 = random.nextInt(5) + 1;
								blockOrder3 = random.nextInt(5) + 1;
								while (blockOrder2 == blockOrder1)
									blockOrder2 = random.nextInt(5) + 1;
								while (blockOrder3 == blockOrder1
										|| blockOrder3 == blockOrder2)
									blockOrder3 = random.nextInt(5) + 1;

								switchSpot1 = 36;
								switchSpot2 = 36;
							}

							else if (blocks[switchSpot1].isyMatch()
									&& blocks[switchSpot2].isxMatch()
									&& switchBlock1 == blockOrder1
									&& switchSpot1 > switchSpot2
									&& switchBlock2 == blockOrder2
									|| blocks[switchSpot2].isyMatch()
									&& blocks[switchSpot1].isxMatch()
									&& switchBlock2 == blockOrder1
									&& switchSpot2 > switchSpot1
									&& switchBlock1 == blockOrder2) {
								System.out.println("test3");
								blockOrderComboCount = blockOrderComboCount + 2;

								blockOrder1 = blockOrder3;
								blockOrder2 = random.nextInt(5) + 1;
								blockOrder3 = random.nextInt(5) + 1;
								while (blockOrder2 == blockOrder1)
									blockOrder2 = random.nextInt(5) + 1;
								while (blockOrder3 == blockOrder1
										|| blockOrder3 == blockOrder2)
									blockOrder3 = random.nextInt(5) + 1;

								switchSpot1 = 36;
								switchSpot2 = 36;
							}
						}

						if (blocks[x].getBlockType() == blockOrder1) {
							lastBlockOrder = blockOrder1;
							blockOrder1 = blockOrder2;
							blockOrder2 = blockOrder3;
							blockOrder3 = random.nextInt(5) + 1;
							while (blockOrder3 == blockOrder1
									|| blockOrder3 == blockOrder2)
								blockOrder3 = random.nextInt(5) + 1;
							blockOrderComboCount += 1;
							System.out.println("blockorder count ="
									+ blockOrderComboCount);
							blockOrderMiss = 0;
						} else
							blockOrderMiss += 1;

						if (blockOrderMiss == 5)
							blockOrderComboCount = 0;

						System.out.println("combo count =" + comboCount);
						// System.out.println("block count =" + blockCount);

						// Level Condition: Alternate match
						if (level == Gamelevel.L6) {
							yMatch = true;
							xMatch = false;
						}
						lastBlockType = blocks[x].getBlockType();
					}
				}

				// activate matches

				for (x = 0; x < 36; x++) {
					if (blocks[x].getMatch() == true) {
						blocks[x].setBlockColor(color);
						for (y = x, z = 0; y < 30; y += 6) {
							if (blocks[y + 6].getMatch() == true) {
								blocks[y + 6].setBlockColor(color);
								z += 6;
							} else {
								if (blocks[y + 6].isFalling() == true)
									blocks[y - z].setCenterY(blocks[y + 6]
											.getCenterY());
								else
									blocks[y - z].setCenterY(blocks[y + 6]
											.getOldCenterY());
								// set fall speed
								// blocks[y - z].setSpeedY(speedY);
								blocks[y - z].setFalling(true);
								blocks[y - z].setBlockType(blocks[y + 6]
										.getBlockType());
								blocks[y - z].setMatch(false);
								blocks[y - z].setxMatch(false);
								blocks[y - z].setyMatch(false);
							}

						}

						z = y - z;
						y = 226;

						while (z < 36) {
							blocks[z].setFalling(true);
							blocks[z].setCenterY(y);
							y -= 60;
							blocks[z].generateBlockType();
							blocks[z].setMatch(false);
							blocks[z].setxMatch(false);
							blocks[z].setyMatch(false);
							z += 6;
						}
					}
				}

				// reset combo count
				for (x = 0; x < 36; x++) {
					if (blocks[x].isFalling()) {
						x = 35;
						break;
					}

					else if (x == 35) {
						comboCount = 0;
						blockOrderComboCount = 0;
						blockOrderMiss = 0;
					}
				}

				// Level Win Conditions

				if (level == Gamelevel.L1) {
					if (blockOrderComboCount == 10) {
						state = Gamestate.Win;
					}
				}

				if (level == Gamelevel.L2) {
					if (blockOrderComboCount == 10) {
						state = Gamestate.Win;
					}
				}

				if (level == Gamelevel.L4) {
					yMatch = false;
					if (blockOrderComboCount == 10) {
						state = Gamestate.Win;
					}
				}

				if (level == Gamelevel.L5) {
					xMatch = false;
					if (blockOrderComboCount == 10) {
						state = Gamestate.Win;
					}
				}

				if (level == Gamelevel.L6) {
					if (blockOrderComboCount == 10) {
						state = Gamestate.Win;
					}
				}

				if (state == Gamestate.Win) {
					xMatch = false;
					yMatch = false;
					player.setFrozen(true);

					// for (x = 0; x < 36; x++) { blocks[x].setBlockType(6); }

				}

				repaint();
				// animate();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

		// System.out.println("match count total =" + matchCount);
		// matchCount = 0;

		// System.out.println("block count total =" + blockCount);
		// blockCount = 0;

	}
}
