package com.example.ormlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.ormlitetest.beans.Child;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOHelper<T> extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "search_db";
	private static final int DATABASE_VERSON = 1;
	
	private Dao<T, Integer> dao;

    private static ArrayList<Class> classes = new ArrayList<Class>();
    static {
        classes.add(Child.class);
    }

    public DAOHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSON);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
            initTable(connectionSource);
        } catch (SQLException e) {
			e.printStackTrace();
		}

	}

    @Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int arg2, int arg3) {
		try {
            updateTable(connectionSource);
            onCreate(db, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    private void updateTable(ConnectionSource connectionSource) throws SQLException {
        for (Class c : classes) {
            TableUtils.dropTable(connectionSource, c, true);
        }
    }

    private void initTable(ConnectionSource connectionSource) throws SQLException {
        for (Class c : classes) {
            TableUtils.createTable(connectionSource, c);
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
	public Dao<T, Integer> getHelperDao(Class c) {
        if (!classes.contains(c)) {
            throw new RuntimeException("there is no class that named : " + c.getName());
        }
		if (dao == null) {
			try {
				dao = getDao(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dao;
	}
}
