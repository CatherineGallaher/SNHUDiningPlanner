import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryption {
	public static String encryptionAES(String strToEncrypt)
	{
		try 
		{
			ReadWriteFiles read = new ReadWriteFiles();
			String[] text = read.readFile();
			
			String key = text[0];
			
			String PUBLIC = text[1];
			
			byte[] iv = { 127, 6, 45, 28, 99, 28, 34, 22, 105, 46, 48, 35, 79, 31, 4, 114 };
			
			IvParameterSpec ivspc = new IvParameterSpec(iv);
			
			SecretKeyFactory fac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(key.toCharArray(), PUBLIC.getBytes(), 65536, 256);
			SecretKey tmp = fac.generateSecret(spec);
			SecretKeySpec SKEY = new SecretKeySpec(tmp.getEncoded(), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, SKEY, ivspc);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		}
		catch (Exception e)
		{
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
}
