package builder;

import java.util.List;

import htmlhelper.HtmlPageBase;
import htmlhelper.HtmlTagAttributes;
import htmlhelper.HtmlTags;
import jsondata.JsonDataCategory;
import jsondata.JsonDataCharacteristic;
import jsondata.JsonDataTransformation;

public class CharacteristicPage extends HtmlPageBase {
	private final JsonDataCategory category;
	private final JsonDataCharacteristic characteristic;

	public CharacteristicPage(String destFolder, JsonDataCharacteristic characteristicData,
			JsonDataCategory categoryData) {
		super(destFolder, characteristicData.getPageInfo());

		category = categoryData;
		characteristic = characteristicData;
	}

	@Override
	protected String getCustomBodyContent() {
		String contentLines = "";

		// Introduction block.
		contentLines += getPageIntroduction();

		// Block suggesting additional scripture references.
		contentLines += getAdditionalScriptures();

		// List of Transitions and hyperlink to each one.
		contentLines += getTransformationsList();

		// Block for the transformations.
		contentLines += getTransformations();

		// Page navigation block.
		contentLines += getCharacteristicNavigationSection();

		return contentLines;
	}

	private String getAdditionalScriptures() {
		String sectionLines = "";
		boolean firstScripture = true;
		List<String> additionalScriptures = characteristic.getAdditionalScriptures();

		if (additionalScriptures != null) {
			sectionLines += HtmlTags.p.getTag();
			sectionLines += "Scriptures to explore: ";

			for (String scripture : additionalScriptures) {
				if (!firstScripture) {
					sectionLines += "; ";
				} else {
					firstScripture = false;
				}

				sectionLines += scripture;
			}

			sectionLines += HtmlTags.p.getCloseTagLine();

			sectionLines += getEndOfLine();
		}
		return sectionLines;
	}

	private String getTransformationsList() {
		String sectionLines = "";
		String hrefTitle;
		String tranSectionLink;
		HtmlTagAttributes linkAttributes = new HtmlTagAttributes();
		List<JsonDataTransformation> transformations = characteristic.getTransformations();
		
		if (transformations != null) {
			sectionLines += HtmlTags.h3.getContentTagLine("Growth towards Christian maturity"); 
			sectionLines += HtmlTags.ul.getTagLine();
			
			for (JsonDataTransformation tranInfo : transformations) {
				hrefTitle = "#" + getLocalHyperLinkName(tranInfo);
				
				linkAttributes.clearAttributes();
				linkAttributes.addAttribute("href", hrefTitle);
				
				tranSectionLink = HtmlTags.a.getTagWithAttributes(linkAttributes) + tranInfo.getName() + HtmlTags.a.getCloseTag();
				
				sectionLines += HtmlTags.li.getContentTagLine(tranSectionLink);
				sectionLines += getEndOfLine();
			}
			
			sectionLines += HtmlTags.ul.getCloseTagLine();
			sectionLines += getEndOfLine();
		}
		
		return sectionLines;
	}
	
	private String getLocalHyperLinkName(JsonDataTransformation tranInfo) {
		return tranInfo.getName().replaceAll(" ", "-");
	}

	private String getTransformations() {
		String sectionLines = "";
		String hrefTitle;
		HtmlTagAttributes linkAttributes = new HtmlTagAttributes();
		List<JsonDataTransformation> transformations = characteristic.getTransformations();

		if (transformations != null) {
			for (JsonDataTransformation tranInfo : transformations) {
				hrefTitle = getLocalHyperLinkName(tranInfo);

				linkAttributes.clearAttributes();
				linkAttributes.addAttribute("id", hrefTitle);

				sectionLines += HtmlTags.h3.getTagWithAttributes(linkAttributes) + tranInfo.getName() + HtmlTags.h3.getCloseTagLine();
				sectionLines += HtmlTags.p.getContentTagLine(tranInfo.getDescription());
				sectionLines += getEndOfLine();
			}
		}

		return sectionLines;
	}

	private String getCharacteristicNavigationSection() {
		String sectionLines = "";

		sectionLines += HtmlTags.p.getTagLine();
		sectionLines += getIndent() + getBackArrowNavLink(category.getPageInfo());
		sectionLines += getIndent() + getHomePageNavLink();
		sectionLines += HtmlTags.p.getCloseTagLine();

		return sectionLines;
	}
}
