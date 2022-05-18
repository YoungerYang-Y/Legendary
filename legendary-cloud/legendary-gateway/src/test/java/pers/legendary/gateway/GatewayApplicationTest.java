package pers.legendary.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/28 20:49
 */
@Slf4j
class GatewayApplicationTest {

    @Test
    void main() throws Exception {
        String content = "sundy1647822810777{\"time\":\"2022-03-16\"}";
        String key = "CF7E8ED8BBC543AB8CC9C5E4A6182F6F";
        System.out.println("原文=" + content);
        String s1 = GatewayApplicationTest.encryptAES(content, key);
        System.out.println(s1);
        System.out.println("8E3A3CA1B86FEEA0354EE9900C9DAF0AC8567D45971317BD9F2C7BBCDDAD97AE8D47F5B4220A9E223D994B781BC2C176FDCBD615DD55EC22DF196B50B471020B");
        System.out.println("8E3A3CA1B86FEEA0354EE9900C9DAF0AC8567D45971317BD9F2C7BBCDDAD97AE8D47F5B4220A9E223D994B781BC2C176FDCBD615DD55EC22DF196B50B471020B".equalsIgnoreCase(s1));
    }

    /**
     * @param data 明文
     * @param key  密钥，长度16
     * @param iv   偏移量，长度16
     * @return 密文
     * @author miracle.qu
     * @see AES算法加密明文
     */
    public static String encryptAES(String data, String key) throws Exception {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            int blockSize = cipher.getBlockSize();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            StringBuilder sb = new StringBuilder();
            for (byte b : encrypted) {
                String s = Integer.toHexString(b & 0xFF);
                if (s.length() == 1) {
                    sb.append("0");
                }
                sb.append(s);
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}