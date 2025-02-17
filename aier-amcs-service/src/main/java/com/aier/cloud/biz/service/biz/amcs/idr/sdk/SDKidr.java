package com.aier.cloud.biz.service.biz.amcs.idr.sdk;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrDictType;
import com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo.Base64Utils;
import com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo.DesFactory;
import com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo.SHA1;
import com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo.Util;

/**
 * 中国疾病预防控制中心公共卫生传染病、慢病及死亡信息交换数据集规范上报接口sdk
 *
 * @author luorz
 * @since 2023年5月29日 上午10:15:28
 */
@Component
public class SDKidr {

	public static final String CENTER = "idr";

	@Resource
	RestTemplate rt;

	// 头部getHttpHeaders
	public HttpHeaders getHttpHeaders(String func) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONNECTION, "Keep-Alive");
		headers.add(HttpHeaders.CONTENT_TYPE, "text/xml;charset=UTF-8");
		headers.add(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
		headers.add("SOAPAction", func);
		headers.add(HttpHeaders.CONTENT_LENGTH, "810");
		headers.add(HttpHeaders.CONTENT_LENGTH, "810");
		headers.add(HttpHeaders.USER_AGENT, "Apache-HttpClient/4.1.1 (java 1.5)");
		return headers;
	}

	public String call(String func, List<Map<String, Object>> param, AmcsIdrDictType apply, String eventId)
			throws Exception {
		String icall = "";
		String timestamp = String.valueOf(Util.timeStamp()); // 时间戳 String
		String xml = syncPamam(func, param, apply, timestamp, eventId);
		try {
			HttpHeaders headers = getHttpHeaders(func);
			HttpEntity<String> requestEntity = new HttpEntity<String>(xml, headers);
			String url = "";
			if (func.equals("transferDataToDmp")) {
				url = apply.getValueRemark();
			} else {
				url = apply.getUrl();
			}
			// System.out.print(param.toString()+"\n");
			// System.out.print(requestEntity.toString()+"\n");
			ResponseEntity<String> re = rt.postForEntity(url, requestEntity, String.class);
			int iBegn1 = re.getBody().indexOf("<return>") + "<return>".length();
			int iBegn2 = re.getBody().indexOf("</return>");

			String Ii = re.getBody().substring(iBegn1, iBegn2);

			byte[] bytes = Base64Utils.decode(Ii);
			bytes = DesFactory.decrypt(bytes, apply.getValueEnglish() + timestamp);
			String res_xml = (bytes == null ? "无数据返回" : new String(bytes, "UTF-8"));
			icall = "";
			throwBIz(res_xml, "<ErrorMessage>", "</ERROR_DETAIL>");
			throwBIz(res_xml, "<error-msg>", "</error-desc>");

		} catch (HttpServerErrorException e) {
			throw new BizException(e.toString());

		}
		return icall;
	}

	private void throwBIz(String res_xml, String iSeacrh1, String iSeacrh2) {
		if (res_xml.indexOf(iSeacrh1) > 0) {
			int iBegn1 = res_xml.indexOf(iSeacrh1) + iSeacrh1.length();
			int iBegn2 = res_xml.indexOf(iSeacrh2);
			String icall = res_xml.substring(iBegn1, iBegn2);
			if (!icall.equals("")) {
				throw new BizException("上传时发生错误：" + icall);
			}
		}
	}

	public String syncPamam(String func, List<Map<String, Object>> param, AmcsIdrDictType apply, String timestamp,
			String eventId) {
		try {
			String iCHusa = " <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.webService.sinosoft.com.cn/\">";
			iCHusa += "   <soapenv:Header/>";
			iCHusa += "   <soapenv:Body>";
			iCHusa += "      <ser:" + func + ">";
			// // 账号
			iCHusa += "         <userName>" + apply.getValueDesc() + "</userName>";
			// remark账号密码
			iCHusa += "         <passWord>" + apply.getRemark() + "</passWord>";
			// 随机定义一随机码
			String nonce = "BE0D444C90034772abc8E242";

			String token = apply.getValueEnglish();
			// 调用接口方法进行SHA加密
			String signature = new BigInteger(SHA1.encryptSHA(timestamp, nonce, token)).toString(64);
			iCHusa += "         <signature>" + signature + "</signature>";
			iCHusa += "         <timestamp>" + timestamp + "</timestamp>";
			iCHusa += "         <nonce>" + nonce + "</nonce>";
			String sb = "";
			String idata1 = "";
			String idata = "";
			if (func.equals("transferDataToDmp")) {
				sb = toRequestXml(func, param, apply);
				byte[] encodedData = DesFactory.desCrypto(sb.getBytes("UTF-8"), token + timestamp);// todo 加密后数据
				String dataMD5 = Util.EncoderByMd5(sb);
				iCHusa += "         <dataMD5>" + dataMD5 + "</dataMD5>";
				idata = new String(Base64Utils.encode(encodedData));

				  //System.out.print(sb + "\n");

				idata1 = new String(DesFactory.decrypt(encodedData, token + timestamp), "UTF-8");
				// System.out.print(idata1 + "\n");
				// System.out.print("\n" + "解密后的的");
				iCHusa += "         <data>" + idata + "</data>";
			} else {
				sb = eventId; 
				byte[] encodedData = DesFactory.desCrypto(sb.getBytes("UTF-8"), token + timestamp);
				idata = new String(Base64Utils.encode(encodedData));

				idata1 = new String(DesFactory.decrypt(encodedData, token + timestamp), "UTF-8");
				iCHusa += "         <eventId>" + idata + "</eventId>";

			}
			iCHusa += "      </ser:" + func + ">";
			iCHusa += "   </soapenv:Body>";
			iCHusa += "</soapenv:Envelope>";
			return iCHusa;

		} catch (BizException e) {
			throw e;
		} catch (Exception e) {

			throw new BizException(e);
		}
	}

	public String toRequestXml(String func, List<Map<String, Object>> param, AmcsIdrDictType apply) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		if (param != null) {
			for (Map<String, Object> result : param) {

				for (Map.Entry<String, Object> en : result.entrySet()) {
					String kk = en.getKey();// .toUpperCase();
					Object vv = en.getValue();
					if (vv instanceof Map) {
						sb.append(sb1(kk, vv));
					} else if (vv instanceof Date) {
						sb.append("<" + kk + ">").append(DateFormatUtils.format((Date) vv, "yyyy-MM-dd"))
								.append("</" + kk + ">");
					} else {
						sb.append("<" + kk + (vv == null ? "" : ">"))
								.append(vv == null ? "/>" : vv.toString() + "</" + kk + ">");
					}
				}
			}
		}
		return sb.toString();
	}

	private String sb1(String kk1, Object vv1) {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + kk1 + ">");
		for (Map.Entry<String, Object> en2 : ((Map<String, Object>) vv1).entrySet()) {
			String kk2 = en2.getKey();// .toUpperCase();
			Object vv2 = en2.getValue();
			if (vv2 instanceof Map) {
				sb.append(sb1(kk2, vv2));

			} else if (vv2 instanceof Date) {
				sb.append("<" + kk2 + ">").append(DateFormatUtils.format((Date) vv2, "yyyy-MM-dd"))
						.append("</" + kk2 + ">");
			} else {
				sb.append("<" + kk2 + (vv2 == null ? "" : ">"))
						.append(vv2 == null ? "/>" : vv2.toString() + "</" + kk2 + ">");
			}
		}
		sb.append("</" + kk1 + ">");
		return sb.toString();
	}

}
