package  com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo;

 
import java.io.*; 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 
import java.util.GregorianCalendar; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream; 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource; 
/**
 * 工具类
 * @since 2015年12月29日
 * @author yuanliu
 *
 */
public class Util {
	// ***************************日期时间的格式*********************************
	public static String JAVA_DATE_FORMATTER = "yyyy-MM-dd";
	public static String JAVA_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

	/*******************************************************************************************************************
	 * 取得系统的当前时间,类型为java.util.Date
	 *
	 * @return java.util.Date
	 ******************************************************************************************************************/
	public static java.util.Date getNowDate() {
		java.util.Date date = new java.util.Date();
		return date;
	}

	/*******************************************************************************************************************
	 * 从Date类型转化为pattern的字符串，如果date为null 则返回null
	 *
	 * @param date
	 *            进行转化的Timestamp
	 * @param pattern
	 *            转化的格式
	 * @return
	 ******************************************************************************************************************/
	public static String DateToString(Date date, String pattern) {
		String strTemp = null;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}
	public static String DateToString1(String pattern,Date date) {
		String strTemp = null;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}
	/*******************************************************************************************************************
	 * 把format格式的字符串转化为java.util.date类型
	 *
	 * @param strDate
	 * @return 返回转化后的Date对象如果strDate为null或""或转换失败 返回null
	 ******************************************************************************************************************/
	public static java.util.Date StringToDate(String strDate, String format) {
		if (strDate != null && !strDate.equals("")) {
			try {
				if(format.length()>strDate.length()) {
					String[] dates = strDate.split(" ");
					if (dates.length == 1) {
						String[] c_dates = dates[0].split("-");
						if (c_dates.length == 1 ) {
							strDate = strDate + "-01-01 00:00:00";
						}else if (c_dates.length == 2 ) {
							strDate = strDate + "-01 00:00:00";
						}else if (c_dates.length == 3 ) {
							strDate = strDate + " 00:00:00";
						}
					}else if (dates.length == 2) {
						if (dates[1] != null && !dates[1].equals("")) {
							String[] c_dates = dates[1].split(":");
							if (c_dates.length == 1 ) {
								strDate = strDate + ":00:00";
							}else if (c_dates.length == 2 ) {
								strDate = strDate + ":00";
							}else if (c_dates.length == 3 ) {
							}
						}else {
							strDate = strDate + " 00:00:00";
						}
					}
				}
				SimpleDateFormat formatter = new SimpleDateFormat(format);
				java.util.Date d = formatter.parse(strDate);
				return d;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*******************************************************************************************************************
	 * 判断字符串是否是日期格式 yyyy/mm/dd，分隔符可不同
	 *
	 * @param strExp
	 *            类型String，进行判断的字符串
	 * @return 类型Boolean，如果strExp是日期型的则返回true， 否则返回false。
	 ******************************************************************************************************************/
	public static boolean isDate(String strExp) {
		if (strExp.length() != 10)
			return false;

		try {
			Calendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.set(Integer.parseInt(strExp.substring(0, 4)),
					Integer.parseInt(strExp.substring(5, 7)) - 1,
					Integer.parseInt(strExp.substring(8, 10)));
			java.util.Date ud = cal.getTime();
			return true;
		} catch (Exception e) {
			return false;
		}

	} 

	/**
	 * 格式化数字到定长的字符串，不足长度的在数字前补0
	 *
	 * @param i
	 *            待格式化的数字
	 * @param len
	 *            将要格式化成几位
	 * @return 格式后的字符串
	 */
	public static String formatIntToStr(int i, int len) {
		String s = "";
		String t = "";
		s = String.valueOf(i);

		if (s.length() < len) {
			for (int j = 0; j < (len - s.length()); j++)
				t = t + "0";
		}
		return (t + s);
	}

	/**
	 * 时间戳转换成日期格式字符串
	 *
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(JAVA_TIME_FORMATTER);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 日期格式字符串转换成时间戳
	 *
	 * @param date
	 *            字符串日期
	 * @return
	 */
	public static Long date2TimeStamp(String date_str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(JAVA_TIME_FORMATTER);
			return (sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 *
	 * @return
	 */
	public static Long timeStamp() {
		long time = System.currentTimeMillis();
		Long t = time / 1000;
		return t;
	}

	/**
	 * 判断指定时间，是否在指定时间段内
	 * 如：指定时间段为5分钟 则判断条件： 当前时间-5分钟 <= 指定时间 <= 当前时间+5分钟
	 * @param v_time 时间戳
	 * @param 指定时间段，单位：分钟
	 * @return
	 * @throws Exception
	 */
	public static boolean validTime(String v_time, Long n) throws Exception {
		if (v_time == null || "".equals(v_time)) {
			return false;
		}
		Long m = n * 60L * 1000L;
		try {
			Date date = getNowDate(); // 获取当前时间
			Date beginDate = new Date(date.getTime() - m);
			Date endDate = new Date(date.getTime() + m);

			if(v_time.length()==10){
				//秒为单位
				v_time = v_time+"000";
			}
			Date time = new Date(Long.valueOf(v_time));
//			Long time1 = Long.valueOf(v_time);// 获得指定时间戳
//			Long time2 = date2TimeStamp(DateToString(beginDate,
//					JAVA_TIME_FORMATTER));
//			Long time3 = date2TimeStamp(DateToString(endDate,
//					JAVA_TIME_FORMATTER));
//
//			if (time2 <= time1 && time1 <= time3) {
			if(time.after(beginDate)&&time.before(endDate)){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
 
	public static String EncoderByMd5(String str) {  
	    String result = "";  
	    MessageDigest md5 = null;  
	    try {  
	        md5 = MessageDigest.getInstance("MD5");  
	        // 这句是关键  
	        md5.update(str.getBytes("UTF-8"));  
	    } catch (NoSuchAlgorithmException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    } catch (UnsupportedEncodingException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	    byte b[] = md5.digest();  
	    int i;  
	    StringBuffer buf = new StringBuffer("");  
	    for (int offset = 0; offset < b.length; offset++) {  
	        i = b[offset];  
	        if (i < 0)  
	            i += 256;  
	        if (i < 16)  
	            buf.append("0");  
	        buf.append(Integer.toHexString(i));  
	    }  
	    result = buf.toString();  
	  
	    return result;  
	}  
	public static boolean validDataMD5(String dataMD5, String data,String salt) throws Exception {
		String v_passwordMd5 = EncoderByMd5(data);
		System.out.println("完整性校验:");
		System.out.println("上传MD5:"+dataMD5);
		System.out.println("本地加密后MD5:"+v_passwordMd5);
		if (v_passwordMd5.equals(dataMD5))
			return true;
		return false;
	}
	/***
	 * 压缩Zip
	 *
	 * @param data
	 * @return
	 */
	public static byte[] zip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ZipOutputStream zip = new ZipOutputStream(bos);
			ZipEntry entry = new ZipEntry("zip");
			entry.setSize(data.length);
			zip.putNextEntry(entry);
			zip.write(data);
			zip.closeEntry();
			zip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/***
	 * 解压Zip
	 *
	 * @param data
	 * @return
	 */
	public static byte[] unZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			ZipInputStream zip = new ZipInputStream(bis);
			while (zip.getNextEntry() != null) {
				byte[] buf = new byte[1024];
				int num = -1;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((num = zip.read(buf, 0, buf.length)) != -1) {
					baos.write(buf, 0, num);
				}
				b = baos.toByteArray();
				baos.flush();
				baos.close();
			}
			zip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	public static void getFileFromBytes(byte[] b,String outFile){
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outFile);
			String fileDir = outFile.substring(0, outFile.lastIndexOf("/"));
			File fileDirFile = new File(fileDir);
			if (!fileDirFile.exists()) {
				fileDirFile.mkdirs();
			}
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	 
	/**
	 * 进行文件的拷贝，利用带缓冲的字节流
	 * @param srcFile  目标文件
	 * @param destFile  复制目的地
	 * @throws IOException
	 */
	public static void copyFileByBuffer(File srcFile,File destFile)throws IOException{
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件:"+srcFile+"不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		int bytesum = 0;
		int byteread = 0;
		InputStream inStream = new FileInputStream(srcFile); //读入原文件
		FileOutputStream fs = new FileOutputStream(destFile);
		byte[] buffer = new byte[inStream.available()];
		while ( (byteread = inStream.read(buffer)) != -1) {
			bytesum += byteread; //字节数 文件大小
			fs.write(buffer, 0, byteread);
		}

		fs.close();
		inStream.close();

	}

	public static Object toBean(String dataXml, Class cls) throws Exception,
			ClassNotFoundException {
		// String className = getBeanName(xmlStr);
		Unmarshaller un;
		StreamSource streamSource;
		StringReader sr = null;
		JAXBContext jaxbContext = JAXBContext.newInstance(cls);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		un = jaxbContext.createUnmarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		sr = new StringReader(dataXml);
		streamSource = new StreamSource(sr);
		Object o = un.unmarshal(streamSource);
		return o;
	}

    public static <T> T xmlToBean(String xml, T t) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Unmarshaller um = context.createUnmarshaller();
        StringReader sr = new StringReader(xml);
        t = (T) um.unmarshal(sr);
        return t;
    }
	   

	public static void removeFile(File srcFile) {
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件:"+srcFile+"不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		srcFile.delete();
	}
}
