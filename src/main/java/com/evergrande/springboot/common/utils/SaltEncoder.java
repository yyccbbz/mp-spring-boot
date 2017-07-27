package com.evergrande.springboot.common.utils;

import java.security.MessageDigest;
import java.util.logging.Logger;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.common.encrypt.Byte2Hex;
import com.baomidou.kisso.common.encrypt.MD5;

/**
 * <p>
 * 盐值加密工具类（常用登录密码加密）
 * </p>
 * 
 * @author cc
 * @Date 2016-01-20
 */
public class SaltEncoder {

	private static final Logger logger = Logger.getLogger("PasswordEncoder");

	/**
	 * 盐值
	 */
	private String salt;

	/**
	 * 算法
	 */
	private String algorithm;


	protected SaltEncoder() {
		/* 保护 */
	}


	public SaltEncoder( String salt, String algorithm ) {
		this.salt = salt;
		this.algorithm = algorithm;
	}

	/**
	 * 
	 * <p>
	 * md5 盐值加密字符串
	 * </p>
	 * 
	 * @param salt
	 * 				盐值
	 * @param rawText
	 *				需要加密的字符串
	 * @return
	 */
	public static String md5SaltEncode( String salt, String rawText ) {
		return new SaltEncoder(salt, MD5.ALGORITHM).encode(rawText);
	}
	
	/**
	 * 
	 * <p>
	 * 判断md5 盐值加密内容是否正确
	 * </p>
	 * 
	 * @param salt
	 * 				盐值
	 * @param encodeText
	 * 				加密后的文本内容
	 * @param rawText
	 * 				加密前的文本内容
	 * @return
	 */
	public static boolean md5SaltValid( String salt, String encodeText, String rawText ) {
		return new SaltEncoder(salt, MD5.ALGORITHM).isValid(encodeText, rawText);
	}

	/**
	 * 
	 * <p>
	 * 字符串盐值加密
	 * </p>
	 * 
	 * @param rawText
	 *            需要加密的字符串
	 * @return
	 */
	public String encode( String rawText ) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			//加密后的字符串  
			return Byte2Hex.byte2Hex(md.digest(mergeRawTextAndSalt(rawText).getBytes(SSOConfig.getSSOEncoding())));
		} catch ( Exception e ) {
			logger.severe(" SaltEncoder encode exception.");
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 
	 * <p>
	 * 判断加密内容是否正确
	 * </p>
	 * 
	 * @param encodeText
	 * 				加密后的文本内容
	 * @param rawText
	 * 				加密前的文本内容
	 * @return
	 */
	public boolean isValid( String encodeText, String rawText ) {
		return this.encode(rawText).equals(encodeText);
	}

	/**
	 * 
	 * <p>
	 * 合并混淆盐值至加密内容
	 * </p>
	 * 
	 * @param rawText
	 * 				需要加密的字符串
	 * @return
	 */
	private String mergeRawTextAndSalt( String rawText ) {
		if ( rawText == null ) {
			rawText = "";
		}

		if ( this.salt == null || "".equals(this.salt) ) {
			return rawText;
		} else {
			StringBuffer mt = new StringBuffer();
			mt.append(rawText);
			mt.append(SSOConfig.CUT_SYMBOL);
			mt.append(this.salt);
			return mt.toString();
		}
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt( String salt ) {
		this.salt = salt;
	}


	public String getAlgorithm() {
		return algorithm;
	}


	public void setAlgorithm( String algorithm ) {
		this.algorithm = algorithm;
	}

	public static void main(String[] args) {
//		String str = user.setPassword(SaltEncoder.md5SaltEncode(user.getLoginName(), user.getPassword()));
//		SaltEncoder.md5SaltValid(loginName, user.getPassword(), password)
		String str = SaltEncoder.md5SaltEncode("wangyi", "123");
		System.out.println("str = " + str);
		boolean b = SaltEncoder.md5SaltValid("wangyi", "909499dbd130c05ec4e3862a0b9b64ea", "123");
		System.out.println("b = " + b);
	}

}