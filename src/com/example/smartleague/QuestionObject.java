package com.example.smartleague;

public class QuestionObject {
	
	private String question;
	private int myswitch;
	
	
	public QuestionObject(String question, int myswitch) {
		super();
		this.question = question;
		this.myswitch = myswitch;
	}
	//Getters and setters
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getMyswitch() {
		return myswitch;
	}
	public void setMyswitch(int myswitch) {
		this.myswitch = myswitch;
	}
	
	

}
