package builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jsondata.JsonDataCategory;
import jsondata.JsonDataCharacteristic;
import jsondata.JsonDataMainPage;
import jsondata.JsonDataPageInfo;
import jsondata.JsonLoader;

public class SiteBuilder {
	private final ArrayList<PageBuilder> allPages = new ArrayList<PageBuilder>();
	private final Path jsonResPath;
	private final String destFolder;

	private JsonDataMainPage jsonData;

	private SiteBuilder() {
		// Load the path to where the JSON data file lives.
		jsonResPath = Paths.get("src", "main", "resources", "MatureChristian.json");

		// Get the folder into which the pages are to be generated.
		destFolder = getDestinationFolder();
	}

	public static void main(String[] args) {
		SiteBuilder builder = new SiteBuilder();

		builder.loadData();

		builder.initHomePage();
		builder.initCategoryPages();

		builder.generatePages();
	}

	private void initHomePage() {
		PageBuilder homePage;

		// First page to be created is the site's HOME page.
		homePage = new SiteHomePage(destFolder, jsonData);

		// Add the home page to the list of all the pages to be built.
		allPages.add(homePage);
	}

	private void loadData() {
		// Build's introductory messages to the console regarding the JSON data file.
		System.out.println("");
		System.out.println("Loading JSON data file: " + jsonResPath.toFile().getAbsolutePath());

		// Load the JSON data file into the data structure that is used for the page
		// generation.
		jsonData = JsonLoader.loadJsonData(jsonResPath);
	}

	private void initCategoryPages() {
		List<JsonDataCategory> categoryList = jsonData.getCategories();
		CategoryPage categoryPage;

		if (categoryList != null) {
			for (JsonDataCategory category : categoryList) {
				// Add the Category page.
				categoryPage = new CategoryPage(destFolder, category);
				allPages.add(categoryPage);

				// Add pages for each of the characteristics within the category.
				initCharacteristicPages(category);
			}
		}
	}

	private void initCharacteristicPages(JsonDataCategory category) {
		List<JsonDataCharacteristic> characteristicsList;
		CharacteristicPage characteristicPage;
		String categoryBodyClassName = category.getPageInfo().getBodyClassName();
		JsonDataPageInfo pageInfo;

		characteristicsList = category.getCharacteristics();
		if (characteristicsList != null) {
			for (JsonDataCharacteristic characteristic : characteristicsList) {
				// Configure the fields in the Characteristic data that are to be taken from the
				// Category fields.
				pageInfo = characteristic.getPageInfo();
				pageInfo.setBodyClassName(categoryBodyClassName);

				// Add the Characteristic page.
				characteristicPage = new CharacteristicPage(destFolder, characteristic, category);
				allPages.add(characteristicPage);

			}
		}
	}

	private void generatePages() {
		// Build's introductory messages to the console as the page building begins.
		System.out.println("Building page files for MatureChristian site...");
		System.out.println("Destination folder is " + destFolder);

		// Loop through all of the initialized pages and generate each one.
		for (PageBuilder page : allPages) {
			System.out.println("   Generating " + page.getPageGenerationPath());
			page.createPage();
		}

		// Build's summary messages to the console.
		System.out.printf("MatureChristian site built %d pages\n", allPages.size());
		System.out.println("");
	}

	private String getDestinationFolder() {
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
}
