package GitHub;
import java.io.IOException;
import java.util.Scanner;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class Analyzer {

	
	public static void main(String[] args) throws Exception{
		//opens scanner to read url from user
		Scanner scan = new Scanner(System.in);
		//stores url and stores token from OS 
		String url = "";
		String auth_token = System.getenv("token");
		//checks token, and if its valid to use, if not, throws exception
		if (auth_token == null || auth_token.isBlank()) {
            throw new IllegalStateException("Failed to get the token");
        }
		//enter url here and read it
		System.out.println("Enter your url here: ");
		url = scan.nextLine();
		//passing arguments to the method, and displays the information
       display(auth_token, url);
       //closes scanner
       scan.close();
	}
	//method to split url into pieces divided by slash, and after extracts right version of url to use 
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
	//method that shows information about repository 
	static void display( String auth_token,String url) throws IOException {
		//creates github objects that establishes connection to github using auth token
		GitHub github = GitHub.connectUsingOAuth(auth_token);
		//uses method to get the right url
		url = repoFromUrl(url);
		//creates object that gets the repository by url
		GHRepository repo = github.getRepository(url);
		
		
		//displays information, and checks variables for null/empty where appropriate 
		 System.out.println("Name: " + repo.getName());
		 System.out.println("Owner's name: " + repo.getOwnerName());
		 if (repo.getDescription() == null || repo.getDescription().isBlank()) {
		 System.out.println("No description yet");
		 }else System.out.println("Description: " + repo.getDescription());
		 
	        System.out.println("Stars: " + repo.getStargazersCount());
	        if (repo.listLanguages().isEmpty() || repo.listLanguages() == null) {
	   		 System.out.println("No languages yet, or it is to small for github to detect");
	   		 }else
	        System.out.println("Languages in bites: " + repo.listLanguages());
	        
	        System.out.println("Size of repositery in kilobytes is: " + repo.getSize());
	        System.out.println("Default branch: " + repo.getDefaultBranch());
	        System.out.println("---------");
	        System.out.println("Default branch: " + repo.getBranches());
	        System.out.println("COmmits");
	        
	      
	        //creates object, that allows to work with commits part of repository 
	     
	       //gets information related to commit
	        GHCommit commit = repo.listCommits().toList().get(0);

	        
	        System.out.println(commit.getCommitShortInfo().getMessage());
	        System.out.println(commit.getCommitDate());
	        System.out.println(commit.getAuthor());
	}

}

