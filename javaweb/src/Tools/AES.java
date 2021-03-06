package Tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    static final String algorithmStr = "AES/ECB/PKCS5Padding";

    private static final Object TAG = "AES";

    static private KeyGenerator keyGen;

    static private Cipher cipher;

    static boolean isInited = false;

    private static  void init() {
        try {
            /**为�??�??�??�????????�??�?? KeyGenerator 对象???
             *此类???�??�??对称�??�????��???????��???????��??
             *�????��???????��??使�?��?�类??????�?? getInstance 类�?��??????????????
             *KeyGenerator 对象??????�??使�??�??�??就�??说�????��?????�????��??�??
             *???以�??�??使�?��??�?? KeyGenerator 对象??��?????�??�??步�??�????��??
             *??????�????��????��?????两�??�??�??�??�???????��????��??�??以�????��??�??�??�???????��?????
             *两�??�????��?????�??�????????对象??????�?????�??
             *�??�??�???????��?????�?????
             *??????�????��???????��?��?��??�????��?�度 ????????��?? ???�??念�??
             *�?? KeyGenerator 类中???�??�?? init ??��??�??�??????????��??两个?????��??念�???????��??
             *�?????�??�?????�?? keysize ?????��?? init ??��??�??
             *�??使�?��?��?????�??�?????级�?????�??�??�????? SecureRandom �????��??为�????��??
             *�??�?????�??�????????�??�??�????��?????�?? SecureRandom �????��?????使�?�系�?????�??????????��??�?????
             *�?? KeyGenerator 类�?????�??�??�?????�???????��???????��?? inti ??��?????
             *???为�????��??述�??�??�???????��?? init ??��????��?????�????��???????��??
             *???以�?��??�??�??�????��??�??�??�?????�??�??�??�??�????��?��?��????��??�??�??�??????????��??�????????�?????
             *??��??�??�??�????????�?????
             *??�已�??�????��?��??�??�??�??????????��??????????��??�??
             *???两个??��?? AlgorithmParameterSpec ?????��?? init ??��?????
             *??�中�??�????��??�?????�??�?? SecureRandom ?????��??
             *??????�??�????��??�??已�??�?????�??�?????级�??�??�??�????? SecureRandom �????��?��???????��??
             *�????????�??为系�?????�??????????��??�??�?????�??�????????�??�??�????��?????�?? SecureRandom �????��?????
             *�?????�?�??��??没�????��????��??�????? KeyGenerator�?????�??�????? init ??��??�??�??
             *�??�?????�??�??�??�??须�??�??�?????记�??�??�??认�??�????????
             */
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // ???�?????此�????��????????�??使�?��?��??�??�?????�????��?�度???
        keyGen.init(128); //128�?????AES???�??
        try {
            // ??????�??�??�????��??�??�????��?? Cipher 对象???
            cipher = Cipher.getInstance(algorithmStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        //???�??已�?????�?????�??�?????�??�??
        isInited = true;
    }

    private static byte[] genKey() {
        if (!isInited) {
            init();
        }
        //�????? ??????�??�??�?????(SecretKey),
        //??��??,???�??�??�??�?????,�???????��??�???????��?????�????��??�?????此�????��????????�?????�?????�????? null???
        return keyGen.generateKey().getEncoded();
    }

    private static byte[] encrypt(byte[] content, byte[] keyBytes) {
        byte[] encryptedText = null;
        if (!isInited) {
            init();
        }
        /**
         *�?? SecretKeySpec
         *???以使??��?�类??��?��??�??�??�???????��????????�??�?? SecretKey�??
         *??????须�??�??�??�??�????��?? provider ???�??SecretKeyFactory???
         *此类�??对�?�表示为�??�??�???????��??并�??没�??任�??�??�????��?��???????��????��?????�??�????��?????
         *????????��????��??�??�?????�???????��????????�??�??�????��??
         *此�???????��??�??�????��??�?????�???????��???????????�??�??�??�??�??�?????�????��??
         */
        Key key = new SecretKeySpec(keyBytes, "AES");
        try {
            // ??��????��??�?????�?? cipher???
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            //????????��?????�?????�?????解�????��??�????????�?????�??�??�????��?????�?????(�????��??�??�????????)
            encryptedText = cipher.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    private static byte[] encrypt(String content, String password) {
        try {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance(algorithmStr);//algorithmStr
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);//   ʼ
            byte[] result = cipher.doFinal(byteContent);
            return result; //
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance(algorithmStr);//algorithmStr
            cipher.init(Cipher.DECRYPT_MODE, key);//   ʼ
            byte[] result = cipher.doFinal(content);
            return result; //
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getKey(String password) {
        byte[] rByte = null;
        if (password!=null) {
            rByte = password.getBytes();
        }else{
            rByte = new byte[24];
        }
        return rByte;
    }

    /**
     * �??�??�????�转??��??16�?????
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * �??16�????�转???为�??�?????
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    //�?�???: �????????password(�????��??须�??16�?????)
    private static final String keyBytes = "abcdefgabcdefg12";

    /**
     *???�??
     */
    public static String encode(String content){
        //???�??�????????�???????��??,�?????16�????��??�??�??串形�??�?????
        return parseByte2HexStr(encrypt(content, keyBytes));
    }

    /**
     *解�??
     */
    public static String decode(String content){
        //解�??�?????,???�??�????��??�??�??串�?????16�????�转???�??�????��??�???????��??,�??为�??解�????????容�?????
        byte[] b = decrypt(parseHexStr2Byte(content), keyBytes);
        String returnValue="";
		try {
			returnValue = new String(b,"UTF-8");//�??�?????�??乱�??
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return returnValue;
    }




}
