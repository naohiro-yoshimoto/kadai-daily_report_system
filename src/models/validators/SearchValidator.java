package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Search;

public class SearchValidator {
    public static List<String> validate(Search r) {
        List<String> errors = new ArrayList<String>();

        String search_error = _validateSearch(r.getSearch());
        if(!search_error.equals("")) {
            errors.add(search_error);
        }

        return errors;
    }

    private static String _validateSearch(String search) {
        if(search == null || search.equals("")) {
            return "内容を入力してください。";
        }

        return "";
    }
}
