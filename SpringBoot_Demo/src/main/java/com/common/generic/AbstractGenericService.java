package com.common.generic;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.common.util.PropertyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author tiger
 *
 * @param <PK>
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGenericService<PK extends Serializable, T extends GenericEntity<PK>>
		implements GenericService<PK, T> {
	protected Class<PK> pkClass;
	protected Class<PK> entityClass;
	@Autowired
	private PropertyUtils propertyUtils;

	{
		// 获得超类的泛型参数的实际类型,dao与mapper的指定在AbstractDAO类的构造函数中，通过命名空间来指定对应的mapper文件
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();

		pkClass = (Class<PK>) (parameterizedType.getActualTypeArguments()[0]);
		entityClass = (Class<PK>) (parameterizedType.getActualTypeArguments()[1]);
	}

	abstract protected GenericMapper<PK, T> getMapper();

	@Override
	public Long count(HashMap<String, Object> condition) {
		return getMapper().count(condition);
	}

	@Override
	public PageInfo<T> page(HashMap<String, Object> condition) {

		// 处理排序字段，传递属性转为列名
		if (PageModelContext.getPageOrderBy() != null) {
			String ColumnName = propertyUtils.getPropertyValue(
					new File(this.getClass().getResource("/PropertyName2ColumnName.properties").getFile() ),
					entityClass.getSimpleName() + "." + PageModelContext.getPageOrderBy());
			if (ColumnName != null) {
				if (PageModelContext.getPageOrderType() != null) {
					//oracle
					//PageModelContext.setPageOrderBy(ColumnName + " " + PageModelContext.getPageOrderType() + " nulls last");
					PageModelContext.setPageOrderBy(ColumnName + " " + PageModelContext.getPageOrderType());
				} else {
					//oracle
					//PageModelContext.setPageOrderBy(ColumnName + " nulls last");
					PageModelContext.setPageOrderBy(ColumnName);
				}
			} else {
				PageModelContext.setPageOrderBy(null);
			}
		}
		PageHelper.startPage(PageModelContext.getPageNo(), PageModelContext.getPageSize(),
				PageModelContext.getPageOrderBy());
		List<T> list = getMapper().page(condition);
		// 用PageInfo对结果进行包装
		PageInfo<T> page = new PageInfo<>(list);
		return page;

	}
	
	@Override
	public List<T> queryAll(HashMap<String, Object> condition) {
		List<T> result = getMapper().queryAll(condition);
		if (result == null)
			result = new LinkedList<T>();
		return result;
	}

	@Override
	public T queryObject(HashMap<String, Object> condition) {
		return getMapper().queryObject(condition);
	}

	@Override
	public void delete(HashMap<String, Object> condition) {
		getMapper().delete(condition);
	}

	@Override
	public void multiPKDelete(HashMap<String, Object> condition) {
		getMapper().multiPKDelete(condition);
	}

	@Override
	public PK addOrModify(T object) {
		PK pk = getMapper().saveOrUpdate(object);
		return pk;
	}

	@Override
	public void add(T object) {
		getMapper().insert(object);
	}

	@Override
	public Integer modify(T object) {
		Integer count = getMapper().update(object);
		return count;
	}

	@Override
	public Integer multiModify(HashMap<String, Object> condition) {
		Integer count = getMapper().multiUpdate(condition);
		return count;
	}
	

}
