package com.common.generic;



public class PageModelContext {

	//分页号
	private static ThreadLocal<Integer> pageNoThread = new ThreadLocal<Integer>();
	//分页大小
	private static ThreadLocal<Integer> pageSizeThread = new ThreadLocal<Integer>();
	//排序字段
	private static ThreadLocal<String> pageOrderByThread = new ThreadLocal<String>();
	//排序ASC，DESC
	private static ThreadLocal<String> pageOrderTypeThread = new ThreadLocal<String>();

	public static int getPageNo() {
		if (pageNoThread.get() != null) {
			return (Integer) pageNoThread.get();
		}
		return -1;
	}

	public static void setPageNo(int pageNo) {
		pageNoThread.set(pageNo);
	}

	public static void removePageNoThread() {

		pageNoThread.remove();

	}
	
	
	
	public static int getPageSize() {
		if (pageSizeThread.get() != null) {
			return (Integer) pageSizeThread.get();
		}
		return -1;
	}

	public static void setPageSize(int pageSize) {
		pageSizeThread.set(pageSize);
	}

	public static void removePageSizeThread() {

		pageSizeThread.remove();

	}
	
	
	public static String getPageOrderBy() {
		if (pageOrderByThread.get() != null) {
			return (String) pageOrderByThread.get();
		}
		return null;
	}

	public static void setPageOrderBy(String orderByString) {
		pageOrderByThread.set(orderByString);
	}

	public static void removePageOrderByThread() {

		pageOrderByThread.remove();

	}
	
	
	public static String getPageOrderType() {
		if (pageOrderTypeThread.get() != null) {
			return (String) pageOrderTypeThread.get();
		}
		return null;
	}

	public static void setPageOrderType(String orderTypeString) {
		pageOrderTypeThread.set(orderTypeString);
	}

	public static void removePageOrderTypeThread() {

		pageOrderTypeThread.remove();

	}

}
