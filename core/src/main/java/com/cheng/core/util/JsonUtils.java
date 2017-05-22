package com.cheng.core.util;


import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;  
  
public class JsonUtils {  
    private static final Log log = LogFactory.getLog(JsonUtils.class);  
    public static final String EMPTY = "";  
    /** 空的 {@code JSON} 数据 - <code>"{}"</code>�? */  
    public static final String EMPTY_JSON = "{}";  
    /** 空的 {@code JSON} 数组(集合)数据 - {@code "[]"}�? */  
    public static final String EMPTY_JSON_ARRAY = "[]";  
    /** 默认�? {@code JSON} 日期/时间字段的格式化模式�? */  
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";  
    /** {@code Google Gson} �? {@literal @Since} 注解常用的版本号常量 - {@code 1.0}�? */  
    public static final Double SINCE_VERSION_10 = 1.0d;  
    /** {@code Google Gson} �? {@literal @Since} 注解常用的版本号常量 - {@code 1.1}�? */  
    public static final Double SINCE_VERSION_11 = 1.1d;  
    /** {@code Google Gson} �? {@literal @Since} 注解常用的版本号常量 - {@code 1.2}�? */  
    public static final Double SINCE_VERSION_12 = 1.2d;  
  
    /** 
     * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串�? 
     * <p /> 
     * <strong>该方法转换发生错误时，不会抛出任何异常�?�若发生错误时，曾�?�对象返�? <code>"{}"</code>�? 集合或数组对象返�? 
     * <code>"[]"</code></strong> 
     *  
     * @param target 
     *            目标对象�? 
     * @param targetType 
     *            目标对象的类型�?? 
     * @param isSerializeNulls 
     *            是否序列�? {@code null} 值字段�?? 
     * @param version 
     *            字段的版本号注解�? 
     * @param datePattern 
     *            日期字段的格式化模式�? 
     * @param excludesFieldsWithoutExpose 
     *            是否排除未标�? {@literal @Expose} 注解的字段�?? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, Type targetType,  
            boolean isSerializeNulls, Double version, String datePattern,  
            boolean excludesFieldsWithoutExpose) {  
        if (target == null)  
            return EMPTY_JSON;  
        GsonBuilder builder = new GsonBuilder();  
        if (isSerializeNulls)  
            builder.serializeNulls();  
        if (version != null)  
            builder.setVersion(version.doubleValue());  
        if (isEmpty(datePattern))  
            datePattern = DEFAULT_DATE_PATTERN;  
        builder.setDateFormat(datePattern);  
        if (excludesFieldsWithoutExpose)  
            builder.excludeFieldsWithoutExposeAnnotation();  
        String result = EMPTY;  
        Gson gson = builder.create();  
        try {  
            if (targetType != null) {  
                result = gson.toJson(target, targetType);  
            } else {  
                result = gson.toJson(target);  
            }  
        } catch (Exception ex) {  
            log.warn("目标对象 " + target.getClass().getName()  
                    + " 转换 JSON 字符串时，发生异常！", ex);  
            if (target instanceof Collection || target instanceof Iterator  
                    || target instanceof Enumeration  
                    || target.getClass().isArray()) {  
                result = EMPTY_JSON_ARRAY;  
            } else  
                result = EMPTY_JSON;  
        }  
        return result;  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法只用来转换普�?�的 {@code JavaBean} 
     * 对象�?</strong> 
     * <ul> 
     * <li>该方法只会转换标�? {@literal @Expose} 注解的字段；</li> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法会转换�?有未标注或已标注 {@literal @Since} 的字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target) {  
        return toJson(target, null, false, null, null, true);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法只用来转换普�?�的 {@code JavaBean} 
     * 对象�?</strong> 
     * <ul> 
     * <li>该方法只会转换标�? {@literal @Expose} 注解的字段；</li> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法会转换�?有未标注或已标注 {@literal @Since} 的字段；</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param datePattern 
     *            日期字段的格式化模式�? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, String datePattern) {  
        return toJson(target, null, false, null, datePattern, true);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法只用来转换普�?�的 {@code JavaBean} 
     * 对象�?</strong> 
     * <ul> 
     * <li>该方法只会转换标�? {@literal @Expose} 注解的字段；</li> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param version 
     *            字段的版本号注解({@literal @Since})�? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, Double version) {  
        return toJson(target, null, false, version, null, true);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法只用来转换普�?�的 {@code JavaBean} 
     * 对象�?</strong> 
     * <ul> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法会转换�?有未标注或已标注 {@literal @Since} 的字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param excludesFieldsWithoutExpose 
     *            是否排除未标�? {@literal @Expose} 注解的字段�?? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target,  
            boolean excludesFieldsWithoutExpose) {  
        return toJson(target, null, false, null, null,  
                excludesFieldsWithoutExpose);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法只用来转换普�?�的 {@code JavaBean} 
     * 对象�?</strong> 
     * <ul> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param version 
     *            字段的版本号注解({@literal @Since})�? 
     * @param excludesFieldsWithoutExpose 
     *            是否排除未标�? {@literal @Expose} 注解的字段�?? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, Double version,  
            boolean excludesFieldsWithoutExpose) {  
        return toJson(target, null, false, version, null,  
                excludesFieldsWithoutExpose);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法�?�常用来转换使用泛型的对象�??</strong> 
     * <ul> 
     * <li>该方法只会转换标�? {@literal @Expose} 注解的字段；</li> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法会转换�?有未标注或已标注 {@literal @Since} 的字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param targetType 
     *            目标对象的类型�?? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, Type targetType) {  
        return toJson(target, targetType, false, null, null, true);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法�?�常用来转换使用泛型的对象�??</strong> 
     * <ul> 
     * <li>该方法只会转换标�? {@literal @Expose} 注解的字段；</li> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param targetType 
     *            目标对象的类型�?? 
     * @param version 
     *            字段的版本号注解({@literal @Since})�? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, Type targetType, Double version) {  
        return toJson(target, targetType, false, version, null, true);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法�?�常用来转换使用泛型的对象�??</strong> 
     * <ul> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法会转换�?有未标注或已标注 {@literal @Since} 的字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param targetType 
     *            目标对象的类型�?? 
     * @param excludesFieldsWithoutExpose 
     *            是否排除未标�? {@literal @Expose} 注解的字段�?? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, Type targetType,  
            boolean excludesFieldsWithoutExpose) {  
        return toJson(target, targetType, false, null, null,  
                excludesFieldsWithoutExpose);  
    }  
  
    /** 
     * 将给定的目标对象转换�? {@code JSON} 格式的字符串�?<strong>此方法�?�常用来转换使用泛型的对象�??</strong> 
     * <ul> 
     * <li>该方法不会转�? {@code null} 值字段；</li> 
     * <li>该方法转换时使用默认�? 日期/时间 格式化模�? - {@code yyyy-MM-dd HH:mm:ss SSS}�?</li> 
     * </ul> 
     *  
     * @param target 
     *            要转换成 {@code JSON} 的目标对象�?? 
     * @param targetType 
     *            目标对象的类型�?? 
     * @param version 
     *            字段的版本号注解({@literal @Since})�? 
     * @param excludesFieldsWithoutExpose 
     *            是否排除未标�? {@literal @Expose} 注解的字段�?? 
     * @return 目标对象�? {@code JSON} 格式的字符串�? 
     */  
    public static String toJson(Object target, Type targetType, Double version,  
            boolean excludesFieldsWithoutExpose) {  
        return toJson(target, targetType, false, version, null,  
                excludesFieldsWithoutExpose);  
    }  
  
