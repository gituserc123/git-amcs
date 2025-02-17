package  com.aier.cloud.biz.service.biz.amcs.idr.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.aier.cloud.basic.common.exception.BizAssert;
import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.basic.common.util.SpringUtils; 
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 使用上下文中的翻译配置对结果集进行翻译<br/>
 * 1，在Controller方法的返回之前调用add方法添加翻译配置。<br/>
 * 2，在HttpMessageConverter中调用doTrans方法进行翻译处理。 兼容各种Collection/Map/Javabean<br/>
 *<br/>
 * 2种方法配置翻译基础信息<br/>
 * 1，t_dcg(h)_code_dict里的type字段 <br/>
 * 4，实现了EnumDict接口的enum类型的枚举
 * 
 * @author xiaokek
 * @since 2018年2月26日 上午9:10:41
 */
public class TransCode {
	public static final String STAFF = "t_sys_staff|id|name";
	public static final String INST = "t_sys_institution|id|name";
	public static final String HOSP = "t_sys_institution|ahis_hosp|name";
	public static final String LAZY_CHAR = "|";

	/** 存在当前请求里的Set<TransCode>键 */
	private static final String ATTR = TransCode.class.getName();
	
	/** 翻譯類型*/
	private TransType transTypeEnum = TransType.LOCAL_ENUM_DICT;
	
	public enum TransType{
		/**本地代码中的枚举字典*/
		LOCAL_ENUM_DICT(1),
		/**表t_dcg(h)_code_dict的字典*/
		DCG_CODE_DICT(2),
		
		/**自动在表t_sys_sql_dict生成的带|的字典*/
		LAZY_SYS_SQL_DICT(3),
		
		/**手动在表t_sys_sql_dict建立的字典*/
		SYS_SQL_DICT(4);
		
		int order;
		TransType(int order){
			this.order = order;
		}
		
	}
	
	/** 待转换字段的名称 */
	private String keyFieldName;

	/** 使用的字典类型 */
	private String transType;
	

	/** 适配区卫上报, 是否转成外部接口的名称, 而不是编码 */
	private boolean forInterfaceName = false;

	public TransCode(String keyFieldName, String transType, String valueFieldName) {
		this(keyFieldName, transType, keyFieldName, false);
	}
	public TransCode(String keyFieldName, String transType, String valueFieldName, boolean forInterfaceName) {
		this.keyFieldName = keyFieldName;
		this.transType = transType;
		this.valueFieldName = valueFieldName;
		this.forInterfaceName = forInterfaceName;
	}
	public TransCode(String keyFieldName, String transType) {
		this(keyFieldName, transType, keyFieldName);
	}
	public TransCode() {
	}

	/** 转换后字段的名称，如果不填，默认使用keyFieldName的名称覆盖 */
	private String valueFieldName;
	
	private Map<String, String> tempCache = null;

	public void setTempCache(Map keyAndValues) {
		if(MapUtils.isNotEmpty(keyAndValues)) {
			tempCache = Maps.newHashMap();
			keyAndValues.forEach((k ,v) ->{
				tempCache.put(genKey(String.valueOf(k), this.transType), String.valueOf(v));
			});
		}
	}

	public String getKeyFieldName() {
		return keyFieldName;
	}

