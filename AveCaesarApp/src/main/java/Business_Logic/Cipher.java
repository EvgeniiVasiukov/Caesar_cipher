package Business_Logic;

public class Cipher {
    private static final int ALPHABET_SIZE = Alphabets.ALPHABET_RUS.length;
    FileManager fileManager = new FileManager();
    Validator validator = new Validator();

    public void encrypt(String inputFile, String outputFile, int key) {
        // Проверка существования файла
        if (!validator.isFileExists(inputFile)) {
            System.err.println("Ошибка: файл " + inputFile + " не существует.");
            return;
        }

        // Корректировка ключа
        key = validator.normalizeKey(key, ALPHABET_SIZE);

        String content = fileManager.readFile(inputFile);
        String encryptedContent = processText(content, key);
        fileManager.writeFile(encryptedContent, outputFile);
    }


    public void decrypt(String decryptedText, String outputFile, int key) {
        if (!validator.isFileExists(decryptedText)) {
            System.err.println("Ошибка: файл " + decryptedText + " не существует.");
            return;
        }

        // Корректировка ключа
        key = validator.normalizeKey(key, ALPHABET_SIZE);

        String content = fileManager.readFile(decryptedText);
        String decryptedContent = processText(content, -key);
        fileManager.writeFile(decryptedContent, outputFile);
    }

    // Метод для обработки текста (используется для шифрования и дешифрования)
    public String processText(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (char currentChar : text.toCharArray()) {
            int originalPosition = getCharIndex(currentChar);

            if (originalPosition != -1) {
                int newPosition = (originalPosition + key) % ALPHABET_SIZE;
                if (newPosition < 0) newPosition += ALPHABET_SIZE;
                result.append(Alphabets.ALPHABET_RUS[newPosition]);
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    // Метод для получения индекса символа в алфавите
    protected int getCharIndex(char character) {
        for (int i = 0; i < Alphabets.ALPHABET_RUS.length; i++) {
            if (Alphabets.ALPHABET_RUS[i] == character) {
                return i;
            }
        }
        return -1;
    }
}
