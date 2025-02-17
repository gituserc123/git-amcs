package com.aier.cloud.biz.ui.biz.adverse.utils;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-01-09 10:15
 **/

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.aier.cloud.api.amcs.adverse.domain.AeOaPayVO;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.common.exception.BizException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Token认证测试
 *
 *  认证过程主要采用RSA非对称加密算法
 *
 * @author tzf 2020/6/9
 */

@Component
public class OaUtil {

    private final static Logger log = LoggerFactory.getLogger(OaUtil.class);

//    /**
//     * 模拟缓存服务
//     */
//    private static final Map<String,String> SYSTEM_CACHE = new HashMap <>();
    /**
     * ecology系统发放的授权许可证(appid)
     */

//    private static String APPID;
//
//
//    @Value("${oa.appid:DF15DAB7537463F7960CA3C4B7B32005}")
//    public  void setAPPID(String appid) {
//        APPID = appid;
//    }


    /**
     * 第一步：
     *
     * 调用ecology注册接口,根据appid进行注册,将返回服务端公钥和Secret信息
     */
    public static Map<String,Object> Regist(String address,RedisService rs,String appid){
        //获取当前系统RSA加密的公钥
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();
        // 客户端RSA私钥
        rs.set("AE:LOCAL_PRIVATE_KEY:"+address,privateKey, TimeUnit.HOURS.toSeconds(2));
//        SYSTEM_CACHE.put("LOCAL_PRIVATE_KEY",privateKey);
        // 客户端RSA公钥
        rs.set("AE:LOCAL_PUBLIC_KEY:"+address,publicKey, TimeUnit.HOURS.toSeconds(2));
//        SYSTEM_CACHE.put("LOCAL_PUBLIC_KEY",publicKey);
        //调用ECOLOGY系统接口进行注册
        String data = HttpRequest.post(address + "/api/ec/dev/auth/regist")
                .header("appid",appid)
                .header("cpk",publicKey)
                .timeout(2000)
                .execute().body();
        // 打印ECOLOGY响应信息
        log.info("OA注册:{}",data);
        Map<String,Object> datas = JSONUtil.parseObj(data);
        if (datas.get("msg").toString().equals("ok")){
            //ECOLOGY返回的系统公钥
            rs.set("AE:SERVER_PUBLIC_KEY:"+address,StrUtil.nullToEmpty((String)datas.get("spk")), TimeUnit.HOURS.toSeconds(2));
//        SYSTEM_CACHE.put("SERVER_PUBLIC_KEY",StrUtil.nullToEmpty((String)datas.get("spk")));
            //ECOLOGY返回的系统密钥
            rs.set("AE:SERVER_SECRET:"+address,StrUtil.nullToEmpty((String)datas.get("secrit")), TimeUnit.HOURS.toSeconds(2));
//        SYSTEM_CACHE.put("SERVER_SECRET",StrUtil.nullToEmpty((String)datas.get("secrit")));
            return datas;
        }else{
            return null;
        }

    }

