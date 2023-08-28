# 基于OAuth2的统一认证中心

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