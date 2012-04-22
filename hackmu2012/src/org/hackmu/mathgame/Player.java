package org.hackmu.mathgame;

public class Player {
	private int mPoints = 0;
	private int mIconID = R.id.playerImage;
	private int mCurrentHP = 20;
	private int mMaxHP = 20;

	public Player() {
		// nothing
	}

	public int getPoints() {
		return this.mPoints;
	}
	
	public void setPoints(int newPoints) {
		this.mPoints = newPoints;
		
		if(this.mPoints < 0){
			this.mPoints = 0;
		}
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
