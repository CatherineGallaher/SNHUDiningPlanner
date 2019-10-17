public class MainClass {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SNHULogOn a = new SNHULogOn();
		a.logOn();
		//SQLConnect con = new SQLConnect();
		//con.connect();
		String encryptThis = "Hello World";
		
		System.out.println("\nEncrypting and decrypting a message/password: \n");
		PasswordEncryption encryp = new PasswordEncryption();
		String encrypted = encryp.encryptionAES(encryptThis);
		PasswordDecryption decryp = new PasswordDecryption();
		String decrypted = decryp.decryptAES(encrypted);
		
		System.out.println(encrypted);
		System.out.println(decrypted);

	}
}
