package org.hackmu.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;


public class Hackmu2012Activity extends Activity implements OnClickListener {
	
	Button answerOneB, answerTwoB, answerThreeB, answerFourB, storeOneB, storeTwoB;
	ProgressBar enemyHealthPB, playerHealthPB, timeLeftPB;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        answerOneB = (Button)findViewById(R.id.answerOne);
        answerTwoB = (Button)findViewById(R.id.answerTwo);
        answerThreeB = (Button)findViewById(R.id.answerThree);
        answerFourB = (Button)findViewById(R.id.answerFour);
        
        enemyHealthPB = (ProgressBar)findViewById(R.id.enemyHealth);
        playerHealthPB = (ProgressBar)findViewById(R.id.playerHealth);
        
        answerOneB.setOnClickListener(this);
        answerTwoB.setOnClickListener(this);
        answerThreeB.setOnClickListener(this);
        answerFourB.setOnClickListener(this);
        
        enemyHealthPB.setMax(100);
        enemyHealthPB.setProgress(50);
    }
    
   
    
    public void onClick(View src) {
    	
    }
}
