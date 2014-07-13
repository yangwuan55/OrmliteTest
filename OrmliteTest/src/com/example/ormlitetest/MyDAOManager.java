package com.example.ormlitetest;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MyDAOManager<T> {
	public DAOHelper helper;
	public Dao<T, Integer> dao;
	public static MyDAOManager manager = null;

	public MyDAOManager(Context context, Class c) {
		helper = OpenHelperManager.getHelper(context, DAOHelper.class);
		dao = helper.getHelperDao(c);
	}

    /**
	 * 关闭管理器
	 */
	public void close() {
		if (helper != null) {
			helper.close();
			OpenHelperManager.releaseHelper();
			helper = null;
		}
	}

	/**
	 * 增
	 * 
	 * @param childs
	 * @throws java.sql.SQLException
	 */
	public void add(List<T> childs) throws SQLException {
		if (childs != null) {
			for (T t : childs) {
				dao.create(t);
			}
		}
	}

	public void add(T shortcut) throws SQLException {
		dao.create(shortcut);
	}

	/**
	 * 删
	 *
	 * @param shortcut
	 * @throws java.sql.SQLException
	 */
	public void delete(T shortcut) throws SQLException {
		if (shortcut != null) {
			dao.delete(shortcut);
		}
	}

	public int delete(List<T> childs) throws SQLException {
		if (childs != null && childs.size() > 0) {
			return dao.delete(childs);
		}
		return 0;
	}

	/**
	 * 改
	 *
	 * @param shortcut
	 * @throws java.sql.SQLException
	 */
	public int update(T shortcut) throws SQLException {
		if (shortcut != null) {
			return dao.update(shortcut);
		}
		return 0;
	}

	/**
	 * 查
	 *
	 * @return
	 * @throws java.sql.SQLException
	 */
	public List<T> quaryAll() throws SQLException {
		List<T> list = null;
		list = dao.queryForAll();
		return list;
	}
	
}