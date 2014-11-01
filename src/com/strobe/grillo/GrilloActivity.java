package com.strobe.grillo;


import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.strobe.grillo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GrilloActivity extends Activity {
	
	MediaPlayer soundChannel;
	Spinner cricketType;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        populateSpinner();
        
        // Default cricket
        soundChannel = MediaPlayer.create(this, R.raw.cricket01);
    	soundChannel.setLooping(true);
    }
    
    @Override
    public void onStart() {
    	super.onStart();
      	// Google Analytics tracking activated.
      	EasyTracker.getInstance().activityStart(this);
    }
    
    @Override
    public void onPause()
    {
    	super.onPause();
    	// Releasing resources of soundChannel
    	soundChannel.stop();
    }

    @Override
    public void onStop() {
    	super.onStop();
    	// Releasing resources of soundChannel
    	soundChannel.stop();
      	// Google Analytics tracking stopped.
      	EasyTracker.getInstance().activityStop(this);
    }

    
    public void playSound(View arg0)
    {
    	EasyTracker.getInstance().setContext(this);
    	Tracker gaTracker = EasyTracker.getTracker();
    	
    	if(soundChannel.isPlaying())
    	{
    		soundChannel.stop();
    	}
    	else
    	{
    		cricketType = (Spinner)findViewById(R.id.cricketType);
        	String type = String.valueOf(cricketType.getSelectedItem());
        	
        	// Play and track cricket type: Jardin
        	if(type.equals("Jardin")) { 
        		soundChannel = MediaPlayer.create(this, R.raw.cricket01); 
        		soundChannel.setLooping(true); 
        		gaTracker.trackEvent("Cricket", "Play", "Jardin", Long.decode("0"));
        	}
        	
        	// Play and track cricket type: Bosque
        	if(type.equals("Caricatura")) { 
        		soundChannel = MediaPlayer.create(this, R.raw.cricket02); 
        		soundChannel.setLooping(true);
        		gaTracker.trackEvent("Cricket", "Play", "Caricatura", Long.decode("0"));
        	}
        	
        	// Play and track cricket type: Rio
        	if(type.equals("Rio")) { 
        		soundChannel = MediaPlayer.create(this, R.raw.cricket03); 
        		soundChannel.setLooping(true); 
        		gaTracker.trackEvent("Cricket", "Play", "Rio", Long.decode("0"));
        	}
        	
        	soundChannel.start();
    	}
    	
    }
   
    public void populateSpinner()
    {
    	Spinner spinner = (Spinner)findViewById(R.id.cricketType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.crikets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}