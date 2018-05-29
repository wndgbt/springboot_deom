package com.common.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
/**
 * 防止跨站脚本（XSS）注入攻击 
 */
public final class HttpRequestWrapper extends HttpServletRequestWrapper {
 
    private Map<String, String> xssMap;
    private Map<String, String[]> paramMap = new HashMap<String, String[]>();
     
    public HttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }
 
    public HttpRequestWrapper(HttpServletRequest request,
            Map<String, String> xssMap) {
        super(request);
        this.xssMap = xssMap;
       // initParameterMap(request);
    }
 
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        // 遍历每一个参数，检查是否含有
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }
 
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }
    

/*    private void initParameterMap(HttpServletRequest request) {
        if (request == null) {
             return;
        }
       Map<String,String[]> map = request.getParameterMap();
         Set<String> names = map.keySet();
        String[] values;
        for (Iterator<String> i = names.iterator(); i.hasNext(); ) {
            String name = i.next();
            values = map.get(name);
            for (int j = 0; j < values.length; j++) {
               values[j] = cleanXSS(values[j]);
            }
            this.paramMap.put(name, values);
        }
    }*/

    
	@Override
	public Map<String, String[]> getParameterMap() {
		if(super.getParameterMap() == null){
			return paramMap;
		}
		
	   Map<String,String[]> map = super.getParameterMap();
       Set<String> names = map.keySet();
       String[] values;
       for (Iterator<String> i = names.iterator(); i.hasNext(); ) {
           String name = i.next();
           values = map.get(name);
           for (int j = 0; j < values.length; j++) {
              values[j] = cleanXSS(values[j]);
           }
           this.paramMap.put(name, values);
       }
		
		return paramMap;
	}

	public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);
 
    }
 
    /**
     * 清除恶意的XSS脚本
     * 
     * @param value
     * @return
     */
    private String cleanXSS(String value) {
        Set<String> keySet = xssMap.keySet();
        for(String key : keySet){
            String v = xssMap.get(key);
            value = value.replaceAll(key,v);
        }
        return value;
    }
}