package com.example.ormlitetest;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.example.ormlitetest.beans.Child;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class ChildDAOManager {
	public DAOHelper helper;
	public Dao<Child, Integer> dao;
	private Context mContext;
	public static ChildDAOManager manager = null;

	public ChildDAOManager(Context context) {
		helper = OpenHelperManager.getHelper(context, DAOHelper.class);
		dao = helper.getHelperDao();
		this.mContext = context;
	}

	/**
	 * 获取管理器
	 * 
	 * @param context
	 * @return
	 */
	public static ChildDAOManager getInstance(Context context) {
		if (manager == null) {
			manager = new ChildDAOManager(context);
		}
		return manager;
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
	 * @throws SQLException
	 */
	public void add(List<Child> childs) throws SQLException {
		if (childs != null) {
			for (Child person : childs) {
				dao.create(person);
			}
		}
	}

	public void add(Child shortcut) throws SQLException {
		dao.create(shortcut);
	}

	/**
	 * 删
	 * 
	 * @param shortcut
	 * @throws SQLException
	 */
	public void delete(Child shortcut) throws SQLException {
		if (shortcut != null) {
			dao.delete(shortcut);
		}
	}
	
	public int delete(List<Child> childs) throws SQLException {
		if (childs != null && childs.size() > 0) {
			return dao.delete(childs);
		}
		return 0;
	}

	/**
	 * 改
	 * 
	 * @param shortcut
	 * @throws SQLException
	 */
	public int update(Child shortcut) throws SQLException {
		if (shortcut != null) {
			return dao.update(shortcut);
		}
		return 0;
	}

	/**
	 * 查
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Child> quaryAll() throws SQLException {
		List<Child> list = null;
		list = dao.queryForAll();
		return list;
	}
	
}