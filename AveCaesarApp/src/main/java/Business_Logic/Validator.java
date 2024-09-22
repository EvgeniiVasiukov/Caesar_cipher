package Business_Logic;

import java.io.File;

public class Validator {
    public boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    public int normalizeKey(int key, int alphabetSize) {
        return ((key % alphabetSize) + alphabetSize) % alphabetSize; // Коррекция отрицательных значений
    }
}
