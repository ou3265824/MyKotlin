package com.olq.service.utils

import java.security.Key
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec
import javax.crypto.spec.IvParameterSpec



object SignUtils{
    // 密钥
    val secretKey = "adminSC@mix@lx100$#365#$"
    // 向量
    val iv = "0F5E3A24"
    // 加解密统一使用的编码方式
    private val encoding = "utf-8"
    /**
     * 3DES加密
     *
     * @param plainText
     * 普通文本
     * @return
     * @throws Exception
     */
    fun encode(plainText: String): String {
        var deskey: Key? = null
        val spec = DESedeKeySpec(secretKey.toByteArray())
        val keyfactory = SecretKeyFactory.getInstance("desede")
        deskey = keyfactory.generateSecret(spec)

        val cipher = Cipher.getInstance("desede/CBC/PKCS5Padding")
        val ips = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips)
        val encryptData = cipher.doFinal(plainText.toByteArray(charset(encoding)))
        //return Base64Utils.encodeToString(encryptData);
        return Base64Utils.encode(encryptData).toString()
    }

    /**
     * 3DES解密
     *
     * @param encryptText
     * 加密文本
     * @return
     * @throws Exception
     */
    fun decode(encryptText: String): String {
        var deskey: Key? = null
        val spec = DESedeKeySpec(secretKey.toByteArray())
        val keyfactory = SecretKeyFactory.getInstance("desede")
        deskey = keyfactory.generateSecret(spec)
        val cipher = Cipher.getInstance("desede/CBC/PKCS5Padding")
        val ips = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips)

        val decryptData = cipher.doFinal(Base64Utils.decode(encryptText))

        return String(decryptData, charset(encoding))
    }

    /**
     * 3DES加密
     *
     * @param plainText
     * 普通文本
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun encode(plainText: String, encryptKey: String, encryptIv: String): String {
        var deskey: Key? = null
        val spec = DESedeKeySpec(encryptKey.toByteArray())
        val keyfactory = SecretKeyFactory.getInstance("desede")
        deskey = keyfactory.generateSecret(spec)

        val cipher = Cipher.getInstance("desede/CBC/PKCS5Padding")
        val ips = IvParameterSpec(encryptIv.toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips)
        val encryptData = cipher.doFinal(plainText.toByteArray(charset(encoding)))
        return Base64Utils.encode(encryptData).toString()
    }

    /**
     * 3DES解密
     *
     * @param encryptText
     * 加密文本
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun decode(encryptText: String, encryptKey: String, encryptIv: String): String {
        var deskey: Key? = null
        val spec = DESedeKeySpec(encryptKey.toByteArray())
        val keyfactory = SecretKeyFactory.getInstance("desede")
        deskey = keyfactory.generateSecret(spec)
        val cipher = Cipher.getInstance("desede/CBC/PKCS5Padding")
        val ips = IvParameterSpec(encryptIv.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips)

        val decryptData = cipher.doFinal(Base64Utils.decode(encryptText))

        return String(decryptData, charset(encoding))
    }


}

//fun main(args: Array<String>) {
//    val text = "123456"
//    val encode = SignUtils.encode(text)
//    println("加密：$encode")
//    val decode = SignUtils.decode(encode)
//    println("解密：$decode")
////    val encode = SignUtils.encode(text, "adminSC@mix@lx100$#365#$", "01234567")
////    println("加密：$encode")
////    val decode = SignUtils.decode(encode, "adminSC@mix@lx100$#365#$", "01234567")
////    println("解密：$decode")
//    println("liuyunqiang@lx100$#365#$".length)
//}