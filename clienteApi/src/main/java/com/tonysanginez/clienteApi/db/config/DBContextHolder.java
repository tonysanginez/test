package com.tonysanginez.clienteApi.db.config;

import com.tonysanginez.clienteApi.enums.DBTypeEnum;

public class DBContextHolder {

	private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

	public static void setCurrentDb(DBTypeEnum dbType) {
		contextHolder.set(dbType);
	}

	public static DBTypeEnum getCurrentDb() {
		return contextHolder.get();
	}

	public static void clear() {
		contextHolder.remove();
	}

}