package com.example.achievebox;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.achievebox.R;

public class MainActivity extends Activity implements OnClickListener {

	ArrayList<Product> products = new ArrayList<Product>();
	BoxAdapter boxAdapter;
	ListView listviewbody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fillData();
		boxAdapter = new BoxAdapter(this, products);

		// настраиваем список
		ListView lvMain = (ListView) findViewById(R.id.listViewBody);
		lvMain.setAdapter(boxAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_new:
			
			Intent i = new Intent(this, AchievementNew.class);
			startActivity(i);
			
			//fillData();
			//boxAdapter.notifyDataSetChanged();
			
			break;
		default:
			break;
		}
		
		

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		default:
			break;
		}

	}

	// генерируем данные для адаптера
	void fillData() {
		
		for (int i = 1; i <= 1; i++) {
			products.add(new Product("Product " + Integer.toString(products.size() + 1), i * 1000,
					R.drawable.ic_launcher, false));
		}
		
		
	}

	// выводим информацию о корзине
	public void showResult(View v) {
		String result = "Товары в корзине:";
		for (Product p : boxAdapter.getBox()) {
			if (p.box)
				result += "\n" + p.name;
		}
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}

}
