public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World, here we go again");
		//SNHULogOn a = new SNHULogOn();
		//a.logOn();
		SQLConnect con = new SQLConnect();
		con.connect();
		System.out.println("This is a test for the umpteenth time");
	}
}
