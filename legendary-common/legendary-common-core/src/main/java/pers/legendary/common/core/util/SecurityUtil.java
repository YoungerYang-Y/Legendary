package pers.legendary.common.core.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Description: 对 加解密 方法的封装
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-09-04 22:13:27
 */
public class SecurityUtil {
    private SecurityUtil() {

    }

    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Encodes the given raw password.
     * <p>
     * 返回的结果中已经包含了盐值，这种格式的哈希值可以通过BCryptPasswordEncoder的matches()方法来验证密码的正确性。
     *
     * @param rawPassword the raw password to be encoded
     * @return the encoded password
     */
    public static String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * A function that matches a raw password with an encoded password.
     * <p>
     * 在验证过程中，该方法会从哈希值中提取盐值，并将输入的密码与提取的盐值一起进行哈希，然后将结果与存储的哈希值进行比较。如果结果匹配，则密码被视为正确。
     *
     * @param rawPassword     the raw password to be matched
     * @param encodedPassword the encoded password to compare with
     * @return true if the passwords match, false otherwise
     */
    public static boolean match(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
