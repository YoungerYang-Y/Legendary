# 基于OAuth2的统一认证中心

## 用途

* 提供获取token接口：对用户提供的用户名、密码进行校验，给用户进行授权，确定用户能访问哪些资源，返回一个JWT
* 提供获取公钥接口：提供接口`/rsa/publicKey`，获取公钥
* 提供token验证接口：提供接口`/oauth/check_token`，提供给其他服务验证token是否正确

## 创建oauth2.jks

1. 生成jks，设置密钥库口令，例如：`2sMsWi`

    ```bash
    # 生成oauth2.jks
    keytool -genkey -alias oauth2 -keyalg RSA -keystore oauth2.jks -keypass 2sMsWi -keysize 2048 -validity 3650
   
    # 迁移到行业标准格式PKCS12
    keytool -importkeystore -srckeystore oauth2.jks -destkeystore oauth2.jks -deststoretype pkcs12
    ```

2. 导出证书

    ```bash
    keytool -export -alias oauth2 -file public.cer -keystore oauth2.jks
    ```

3. 查看公钥

   ```bash
   keytool -list -rfc --keystore oauth2.jks | openssl x509 -inform pem -pubkey
   ```