package GitHub;

import java.util.Scanner;

public class Analyzer {

	
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);	
		String url = "";
		String auth_token = System.getenv("token");
		
		System.out.println("hello");
		
		System.out.println(auth_token);
		if (auth_token == null || auth_token.isBlank()) {
            throw new IllegalStateException("Missing GitHub token");
        }
		
	}
}
