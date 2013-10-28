package com.example.ormlitetest;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ormlitetest.beans.Child;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DAOHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "Child_db";
	private static final int DATABASE_VERSON = 1;
	
	private Dao<Child, Integer> dao;
	
	public DAOHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSON);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Child.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int arg2, int arg3) {
		try {
			TableUtils.dropTable(connectionSource, Child.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		super.close();
		dao = null;
	}

	/**
	 * 获取Dao操作类
	 * 
	 * @return
	 */
	public Dao<Child, Integer> getHelperDao() {
		if (dao == null) {
			try {
				dao = getDao(Child.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dao;
	}
}
