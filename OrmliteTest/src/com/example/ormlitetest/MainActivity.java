package com.example.ormlitetest;

import java.sql.SQLException;
import java.util.List;

import com.example.ormlitetest.beans.Child;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("-----------oncreate");
		ChildDAOManager childDAOManager = ChildDAOManager.getInstance(this);
		
		Child child = new Child();
		child.setAge(5);
		child.setName("aaaaa");
		try {
			childDAOManager.add(child);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("-----------c qurey");
			List<Child> quaryAll = childDAOManager.quaryAll();
			for (Child c : quaryAll) {
				System.out.println("-----------c = " + c.getName() + "  " + c.getId());
			}
			System.out.println("-----------c finish");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
