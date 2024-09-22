package Business_Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    // Метод для чтения содержимого из файла построчно
    public String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        Path path = Paths.get(filePath); // Создаем путь к файлу

        try (BufferedReader reader = Files.newBufferedReader(path, CHARSET)) { // Создаем BufferedReader для чтения
            String line;
            while ((line = reader.readLine()) != null) { // Читаем файл построчно
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return content.toString();
    }

    // Метод для записи содержимого в файл построчно
    public void writeFile(String content, String filePath) {
        Path path = Paths.get(filePath); // Создаем путь к файлу

        try (BufferedWriter writer = Files.newBufferedWriter(path, CHARSET)) { // Создаем BufferedWriter для записи
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
