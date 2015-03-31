package com.grahamtech.eis.utilities;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//import org.apache.commons.codec.binary.Base64();

public final class StringUtil {
  private static final int SALT_LENGTH = 16;
  private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
  private static final int ITERATION_COUNT = 8192;
  private static final int HASH_KEY_SIZE = 160;
  private static final int ENCRYP_KEY_SIZE = 128;
  private static Cipher cipher;
  private static SecretKey secretKey;
  private static String KEY_GEN_ALGORITHM = "AES";

  static {
    KeyGenerator keyGenerator = null;
    secretKey = null;
    try {
      keyGenerator = KeyGenerator.getInstance(KEY_GEN_ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    keyGenerator.init(ENCRYP_KEY_SIZE);
    secretKey = keyGenerator.generateKey();
    try {
      cipher = Cipher.getInstance("AES");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    }
  }

  public static String encode(byte[] bytes) {
    new org.apache.commons.codec.binary.Base64();
    return Base64.encodeBase64String(bytes);
  }

  public static byte[] decode(String base64String) {
    try {

      new org.apache.commons.codec.binary.Base64();
      return Base64.decodeBase64(base64String);
    } catch (Exception e) {

      throw new RuntimeException(e);
    }
  }

  public static byte[] nextSalt() {
    byte[] salt = new byte[SALT_LENGTH];
    SecureRandom secureRandom;
    try {
      secureRandom = SecureRandom.getInstance("NativePRNG");
      secureRandom.nextBytes(salt);
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return salt;
  }
  
  public static byte[] hashPassword(char[] password, byte[] salt)
      throws GeneralSecurityException {
    return hashPassword(password, salt, ITERATION_COUNT, HASH_KEY_SIZE);
  }

  private static byte[] hashPassword(char[] password, byte[] salt,
      int iterationCount, int keySize) throws GeneralSecurityException {
    PBEKeySpec spec = new PBEKeySpec(password, salt, iterationCount, keySize);
    SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
    return factory.generateSecret(spec).getEncoded();
  }

  // Use the following method to authenticate a supplied password, having
  // retrieved the hash and salt from our database.
  public static boolean matches(char[] password, byte[] passwordHashFromDB,
      byte[] saltFromDB) throws GeneralSecurityException {
    return matches(password, passwordHashFromDB, saltFromDB, ITERATION_COUNT,
        HASH_KEY_SIZE);
  }

  private static boolean matches(char[] password, byte[] passwordHash,
      byte[] salt, int iterationCount, int keySize)
      throws GeneralSecurityException {
    return Arrays.equals(passwordHash,
        hashPassword(password, salt, iterationCount, keySize));
  }

  public static String encrypt(String plainText) throws Exception {
    return encrypt(plainText, secretKey);
  }

  private static String encrypt(String plainText, SecretKey secretKey)
      throws Exception {
    byte[] plainTextByte = plainText.getBytes();
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedByte = cipher.doFinal(plainTextByte);
    String encryptedText = encode(encryptedByte);
    return encryptedText;
  }

  public static String decrypt(String encryptedText) throws Exception {
    return decrypt(encryptedText, secretKey);
  }

  private static String decrypt(String encryptedText, SecretKey secretKey)
      throws Exception {
    byte[] encryptedTextByte = decode(encryptedText);
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
    String decryptedText = new String(decryptedByte);
    return decryptedText;
  }
}
