package com.cheng.utils.bean;

import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *	@author chengyunfei
 *	@version 1.0
 *	@date : 2017-07-07 10:27:44
 *	@Description : Bean工具类 
 */
public class BeanUtils {

	/**
	 *	@author chengyunfei
	 *	@version 1.0
	 *	@date : 2017-07-07 10:26:26
	 *	@Description : Map转换成 实体Bean
	 */
	public static <T> T mapToBean(Class<T> t, MultivaluedMap<?, ?> map) {
        T obj = null;
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        try {
            if (map != null) {
                map.forEach((key, value) -> {
                    if (value != null) {
                        if (value instanceof List) {
                            List<?> list = (List<?>) value;
                            if (!list.isEmpty()) {
                                if (list.size() == 1) {
                                    Object listValue = list.get(0);
                                    if (listValue != null && !listValue.equals("")) {
                                        paramMap.put(key, value);
                                    }
                                }
                            }
                        } else {
                            paramMap.put(key, value);
                        }
                    }
                });
            }
            obj = t.newInstance();
            org.apache.commons.beanutils.BeanUtils.copyProperties(obj, paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}