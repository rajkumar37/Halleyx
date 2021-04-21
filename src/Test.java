// Java program to perform the
// encryption and decryption
// using asymmetric key

//package java_cryptography;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.xml.bind
	.DatatypeConverter;

public class Test {

	private static final String RSA
		= "RSA";
	private static Scanner sc;

	// Generating public & private keys
	// using RSA algorithm.
	public static KeyPair generateRSAKkeyPair()
		throws Exception
	{
		SecureRandom secureRandom
			= new SecureRandom();
		KeyPairGenerator keyPairGenerator
			= KeyPairGenerator.getInstance(RSA);

		keyPairGenerator.initialize(
			2048, secureRandom);
		return keyPairGenerator
			.generateKeyPair();
	}

	// Encryption function which converts
	// the plainText into a cipherText
	// using private Key.
	public static byte[] do_RSAEncryption(
		String plainText,
		PrivateKey privateKey)
		throws Exception
	{
		Cipher cipher
			= Cipher.getInstance(RSA);

		cipher.init(
			Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(
			plainText.getBytes());
	}

	// Decryption function which converts
	// the ciphertext back to the
	// orginal plaintext.
	public static String do_RSADecryption(
		byte[] cipherText,
		PublicKey publicKey)
		throws Exception
	{
		Cipher cipher
			= Cipher.getInstance(RSA);

		cipher.init(Cipher.DECRYPT_MODE,
					publicKey);
		byte[] result
			= cipher.doFinal(cipherText);

		return new String(result);
	}

	// Driver code
	public static void main(String args[])
		throws Exception
	{
		KeyPair keypair
			= generateRSAKkeyPair();
		 try {
		      File file = new File("C:\\Users\\Raj\\Downloads\\raj.pdf");
		      byte [] bytes = Files.readAllBytes(file.toPath());

		    final String ss = Base64.getEncoder().encodeToString(bytes);
		   
		      System.out.println(ss); //-> "JVBERi..."
		    } catch (Exception e) {
		      e.printStackTrace();
		    }

		String plainText = "This is the PlainText "
						+ "I want to Encrypt using RSA.";

		byte[] cipherText
			= do_RSAEncryption(
				plainText,
				keypair.getPrivate());

		System.out.println(
			"The Public Key is: "
			+ DatatypeConverter.printHexBinary(
				keypair.getPublic().getEncoded()));

		System.out.println(
			"The Private Key is: "
			+ DatatypeConverter.printHexBinary(
				keypair.getPrivate().getEncoded()));

		System.out.print("The Encrypted Text is: ");

		System.out.println(
			DatatypeConverter.printHexBinary(
				cipherText));

		String decryptedText
			= do_RSADecryption(
				cipherText,
				keypair.getPublic());

		System.out.println(
			"The decrypted text is: "
			+ decryptedText);
	}
}
