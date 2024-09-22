package Business_Logic;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BruteForce extends Cipher{
    private Cipher caesarCipher;
    private FileManager fileManager;
    private Set<String> dictionary;

    public BruteForce(Cipher caesarCipher, FileManager fileManager) {
        dictionary = new HashSet<>();
        populateDictionary();

        this.caesarCipher = caesarCipher;
        this.fileManager = fileManager;
    }


    public BruteForceResult  decryptByBruteForce(String encryptedFilePath, char[] alphabet) throws IOException {

        String encryptedText = fileManager.readFile(encryptedFilePath);

        int alphabetSize = alphabet.length;
        String bestDecryptedText = null;
        int bestScore = 0;
        int bestKey = 0;


        for (int key = 0; key < alphabetSize; key++) {

            String decryptedText = decryptWithKey(encryptedText, key, alphabet);


            int score = calculateScore(decryptedText);


            if (score > bestScore) {
                bestScore = score;
                bestDecryptedText = decryptedText;
                bestKey = key;
            }
        }


        if (bestDecryptedText != null) {
            fileManager.writeFile(bestDecryptedText, encryptedFilePath);
            return new BruteForceResult(bestDecryptedText, bestKey);
        } else {
            return null;
        }
    }


    private String decryptWithKey(String text, int key, char[] alphabet) {
        StringBuilder result = new StringBuilder();
        int alphabetSize = alphabet.length;

        for (char currentChar : text.toCharArray()) {
            int originalPosition = getCharIndex(currentChar);

            if (originalPosition != -1) {
                int newPosition = (originalPosition - key) % alphabetSize;
                if (newPosition < 0) newPosition += alphabetSize;
                result.append(alphabet[newPosition]);
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }



    private int calculateScore(String text) {
        int score = 0;

        // Разбиваем текст на слова
        String[] words = text.split("\\s+");

        // Проверяем, сколько слов совпадает со словарем
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                score += word.length();
            }
        }

        return score;
    }

    private void populateDictionary() {
        dictionary.add("и");
        dictionary.add("в");
        dictionary.add("на");
        dictionary.add("с");
        dictionary.add("что");
        dictionary.add("не");
        dictionary.add("он");
        dictionary.add("она");
        dictionary.add("это");
        dictionary.add("по");
        dictionary.add("как");
        dictionary.add("мы");
        dictionary.add("я");
        dictionary.add("вы");
        dictionary.add("ты");
        dictionary.add("они");
        dictionary.add("у");
        dictionary.add("из");
        dictionary.add("к");
        dictionary.add("его");
        dictionary.add("её");
        dictionary.add("а");
        dictionary.add("но");
        dictionary.add("до");
        dictionary.add("за");
        dictionary.add("тот");
        dictionary.add("так");
        dictionary.add("если");
        dictionary.add("то");
        dictionary.add("уже");
        dictionary.add("или");
        dictionary.add("еще");
        dictionary.add("был");
        dictionary.add("быть");
        dictionary.add("для");
        dictionary.add("всё");
        dictionary.add("все");
        dictionary.add("как");
        dictionary.add("там");
    }
}
