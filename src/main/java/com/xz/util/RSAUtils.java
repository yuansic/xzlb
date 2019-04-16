package com.xz.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * @author yuansc
 * @date 2019/2/28 0028 下午 12:46
 */
public class RSAUtils {

    static final String ALGORITHM_RSA = "RSA";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }

    /**
     * 生成密钥对
     * @throws Exception
     */
    public static Map<String, String> generatorKeyPair() throws Exception {

        String publicKey = null;
        String privateKey = null;
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        byte[] keyBs = rsaPublicKey.getEncoded();
        publicKey = encodeBase64(keyBs);
        System.out.println("生成的公钥：\r\n" + publicKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        keyBs = rsaPrivateKey.getEncoded();
        privateKey = encodeBase64(keyBs);
        System.out.println("生成的私钥：\r\n" + privateKey);

        Map<String,String> map = new HashMap<>();

        map.put("publicKey",publicKey);
        map.put("privateKey",privateKey);

        return map;

    }

    /**
     * 公钥解密
     * @param target
     * @throws Exception
     */
    public static String decryptionByPublicKey(String target,PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        cipher.update(decodeBase64(target));
        String source = new String(cipher.doFinal(), "UTF-8");
        System.out.println("公钥解密后的数据：\r\n" + source);
        return source;
    }


    /**
     * 私钥加密
     * @param source
     * @return
     * @throws Exception
     */
    public String encryptionByPrivateKey(String source,PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        cipher.update(source.getBytes("UTF-8"));
        String target = encodeBase64(cipher.doFinal());
        System.out.println("私钥加密后的数据：\r\n" + target);
        return target;
    }


    /**
     * 私钥解密
     * @param target
     * @throws Exception
     */
    public static String decryptionByPrivateKey(String target,PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        cipher.update(decodeBase64(target));
        String source = new String(cipher.doFinal(), "UTF-8");
        System.out.println("私钥解密后的数据：\r\n" + source);
        return source;
    }

    /**
     * 公钥加密
     * @param source
     * @return
     * @throws Exception
     */
    public static String encryptionByPublicKey(String source,PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        cipher.update(source.getBytes("UTF-8"));
        String target = encodeBase64(cipher.doFinal());
        System.out.println("公钥加密后的数据：\r\n" + target);
        return target;
    }

    /**
     * 获取公钥
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(publicKeySpec);
    }

    /**
     * 获取私钥
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    /**
     * base64编码
     * @param source
     * @return
     * @throws Exception
     */
    static String encodeBase64(byte[] source) throws Exception{
        return new String(Base64.encodeBase64(source), "UTF-8");
    }

    /**
     * Base64解码
     * @param target
     * @return
     * @throws Exception
     */
    static byte[] decodeBase64(String target) throws Exception{
        return Base64.decodeBase64(target.getBytes("UTF-8"));
    }

}
