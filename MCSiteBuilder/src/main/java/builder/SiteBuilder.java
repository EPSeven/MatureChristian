package builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SiteBuilder {
	public static void main(String[] args) {
		ArrayList<PageBuilder> pages;
		String destFolder;
		
		// Get the folder into which the pages are to be generated.
		destFolder = getDestinationFolder();
		
		// Get the list of pages to be created.
		pages = GeneratePageInstances(destFolder);
		
		// Build's introductory messages to the console.
		System.out.println("");
		System.out.println("Building page files for MatureChristian site...");
		System.out.println("Destination folder is " + destFolder);
		
		for (PageBuilder page : pages) {
			System.out.println("   Generating " + page.getDescription());
			page.createPage();
		}
		
		// Build's summary messages to the console.
		System.out.printf("MatureChristian site built %d pages\n", pages.size());
		System.out.println("");
	}

	private static String getDestinationFolder() {
		File destFolder;
		String fullPath = "";
		
		destFolder = new File("../docs/");
		try {
			fullPath = destFolder.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fullPath;
	}

	private static ArrayList<PageBuilder> GeneratePageInstances(String destFolder) {
		ArrayList<PageBuilder> pages;
		
		pages = new ArrayList<PageBuilder>();
		
		pages.add(new SiteHomePage(destFolder));
		
		return pages;
	}
}
