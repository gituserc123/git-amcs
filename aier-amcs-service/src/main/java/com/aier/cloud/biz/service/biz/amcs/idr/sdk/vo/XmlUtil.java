package com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author szz
 * @since 2019/8/5 10:36
 */
public class XmlUtil {

	/**
	 * XML格式字符串转换为Map
	 *
	 * @param strXML
	 *            XML字符串
	 * @return XML数据转换后的Map
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(String strXML) throws Exception {
		try {
			Map<String, String> data = new HashMap<String, String>();
			DocumentBuilder documentBuilder = newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(strXML.getBytes("GB2312"));
			org.w3c.dom.Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			try {
				stream.close();
			} catch (Exception ex) {
				// do nothing
			}
			return data;
		} catch (Exception ex) {
			throw ex;
		}

	}

	public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
		documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
		documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		documentBuilderFactory.setXIncludeAware(false);
		documentBuilderFactory.setExpandEntityReferences(false);

		return documentBuilderFactory.newDocumentBuilder();
	}

	public static org.w3c.dom.Document newDocument() throws ParserConfigurationException {
		return newDocumentBuilder().newDocument();
	}

	/**
	 * 将Map转换为XML格式的字符串
	 *
	 * @param data
	 *            Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String mapToXml(Map<String, String> data, String baseTagName, String encoding) throws Exception {
		org.w3c.dom.Document document = newDocument();
		org.w3c.dom.Element root = document.createElement(baseTagName);
		document.appendChild(root);
		for (String key : data.keySet()) {
			String value = data.get(key);
			if (value == null) {
				value = "";
			}
			value = value.trim();
			org.w3c.dom.Element filed = document.createElement(key);
			filed.appendChild(document.createTextNode(value));
			root.appendChild(filed);
		}
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);
		String output = writer.getBuffer().toString(); // .replaceAll("\n|\r", "");
		try {
			writer.close();
		} catch (Exception ex) {
		}
		return output;
	}

	/**
	 * 重载，默认根节点为Request 编码格式为GB2312
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String mapToXml(Map<String, String> data) throws Exception {
		return mapToXml(data, "Request", "GB2312");
	}

	/**
	 * 反射设置实体不同类型字段的值 <暂时只支持 日期 字符串 boolean Integer值设置 待扩建>
	 *
	 * @param field
	 * @param obj
	 * @param value
	 * @throws Exception
	 */
	public static void convertValue(Field field, Object obj, String value) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (field.getGenericType().toString().equals("class java.lang.Integer")) {
			field.set(obj, Integer.parseInt(value));
		} else if (field.getGenericType().toString().equals("boolean")) {
			field.set(obj, Boolean.parseBoolean(value));
		} else if (field.getGenericType().toString().equals("class java.util.Date")) {
			field.set(obj, sim.parse(value));
		} else {
			field.set(obj, value);
		}

	}

	/**
	 * 解析xml文件返回保存Map的集合，map中可能包含key值为returnCode、desc、totalCount等单字段.
	 * 也可能包含存储对象为List<cls>的集合值. 获取List值key cls_List
	 *
	 * @param requestPath
	 * @param cls
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseXml2Map(String requestPath, Class<?> cls) throws Exception {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<Object> lists = new ArrayList<Object>();
		SAXReader saxReader = new SAXReader();
		// 当传入的是xml字符串时，采用这个方案
		Document doc = doc = DocumentHelper.parseText(requestPath);
		Element et = doc.getRootElement();
		// 标记List是否为空
		// boolean bool = true ;
		// 根节点名字
		List<Element> rList = et.elements();
		for (Element element : rList) {
			List<Element> rLists = element.elements();
			if (!rLists.isEmpty() && rLists.size() > 0) {
				// bool = false;
				// 判断二级节点
				for (Element e : rLists) {
					List<Element> li = e.elements();
					Class<?> cl = (Class<?>) Class.forName(cls.getName());
					Object ob = cl.newInstance();
					for (Element element2 : li) {
						String name = element2.getName();
						String value = element2.getText();
						Field field = ob.getClass().getDeclaredField(name);
						field.setAccessible(true);
						convertValue(field, ob, value);
					}
					lists.add(ob);
				}
			} else {
				maps.put(element.getName(), element.getText());
			}
			// maps.put(cls.getSimpleName() + "_List", lists);
			maps.put("ItemList", lists);
		}
		return maps;
	}

	/**
	 * https://www.cnblogs.com/interdrp/p/5825453.html1
	 * 解析xml文件返回保存cls的List集合，如果返回码ResultCode=1时则返回List为null
	 *
	 * @param xml
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<?> parseXml2List(String xml, Class<?> cls) throws Exception {
		List<Object> lists = null;
		Document doc = DocumentHelper.parseText(xml);
		Element et = doc.getRootElement();
		String root = et.getName();
		// 查看返回码是否为真.
		List<org.dom4j.Node> list = doc.selectNodes("//" + root + "/ResultCode");
		if (!list.isEmpty() && list.size() > 0) {
			Element element = (Element) list.get(0);
			String returnResult = element.getText();
			if (returnResult.equals("0")) {
				List<org.dom4j.Node> father = doc.selectNodes("//" + root + "/" + cls.getSimpleName() + "s");
				// 判断对象父节点是否有包含数据
				if (father != null && !father.isEmpty() && father.size() == 1) {
					List<Element> userLists = ((Element) father.get(0)).elements();
					if (userLists != null && !list.isEmpty()) {
						lists = new ArrayList<Object>();
						for (Element e : userLists) {
							List<Element> li = e.elements();
							Class<?> cl = (Class<?>) Class.forName(cls.getName());
							Object ob = cl.newInstance();
							for (Element element2 : li) {
								String name = element2.getName();
								String value = element2.getText();
								Field field = ob.getClass().getDeclaredField(name);
								field.setAccessible(true);
								convertValue(field, ob, value);
							}
							lists.add(ob);
						}
					}
				}
			}

		}
		return lists;
	}

	/**
	 * 解析xml文件返回保存cls的List集合，如果返回码ResultCode=1时则返回List为null
	 *
	 * @param url
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<?> parseXml2List(URL url, Class<?> cls) throws Exception {
		List<Object> lists = null;
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(url);
		Element et = doc.getRootElement();
		String root = et.getName();
		// 查看返回码是否为真.
		List<org.dom4j.Node> list = doc.selectNodes("//" + root + "/ResultCode");
		if (!list.isEmpty() && list.size() > 0) {
			Element element = (Element) list.get(0);
			String returnResult = element.getText();
			if (returnResult.equals("0")) {
				List<org.dom4j.Node> father = doc.selectNodes("//" + root + "/" + cls.getSimpleName() + "s");
				// 判断对象父节点是否有包含数据
				if (father != null && !father.isEmpty() && father.size() == 1) {
					List<Element> userLists = ((Element) father.get(0)).elements();
					if (userLists != null && !list.isEmpty()) {
						lists = new ArrayList<Object>();
						for (Element e : userLists) {
							List<Element> li = e.elements();
							Class<?> cl = (Class<?>) Class.forName(cls.getName());
							Object ob = cl.newInstance();
							for (Element element2 : li) {
								String name = element2.getName();
								String value = element2.getText();
								Field field = ob.getClass().getDeclaredField(name);
								field.setAccessible(true);
								convertValue(field, ob, value);
							}
							lists.add(ob);
						}
					}
				}
			}

		}
		return lists;
	}

	/**
	 * 解析xml文件返回保存Map的集合，map中可能包含key值为returnCode、desc、totalCount等单字段.
	 * 也可能包含存储对象为List<cls>的集合值. 获取List值key cls_List
	 *
	 * @param requestPath
	 * @param cls
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseXml2MapOld(String requestPath, Class<?> cls) throws Exception {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<Object> lists = new ArrayList<Object>();
		SAXReader saxReader = new SAXReader();
		// Document doc = saxReader.read(new File(requestPath));
		// Document doc = saxReader.read(new URL(requestPath));
		// 当传入的是xml字符串时，采用这个方案
		Document doc = doc = DocumentHelper.parseText(requestPath);
		Element et = doc.getRootElement();
		// 标记List是否为空
		// boolean bool = true ;
		// 根节点名字
		List<Element> rList = et.elements();
		for (Element element : rList) {
			List<Element> rLists = element.elements();
			if (!rLists.isEmpty() && rLists.size() > 0) {
				// bool = false;
				// 判断二级节点
				for (Element e : rLists) {
					List<Element> li = e.elements();
					Class<?> cl = (Class<?>) Class.forName(cls.getName());
					Object ob = cl.newInstance();
					for (Element element2 : li) {
						String name = element2.getName();
						String value = element2.getText();
						Field field = ob.getClass().getDeclaredField(name);
						field.setAccessible(true);
						convertValue(field, ob, value);
					}
					lists.add(ob);
				}
			} else {
				maps.put(element.getName(), element.getText());
			}
			// maps.put(cls.getSimpleName() + "_List", lists);
			maps.put("ItemList", lists);
		}
		return maps;
	}

	/**
	 * 只获取返回码0为保存成功(true)1为保存失败(false)
	 */
	@SuppressWarnings("unchecked")
	public static boolean parseXmlReturnCode(String xml) {
		boolean bool = false;
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element et = doc.getRootElement();
			String root = et.getName();
			// 查看返回码是否为真.
			
			List<org.dom4j.Node> list = doc.selectNodes("//" + root + "/ResultCode");
			if (!list.isEmpty() && list.size() > 0) {
				Element element = (Element) list.get(0);
				String returnResult = element.getText();
				if (returnResult.equals("0")) {
					bool = true;
				}
			} 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 *
	 * https://blog.csdn.net/javaniche/article/details/79655473 格式化输出xml
	 *
	 * @param document
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String formatXml(Document document) throws DocumentException, IOException {
		// 格式化输出格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GB2312");
		StringWriter writer = new StringWriter();
		// 格式化输出流
		XMLWriter xmlWriter = new XMLWriter(writer, format);
		// 将document写入到输出流
		xmlWriter.write(document);
		xmlWriter.close();
		return writer.toString();
	}

	/**
	 * 
	 * @param document
	 * @param encoding
	 *            编码
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String formatXml(Document document, String encoding) throws DocumentException, IOException {
		// 格式化输出格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);
		StringWriter writer = new StringWriter();
		// 格式化输出流
		XMLWriter xmlWriter = new XMLWriter(writer, format);
		// 将document写入到输出流
		xmlWriter.write(document);
		xmlWriter.close();
		return writer.toString();
	}

	public static Document map2Xml(Map<String, Object> map, String node, String type) throws Exception {
		Document document = DocumentHelper.createDocument();
		Element nodeElement = document.addElement(node);
		map2Xml(map, nodeElement, type);
		return document;
	}

	public static Document map2Xml(Map<String, Object> map, String node, String listItemName, String type)
			throws Exception {
		Document document = DocumentHelper.createDocument();
		Element nodeElement = document.addElement(node);
		if(StringUtils.isNotEmpty(listItemName)) {
			map2Xml(map, nodeElement, listItemName, type);
		} else {
			map2Xml(map, nodeElement, type);
		}
		return document;
	}

	public static final String map2Xml_TYPE_listElementWrapper = "1";

	private static void map2Xml(Map<String, Object> map, Element nodeElement, String type) throws Exception {

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			// 给list的每个对象添加根节点Item
			if ((value instanceof List) && StringUtils.isBlank(type)) {
				Element element = nodeElement.addElement(key);
				List<Map<String, Object>> list = (List<Map<String, Object>>) value;
				for (Map<String, Object> subMap : list) {
					Element itemElement = element.addElement("Item");
					map2Xml(subMap, itemElement, type);
				}
			} else if ((value instanceof List) && StringUtils.isNotBlank(type)
					&& type.contains(map2Xml_TYPE_listElementWrapper)) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) value;
				for (Map<String, Object> subMap : list) {
					Element element = nodeElement.addElement(key);
					map2Xml(subMap, element, type);
				}
			} else if (value instanceof Map) {
				Element element = nodeElement.addElement(key);
				Map<String, Object> valueMap = (Map<String, Object>) value;
				map2Xml(valueMap, element, type);
			} else if (value instanceof String) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText((String) value);
			} else if (value instanceof Integer) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText(((Integer) value).toString());
			} else if (value instanceof Long) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText(((Long) value).toString());
			} else if (null == value) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText("");
			} else if (value instanceof BigDecimal) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText(((BigDecimal) value).toString());
			} else if (value instanceof Date) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value)).toString());
			} else {
				// null没有处理方式
				throw new Exception("不支持的 value 类型");
			}
		}
	}

	private static void map2Xml(Map<String, Object> map, Element nodeElement, String listItemName, String type)
			throws Exception {

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			// 给list的每个对象添加根节点Item
			if ((value instanceof List) && StringUtils.isBlank(type)) {
				Element element = nodeElement.addElement(key);
				List<Map<String, Object>> list = (List<Map<String, Object>>) value;
				for (Map<String, Object> subMap : list) {
					Element itemElement = element.addElement(listItemName);
					map2Xml(subMap, itemElement, type);
				}
			} else if ((value instanceof List) && StringUtils.isNotBlank(type)
					&& type.contains(map2Xml_TYPE_listElementWrapper)) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) value;
				for (Map<String, Object> subMap : list) {
					Element element = nodeElement.addElement(key);
					map2Xml(subMap, element, type);
				}
			} else if (value instanceof Map) {
				Element element = nodeElement.addElement(key);
				Map<String, Object> valueMap = (Map<String, Object>) value;
				map2Xml(valueMap, element, type);
			} else if (value instanceof String) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText((String) value);
			} else if (value instanceof Integer) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText(((Integer) value).toString());
			} else if (value instanceof Long) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText(((Long) value).toString());
			} else if (null == value) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText("");
			} else if (value instanceof BigDecimal) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText(((BigDecimal) value).toString());
			} else if (value instanceof Date) {
				Element keyElement = nodeElement.addElement(key);
				keyElement.setText((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value)).toString());
			} else {
				// null没有处理方式
				throw new Exception("不支持的 value 类型");
			}
		}
	}

	/**
	 * GBK转UTF-8<br>
	 * 
	 * @param gbk
	 * @return <br>
	 */
	public static String gbkToUtf8(String gbk) {
		String utf8 = null;
		try {
			utf8 = new String(new String(gbk.getBytes("UTF-8"), "ISO-8859-1").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utf8;
	}

	/**
	 * 获取指定路径元素的文本值
	 * @param xml xml文本
	 * @param xpaxh 元素XPATH路径
	 * @return
	 * @throws DocumentException
	 */
	public static List<String> getElementsText(String xml, String xpaxh) throws DocumentException {
		if(isXmlDocument(xml)) {
			Document document = DocumentHelper.parseText(xml);
			return getElementsText(document, xpaxh);
		}else{
			return new ArrayList<>();
		}
	}

	/**
	 * 获取指定路径元素的文本值
	 * @param document xml文档对象
	 * @param xpaxh 元素XPATH路径
	 * @return
	 * @throws DocumentException
	 */
	public static List<String> getElementsText(Document document, String xpaxh) throws DocumentException {
		List<String> values = new ArrayList<>();
		List<org.dom4j.Node> elements = document.selectNodes(xpaxh);
		elements.stream().forEach(element -> {
			values.add(((Element) element).getTextTrim());
		});
		return values;
	}


	/**
	 * 判断字符串是否是xml格式
	 * @param rtnMsg
	 * @return
	 */
	private static boolean isXmlDocument(String rtnMsg) {
		boolean flag = true;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			builder.parse(new InputSource(new StringReader(rtnMsg)));
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
