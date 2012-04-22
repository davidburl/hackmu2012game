package org.hackmu.mathgame;

public class TrigEnemy implements Enemy {

	private Problem[] mProblems = new Problem[4];
	private String mName;
	private int mIconID;
	
	private int maxHp;
	private int currHp;

	public TrigEnemy() {
		this.mName = "Trigonometry Triforce";
		this.mProblems[0] = new Problem("sin b / tan b = ?", "arctan b",
				"cos b", "Bill Cosby", "tan b", 1, 20);
		this.mProblems[1] = new Problem("1 = ?", "(sin x)^2 + (cos x)^2",
				"(tan x)^2 + (cot x)^2", "arctan x", "log 0", 0, 10);
		this.mProblems[2] = new Problem("sin x = ?", "opposite / hypotenuse",
				"adjacent / opposite", "nyan / cat", "(arcsin x)^2", 0, 12);
		this.mProblems[3] = new Problem("(1/2)(a)(b)(sin C) = ?", "Perimeter",
				"a^2 + b^2", "Area of Triangle", "Yoshi", 2, 42);
		
		maxHp = 4;
		currHp = 4;
	}

	public Problem[] getProblems() {
		return this.mProblems;
	}

	public String getName() {
		return this.mName;
	}

	public int getIconID() {
		return this.mIconID;
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
