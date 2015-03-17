package com.example.counter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Counter extends ActionBarActivity {

	private int mCount;
	private int n;
    private TextView textView;
    private String fileName = "testfile.txt";
    Random r = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);

        textView = (TextView) findViewById(R.id.textView1);
        
        String str = readFile(fileName);
        if(str != null){
            textView.setText(str);
        }
        else{
            textView.setText("0");
        }
    }

    // ファイルを読み出し
    public String readFile(String file) {
        FileInputStream fileInputStream;
        String text = null;
 
        try {
            fileInputStream = openFileInput(file);
            String lineBuffer = null;
 
            BufferedReader reader= new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
            while( (lineBuffer = reader.readLine()) != null ) {
                text = lineBuffer ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return text;
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void click(View view) {
	    n = r.nextInt(10);

	    mCount++;

	    TextView countView = (TextView) findViewById(R.id.textView1);
		countView.setText(String.valueOf(mCount));
 
		if (n != 0) {
			} else {
				countView.setText("GAME OVER");
				Button mBtn = (Button)findViewById(R.id.button1);
				mBtn.setEnabled(false);
			}
		}

	public void save_click(View view) {
        // テキストを取得
        String text = textView.getText().toString();
        saveFile(fileName, text);
    }

	// ファイルを保存
	public void saveFile(String file, String str) {
		FileOutputStream fileOutputstream = null;
 
		try {
			fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE);
			fileOutputstream.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
