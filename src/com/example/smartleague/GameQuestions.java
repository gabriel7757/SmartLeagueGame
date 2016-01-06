package com.example.smartleague;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.globaldata.Question;
import com.swipecards.models.CardModel;
import com.swipecards.view.CardContainer;
import com.swipecards.view.SimpleCardStackAdapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.graphics.drawable.DrawableCompat;



public class GameQuestions extends Activity {
	
	 /**
     * This variable is the container that will host our cards
     */
	private CardContainer mCardContainer;
	TextView textViewTime;
	TextView textViewprueba;
	
	
	 private Button buttonQuestion;
	 private Button buttonOption1;
	 private Button buttonOption2;
	 private Button buttonOption3;
	 private Button buttonOption4;
	 
	private  ArrayList<Question> questions;

	private CounterClass counter;
	static float j;
	private int counterQuestion=0;
	private int points=0;
	private boolean popupWindow=false;
	private int lenghtQuestions=0;
	@Override
	//generate events and set the values to the itmes on the screen
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gamequestions);		
		
		textViewTime=(TextView) findViewById(R.id.textTimer);
		
		
		textViewTime.setText("01:00");
		
		counter = new CounterClass(60000,1000);
		counter.mystart();
		
		 buttonQuestion=(Button) findViewById(R.id.titlequestion);
		 buttonOption1=(Button) findViewById(R.id.option1);
		 buttonOption2=(Button) findViewById(R.id.option2);
		 buttonOption3=(Button) findViewById(R.id.option3);
		 buttonOption4=(Button) findViewById(R.id.option4);
		
		
		QuestionsData myglobal = new QuestionsData();
		questions=myglobal.getGlobalVariable();
		lenghtQuestions=questions.size()-1;
		
		processClickQuestion();
		
		
		buttonOption1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub				
					processClickQuestion(buttonOption1);		
				
			}
		});
		
	
		buttonOption2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
					processClickQuestion(buttonOption2);				
				
			}
		});
		
		buttonOption3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
					processClickQuestion(buttonOption3);
								
			}
		});
		
		
		
		buttonOption4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
					processClickQuestion(buttonOption4);
				
				
			}
		});
		
		
		
		
		
		
		
		
		//CODING FOR SWIPING CARDS
		
		/*		
		this.getResources().getDrawable(R.drawable.picture1,null);	
		

		mCardContainer = (CardContainer) findViewById(R.id.layoutview);

		Resources r = getResources();

		
		SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

		adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
		adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
		adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
	

        CardModel cardModel = new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1));
        cardModel.setOnClickListener(new CardModel.OnClickListener() {
           @Override
           public void OnClickListener() {
               Log.i("Swipeable Cards","I am pressing the card");
           }
        });

        cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards","I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards","I dislike the card");
            }
        });

        adapter.add(cardModel);

		mCardContainer.setAdapter(adapter);
*/	
			
   }
	
	
	//this method handle the TEXT of the question and the answers
	public void processClickQuestion(Button... button){
		Question myquestion=questions.get(counterQuestion);
		if(button.length>0){			
			
			if(button[0].getText().toString().equalsIgnoreCase(myquestion.getAnswer())){
				points++;	
				Toast.makeText(getBaseContext(),"texxto boton  "+button[0].getText().toString()+" y respuesta: "+myquestion.getAnswer()+ "Puntos: "+points ,Toast.LENGTH_LONG).show();
			}
			
			if(counterQuestion<lenghtQuestions){
				counterQuestion++;
				myquestion=questions.get(counterQuestion);
				changeTextButtons(myquestion);
			}
			else if(counterQuestion>=lenghtQuestions){
				Intent intent = new Intent(GameQuestions.this, PopUpWindow.class);
				startActivity(intent);
				popupWindow=true;
				//counter.finnishTimer();
				
			}
			
			
			
			
		}else{
				
			changeTextButtons(myquestion);		
				
		}		
	    
		
		
		
	}
	
	//change the text of the questions	
	public void changeTextButtons(Question myquestion){
		buttonQuestion.setText(myquestion.getQuestion());
		buttonOption1.setText(myquestion.getOption1());
		buttonOption2.setText(myquestion.getOption2());
		buttonOption3.setText(myquestion.getOption3());
		buttonOption4.setText(myquestion.getOption4());		
	
	}
	
	//Counter class for the watch  in the screen
	public class CounterClass {

		private long millisInFuture;
	    private long countDownInterval;
	    
	     Handler handler;
	     Runnable counter;
		public CounterClass(long pMillisInFuture, long pCountDownInterval) {
			this.millisInFuture = pMillisInFuture;
            this.countDownInterval = pCountDownInterval;
		}

	
		
		//this method does the countdown in the clock 
		public void mystart(){
			
				handler = new Handler();
		        Log.v("status", "starting");
		        counter = new Runnable(){

		            public void run(){		            	
		            	
		            		//a popupwindow opens when the clock strikes 0 seconds
				                if(millisInFuture <= 0) {
				                    Log.e("status", "done");
				                    if(popupWindow==false){
					                    Intent intent = new Intent(GameQuestions.this, PopUpWindow.class);
					        			startActivity(intent);
				                    }
				                }
				                //if the countdown is equal or les than 10 seconds, the watch starts to blink
				                else if(millisInFuture<=10000){
				                	long sec = millisInFuture/1000;
				                    Log.e("status poco tiempo: ", Long.toString(sec) + " seconds remain");
				                    millisInFuture -= countDownInterval;
				                    handler.postDelayed(this, countDownInterval);
				                    String hsm=	String.format("%02d:%02d",TimeUnit.MILLISECONDS.toMinutes(millisInFuture) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisInFuture)),
				        					TimeUnit.MILLISECONDS.toSeconds(millisInFuture)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisInFuture)));
				                    Log.e("CountDown en menos de 10 segundos",String.valueOf(countDownInterval));
				                    if(popupWindow==false)
				                    	textViewTime.setText(hsm);
				                   
				                }//makes the the countdown until 10 seconds left		                
				                else {
				                    long sec = millisInFuture/1000;
				                    Log.e("status", Long.toString(sec) + " seconds remain");
				                    millisInFuture -= countDownInterval;
				                    handler.postDelayed(this, countDownInterval);//we call the same method with 1 less second than the last iteration
				                    String hsm=	String.format("%02d:%02d",TimeUnit.MILLISECONDS.toMinutes(millisInFuture) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisInFuture)),
				        					TimeUnit.MILLISECONDS.toSeconds(millisInFuture)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisInFuture)));
				                    Log.e("CountDowninterval, millis: "+millisInFuture,String.valueOf(countDownInterval));
				                    if(popupWindow==false)
				                    	textViewTime.setText(hsm);
				                    
				                 
				                    
				                }
		                
		            	}
		            
		        };
		        handler.postDelayed(counter, countDownInterval);
			
		}
		
		
		void startRepeatingTask()
		{
		    counter.run(); 
		}

		void stopRepeatingTask()
		{
		    handler.removeCallbacks(counter);
		}
		
		private void  finnishTimer()
		{
		    handler.removeCallbacks(counter);
		// close activity or whatever
		finish();
		}
	
		
		
	}
	    
	
	

}
