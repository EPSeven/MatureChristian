package builder;

import java.io.FileWriter;
import java.io.IOException;

import htmlhelper.HtmlPageAssistant;
import htmlhelper.HtmlTagAttributes;
import htmlhelper.HtmlTags;

public class SiteHomePage extends HtmlPageAssistant implements PageBuilder {
	private static final String PAGE_FILE_NAME = "index.html";
	private static final String BODY_CLASS_NAME = "home";
	private static final String PAGE_TITLE = "Mature Christian";
	private static final String PAGE_HEADING = "What is a Mature Christian?";
	
	private static final String INTRO_TEXT = 
			"    There are a number of characteristics found in the New Testament that paint a picture of a mature Christian. Those characteristics \r\n" + 
			"    have been grouped into the six categories found below. Explore the categories to discover the characteristics and the milestones that \r\n" + 
			"    you are likely to encounter as you mature in Christ. A mature Christian has moved from being spiritually dead and lost, to being a mature \r\n" +
			"    follower of the Lord Jesus Christ. Blessings as you cooporate with the Holy Spirit at work in forming you into a mature Christian.";
	
	public SiteHomePage(String destFolder) {
		super(destFolder, PAGE_FILE_NAME);
	}

	public String getDescription() {
		return getFilePath();
	}

	public void createPage() {
		FileWriter pageFile;

		try {
			// Create the file to be written.
			pageFile = new FileWriter(getFilePath());
			
			// Write the title section.
			pageFile.write(getCommonTitleSection(PAGE_TITLE));
			
			// Add the body and page heading.
			pageFile.write(getBodyByClass(BODY_CLASS_NAME));
			pageFile.write(getPageHeading(PAGE_HEADING));
			
			// Add the "Under Construction" notification. THIS IS TEMPORARY.
			pageFile.write(getUnderConstruction());
			
			// Introduction text.
			pageFile.write(getPageIntroduction(INTRO_TEXT));
			
			// REST OF THE FILE GOES HERE
			pageFile.write(getPageLinksSection());
			
			// Close out the file's last closing tags section.
			pageFile.write(getCommonClose());
			
			// Close the file which also forces the contents to actually be saved.
			pageFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getPageLinksSection() {
		return HtmlTags.p.getTagLine() +
				getPageLinkLine("Loves/LovesLikeJesus.html", "Loves like Jesus") +
				getPageLinkLine("Trusts/TrustsInJesus.html", "Trusts in Jesus") +
				getPageLinkLine("Time/TimeWithJesus.html", "Time with Jesus") +
				getPageLinkLine("Lives/LivesForJesus.html", "Lives for Jesus") +
				getPageLinkLine("Talks/TalksAboutJesus.html", "Talks about Jesus") +
				getPageLinkLine("Looks/LooksLikeJesus.html", "Looks like Jesus") +
				HtmlTags.p.getCloseTagLine();
	}

	private String getUnderConstruction() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();
		
		attributes.addAttribute("src", "images/under-construction.png");
		attributes.addAttribute("alt", "Under Construction");
		
		return HtmlTags.center.getTagLine() + 
				HtmlTags.img.getTagWithAttributes(attributes) + 
				HtmlTags.center.getCloseTagLine();
	}
}
