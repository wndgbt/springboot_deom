package com.common.generic;

import java.io.Serializable;
import java.util.HashMap;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 范型操作实体抽象类
 * @author tiger
 *
 */
public abstract class AbstractGenericEntity<PK extends Serializable> implements GenericEntity<PK>{
	protected HashMap<String,Object> otherResultColumns=new HashMap<String,Object>(0);
	/**
	 * 用于返回结果集里包含其它数据
	 * @return
	 */
	public HashMap<String,Object> getOtherResultColumns(){
		return otherResultColumns;
	}
	/**
	 * 用于返回结果集里包含其它数据
	 * @return
	 */
	public void setOtherResultColumns(HashMap<String,Object> otherResultColumns){
		this.otherResultColumns=otherResultColumns;
	}
	protected HashMap<String,Object> otherParams=new HashMap<String,Object>(0);
	/**
	 * 用于处理多表查询
	 * @return
	 */
	public HashMap<String,Object> getOtherParams(){
		return otherParams;
	}
	public void setOtherParams(HashMap<String,Object> otherParams){
		this.otherParams=otherParams;
	}
	@JsonIgnore
	abstract public PK getPK();
}
