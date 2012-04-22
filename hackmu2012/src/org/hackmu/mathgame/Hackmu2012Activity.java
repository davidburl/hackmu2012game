package org.hackmu.mathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

// Yoshi WILL rule the world.  There is no escape...
public class Hackmu2012Activity extends Activity implements OnClickListener {

	public final static int ONE_SECOND = 1000;

	private Handler mHandler = new Handler();
	private Runnable updateProgress;

	TextView timerText, question;
	Button answerOneB, answerTwoB, answerThreeB, answerFourB, storeOneB,
			storeTwoB;
	ProgressBar enemyHealthPB, playerHealthPB, timeLeftPB;

	int secondsLeft = 15 * 10;
	
	//
	private int questionNumber;
	private Enemy enemy;
	private Player player;
	//

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		updateProgress = new Runnable() {
			public void run() {

				if (secondsLeft <= 0) {
					questionWrong(true);
				} else {
					timerText.setText(Integer.toString((secondsLeft / 10) + 1));
					secondsLeft--;
					timeLeftPB.setProgress(secondsLeft);
					mHandler.postDelayed(this, ONE_SECOND / 10);
				}
			}
		};

		question = (TextView) findViewById(R.id.questionText);
		
		answerOneB = (Button) findViewById(R.id.answerOne);
		answerTwoB = (Button) findViewById(R.id.answerTwo);
		answerThreeB = (Button) findViewById(R.id.answerThree);
		answerFourB = (Button) findViewById(R.id.answerFour);

		storeOneB = (Button) findViewById(R.id.moreTime);
		storeTwoB = (Button) findViewById(R.id.moreHP);

		timerText = (TextView) findViewById(R.id.txtTimeLeft);

		enemyHealthPB = (ProgressBar) findViewById(R.id.enemyHealth);
		playerHealthPB = (ProgressBar) findViewById(R.id.playerHealth);
		timeLeftPB = (ProgressBar) findViewById(R.id.timeLeft);

		answerOneB.setOnClickListener(this);
		answerTwoB.setOnClickListener(this);
		answerThreeB.setOnClickListener(this);
		answerFourB.setOnClickListener(this);

		storeOneB.setOnClickListener(this);
		storeTwoB.setOnClickListener(this);

		// 
		Log.d("Math","start");
		questionNumber = 0;
		enemy = new IntegralEnemy();
		player = new Player();
		updateButtons();
		//
		
		startTimer();
	}

	// REMEMBER TO RESET secondsLeft before calling this
	public void startTimer() {

		timeLeftPB.setMax(secondsLeft);
		timeLeftPB.setProgress(secondsLeft);
		mHandler.removeCallbacks(updateProgress);
		mHandler.postDelayed(updateProgress, ONE_SECOND / 10);
	}

	public void onClick(View src) {
		switch (src.getId()) {
		case R.id.answerOne:
			choseAnswer(0);
			break;
		case R.id.answerTwo:
			choseAnswer(1);
			break;
		case R.id.answerThree:
			choseAnswer(2);
			break;
		case R.id.answerFour:
			choseAnswer(3);
			break;
		case R.id.moreHP:
			purchasedHP();
			break;
		case R.id.moreTime:
			purchasedTime();
			break;
		}
	}
	
	public void choseAnswer(int answerChosen) {
		if(questionNumber < 4){
			if(answerChosen == enemy.getProblems()[questionNumber].getAnswerID()){
				player.setPoints(player.getPoints() + secondsLeft / 20 + 1);
				questionNumber++;
				
				updateButtons();
				
				if(questionNumber == 4){
					//enemy = new TrigEnemy();
					secondsLeft = enemy.getProblems()[0].getTime() * 10;
				}
				else{
					secondsLeft = enemy.getProblems()[questionNumber].getTime() * 10;
				}
				
				startTimer();
				
			}
			else{
				player.setPoints(player.getPoints() - 2);
			}
		}
		else if(questionNumber < 7){
			if(answerChosen == enemy.getProblems()[questionNumber - 4].getAnswerID()){
				player.setPoints(player.getPoints() + secondsLeft/20 + 1);
				questionNumber++;
				
				updateButtons();
				
				secondsLeft = enemy.getProblems()[questionNumber - 4].getTime() * 10;
				if(secondsLeft > 0){
					startTimer();
				}
			}
			else{
				player.setPoints(player.getPoints() - 2);
			}
		}
	}
	
	public void purchasedHP() {
		player.setCurrentHP(player.getCurrentHP() + 5);
	}
	
	///////////////////////////
	// testing purposes only //
	//      fix later        //
	///////////////////////////
	public void purchasedTime() {
		gameOver();
	}
	
	public void updateButtons(){
		int problemNumber = questionNumber;
		if(questionNumber >= 4){
			problemNumber -= 4;
		}
		answerOneB.setText(enemy.getProblems()[problemNumber].getAnswer(0));
		answerTwoB.setText(enemy.getProblems()[problemNumber].getAnswer(1));
		answerThreeB.setText(enemy.getProblems()[problemNumber].getAnswer(2));
		answerFourB.setText(enemy.getProblems()[problemNumber].getAnswer(3));
		
		question.setText(enemy.getProblems()[problemNumber].getQuestion());
		
	}

	public void questionWrong(boolean timeExpired) {
		player.setCurrentHP(player.getCurrentHP() - 5); // lost lots of points for not being able to answer in time.
		if(questionNumber < 4){
			secondsLeft = enemy.getProblems()[questionNumber].getTime() * 10;
			questionNumber++;
			if(questionNumber == 4){
				//enemy = new TrigEnemy();
			}
			updateButtons();
		}
		else if(questionNumber < 7){
			secondsLeft = enemy.getProblems()[questionNumber - 4].getTime() * 10;
		}
		else	{
			secondsLeft = 100;
		}
		startTimer();
	}
	
	public void gameOver() {
		double overall = 1;
		double trigScore = .76;
		double intScore = .25;
		
		Intent resultWin = new Intent(Hackmu2012Activity.this, results.class);
		Bundle b = new Bundle();
		b.putDouble("overall", overall);
		b.putDouble("trig", trigScore);
		b.putDouble("int", intScore);
		
		resultWin.putExtras(b);
		
		startActivity(resultWin);
		
	
	}

}
