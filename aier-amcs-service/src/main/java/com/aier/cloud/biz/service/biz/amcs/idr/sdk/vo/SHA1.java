package  com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;  

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 基础加密组件
 * 支持SHA-1、MD5、HMAC、BASE64
 * @since 2015年12月29日
 * @author yuanliu
 */
public class SHA1 {
	/**
	 * 合法时间段
	 */
	private static final Long n = 5L;
	public final static String DSRIS_STD_INTERFACE_ENCODING = "UTF8";
	private static final String KEY_SHA = "SHA-1";
	private static final String KEY_MD5 = "MD5";

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5 
	 * HmacSHA1 
	 * HmacSHA256 
	 * HmacSHA384 
	 * HmacSHA512
	 * </pre>
	 */
	private static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		//增加一位，解决符号问题
		byte[] _val = sha.digest();
		byte[] val = new byte[_val.length+1];
		val[0] = 0;
		for(int i=1;i<val.length;i++){
			val[i] = _val[i-1];
		}
		return val;
	}
	public static String encryptSHA1(String timestamp,String nonce,String token) throws Exception {
		String[] arr = new String[]{timestamp,nonce,token}; 
        Arrays.sort(arr);
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
//        System.out.println(content.toString());
		String signature = org.apache.commons.codec.digest.DigestUtils.shaHex(content.toString().getBytes("UTF-8"));

		return signature;
	}
	/**
	 * SHA加密
	 * @param timestamp 时间戳
	 * @param nonce 随机码
	 * @param token 授权码
	 * @return
	 * @throws Exception
	 */
	public static byte[]  encryptSHA(String timestamp,String nonce,String token) throws Exception {
		//
		String[] arr = new String[]{timestamp,nonce,token}; 
        Arrays.sort(arr);
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
		return encryptSHA(content.toString().getBytes("UTF-8"));

	}
	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	private static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);

	}
	
	/**
	 * 验证是否合法 验方式： 
	 * 		1、通过时间戳、随机码、授权码加密
	 * 		2、认证密钥与加密后的密文比较

	 * @param signature 认证密钥
	 * @param timestamp 时间戳
	 * @param nonce 随机码
	 * @param token 授权码
	 * @return true,认证成功；false认证失败
	 * 
	 * 备注：
	 * 	因前期已对接部分单位，不能修改加解密方式；
	 * 	考虑各个单位开发语言不同，所以新增验证方法
	 * 	20180329 yuanl
	 * 
	 * @author yuanliu
	 */
	public static boolean validToken(String signature, String timestamp, String nonce,String token) {
		try {
				String v_signature = new BigInteger(SHA1.encryptSHA(timestamp, nonce, token)).toString(64);//java
				String c_signature = SHA1.encryptSHA1(timestamp, nonce, token) ;//C#
			if (signature.equals(v_signature)) {
				return true;
			} else if (signature.equals(c_signature)){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**  
	   * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]  
	   * hexStr2ByteArr(String strIn) 互为可逆的转换过程  
	   *   
	   * @param arrB  
	   *            需要转换的byte数组  
	   * @return 转换后的字符串  
	   * @throws Exception  
	   *             本方法不处理任何异常，所有异常全部抛出  
	   */
	  public static String byteArr2HexStr(byte[] arrB) throws Exception {
	    int iLen = arrB.length;
	    // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍   
	    StringBuffer sb = new StringBuffer(iLen * 2);
	    for (int i = 0; i < iLen; i++) {
	      int intTmp = arrB[i];
	      // 把负数转换为正数   
	      while (intTmp < 0) {
	        intTmp = intTmp + 256;
	      }
	      // 小于0F的数需要在前面补0   
	      if (intTmp < 16) {
	        sb.append("0");
	      }
	      sb.append(Integer.toString(intTmp, 16));
	    }
	    return sb.toString();
	  }

	  /**  
	   * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)  
	   * 互为可逆的转换过程  
	   *   
	   * @param strIn  
	   *            需要转换的字符串  
	   * @return 转换后的byte数组  
	   * @throws Exception  
	   *             本方法不处理任何异常，所有异常全部抛出  
	   * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>  
	   */
	  public static byte[] hexStr2ByteArr(String strIn) throws Exception {
	    byte[] arrB = strIn.getBytes();
	    int iLen = arrB.length;

	    // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2   
	    byte[] arrOut = new byte[iLen / 2];
	    for (int i = 0; i < iLen; i = i + 2) {
	      String strTmp = new String(arrB, i, 2);
	      arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
	    }
	    return arrOut;
	  }

 }