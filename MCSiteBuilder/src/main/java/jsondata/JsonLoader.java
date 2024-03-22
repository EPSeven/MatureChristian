package jsondata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonLoader {
	// System exit codes to terminates the currently running Java Virtual Machine.
	private static final int DATA_FILE_ERROR = -4;

	// Prevent construction of this class.
	private JsonLoader() {
	}

	public static JsonDataMainPage loadJsonData(Path jsonPath) {
		JsonDataMainPage data = null;

		try {
			byte[] rawBytes = Files.readAllBytes(jsonPath);
			String jsonContent = new String(rawBytes);
			ObjectMapper mapper = new ObjectMapper();

			data = mapper.readValue(jsonContent, JsonDataMainPage.class);
		} catch (IOException e) {
			System.out.println("Error reading data from " + jsonPath);
			e.printStackTrace();
			System.exit(DATA_FILE_ERROR);
		}

		if (data == null) {
			System.out.println("Json data object failed to map from file to data structures");
			System.exit(DATA_FILE_ERROR);
		} else if (data.getPageInfo() == null) {
			System.out.println("Json data object does not have pageInfo");
			System.exit(DATA_FILE_ERROR);
		} else if (data.getCategories() == null) {
			System.out.println("Json data object does not have categories");
			System.exit(DATA_FILE_ERROR);
		}

		return data;
	}
}
