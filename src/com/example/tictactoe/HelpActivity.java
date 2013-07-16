package com.example.tictactoe;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		final ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		
		UpdateColors();
		
		Button buttonBack = (Button) findViewById(R.id.btn_back);
		buttonBack.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	HelpActivity.this.finish();
		    }
		});
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

	private void UpdateColors()
	{
		MyApp app = (MyApp)getApplication();		
		TextView t = (TextView)findViewById(R.id.textback);
		t.setBackgroundColor(app.getBack());
		t.setTextColor(app.getBorder());
		
		RelativeLayout r = (RelativeLayout)findViewById(R.id.helplayout);
		r.setBackgroundColor(app.getBack());
		
		Button btn = (Button)findViewById(R.id.btn_back);
		btn.setTextColor(app.getBorder());

	}
}