    /**
     * 第二步：
     *
     * 通过第一步中注册系统返回信息进行获取token信息
     */
    public static Map<String,Object> Getoken(String address,RedisService rs,String appid){
        return Getoken(address,rs,appid,0);
    }
    public static Map<String,Object> Getoken(String address,RedisService rs,String appid,int i){
        // 从系统缓存或者数据库中获取ECOLOGY系统公钥和Secret信息
        String secret =rs.get("AE:SERVER_SECRET:"+address); // SYSTEM_CACHE.get("SERVER_SECRET");
        String spk =rs.get("AE:SERVER_PUBLIC_KEY:"+address); // SYSTEM_CACHE.get("SERVER_PUBLIC_KEY");
        // 如果为空,说明还未进行注册,调用注册接口进行注册认证与数据更新
        if (Objects.isNull(secret)||Objects.isNull(spk)){
            Regist(address,rs,appid);
            // 重新获取最新ECOLOGY系统公钥和Secret信息
            secret = rs.get("AE:SERVER_SECRET:"+address); //SYSTEM_CACHE.get("SERVER_SECRET");
            spk = rs.get("AE:SERVER_PUBLIC_KEY:"+address); //SYSTEM_CACHE.get("SERVER_PUBLIC_KEY");
        }
        // 公钥加密,所以RSA对象私钥为null
        RSA rsa = new RSA(null,spk);
        //对秘钥进行加密传输，防止篡改数据
        String encryptSecret = rsa.encryptBase64(secret,CharsetUtil.CHARSET_UTF_8,KeyType.PublicKey);
        //调用ECOLOGY系统接口进行注册
        String data = HttpRequest.post(address+ "/api/ec/dev/auth/applytoken")
                .header("appid",appid)
                .header("secret",encryptSecret)
                .header("time","3600")
                .execute().body();
        log.info("OA获取Token:{}",data);
        Map<String,Object> datas = JSONUtil.parseObj(data);

        if(ObjectUtils.isEmpty(datas.get("token")) && i<3){
            i++;
            //Secret失效，重新注册
            Regist(address,rs,address);
            return Getoken(address,rs,address,i);
        }
        //ECOLOGY返回的token
        rs.set("AE:SERVER_TOKEN:"+address,StrUtil.nullToEmpty((String)datas.get("token")),TimeUnit.MINUTES.toSeconds(29));
//        SYSTEM_CACHE.put("SERVER_TOKEN",StrUtil.nullToEmpty((String)datas.get("token")));
        return datas;
    }
    /**
     * 第三步：
     *
     * 调用ecology系统的rest接口，请求头部带上token和用户标识认证信息
     *
     * @param address ecology系统地址
     * @param api rest api 接口地址(该测试代码仅支持GET请求)
     * @param jsonParams 请求参数json串
     *
     * 注意：ECOLOGY系统所有POST接口调用请求头请设置 "Content-Type","application/x-www-form-urlencoded; charset=utf-8"
     */
    public static String Restful(String address,String api,String jsonParams,Map<String,String> headers,RedisService rs,String appid,Map formMap,String userId){
        //ECOLOGY返回的token
        log.info("OA接口调用请求:{}{},{},{},{}",address,api,jsonParams,formMap,userId);
        String token= rs.get("AE:SERVER_TOKEN:"+address);//SYSTEM_CACHE.get("SERVER_TOKEN");
        if (StrUtil.isEmpty(token)){
            token = (String) Getoken(address,rs,appid).get("token");
        }
        String spk =rs.get("AE:SERVER_PUBLIC_KEY:"+address);// SYSTEM_CACHE.get("SERVER_PUBLIC_KEY");
        //封装请求头参数
        RSA rsa = new RSA(null,spk);
        //对用户信息进行加密传输,暂仅支持传输OA用户ID
        String encryptUserid = rsa.encryptBase64(userId,CharsetUtil.CHARSET_UTF_8,KeyType.PublicKey);
        //调用ECOLOGY系统接口，注意此处的disableCookie，可翻阅hutool的文档查看
        HttpRequest httpRequest=new HttpRequest(address + api);
        String data = httpRequest.disableCookie()
                .header("appid",appid)
                .header("token",token)
                .header("userid",encryptUserid)
                .headerMap(headers,true)
                .setMethod(Method.POST)
                .form(formMap)
                .body(jsonParams)
                .execute().body();
        log.info("OA接口调用请求头:{}",httpRequest.headers());
        log.info("OA接口调用返回:{}",data);
        return data;
    }

