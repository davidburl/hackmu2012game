package org.hackmu.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Hackmu2012Activity extends Activity implements OnClickListener {
	
	Button answerOne, answerTwo, answerThree, answerFour, storeOne, storeTwo;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        answerOne = (Button)findViewById(R.id.answerOne);
        answerTwo = (Button)findViewById(R.id.answerTwo);
        answerThree = (Button)findViewById(R.id.answerThree);
        answerFour = (Button)findViewById(R.id.answerFour);
        
        
        answerOne.setOnClickListener(this);
    }
    
   
    
    public void onClick(View src) {
    	
    }
}
