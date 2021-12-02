import java.text.Normalizer;

public class Normalizador {
    public static String standardize(String text) {
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return text.toLowerCase();
    }
    
    public static String[] standardizeArray(String[] array) {
        for(int i = 0; i < array.length; i++) {
            array[i] = standardize(array[i]);
        }
        return array;
    }
    
    public static String removeBlankSpaces(String text) {
        text = text.replaceAll("\\s+", " ");
        return text;
    }
    
    public static void emptyNullWord(String word) throws Exception {
        if (((word==null) || word.isEmpty())) {
            throw new Exception("void argument");
        }
    }

    public static boolean matched(String word) {
        try {
            emptyNullWord(word);
            return word.matches("[+-]?\\d*(\\.\\d+)?");
        } catch (Exception e) {}
        return false;
    }
}