    public static String getOaCMPY(String cmpyId,String ADDRESS,String SIGN,String APPID,RedisService redisService) {
        Map<String, String> map = new HashMap<>();
        Long ts = Calendar.getInstance().getTimeInMillis();
        String str = SIGN + ts;
        String key = DigestUtils.md5DigestAsHex(str.getBytes());
        map.put("ts", ts.toString());
        map.put("key", key);
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("subcompanycode", cmpyId);
        req.put("params", params);
        JSONObject json = new JSONObject(req);
        try {
            String res = OaUtil.Restful(ADDRESS, "/api/hrm/resful/getHrmsubcompanyWithPage", json.toJSONString(), map, redisService,APPID,null,"1");
            JSONObject JosnRes = JSONObject.parseObject(res);
            return JosnRes.getJSONObject("data").getJSONArray("dataList").getJSONObject(0).getString("id");
        } catch (Exception e) {
            log.error("{},{},{}","OA中找对应不到机构"+cmpyId,json,ADDRESS);
            e.printStackTrace();

            throw new BizException("OA中找对应不到机构"+cmpyId);
        }

    }
    public static String getOaUserId(String UserCode,String ADDRESS,String SIGN,String APPID,RedisService redisService) {
        Map<String, String> map = new HashMap<>();
        Long ts = Calendar.getInstance().getTimeInMillis();
        String str = SIGN + ts;
        String key = DigestUtils.md5DigestAsHex(str.getBytes());
        map.put("ts", ts.toString());
        map.put("key", key);
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("workcode", UserCode);
        req.put("params", params);
        JSONObject json = new JSONObject(req);
        try {
            String res = OaUtil.Restful(ADDRESS, "/api/hrm/resful/getHrmUserInfoWithPage", json.toJSONString(), map, redisService,APPID,null,"1");
            JSONObject JosnRes = JSONObject.parseObject(res);
            String userId=JosnRes.getJSONObject("data").getJSONArray("dataList").getJSONObject(0).getString("id");
            return userId;
        } catch (Exception e) {
            log.error("{},{}","OA中找不到该员工工号"+UserCode,json);
            e.printStackTrace();

            throw new BizException("OA中找不到该员工工号"+UserCode);
        }

    }
    public static Integer createNew(AeOaPayVO aeOaPayVO, String title, String userId,String SIGN,String WORKFLOWID,String ADDRESS,RedisService redisService,String APPID){
        Map<String, String> map = new HashMap<>();
        Long ts = Calendar.getInstance().getTimeInMillis();
        String str = SIGN + ts;
        String key = DigestUtils.md5DigestAsHex(str.getBytes());
        map.put("ts", ts.toString());
        map.put("key", key);
        Map<String, Object> req = new HashMap<>();
        List<Map<String,String>> mainData=new ArrayList<>();

        Class clz=aeOaPayVO.getClass();
        Field[] fldz= clz.getDeclaredFields();
        for(Field field:fldz){
            // 获取属性的名字
            String name = field.getName();
            // 将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                java.lang.reflect.Method m = clz.getDeclaredMethod("get" + name);
                // 调用getter方法获取属性值
                String value = (String) m.invoke(aeOaPayVO);
                if(!ObjectUtils.isEmpty(value)){
                    Map<String,String> parm=new HashMap<>();
                    parm.put("fieldName",field.getName());
                    parm.put("fieldValue",value);
                    mainData.add(parm);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        req.put("workflowId",WORKFLOWID);
        req.put("requestName",title);
        req.put("remark",null);
        req.put("requestLevel",null);
        req.put("mainData", mainData);
        JSONObject json = new JSONObject(req);
        try {
            String res = OaUtil.Restful(ADDRESS, "/api/workflow/paService/doCreateRequest",json.toJSONString() , map, redisService,APPID,null,userId);
            JSONObject JosnRes = JSONObject.parseObject(res);
            if (ObjectUtils.isEmpty(JosnRes.getJSONObject("data").getInteger("requestid"))){
                throw new BizException("OA创建流程失败，请联系管理员！！！");
            }
            return JosnRes.getJSONObject("data").getInteger("requestid");
        } catch (Exception e) {
            log.error("{},{}","OA创建流程失败",json);
            throw new BizException("OA创建流程失败,请确定OA中开放了上报权限");
        }
    }
}