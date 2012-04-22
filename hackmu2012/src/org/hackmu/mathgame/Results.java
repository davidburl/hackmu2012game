package org.hackmu.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;



public class Results extends Activity {
	
	ProgressBar overall, trig, integral;
	
	public void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        
			//requestWindowFeature(Window.FEATURE_NO_TITLE);

	        
			setContentView(R.layout.results);
			
			Bundle b = getIntent().getExtras();
			
			overall = (ProgressBar) findViewById(R.id.mainProgress);
			trig = (ProgressBar) findViewById(R.id.trigProgress);
			integral = (ProgressBar) findViewById(R.id.integralBar);
			
			overall.setMax(1000);
			trig.setMax(1000);
			integral.setMax(1000);
			
			overall.setProgress((int) (b.getDouble("overall") * 1000));
			trig.setProgress((int) (b.getDouble("trig") * 1000) );
			integral.setProgress((int) (b.getDouble("int") * 1000));

	}
}
