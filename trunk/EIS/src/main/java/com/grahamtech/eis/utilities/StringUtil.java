package com.grahamtech.eis.utilities;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//import org.apache.commons.codec.binary.Base64();

public final class StringUtil {
  private static final Logger logger = LoggerFactory
      .getLogger(StringUtil.class);
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

  public static String dateToString(Date date, String dateFormat) {

    DateFormat df = new SimpleDateFormat(dateFormat); // 2015-01-13T17:59:00.050-05:00
    String formattedDateStr = df.format(date);
    return formattedDateStr;
  }

  public static Date stringToFormattedDate(String dateString,
 String dateFormat)
      throws ParseException {

    DateFormat df = new SimpleDateFormat(dateFormat); // 2015-01-13T17:59:00.050-05:00
    Date strDate = null;
    strDate = df.parse(dateString); // Wed Jan 14 11:29:25 EST 2015

    return strDate;
  }

  /**
   * Retrieves the HTTP Last-Modified header from the given URL.
   */
  public static Date getHttpHeadDate(URL resource) throws IOException {
    URLConnection conn = resource.openConnection();
    String lastModified = conn.getHeaderField("Last-Modified");
    logger.info("Resource [{}] was last modified on [{}]", resource.toString(),
        lastModified);

    SimpleDateFormat dateFormat =
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    try {
      return dateFormat.parse(lastModified);
    } catch (ParseException e) {
      System.out.println("SAU did not parse");
      logger.warn("Cannot parse last modified date [{}]", e, lastModified);
      return null;
    }
  }

  public static String modifyDateLayout(String inputDate) throws ParseException {
    Date date =
        new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).parse(inputDate);
    return new SimpleDateFormat(ConstantsUtil.DATE_FORMAT_BACKUP).format(date);
  }

  public static BigDecimal stringToBigDecimal(String decimalString) {
    Locale locale = new Locale("en", "US");

    DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(locale);
    nf.setParseBigDecimal(true);

    BigDecimal bd = (BigDecimal) nf.parse(decimalString, new ParsePosition(0));

    return bd;
    // DecimalFormat df = new DecimalFormat();
    // df.setParseBigDecimal(true);
    // try {
    // Number number = df.parse(decimalString);
    // Double doubleValue = number.doubleValue();
    // return doubleValue;
    // } catch (ParseException e) {
    // e.printStackTrace();
    // }
  }
}
