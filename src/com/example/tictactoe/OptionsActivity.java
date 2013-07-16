package com.example.tictactoe;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class OptionsActivity extends Activity {

	private int X_MARKER, O_MARKER, BOX_BACK, BOX_BORDER;
	private MyApp app;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		app = (MyApp)getApplication();
		X_MARKER = app.getxMarker();	
		O_MARKER = app.getoMarker();
		BOX_BACK = app.getBack();
		BOX_BORDER = app.getBorder();
		
		final ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		
		Button buttonBack = (Button) findViewById(R.id.btnCancel);
		buttonBack.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	OptionsActivity.this.finish();
		    }
		});
		
		Button buttonSave = (Button) findViewById(R.id.btnOk);
		buttonSave.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	saveColors();
		    	OptionsActivity.this.finish();
		    }
		});
		
		Spinner spinner1 = (Spinner)findViewById(R.id.spinnerBack);
		spinner1.setSelection(ColortoCode(CodetoColor(BOX_BACK), true));
		spinner1.setBackgroundColor(BOX_BORDER);
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        
		    	BOX_BACK = ColortoCode(parent.getItemAtPosition(pos).toString(), false);
		    }
		    
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		Spinner spinner2 = (Spinner)findViewById(R.id.SpinnerLine);
		spinner2.setSelection(ColortoCode(CodetoColor(BOX_BORDER), true));
		spinner2.setBackgroundColor(BOX_BORDER);
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        
		    	BOX_BORDER = ColortoCode(parent.getItemAtPosition(pos).toString(), false);
		    }
		    
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		Spinner spinner3 = (Spinner)findViewById(R.id.SpinnerOmarker);
		spinner3.setSelection(ColortoCode(CodetoColor(O_MARKER), true));
		spinner3.setBackgroundColor(BOX_BORDER);
		spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        
		    	O_MARKER = ColortoCode(parent.getItemAtPosition(pos).toString(), false);
		    }
		    
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		Spinner spinner4 = (Spinner)findViewById(R.id.SpinnerXmarker);
		spinner4.setSelection(ColortoCode(CodetoColor(X_MARKER), true));
		spinner4.setBackgroundColor(BOX_BORDER);
		spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        
		    	X_MARKER = ColortoCode(parent.getItemAtPosition(pos).toString(), false);
		    }
		    
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		TextView t = (TextView)findViewById(R.id.textback);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);
		
		t = (TextView)findViewById(R.id.textborder);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);
		
		t = (TextView)findViewById(R.id.texto);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);	
		
		t = (TextView)findViewById(R.id.textx);
		t.setBackgroundColor(BOX_BACK);
		t.setTextColor(BOX_BORDER);
	}
	
	public static String CodetoColor(int c)
	{
		if (c == Color.BLACK)
			return "Black";
		else if (c == Color.WHITE)
			return "White";
		else if (c == Color.RED)
			return "Red";
		else if (c == Color.BLUE)
			return "Blue";
		else if (c == Color.GREEN)
			return "Green";
		else if (c == Color.YELLOW)
			return "Yellow";
		else if (c == Color.GRAY)
			return "Gray";

		else return "";
	}
	
	public static int ColortoCode(CharSequence sel, boolean order)
	{
		if (sel.equals("Black"))
		{
			if (order) return 0;
			return Color.BLACK;
		}
		else if (sel.equals("White"))
		{
			if (order) return 1;
			return Color.WHITE;
		}
		else if (sel.equals("Red"))
		{
			if (order) return 2;
			return Color.RED;
		}
		else if (sel.equals("Blue"))
		{
			if (order) return 3;
			return Color.BLUE;
		}
		else if (sel.equals("Green"))
		{
			if (order) return 4;
			return Color.GREEN;
		}
		else if (sel.equals("Yellow"))
		{
			if (order) return 5;
			return Color.YELLOW;
		}
		else if (sel.equals("Gray"))
		{
			if (order) return 6;
			return Color.GRAY;	
		}
		else return 0;		
	}

	private void saveColors()
	{
		app.setxMarker(X_MARKER);
		app.setoMarker(O_MARKER);
		app.setBack(BOX_BACK);
		app.setBorder(BOX_BORDER);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.back_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
	    case R.id.back:
	        this.finish();
	        break;	     	        
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	    
	    return true;
	}
	
}
