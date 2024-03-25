package builder;

import java.util.List;

import htmlhelper.HtmlPageBase;
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

	private String getTransformations() {
		String sectionLines = "";
		List<JsonDataTransformation> transformations = characteristic.getTransformations();

		if (transformations != null) {
			sectionLines += HtmlTags.table.getTagLine();
			for (JsonDataTransformation tranInfo : transformations) {
				sectionLines += getIndent() + HtmlTags.tr.getTagLine();
				sectionLines += getIndent() + getIndent() + HtmlTags.td.getContentTagLine(tranInfo.getName());
				sectionLines += getIndent() + getIndent() + HtmlTags.td.getContentTagLine(tranInfo.getDescription());
				sectionLines += getIndent() + HtmlTags.tr.getCloseTagLine();
			}
			sectionLines += HtmlTags.table.getCloseTagLine();

			sectionLines += getEndOfLine();
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
