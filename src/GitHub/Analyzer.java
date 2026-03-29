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
		
		
		System.out.println("Enter your url here: ");
		url = scan.nextLine();
		url = repoFromUrl(url);
		if (auth_token == null || auth_token.isBlank()) {
            throw new IllegalStateException("Failed to get the token");
        }
		
        GHRepository repo = github.getRepository(url);

        System.out.println("Name: " + repo.getName());
        System.out.println("Description: " + repo.getDescription());
        System.out.println("Stars: " + repo.getStargazersCount());
        System.out.println("Language: " + repo.getLanguage());
        System.out.println("Stars: " + repo.getOwnerName());
        System.out.println("Language: " + repo.getSize());
        System.out.println("br: " + repo.getDefaultBranch());
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
}

