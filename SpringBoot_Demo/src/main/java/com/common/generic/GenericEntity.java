package com.common.generic;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 范型操作实体需实现的接口
 * @author tiger
 *
 */
public interface GenericEntity<PK extends Serializable>{
	public static final int DEFAULT_PAGE_SIZE=10;
	/**
	 * 得到主键，用于GenericAction中，toEdit的新建与修改的判断
	 * @return
	 */
	public PK getPK();
	/**
	 * 用于处理多表查询
	 * @return
	 */
	public HashMap<String,Object> getOtherResultColumns();
	/**
	 * 用于处理多表查询
	 * @return
	 */
	public void setOtherResultColumns(HashMap<String, Object> otherResultColumns);
	/**
	 * 用于处理多表查询
	 * @return
	 */
	public HashMap<String,Object> getOtherParams();
	/**
	 * 用于处理多表查询
	 * @return
	 */
	public void setOtherParams(HashMap<String, Object> otherParams);
	
	/**
	 * 所有数据对象都有的属性
	 * @return
	 */
}
