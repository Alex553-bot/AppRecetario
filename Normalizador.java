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
    
    public static boolean emptyNullWord(String word) throws Exception{
        if(word.isEmpty() || word == null) {
            throw new Exception("Voids are not accepted >:v");
        }
        return false;
    }
        
    public static boolean matched(String word) throws Exception{
        boolean isNumeric = word.matches("[+-]?\\d*(\\.\\d+)?");
        if(isNumeric) {
            throw new Exception();
        }
        return isNumeric;
    }
}