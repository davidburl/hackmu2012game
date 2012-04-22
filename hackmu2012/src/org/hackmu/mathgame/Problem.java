package org.hackmu.mathgame;

public class Problem {
	private String[] answers;
	private boolean isCorrect;
	private int answerID;
	private String question;
	private int[] seconds;

	public Problem()	{
		isCorrect = false;
		
		seconds = new int[4];
		seconds[0] = 10;
		seconds[1] = 10;
		seconds[2] = 10;
		seconds[3] = 10;
		
		question = new String("Which name is the best?");
		
		answers = new String[4];
		answers[0] = new String("Matthew");
		answers[1] = new String("Bob");
		answers[2] = new String("Blululu");
		answers[3] = new String("Sakura");
		answerID = 1;
	}
	
	public Problem(String question, String answer1, int time1, String answer2, int time2, String answer3, int time3, String answer4, int time4, int answerID)	{
		isCorrect = false;
		
		seconds = new int[4];
		seconds[0] = time1;
		seconds[1] = time2;
		seconds[2] = time3;
		seconds[3] = time4;
		
		this.question = question;
		
		answers = new String[4];
		answers[0] = answer1;
		answers[1] = answer2;
		answers[2] = answer3;
		answers[3] = answer4;
		
		this.answerID = answerID;
		
	}
	
	public boolean getIsCorrect(){
		return isCorrect;
	}
	
	// return question
	public String getQuestion(){
		return question;
	}
	
	// getAnswer between 0 to 3
	public String getAnswer(int i){
		if(i <= 3 && i >= 0){
			return answers[i];
		}
		else{
			return "null";
		}
	}
	
	// return answerID
	public int getAnswerID(){
		return answerID;
	}
	
	// set question
	public void setQuestion(String question){
		this.question = question;
	}
	
	// set answer for button i (must be between 0 and 3)
	public void setAnswer(int i, String answer){
		if(i >= 0 && i <= 3){
			answers[i] = answer;
		}
	}
	
	public void setAnswerID(int i){
		if(i >=0 && i <= 3){
			answerID = i;
		}
	}
	
	public void setIsCorrect(boolean correct){
		isCorrect = correct;
	}
	
}
