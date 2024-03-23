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

		// Block containing the scripture focusing the characteristic.
		contentLines += getScriptureSection();
		contentLines += getEndOfLine();

		// Introduction block.
		contentLines += getPageIntroduction();
		contentLines += getEndOfLine();

		// Block for the transformations.
		contentLines += getTransformations();
		contentLines += getEndOfLine();

		// Page navigation block.
		contentLines += getCharacteristicNavigationSection();

		return contentLines;
	}

	private String getScriptureSection() {
		String sectionLines = "";
		String scripture = characteristic.getScripture();

		if (scripture != null) {
			sectionLines += HtmlTags.p.getTagLineWithClass("scripture");
			sectionLines += getIndent() + scripture + getEndOfLine();
			sectionLines += HtmlTags.p.getCloseTagLine();
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
