package com.common.generic;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;

public interface GenericService<PK extends Serializable,T extends GenericEntity<PK>> {
	/**
	 * @author tiger
	 * 根据条件查询并返回所有查询结果
	 * @param condition
	 * @return
	 */
	public List<T> queryAll(HashMap<String, Object> condition);
	/**
	 * 根据条件查询对象
	 * @param condition
	 * @return
	 */
	public T queryObject(HashMap<String, Object> condition);
	/**
	 * 根据条件查询所有内容,并使用condition对象中的分页参数分页返回结果
	 * 此方法用于与easyui配合使用
	 * @param condition
	 * @return
	 */
	public PageInfo<T> page(HashMap<String, Object> condition);
	
	/**
	 * 保存或更新实体
	 * Oracle,此处采用存储过程完成,以保证id序列的正确,并将新插入的id返回给插入对象
	 * @param
	 * @return
	 */
	public PK addOrModify(T object);
	/**
	 * 插入实体
	 * @param
	 * @return
	 */
	public void add(T object);
	/**
	 * 更新实体(使用特殊语句)
	 * @param
	 * @return
	 */
	public Integer modify(T object);
	/**
	 * 更新实体(使用特殊语句)
	 * @param
	 * @return
	 */
	public Integer multiModify(HashMap<String, Object> condition);
	/**
	 * 删除实体,或根据条件删除列表
	 * @param condition
	 * @return
	 */
	public void delete(HashMap<String, Object> condition);
	/**
	 * 根据条件多选删除
	 * @param condition
	 * @return
	 */
	public void multiPKDelete(HashMap<String, Object> condition);
	/**
	 * 计数
	 * @return
	 */
	public Long count(HashMap<String, Object> condition);
	
}
