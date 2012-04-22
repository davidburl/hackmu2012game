package org.hackmu.mathgame;

public class Player {
	private int mPoints = 10;
	private int mIconID = R.id.playerImage;
	private int mCurrentHP = 100;
	private int mMaxHP = 100;

	public Player() {
		// nothing
	}

	public int getPoints() {
		return this.mPoints;
	}
	
	public void setPoints(int newPoints) {
		this.mPoints = newPoints;
	}

	public int getIconID() {
		return this.mIconID;
	}
	
	public void setIconID(int newIconID) {
		this.mIconID = newIconID;
	}

	public int getCurrentHP() {
		return this.mCurrentHP;
	}

	public void setCurrentHP(int newCurrentHP) {
		if(newCurrentHP <= mMaxHP){
			this.mCurrentHP = newCurrentHP;
		}
		else {
			this.mCurrentHP = this.mMaxHP;
		}
	}
	
	public int getMaxHP() {
		return this.mMaxHP;
	}
	 
	public void setMaxHP(int newMaxHP) {
		this.mMaxHP = newMaxHP;
	}
}
