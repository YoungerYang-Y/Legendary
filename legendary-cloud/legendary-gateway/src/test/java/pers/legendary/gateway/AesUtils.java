package pers.legendary.gateway;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/28 21:48
 */
public class AesUtils {
    private static final String SECRET = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * AES/ECB/PKCS7Padding  加密
     */
    public static String AESECBPkcs5PaddingEncrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        byte[] keyBytes = key.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, SECRET));
        byte[] dataBytes = data.getBytes();
        byte[] doFinal = cipher.doFinal(dataBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : doFinal) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() < 2) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

//    /**
//     * AES/ECB/PKCS7Padding  解密
//     */
//    public static String AESECBPkcs5PaddingDecrypt(String str, String key) throws Exception {
//        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
//        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, SECRET));
//        byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(str));
//        return new String(doFinal);
//    }

    /**
     * 函数测试
     */
    public static void main(String[] args) throws Exception {
        String str = "sundy1647822810777{\"time\":\"2022-03-16\"}";
        String key = "CF7E8ED8BBC543AB8CC9C5E4A6182F6F";
        System.out.println("原始字符串：" + str);
        String encryptStr = AESECBPkcs5PaddingEncrypt(str, key);
        System.out.println("加密字符串：" + encryptStr);
//        String decryptStr = AESECBPkcs7PaddingDecrypt(encryptStr, key);
//        System.out.println("解密字符串：" + decryptStr);
        System.out.println("加密字符串：" + "8E3A3CA1B86FEEA0354EE9900C9DAF0A7E7782C4F1F5D469A5657A739D2168610BD98C79B633143ED9D9EA907FC098D6");
        System.out.println("8E3A3CA1B86FEEA0354EE9900C9DAF0A7E7782C4F1F5D469A5657A739D2168610BD98C79B633143ED9D9EA907FC098D6".equalsIgnoreCase(encryptStr));
    }
}
