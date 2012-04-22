package org.hackmu.mathgame;

import android.app.Activity;
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
	
	TextView timerText;
	Button answerOneB, answerTwoB, answerThreeB, answerFourB, storeOneB, storeTwoB;
	ProgressBar enemyHealthPB, playerHealthPB, timeLeftPB;
	
	
	int secondsLeft = 5*10;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        updateProgress = new Runnable() {
        	public void run() {
        		
        		if(secondsLeft <= 0) {
        			GameOver(true);
        		} else {
        	    	timerText.setText(Integer.toString((secondsLeft/10)+1));
        			secondsLeft--;
	        		timeLeftPB.setProgress(secondsLeft);
	        		mHandler.postDelayed(this, ONE_SECOND/10);
        		}
        	}
        };
        
        answerOneB = (Button)findViewById(R.id.answerOne);
        answerTwoB = (Button)findViewById(R.id.answerTwo);
        answerThreeB = (Button)findViewById(R.id.answerThree);
        answerFourB = (Button)findViewById(R.id.answerFour);
        
        timerText = (TextView)findViewById(R.id.txtTimeLeft);
        
        enemyHealthPB = (ProgressBar)findViewById(R.id.enemyHealth);
        playerHealthPB = (ProgressBar)findViewById(R.id.playerHealth);
        timeLeftPB = (ProgressBar)findViewById(R.id.timeLeft);
        
        answerOneB.setOnClickListener(this);
        answerTwoB.setOnClickListener(this);
        answerThreeB.setOnClickListener(this);
        answerFourB.setOnClickListener(this);
        
        
        startTimer();
    }
    
    
    // REMEMBER TO RESET secondsLeft before calling this
    public void startTimer() {
    	timerText.setText(Integer.toString((secondsLeft/10)+1));
    	timeLeftPB.setMax(secondsLeft);
		timeLeftPB.setProgress(secondsLeft);
    	mHandler.removeCallbacks(updateProgress);
    	mHandler.postDelayed(updateProgress, ONE_SECOND/10);
    }
    
    public void onClick(View src) {
    	
    }
    
    public void GameOver(boolean timeExpired) {
    	startTimer();
    }
    
}
