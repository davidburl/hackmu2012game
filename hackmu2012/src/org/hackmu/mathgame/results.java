package org.hackmu.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;



public class results extends Activity {
	
	ProgressBar overall, trig, integral;
	
	public void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        
			requestWindowFeature(Window.FEATURE_NO_TITLE);

	        
			setContentView(R.layout.results);
			
			Bundle b = getIntent().getExtras();
			
			overall = (ProgressBar) findViewById(R.id.mainProgress);
			trig = (ProgressBar) findViewById(R.id.trigProgress);
			integral = (ProgressBar) findViewById(R.id.integralBar);
			
			overall.setMax((int)Hackmu2012Activity.MAX_POINTS);
			trig.setMax(42);
			integral.setMax(20);
			
			overall.setProgress((int) (b.getDouble("overall") / Hackmu2012Activity.MAX_POINTS * 40));
			trig.setProgress((int) (b.getDouble("trig") / 42 * 40));
			integral.setProgress((int) (b.getDouble("int") / 20 * 40));

	}
}
