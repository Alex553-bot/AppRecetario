package tools;

import java.text.Normalizer;

public class Normalizador {
    public static String removeBlankSpaces(String text) {
        text = text.replaceAll("\\s+", " ");
        return text;
    }
    //*******
    public static String standardize(String text) {
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        text = text.trim();
        return text.toLowerCase();
    }
    
    //********
    public static String[] standardizeArray(String[] array) {
        String[] standardize_array = new String[array.length];
        for(int i = 0; i < array.length; i++) {
            standardize_array[i] = standardize(array[i]);
        }
        return standardize_array;
    }
    
    public static boolean emptyNullWord(String word) throws Exception{
        if(word.isEmpty() || word == null) {
            throw new Exception("Voids are not accepted >:v");
        }
        return false;
    }
    
    public static boolean matched(String word) throws Exception{
        boolean isNumeric = word.matches("[0-9]+");//word.matches("[+-]?\\d*(\\.\\d+)?");
        //if(isNumeric) {
          //  throw new Exception();
        //}
        return isNumeric;
    }
    
    public static String[] deleteVoid(String[] array) throws Exception {
        String res = "";
        for (String item: array) {
            if (!item.isEmpty()) {
                res += item +" ";
            }
        }
        
        if (res.isEmpty()) {
            throw new Exception("Array is empty... <(>_<)>");
        }
        return res.split(" ");
    }
    
    //*****
    public static String deleteSpaces(String word) throws Exception{
        if (word.isEmpty()) {
            throw new Exception("Word is empty... <(>_<)>");
        }
        return standardize(word.replaceAll(" ", ""));
    }
    
    public static String deletePlural(String word) {
        String last_character = word.substring(word.length()-1, word.length());
        String two_last_character = word.substring(word.length()-2, word.length());

        if (standardize(two_last_character).equals("es")) {
            return word.substring(0, word.length()-2);
        }
        
        if (standardize(last_character).equals("s")) {
            return word.substring(0, word.length()-1);
        }

        return word;
    }
    
    public static String[] deletePlural(String... words) {
        String[] simple_words = new String[words.length];
        for(int i = 0; i < words.length; i++) {
            simple_words[i] = deletePlural(words[i]);
        }
        return simple_words;
    }
}