    /** 
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象�?? 
     *  
     * @param <T> 
     *            要转换的目标类型�? 
     * @param json 
     *            给定�? {@code JSON} 字符串�?? 
     * @param token 
     *            {@code com.google.gson.reflect.TypeToken} 的类型指示类对象�? 
     * @param datePattern 
     *            日期格式模式�? 
     * @return 给定�? {@code JSON} 字符串表示的指定的类型对象�?? 
     */  
    public static <T> T fromJson(String json, TypeToken<T> token,  
            String datePattern) {  
        if (isEmpty(json)) {  
            return null;  
        }  
        GsonBuilder builder = new GsonBuilder();  
        if (isEmpty(datePattern)) {  
            datePattern = DEFAULT_DATE_PATTERN;  
        }  
        Gson gson = builder.create();  
        try {  
            return gson.fromJson(json, token.getType());  
        } catch (Exception ex) {  
            log.error(json + " 无法转换�? " + token.getRawType().getName() + " 对象!",  
                    ex);  
            return null;  
        }  
    }  
  
    /** 
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象�?? 
     *  
     * @param <T> 
     *            要转换的目标类型�? 
     * @param json 
     *            给定�? {@code JSON} 字符串�?? 
     * @param token 
     *            {@code com.google.gson.reflect.TypeToken} 的类型指示类对象�? 
     * @return 给定�? {@code JSON} 字符串表示的指定的类型对象�?? 
     */  
    public static <T> T fromJson(String json, TypeToken<T> token) {  
        return fromJson(json, token, null);  
    }  
  
    /** 
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象�??<strong>此方法�?�常用来转换普�?�的 {@code JavaBean} 
     * 对象�?</strong> 
     *  
     * @param <T> 
     *            要转换的目标类型�? 
     * @param json 
     *            给定�? {@code JSON} 字符串�?? 
     * @param clazz 
     *            要转换的目标类�?? 
     * @param datePattern 
     *            日期格式模式�? 
     * @return 给定�? {@code JSON} 字符串表示的指定的类型对象�?? 
     */  
    public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {  
        if (isEmpty(json)) {  
            return null;  
        }  
       /* GsonBuilder builder = new GsonBuilder();  
        builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());*/
        if (isEmpty(datePattern)) {  
            datePattern = DEFAULT_DATE_PATTERN;  
        }  
       /* Gson gson = builder.create();  */
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        try {  
            return gson.fromJson(json, clazz);  
        } catch (Exception ex) {  
            log.error(json + " 无法转换�? " + clazz.getName() + " 对象!", ex);  
            return null;  
        }  
    }  
  
    /** 
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象�??<strong>此方法�?�常用来转换普�?�的 {@code JavaBean} 
     * 对象�?</strong> 
     *  
     * @param <T> 
     *            要转换的目标类型�? 
     * @param json 
     *            给定�? {@code JSON} 字符串�?? 
     * @param clazz 
     *            要转换的目标类�?? 
     * @return 给定�? {@code JSON} 字符串表示的指定的类型对象�?? 
     */  
    public static <T> T fromJson(String json, Class<T> clazz) {  
        return fromJson(json, clazz, null);  
    }  
  
    public static boolean isEmpty(String inStr) {  
        boolean reTag = false;  
        if (inStr == null || "".equals(inStr)) {  
            reTag = true;  
        }  
        return reTag;  
    } 
    public static void main(String[] args) {
		System.out.println(new Date(1474176253000l));
	}
}  

