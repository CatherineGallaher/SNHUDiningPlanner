public class MainClass {

	public static void main(String[] args) {
		System.out.println("Hello World, here we go again");
		//SNHULogOn a = new SNHULogOn();
		//a.logOn();
		SQLConnect con = new SQLConnect();
		con.connect();
		String encryptThis = "Hello World";
		PasswordEncryption encryp = new PasswordEncryption();
		String encrypted = encryp.encryptionAES(encryptThis);
		PasswordDecryption decryp = new PasswordDecryption();
		String decrypted = decryp.decryptAES(encrypted);
		System.out.println(encrypted);
		System.out.println(decrypted);
		System.out.println("This is a test for the umpteenth time");
	}
}
