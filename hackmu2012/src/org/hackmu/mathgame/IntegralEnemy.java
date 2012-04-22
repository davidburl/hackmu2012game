package org.hackmu.mathgame;


public class IntegralEnemy implements Enemy {

	private Problem[] problems;
	private String name;
	private int iconID;
	
	private int maxHp;
	private int currHp;
	
	public IntegralEnemy(){
		problems = new Problem[4];
		problems[0] = new Problem("\u222Be^x dx = ?", "e^x", "x*(e^x)", "e^(2x)", "e^(0.5x)", 0, 10);
		problems[1] = new Problem("\u222Be^(5x) dx = ?", "e^x", "(1/5)e^(5x)", "e^(10x)", "e^(2.5x)", 1, 10);
		problems[2] = new Problem("\u222Bx dx = ?", "x", "e^x", "(x^2)/2", "x^0.5", 2, 10);
		problems[3] = new Problem("\u222B4 dx = ?", "e^4", "x^4", "4(ln|x|)", "4x", 3, 10);
		
		maxHp = 4;
		currHp = 4;
		
		name = "Integral";
		
		//iconID = R.id.integralImage;
	}
	
	public Problem[] getProblems(){
		return problems;
	}
	
	public String getName(){
		return name;
	}
	
	public int getIconID(){
		return iconID;
	}
	
	public int getMaxHP() {
		return maxHp;
	}
	
	public void setHP(int hp) {
		currHp = hp;
	}
	
	public int getHP() {
		return currHp;
	}
}
