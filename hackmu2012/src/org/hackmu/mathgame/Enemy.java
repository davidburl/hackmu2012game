package org.hackmu.mathgame;

public interface Enemy {
	
	public Problem[] getProblems();
	
	public String getName();
	
	public int getIconID();
	
	public int getMaxHP();
	
	public void setHP(int hp);
	
	public int getHP();
}
