package GitHub;
import java.io.IOException;
import java.util.Scanner;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class Analyzer {

	
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);	
		String url = "";
		String auth_token = System.getenv("token");
		
		if (auth_token == null || auth_token.isBlank()) {
            throw new IllegalStateException("Failed to get the token");
        }
		
		System.out.println("Enter your url here: ");
		url = scan.nextLine();
		
		
        

       display(auth_token, url);
	}
	private static String repoFromUrl(String url) {
	    // Remove trailing slash if present
	    if (url.endsWith("/")) {
	        url = url.substring(0, url.length() - 1);
	    }
	    //divides url into smaller parts divided my "/" and after gets the last and before last elements
	    String[] parts = url.split("/");
	    String owner = parts[parts.length - 2];
	    String repo = parts[parts.length - 1];
	    //returns owner/repo names to use as correct url for git
	    return owner + "/" + repo;
	}
	static void display( String auth_token,String url) throws IOException {
		GitHub github = GitHub.connectUsingOAuth(auth_token);
		url = repoFromUrl(url);
		GHRepository repo = github.getRepository(url);
		
		 System.out.println("Name: " + repo.getName());
		 System.out.println("Owner's name: " + repo.getOwnerName());
		 if (repo.getDescription() == null || repo.getDescription().isBlank()) {
		 System.out.println("No description yet");
		 }else System.out.println("Description: " + repo.getDescription());
		 
	        System.out.println("Stars: " + repo.getStargazersCount());
	        if (repo.listLanguages().isEmpty() || repo.listLanguages() == null) {
	   		 System.out.println("No languages yet, or it is to small for github to detect");
	   		 }else
	        System.out.println("Language: " + repo.listLanguages());
	        
	        System.out.println("Size of repositery: " + repo.getSize());
	        System.out.println("Default branch: " + repo.getDefaultBranch());
	}

}