	public void setKeyFieldName(String keyFieldName) {
		this.keyFieldName = keyFieldName;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getValueFieldName() {
		return valueFieldName;
	}

	public void setValueFieldName(String valueFieldName) {
		this.valueFieldName = valueFieldName;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof TransCode)){
			return false;
		}
		TransCode tc = (TransCode)obj;
		return Objects.equals(this.getKeyFieldName(), tc.getKeyFieldName());
	}

	@Override
	public int hashCode() {
		return this.getKeyFieldName().hashCode();
	} 

	public static String genKey(String code, String type) {
		return type+"_"+code;
	}

	/**
	 * 添加一个转换配置项
	 * 
	 * @param keyFieldName
	 *            你要转换的字段叫什么名字？
	 * @param transType
	 *            你要用什么码表类型来转？
	 * @return
	 */
	public static TransCode add(String keyFieldName, String transType) {
		return add(keyFieldName, transType, null);
	}

	/**
	 * 添加一个转换配置项
	 * 
	 * @param keyFieldName
	 *            你要转换的字段叫什么名字？
	 * @param transType
	 *            你要用什么码表类型来转？
	 * @param valueFieldName
	 *            转换后的字段叫什么名字？
	 * @return
	 */
	public static TransCode add(String keyFieldName, String transType, String valueFieldName) {
		BizAssert.notEmpty(keyFieldName);
		BizAssert.notEmpty(transType);
		TransCode tc = new TransCode();
		tc.keyFieldName = keyFieldName;

		if(transType.indexOf(TransCode.LAZY_CHAR) >= 0){
			transType = transType.toLowerCase();
		}
		tc.transType = transType;
		tc.valueFieldName = valueFieldName;
		tc.transTypeEnum = TransCode.resolve(tc.transType);
		add0(tc);
		return tc;
	}
	
	/**
	 * 添加一个转换配置项
	 * 
	 * @see com.aier.cloud.basic.common.convert.EnumDict
	 * @param keyFieldName
	 *            你要转换的字段叫什么名字？
	 * @param transType
	 *            你要用什么enum枚举类型来转？
	 * @param valueFieldName
	 *            转换后的字段叫什么名字？
	 * @return
	 */
	public static TransCode add(String keyFieldName, Class<? extends EnumDict> transType, String valueFieldName) {
		BizAssert.notEmpty(keyFieldName);
		BizAssert.isTrue(transType!=null, "码表类型不能为空！");
		TransCode tc = new TransCode();
		tc.keyFieldName = keyFieldName;
		tc.transType = transType.getName();
		tc.valueFieldName = valueFieldName;
		tc.transTypeEnum = TransType.LOCAL_ENUM_DICT;
		EnumDictResovler r =  SpringUtils.getBean(EnumDictResovler.class);
		r.add(transType);
		add0(tc);
		return tc;
	}
	
	/**
	 * 添加一个转换配置项
	 * 
	 * @param keyFieldName
	 *            你要转换的字段叫什么名字？
	 * @param transType
	 *            你要用什么enum枚举类型来转？
	 * @return
	 */
	public static TransCode add(String keyFieldName, Class<? extends EnumDict> transType) {
		return add(keyFieldName, transType, null);
	}

	private static void add0(TransCode tc) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Set<TransCode> tcs = (Set<TransCode>)request.getAttribute(ATTR);
		if(tcs == null){
			tcs = Sets.newHashSet();
			request.setAttribute(ATTR, tcs);
		}
		tcs.add(tc);
	}

	/**
	 * 使用上下文中的翻译配置对结果集进行翻译
	 * 
	 * @param o
	 * @return
	 */
	public static Object doTrans(Object o) {
	    if(RequestContextHolder.getRequestAttributes()== null) {
            return o;
        }
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        if (request == null) {
            return o;
        }
		Set<TransCode> tcs = (Set<TransCode>)request.getAttribute(ATTR);
		Object toTrans =  doTrans(o, tcs);
		request.removeAttribute(ATTR);
		return toTrans;
	}
	
	/**
	 * 使用上下文中的翻译配置对结果集进行翻译
	 * 
	 * @param o
	 * @return
	 */
	public static Object doTrans(Object o, Set<TransCode> tcs) {
		if(CollectionUtils.isEmpty(tcs)){
			return o;
		}

		// 此处处理json一个来回的目的是把javabean全部转成Map，使得后续实现不用管javabean了。
		String json = JsonUtil.toJson(o);
		Object toTrans = JsonUtil.fromJson(json, Object.class);
		if(toTrans instanceof Map){
			doTransMap((Map)toTrans, tcs);
		} else if(toTrans instanceof Collection){
			doTransCollection((Collection)toTrans, tcs);
		} else{
			// 既不是Map也不是Collection，那就不用处理咯
		}

		return toTrans;
	}

	/**
	 * 对Map里面的内容进行递归翻译
	 * 
	 * @param toTransMap
	 * @param tcs
	 */
	private static void doTransMap(Map<?, ?> toTransMap, Set<TransCode> tcs) {
		if(MapUtils.isEmpty(toTransMap)){
			return;
		}
		Object[] keys = toTransMap.keySet().toArray();
		for(Object key : keys){
			Object value = toTransMap.get(key);
			if(value == null){
				continue;
			}
			if(value instanceof Map){
				doTransMap((Map<?, ?>)value, tcs);
			} else if(value instanceof Collection){
				doTransCollection((Collection)value, tcs);
			} else{
				// 真正处理翻译逻辑的地方
				//doTrans0(toTransMap, key, value, tcs);
			}
		}
	}

	/**
	 * 对集合里面的内容进行递归翻译
	 * 
	 * @param toTransCollection
	 * @param tcs
	 */
	private static void doTransCollection(Collection toTransCollection, Set<TransCode> tcs) {
		if(CollectionUtils.isEmpty(toTransCollection)){
			return;
		}

		for(Object value : toTransCollection){
			if(value instanceof Map){
				doTransMap((Map<?, ?>)value, tcs);
			} else if(value instanceof Collection){
				doTransCollection((Collection)value, tcs);
			}
		}
	}
 
 
	/**
	 * 解析正确的码表类型
	 * @param transType
	 * @return
	 */
	public static TransType resolve(String transType) {
		String comAier = "com.aier";
		String dot = ".";
		BizAssert.notEmpty(transType);
		if(transType.indexOf(LAZY_CHAR) > -1) {
			return TransType.LAZY_SYS_SQL_DICT;
		}else if(transType.startsWith(comAier)) {
			return TransType.LOCAL_ENUM_DICT;
		}else if(transType.indexOf(dot) > -1) {
			return TransType.SYS_SQL_DICT;
		}else {
			return TransType.DCG_CODE_DICT;
		}
		
	}

	public TransType getTransTypeEnum() {
		return transTypeEnum;
	}

	public void setTransTypeEnum(TransType transTypeEnum) {
		this.transTypeEnum = transTypeEnum;
	}
	public boolean isForInterfaceName() {
		return forInterfaceName;
	}
}
