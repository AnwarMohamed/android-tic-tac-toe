package com.example.tictactoe;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private ArrayList<ArrayList<TextView>> xoGrid;
	private int X_MARKER, O_MARKER, BOX_BACK, BOX_BORDER, nTurns, nTies, xWins, oWins;
	boolean xTurn; 
	String States;
	private MyApp app;
	DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		
		db = new DatabaseHandler(this);
		db.check();
		app = (MyApp)getApplication();  
                 
        if (db.getCount() != 1)
        {
        	db.delete();
    		app.setxMarker(Color.RED);
    		app.setoMarker(Color.GREEN);
    		app.setBack(Color.BLACK);
    		app.setBorder(Color.WHITE);
    		app.setStates("0-0-0-0-0-0-0-0-0-0-0-0-0-O");
    		db.addValues(app);
        }
        else
        {
        	List<MyApp> vList = db.getAllValues();         
    		X_MARKER = vList.get(0).getxMarker() != 0 ? app.setxMarker(vList.get(0).getxMarker()):app.setxMarker(Color.RED);	
    		O_MARKER = vList.get(0).getoMarker() != 0 ? app.setoMarker(vList.get(0).getoMarker()):app.setoMarker(Color.GREEN);
    		BOX_BACK = vList.get(0).getBack() != 0 ? app.setBack(vList.get(0).getBack()):app.setBack(Color.BLACK);
    		BOX_BORDER = vList.get(0).getBorder() != 0 ? app.setBorder(vList.get(0).getBorder()):app.setBorder(Color.WHITE);
    		States = vList.get(0).getStates().equals("") ? app.setStates("0-0-0-0-0-0-0-0-0-0-0-0-0-O") :app.setStates(vList.get(0).getStates());
        }

		
		X_MARKER = app.getxMarker();	
		O_MARKER = app.getoMarker();
		BOX_BACK = app.getBack();
		BOX_BORDER = app.getBorder();
		States = app.getStates();
		
		TextView t = (TextView)findViewById(R.id.turn_status);
		if (States.split("-")[13].equals("X"))
		{
			xTurn = true;
			t.setText("Players Turn: X");
		}
		else
		{
			xTurn = false;
			t.setText("Players Turn: O");
		}

		nTurns = Integer.parseInt(States.split("-")[9].toString());
		nTies = Integer.parseInt(States.split("-")[12].toString());
		xWins = Integer.parseInt(States.split("-")[10].toString());
		oWins = Integer.parseInt(States.split("-")[11].toString());
		
		t = (TextView)findViewById(R.id.ties);
		t.setText(" Tie: " + nTies);
		
		t = (TextView)findViewById(R.id.xwins);		
		t.setText("X Wins: " + xWins);	
		
		t = (TextView)findViewById(R.id.owins);
		t.setText(" O Wins: "+ oWins +" ");
		
		xoGrid = new ArrayList<ArrayList<TextView>>();
		
		ArrayList<TextView> xoGridRow1 = new ArrayList<TextView>();
		xoGridRow1.add((TextView)findViewById(R.id._1_1));
		xoGridRow1.add((TextView)findViewById(R.id._1_2));
		xoGridRow1.add((TextView)findViewById(R.id._1_3));
		xoGrid.add(xoGridRow1);
		
		ArrayList<TextView> xoGridRow2 = new ArrayList<TextView>();
		xoGridRow2.add((TextView)findViewById(R.id._2_1));
		xoGridRow2.add((TextView)findViewById(R.id._2_2));
		xoGridRow2.add((TextView)findViewById(R.id._2_3));
		xoGrid.add(xoGridRow2);
		
		ArrayList<TextView> xoGridRow3 = new ArrayList<TextView>();
		xoGridRow3.add((TextView)findViewById(R.id._3_1));
		xoGridRow3.add((TextView)findViewById(R.id._3_2));
		xoGridRow3.add((TextView)findViewById(R.id._3_3));
		xoGrid.add(xoGridRow3);
		
		ClearGrid();
		int n=0;
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
			{
				xoGrid.get(i).get(j).setText(States.split("-")[n++].equalsIgnoreCase("0") ? "" : States.split("-")[n-1]);
			}
		
		UpdateColors();
	}
	
	@Override
	public void onBackPressed() {
		db.delete();
		String bool = xTurn ?"X":"O";
		String fields = "";
		
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				fields += xoGrid.get(i).get(j).getText().equals("") ? "0" + "-": xoGrid.get(i).get(j).getText() + "-";
		
		app.setStates(fields + nTurns +"-"+ xWins +"-"+ oWins +"-"+ nTies+"-" + bool);
		db.addValues(app);
	    finish();
	    return;
	}  
	
	private void toggleTurn(TextView t)
	{
		TextView turnstatus = (TextView)findViewById(R.id.turn_status);
		if (xTurn)
		{
			t.setText("X");
			t.setTextColor(X_MARKER);
			turnstatus.setText("Players Turn: O");
			xTurn = false;
			
		}
		else
		{
			t.setText("O");
			t.setTextColor(O_MARKER);
			turnstatus.setText("Players Turn: X");
			xTurn = true;
		}
	}
	
	private void ClearGrid()
	{
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				xoGrid.get(i).get(j).setText("");
	}
	
	private void UpdateColors()
	{
		X_MARKER = app.getxMarker();	
		O_MARKER = app.getoMarker();
		BOX_BACK = app.getBack();
		BOX_BORDER = app.getBorder();
		
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
			{
				xoGrid.get(i).get(j).setBackgroundColor(BOX_BACK);
				
				if (xoGrid.get(i).get(j).getText().equals("X"))
					xoGrid.get(i).get(j).setTextColor(X_MARKER);
				else if (xoGrid.get(i).get(j).getText().equals("O"))
					xoGrid.get(i).get(j).setTextColor(O_MARKER);
			}
						
		TextView t = (TextView)findViewById(R.id.turn_status);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);
		
		t = (TextView)findViewById(R.id.ties);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);
		
		t = (TextView)findViewById(R.id.xwins);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);	
		
		t = (TextView)findViewById(R.id.owins);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);
		
		TableRow r = (TableRow)findViewById(R.id.tableRow1);
		r.setBackgroundColor(BOX_BORDER);
		r = (TableRow)findViewById(R.id.tableRow2);
		r.setBackgroundColor(BOX_BORDER);
		r = (TableRow)findViewById(R.id.tableRow3);
		r.setBackgroundColor(BOX_BORDER);
		r = (TableRow)findViewById(R.id.tableRow4);
		r.setBackgroundColor(BOX_BORDER);	
		r = (TableRow)findViewById(R.id.tableRow5);
		r.setBackgroundColor(BOX_BORDER);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus)
	{
		if (hasFocus)
		{
			UpdateColors();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		TextView t;
	    switch(item.getItemId()) {
	    case R.id.options:
	        Intent intent_options = new Intent(this, OptionsActivity.class);
	        this.startActivity(intent_options);
	        break;
	        
	    case R.id.clear_scores:
	    	nTies = 0;
	    	xWins = 0;
	    	oWins = 0;
	    	
			
			t = (TextView)findViewById(R.id.ties);
			t.setText(" Tie: 0");
			
			t = (TextView)findViewById(R.id.xwins);		
			t.setText("X Wins: 0");	
			
			t = (TextView)findViewById(R.id.owins);
			t.setText(" O Wins: 0 ");
	        break;
	        
	    case R.id.new_game:
	        ClearGrid();
	        nTurns = 0;
	        break;
	        
	    case R.id.help:
	        Intent intent_help = new Intent(this, HelpActivity.class);
	        this.startActivity(intent_help);
	        break;
	        
	    default:
	        return super.onOptionsItemSelected(item);
	    }

	    return true;
	}
	
	public boolean checkWin()
	{

		for (int i=0; i<3; i++)
			if ((xoGrid.get(i).get(0).getText() == xoGrid.get(i).get(1).getText()) && 
					(xoGrid.get(i).get(0).getText() == xoGrid.get(i).get(2).getText()) && xoGrid.get(i).get(2).getText() != "")
				return true;
		
		for (int j=0; j<3; j++)
			if ((xoGrid.get(0).get(j).getText() == xoGrid.get(1).get(j).getText()) && 
					(xoGrid.get(0).get(j).getText() == xoGrid.get(2).get(j).getText()) && xoGrid.get(2).get(j).getText() != "")
				return true;
		
		if ((xoGrid.get(0).get(0).getText() == xoGrid.get(1).get(1).getText()) && 
				(xoGrid.get(1).get(1).getText() == xoGrid.get(2).get(2).getText()) && xoGrid.get(2).get(2).getText() != "")
			return true;
		
		else if ((xoGrid.get(0).get(2).getText() == xoGrid.get(1).get(1).getText()) && 
				(xoGrid.get(2).get(0).getText() == xoGrid.get(1).get(1).getText()) && xoGrid.get(1).get(1).getText() != "")
			return true;
		
		else return false;
	}
	
	final Context context = this;
	public void onClick(View v) {
		
		final TextView t = (TextView)v;
		if (t.getText() == "")
		{
			nTurns++;
			toggleTurn(t);
			
			if (checkWin())
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle(t.getText() + " Won !");

				alertDialogBuilder
					.setMessage("Congratulations " + t.getText() + " Player ! Do you want to play again ?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {

							dialog.cancel();
							nTurns = 0;
							if (t.getText()=="X") 
							{
								xWins++;
								TextView wins = (TextView)findViewById(R.id.xwins);
								wins.setText("X Wins: " + xWins);
							} else {
								oWins++;
								TextView wins = (TextView)findViewById(R.id.owins);
								wins.setText(" O Wins: " + oWins + " ");								
							}
							ClearGrid();
						}
					});
				
				alertDialogBuilder
					.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							if (t.getText()=="X") 
							{
								xWins++;
							} else {
								oWins++;								
							}					        
					        nTurns = 0;
					        ClearGrid();

					        onBackPressed();
						}
					  });
	 
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
					return;
			}
			
			if (nTurns >= 9)
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Tie !!");

				alertDialogBuilder
					.setMessage("Do you want to play again ?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {

							dialog.cancel();
							nTies ++;
							nTurns = 0;
							TextView textTies = (TextView)findViewById(R.id.ties);
							textTies.setText(" Tie: " + nTies);
							ClearGrid();
						}
					})
					.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
					        nTurns = 0;
					        nTies ++;
					        ClearGrid();

					        onBackPressed();
							MainActivity.this.finish();
						}
					  });
	 
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();			
			}
		}
		
	}
	
	
}
