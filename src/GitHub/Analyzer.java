package GitHub;
import java.util.Scanner;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class Analyzer {

	
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);	
		String url = "";
		String auth_token = System.getenv("token");
		GitHub github = GitHub.connectUsingOAuth(auth_token);
		
		
		
		
		if (auth_token == null || auth_token.isBlank()) {
            throw new IllegalStateException("Failed to get the token");
        }

        GHRepository repo = github.getRepository("meinertvladyslav/GitHub_Analyzer-");

        System.out.println("Name: " + repo.getName());
        System.out.println("Description: " + repo.getDescription());
        System.out.println("Stars: " + repo.getStargazersCount());
        
		
		
	}
}
