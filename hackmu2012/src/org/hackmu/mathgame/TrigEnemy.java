package org.hackmu.mathgame;

public class TrigEnemy implements Enemy {

	private Problem[] mProblems = new Problem[3];
	private String mName;
	private int mIconID;

	public TrigEnemy() {
		this.mName = "Trigonometry Triforce";
		this.mProblems[0] = new Problem("sin b / tab b = ?", "arctan b",
				"cos b", "Bill Cosby", "tan b", 1, 20);
		this.mProblems[1] = new Problem("1 = ?", "(sin x)^2 + (cos x)^2",
				"(tan x)^2 + (cot x)^2", "arctan x", "log 0", 0, 10);
		this.mProblems[2] = new Problem("sin x = ?", "opposite / adjacent",
				"adjacent / opposite", "nyan / cat", "(arcsin x)^2", 0, 12);
		this.mProblems[3] = new Problem("(1/2)(a)(b)(sin C) = ?", "Perimeter",
				"a^2 + b^2", "Area", "Yoshi", 2, 42);
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
}
