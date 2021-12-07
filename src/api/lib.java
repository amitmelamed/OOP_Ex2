package api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * this class contains function that we use in all the project.
 */
public class lib {

    /**
     * the function will get Json file name and will return JSON object.
     * @param filename
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

}


