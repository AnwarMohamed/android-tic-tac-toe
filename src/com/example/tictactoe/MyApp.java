package com.example.tictactoe;

import android.app.Application;

public class MyApp extends Application {
	
	int X_MARKER;
	int O_MARKER;
	int BOX_BACK;
	int BOX_BORDER;
	String STATES;
	
	public int setxMarker(int i) { X_MARKER = i; return X_MARKER;}	
	public int getxMarker() { return X_MARKER; }
	
	public int setoMarker(int i) { O_MARKER = i; return O_MARKER;}
	public int getoMarker()	{ return O_MARKER; }
	
	public int setBack(int i) { BOX_BACK = i; return BOX_BACK;}
	public int getBack() { return BOX_BACK; }
	
	public int setBorder(int i) { BOX_BORDER = i; return BOX_BORDER;}
	public int getBorder() { return BOX_BORDER; }
	
	public String setStates(String i) { STATES = i; return STATES; }
	public String getStates() { return STATES; }
}
