package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        final String NAME = "name";
        final String MAIN_NAME = "mainName";
        final String ALSO_KNOWN_AS = "alsoKnownAs";
        final String PLACE_OF_ORIGIN = "placeOfOrigin";
        final String DESCRIPTION = "description";
        final String IMAGE = "image";
        final String INGREDIENTS = "ingredients";

        String mainName = "";
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = "";
        String description = "";
        String image = "";
        ArrayList<String> ingredients = new ArrayList<>();

        try {
            JSONObject jsonObjectMain = new JSONObject(json);
            if (jsonObjectMain.has(NAME)) {
                JSONObject jsonObjectName = jsonObjectMain.getJSONObject(NAME);
                if (jsonObjectName.has(MAIN_NAME) && jsonObjectName.has(ALSO_KNOWN_AS)) {
                    mainName = jsonObjectName.getString(MAIN_NAME);

                    JSONArray jsonArrayKnownAs = jsonObjectName.getJSONArray(ALSO_KNOWN_AS);
                    if (jsonArrayKnownAs.length() > 0) {
                        for (int i = 0; i < jsonArrayKnownAs.length(); i++) {
                            alsoKnownAs.add(jsonArrayKnownAs.getString(i));
                        }
                    }
                }
            }
            if (jsonObjectMain.has(PLACE_OF_ORIGIN) && jsonObjectMain.has(DESCRIPTION)
                    && jsonObjectMain.has(IMAGE) && jsonObjectMain.has(INGREDIENTS)) {
                placeOfOrigin = jsonObjectMain.getString(PLACE_OF_ORIGIN);
                description = jsonObjectMain.getString(DESCRIPTION);
                image = jsonObjectMain.getString(IMAGE);

                JSONArray jsonArrayIngredients = jsonObjectMain.getJSONArray(INGREDIENTS);
                if (jsonArrayIngredients.length() > 0) {
                    for (int i = 0; i < jsonArrayIngredients.length(); i++) {
                        ingredients.add(jsonArrayIngredients.getString(i));
                    }
                }
            }

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
