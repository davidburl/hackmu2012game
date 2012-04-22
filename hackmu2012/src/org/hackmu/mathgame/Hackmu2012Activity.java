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

	public final static double MAX_POINTS = 62.0;
	
	public final static int ONE_SECOND = 1000;
	
	private int modifyBy;
	
	private boolean hasEnded = false;

	private Handler mHandler = new Handler();
	private Handler pHHandler = new Handler();
	private Handler eHHandler = new Handler();
	private Runnable updateProgress, playerHealthProgress, enemyHealthProgress;
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
	
	// animatedHealth: the health currently displayed by the progress bar
	// It is also a scale of 100 times that of the player health (for smoothness)
	int animatedPlayerHealth;
	int animatedEnemyHealth;

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

		playerHealthProgress = new Runnable() {
			public void run() {
				// modifyBy > 0, we are increasing player HP
				if(modifyBy > 0) {
					if((animatedPlayerHealth / 100) <= player.getCurrentHP() ) {
						animatedPlayerHealth +=modifyBy;
						playerHealthPB.setProgress(animatedPlayerHealth);
						pHHandler.postDelayed(this, ONE_SECOND / 100);
					}
				} else {
					if((animatedPlayerHealth / 100) >= player.getCurrentHP() ) {
						animatedPlayerHealth +=modifyBy;
						playerHealthPB.setProgress(animatedPlayerHealth);
						pHHandler.postDelayed(this, ONE_SECOND / 100);
					}
				}
			}
		};
		
		enemyHealthProgress = new Runnable() {
			public void run() {
				// modifyBy > 0, we are increasing player HP
				if(modifyBy > 0) {
					if((animatedEnemyHealth / 100) <= enemy.getHP() ) {
						animatedEnemyHealth += modifyBy;
						enemyHealthPB.setProgress(animatedEnemyHealth);
						eHHandler.postDelayed(this, ONE_SECOND / 100);
					}
				} else {
					if((animatedEnemyHealth / 100) >= enemy.getHP() ) {
						animatedEnemyHealth +=modifyBy;
						enemyHealthPB.setProgress(animatedEnemyHealth);
						eHHandler.postDelayed(this, ONE_SECOND / 100);
					}
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
		questionNumber = 0;
		enemy = new IntegralEnemy();
		player = new Player();
		updateButtons();
		//
		
		startTimer();
		
		playerHealthPB.setMax(player.getMaxHP()*100);
		playerHealthPB.setProgress(player.getMaxHP()*100);
		
		enemyHealthPB.setMax(enemy.getMaxHP()*100);
		enemyHealthPB.setProgress(enemy.getMaxHP()*100);
		
	}

	// REMEMBER TO RESET secondsLeft before calling this
	public void startTimer() {

		timeLeftPB.setMax(secondsLeft);
		timeLeftPB.setProgress(secondsLeft);
		mHandler.removeCallbacks(updateProgress);
		mHandler.postDelayed(updateProgress, ONE_SECOND / 10);
	}
	
	public void changePlayerHealth(int health) {
		// update progress to current player health in case we 
		// were in the process of a transition previously
		playerHealthPB.setProgress(player.getCurrentHP()*100);
		pHHandler.removeCallbacks(playerHealthProgress);
		animatedPlayerHealth = player.getCurrentHP()*100;
		if(health > player.getCurrentHP()) {
			modifyBy = 20;
		} else if(health < player.getCurrentHP()){
			modifyBy = -20;
		}
		player.setCurrentHP(health);
		mHandler.postDelayed(playerHealthProgress, ONE_SECOND / 100);
	}

	
	public void changeEnemyHealth(int health) {
		enemyHealthPB.setProgress(enemy.getHP()*100);
		pHHandler.removeCallbacks(enemyHealthProgress);
		animatedEnemyHealth = enemy.getHP()*100;
		if(health > enemy.getHP()) {
			modifyBy = 20;
		} else if(health < enemy.getHP()){
			modifyBy = -20;
		}
		enemy.setHP(health);
		eHHandler.postDelayed(enemyHealthProgress, ONE_SECOND / 100);
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
				changeEnemyHealth(enemy.getHP() - 1);
				questionNumber++;
				
				updateButtons();
				
				if(questionNumber == 4){
					enemy = new TrigEnemy();
					enemyHealthPB.setProgress(enemy.getHP()*100);
					
					secondsLeft = enemy.getProblems()[0].getTime() * 10;
				}
				else{
					secondsLeft = enemy.getProblems()[questionNumber].getTime() * 10;
				}
				
				startTimer();
				
			}
			else{
				changePlayerHealth(player.getCurrentHP() - 2);
			}
		}
		else if(questionNumber <= 7){
			if(answerChosen == enemy.getProblems()[questionNumber - 4].getAnswerID()){
				player.setPoints(player.getPoints() + secondsLeft / 20 + 1);
				questionNumber++;
				changeEnemyHealth(enemy.getHP() - 1);
				
				updateButtons();
				
				if(questionNumber < 8){
					secondsLeft = enemy.getProblems()[questionNumber - 4].getTime() * 10;
					if(secondsLeft > 0){
						startTimer();
					}
				}
			}
			else{
				changePlayerHealth(player.getCurrentHP() - 2);
			}
		}
	}
	
	public void purchasedHP() {
		changePlayerHealth(player.getCurrentHP() + 5);
	}
	
	///////////////////////////
	// testing purposes only //
	//      fix later        //
	///////////////////////////
	public void purchasedTime() {
		secondsLeft += 10;
	}
	
	public void updateButtons(){
		if(questionNumber > 7 || player.getCurrentHP() <= 0){
			gameOver();
		}
		else{
			int problemNumber;
			
			if(questionNumber < 4){
				problemNumber = questionNumber;
			}
			else{
				if(questionNumber == 4){
					enemy = new TrigEnemy();
					enemyHealthPB.setProgress(enemy.getHP()*100);
				}
				problemNumber = questionNumber - 4;
			}
			answerOneB.setText(enemy.getProblems()[problemNumber].getAnswer(0));
			answerTwoB.setText(enemy.getProblems()[problemNumber].getAnswer(1));
			answerThreeB.setText(enemy.getProblems()[problemNumber].getAnswer(2));
			answerFourB.setText(enemy.getProblems()[problemNumber].getAnswer(3));
			
			question.setText(enemy.getProblems()[problemNumber].getQuestion());
		}
	}

	public void questionWrong(boolean timeExpired) {
		changePlayerHealth(player.getCurrentHP() - 5); // lost lots of points for not being able to answer in time.
		if(questionNumber < 4){
			secondsLeft = enemy.getProblems()[questionNumber].getTime() * 10;
			questionNumber++;
			/*
			if(questionNumber == 4){
				enemy = new TrigEnemy();
			}*/
			updateButtons();
		}
		else if(questionNumber <= 7){
			secondsLeft = enemy.getProblems()[questionNumber - 4].getTime() * 10;
		}
		
		startTimer();
	}
	
	public void gameOver() {
		if(!hasEnded){
			hasEnded = true;
			
			double overall = player.getPoints();
			double trigScore = 15;
			double intScore = 5;
			
			Intent resultWin = new Intent(Hackmu2012Activity.this, results.class);
			Bundle b = new Bundle();
			b.putDouble("overall", overall);
			b.putDouble("trig", trigScore);
			b.putDouble("int", intScore);
			
			resultWin.putExtras(b);
			
			startActivity(resultWin);
			
			finish();
		}
	
	}

}
