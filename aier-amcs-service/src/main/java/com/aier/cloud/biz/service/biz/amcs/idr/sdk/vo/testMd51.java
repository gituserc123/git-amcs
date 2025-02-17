package  com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class testMd51 {
    public static String EncoderByMd5(String str) {
        String result = "";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            // ����ǹؼ�
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
    public static byte[] desCrypto(byte[] data, String key) {
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes("UTF-8"));
            //����һ���ܳ׹�����Ȼ��������DESKeySpecת����
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher����ʵ����ɼ��ܲ���
            Cipher cipher = Cipher.getInstance("DES");
            //���ܳ׳�ʼ��Cipher����
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //���ڣ���ȡ���ݲ�����
            //��ʽִ�м��ܲ���
            return cipher.doFinal(data);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }

   
    public static byte[]  encryptSHA(String timestamp,String nonce,String token) throws Exception {
        String[] arr = new String[]{timestamp,nonce,token};
        Arrays.sort(arr);
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        return encryptSHA(content.toString().getBytes("UTF-8"));
    }

    private static final String KEY_SHA = "SHA-1";

    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        //����һλ�������������
        byte[] _val = sha.digest();
        byte[] val = new byte[_val.length+1];
        val[0] = 0;
        for(int i=1;i<val.length;i++){
            val[i] = _val[i-1];
        }
        return val;
    }

    public static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0xf]);
            ret.append(HEX_DIGITS[bytes[i] & 0xf]);
        }
        return ret.toString();
    }

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

}
