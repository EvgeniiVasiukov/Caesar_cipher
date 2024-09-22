package org.example.avecaesarapp;

import Business_Logic.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


import java.io.File;
import java.io.IOException;
import java.util.function.UnaryOperator;

public class CipherController {
    // Элементы 1 вкладки
    @FXML
    private Button chooseFileButton1;

    @FXML
    private Button chooseFileButton2;

    @FXML
    private TextField filePathTextField1;

    @FXML
    private TextField filePathTextField2;

    @FXML
    private RadioButton readFromFileRadioButton;

    @FXML
    private RadioButton readFromConsoleRadioButton;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private TextField keyTextField;

    @FXML
    private Button startEncryptButton;

    //для объединения логики радиобаттонов

    @FXML
    private ToggleGroup inputToggleGroup;

    @FXML
    private ToggleGroup decryptToggleGroup;

    // Элементы для второй вкладки

    @FXML
    private RadioButton readFromFileDecryptRadioButton;

    @FXML
    private RadioButton readFromConsoleDecryptRadioButton;

    @FXML
    private TextField decryptFilePathTextField;

    @FXML
    private TextField decryptFileDestPathTextField;

    @FXML
    private TextField keyTextField2;

    @FXML
    private Button chooseFileDecryptButton;

    @FXML
    private Button chooseFileDestDecryptButton;

    @FXML
    private TextArea inputDecryptTextArea;

    @FXML
    private TextArea outputDecryptTextArea;

    @FXML
    private Button startDecryptButton;

    //элементы для третьей вкладки

    @FXML
    private Button startBruteDecrypButton;

    @FXML
    private TextArea getOutputBruteTextArea;


    @FXML
    private TextField keySolutionTextfield;

    @FXML
    private TextField encryptedFilePathBrute;


    @FXML
    public void initialize() {

        // Слушатели изменений текстовых полей и ключевого поля
        // Слушатель для inputTextArea
        inputTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateEncryptButtonState();
            }
        });


        // Слушатель для keyTextField
        keyTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateEncryptButtonState();
            }
        });

        //слушатель для filePathTextField1
        filePathTextField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateEncryptButtonState();
            }
        });

        //слушатель для filePathTextField2
        filePathTextField2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateEncryptButtonState();
            }
        });

        //слушатель для inputDecryptTextArea
        inputDecryptTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateDecryptButtonState();
            }
        });

        //слушатель для outputDecryptTextArea
        outputDecryptTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateDecryptButtonState();
            }
        });

        //слушатель для keyTextField2
        keyTextField2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateDecryptButtonState();
            }
        });

        //слушатель для decryptFilePathTextField
        decryptFilePathTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateDecryptButtonState();
            }
        });
        //слушатель для decryptFileDestPathTextField
        decryptFileDestPathTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateDecryptButtonState();
            }
        });


        //слушатель для encryptedFilePathBrute
        encryptedFilePathBrute.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateDecryptBruteButtonState();
            }
        });

        // Установить изначальное состояние кнопок
        updateEncryptButtonState();
        updateDecryptButtonState();
        updateDecryptBruteButtonState();









        // Установка группы переключателей для выбора источника данных
        ToggleGroup inputToggleGroup = new ToggleGroup();
        readFromFileRadioButton.setToggleGroup(inputToggleGroup);
        readFromConsoleRadioButton.setToggleGroup(inputToggleGroup);

        ToggleGroup decryptToggleGroup = new ToggleGroup();
        readFromFileDecryptRadioButton.setToggleGroup(decryptToggleGroup);
        readFromConsoleDecryptRadioButton.setToggleGroup(decryptToggleGroup);

        // Изначально проверяем выбранное значение и устанавливаем состояние кнопок
        if (readFromConsoleRadioButton.isSelected()) {
            chooseFileButton1.setDisable(true);
            chooseFileButton2.setDisable(true);
            inputTextArea.setDisable(false);
            outputTextArea.setDisable(false);
        } else if (readFromFileRadioButton.isSelected()) {
            chooseFileButton1.setDisable(false);
            chooseFileButton2.setDisable(false);
            inputTextArea.setDisable(true);
            outputTextArea.setDisable(true);
        }





        // Логика изменения интерфейса при выборе источника данных
        inputToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) {
                if (newToggle == readFromFileRadioButton) {
                    chooseFileButton1.setDisable(false);
                    chooseFileButton2.setDisable(false);
                    inputTextArea.setDisable(true);
                    outputTextArea.setDisable(true);
                    inputTextArea.clear();
                    outputTextArea.clear();
                } else if (newToggle == readFromConsoleRadioButton) {
                    chooseFileButton1.setDisable(true);
                    chooseFileButton2.setDisable(true);
                    inputTextArea.setDisable(false);
                    outputTextArea.setDisable(false);
                    filePathTextField1.clear();
                    filePathTextField2.clear();
                }

            }
        });

        if (readFromFileDecryptRadioButton.isSelected()) {
            inputDecryptTextArea.setDisable(true);
            outputDecryptTextArea.setDisable(true);
            chooseFileDecryptButton.setDisable(false);
            decryptFilePathTextField.setDisable(false);
            chooseFileDestDecryptButton.setDisable(false);
            decryptFileDestPathTextField.setDisable(false);
        } else if (readFromConsoleDecryptRadioButton.isSelected()) {
            inputDecryptTextArea.setDisable(false);
            outputDecryptTextArea.setDisable(false);
            chooseFileDecryptButton.setDisable(true);
            decryptFilePathTextField.setDisable(true);
            chooseFileDestDecryptButton.setDisable(true);
            decryptFileDestPathTextField.setDisable(true);
        }

        decryptToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (t1 == readFromFileDecryptRadioButton) {
                    inputDecryptTextArea.setDisable(true);
                    inputDecryptTextArea.clear();
                    outputDecryptTextArea.setDisable(true);
                    outputDecryptTextArea.clear();
                    chooseFileDecryptButton.setDisable(false);
                    decryptFilePathTextField.setDisable(false);
                    chooseFileDestDecryptButton.setDisable(false);
                    decryptFileDestPathTextField.setDisable(false);

                }
                else if (t1 == readFromConsoleDecryptRadioButton) {
                    inputDecryptTextArea.setDisable(false);
                    outputDecryptTextArea.setDisable(false);
                    chooseFileDecryptButton.setDisable(true);
                    decryptFilePathTextField.setDisable(true);
                    decryptFilePathTextField.clear();
                    chooseFileDestDecryptButton.setDisable(true);
                    decryptFileDestPathTextField.setDisable(true);
                    decryptFileDestPathTextField.clear();

                }
            }
        });

        // Установка фильтра для ввода только цифр в keyTextField
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                String newText = change.getControlNewText();
                if (newText.matches("\\d*")) { // Разрешаем только цифры
                    return change;
                }
                return null;
            }
        };

        keyTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, filter));
        keyTextField2.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, filter));

        // Очищаем поле при первом клике, если оно содержит начальный текст
        keyTextField.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (keyTextField.getText().equals("Set the key...")) {
                    keyTextField.clear();
                }
            }
        });


    }



    @FXML
    public void chooseFile(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String buttonId = sourceButton.getId();

        TextField targetTextField = null;

        switch (buttonId) {
            case "chooseFileButton1":
                targetTextField = filePathTextField1;
                break;
            case "chooseFileButton2":
                targetTextField = filePathTextField2;
                break;
            case "chooseFileDecryptButton":
                targetTextField = decryptFilePathTextField;
                break;
            case "chooseFileDestDecryptButton":
                targetTextField = decryptFileDestPathTextField;
                break;
            case "chooseFileDecryptBruteButton":
                targetTextField = encryptedFilePathBrute;
                break;
            default:
                System.out.println("Unknown button ID: " + buttonId); // Для отладки
                break;
        }


        if (targetTextField != null) {
            selectFile(targetTextField);
        }
    }

    // Метод для выбора файла через диалоговое окно
    private void selectFile(TextField filePathTextField) {
        // Создаем объект FileChooser для открытия диалога выбора файла
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        // Опционально: установить фильтр файлов (например, только текстовые файлы)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        // Открываем диалоговое окно для выбора файла
        Stage stage = (Stage) filePathTextField.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Если файл выбран, устанавливаем путь в TextField
        if (selectedFile != null) {
            filePathTextField.setText(selectedFile.getAbsolutePath());
        } else {
            // Если файл не был выбран (диалог был закрыт без выбора), выводим сообщение в консоль
            System.out.println("Файл не был выбран.");
        }

    }
    private void updateEncryptButtonState() {
        // Проверка на заполненность полей
    boolean isInputFilled = !inputTextArea.getText().trim().isEmpty();
    boolean isKeyFilled = !keyTextField.getText().trim().isEmpty();
    boolean isInputFilePathFilled = !filePathTextField1.getText().trim().isEmpty();
    boolean isOutputFilePathFilled = !filePathTextField2.getText().trim().isEmpty();

    // Установка состояния кнопки
        startEncryptButton.setDisable((!(isInputFilled && isKeyFilled)) && (!(isInputFilePathFilled && isOutputFilePathFilled && isKeyFilled)));
}
    private void updateDecryptButtonState(){
        //Та же проверка на заполненность полей для второй вкладки
        boolean isInputFilled = !inputDecryptTextArea.getText().trim().isEmpty();
        boolean isOutputFilled = !outputDecryptTextArea.getText().trim().isEmpty();
        boolean isKeyFilled = !keyTextField2.getText().trim().isEmpty();
        boolean isInputFilePathFilled = !decryptFilePathTextField.getText().trim().isEmpty();
        boolean isFileDestPathFilled = !decryptFileDestPathTextField.getText().trim().isEmpty();

        // Установка состояния кнопки
        startDecryptButton.setDisable((!(isInputFilled && isKeyFilled)) && (!(isInputFilePathFilled && isFileDestPathFilled && isKeyFilled)));
    }

    private void updateDecryptBruteButtonState() {
        //Та же проверка на заполненность полей для третьей вкладки
        boolean isInputFilled = !encryptedFilePathBrute.getText().trim().isEmpty();

        startBruteDecrypButton.setDisable(!isInputFilled);
    }
    @FXML
    private void startEncryptButtonActionTab1() {
        // Проверяем, какая радиокнопка выбрана
        if (readFromFileRadioButton.isSelected()) {
            // Если выбрана радиокнопка "Чтение из файла", вызываем метод для работы с файлами
            startEncryptButtonWithFiles();
        } else if (readFromConsoleRadioButton.isSelected()) {
            // Если выбрана радиокнопка "Чтение из консоли", вызываем метод для работы с текстовыми полями
            startEncryptButtonWithTextareas();
        }
    }

    @FXML
    private void startEncryptButtonActionTab2() {
        // Проверяем, какая радиокнопка выбрана
        if (readFromFileDecryptRadioButton.isSelected()) {
            // Если выбрана радиокнопка "Чтение из файла", вызываем метод для работы с файлами
            startDecryptButtonWithFiles();
        } else if (readFromConsoleDecryptRadioButton.isSelected()) {
            // Если выбрана радиокнопка "Чтение из консоли", вызываем метод для работы с текстовыми полями
            startDecryptButtonWithTextareas();
        }
    }

    private void startDecryptButtonWithTextareas() {
        String inputText = inputDecryptTextArea.getText();
        int key = Integer.parseInt(keyTextField2.getText());
        Cipher cipher = new Cipher();
        String decryptedText = cipher.processText(inputText, -key);
        outputDecryptTextArea.setText("Ave, Caesar, morituri te salutant! Текст послания таков: \n\n " +
                "\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\n\n\n"+ decryptedText);
    }

    private void startDecryptButtonWithFiles() {
        String inputFilePath = decryptFilePathTextField.getText();
        String outputFilePath = decryptFileDestPathTextField.getText();
        String keyText = keyTextField2.getText();

        // Проверка инпутов
        if (inputFilePath.isEmpty() || outputFilePath.isEmpty() || keyText.isEmpty()) {
            showAlert("Error", "Please ensure all fields are filled.");
            return;
        }

        // Убедиться, что значение ключа это число
        int key;
        try {
            key = Integer.parseInt(keyText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid key format. Please enter a numeric key.");
            return;
        }

        // Вызов метода encrypt класса Cipher
        Cipher cipher = new Cipher();
        cipher.decrypt(inputFilePath, outputFilePath, key);

        // Display success message with the path of the output file
        showAlert("Success", "Цезарь доволен! Послание ему отправлено в файл: \n" + outputFilePath);

    }

    @FXML
    private void startEncryptButtonWithTextareas(){
        try {
            String inputText = inputTextArea.getText();
            int key = Integer.parseInt(keyTextField.getText());
            Cipher cipher = new Cipher();
            String encryptedText = cipher.processText(inputText, key);
            outputTextArea.setText("Надеюсь, шпионы не смогут расшифровать моё послание \n\n" +
                    "\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\n\n\n"+ encryptedText);
        }
        catch (NumberFormatException e) {
            System.out.println("Ошибка: ключ должен быть числом.");
        }
        catch (Exception e){
            outputTextArea.setText("Произошла ошибка при шифровании текста: " + e.getMessage());
        }

    }


    @FXML
    private void startEncryptButtonWithFiles() {
        String inputFilePath = filePathTextField1.getText();
        String outputFilePath = filePathTextField2.getText();
        String keyText = keyTextField.getText();

        // Проверка инпутов
        if (inputFilePath.isEmpty() || outputFilePath.isEmpty() || keyText.isEmpty()) {
            showAlert("Error", "Please ensure all fields are filled.");
            return;
        }

        // Убедиться, что значение ключа это число
        int key;
        try {
            key = Integer.parseInt(keyText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid key format. Please enter a numeric key.");
            return;
        }

        // Вызов метода encrypt класса Cipher
        Cipher cipher = new Cipher();
        cipher.encrypt(inputFilePath, outputFilePath, key);

        // Display success message with the path of the output file
        showAlert("Success", "Зашифрованное послание Гаю Цезарю хранится в: " + outputFilePath);
    }

    // Utility method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Создаем TextArea для отображения полного пути
        TextArea textArea = new TextArea(message);
        textArea.setEditable(false);  // Поле только для чтения
        textArea.setWrapText(true);   // Включаем перенос текста

        // Ограничиваем высоту и ширину TextArea
        textArea.setPrefHeight(100);
        textArea.setPrefWidth(400);

        // Добавляем TextArea в диалоговое окно
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    @FXML
    public void startBruteForceDecryption() {
        // Получаем путь к зашифрованному файлу из TextField
        String encryptedFilePath = encryptedFilePathBrute.getText();

        if (encryptedFilePath.isEmpty()) {
            getOutputBruteTextArea.setText("Пожалуйста, укажите путь к зашифрованному файлу.");
            return;
        }

        try {
            Cipher cipher = new Cipher();
            FileManager fileManager = new FileManager();
            // Создаем экземпляр BruteForce
            BruteForce bruteForce = new BruteForce(cipher, fileManager);

            // Вызываем метод расшифровки и получаем результат
            BruteForceResult result = bruteForce.decryptByBruteForce(encryptedFilePath, Alphabets.ALPHABET_RUS);

            if (result != null) {
                // Выводим лучший ключ в keySolutionTextfield
                keySolutionTextfield.setText(String.valueOf(result.getBestKey()));

                // Выводим расшифрованный текст в getOutputBruteTextArea
                getOutputBruteTextArea.setText("Подходит ли такая расшифровка?\n\uD83D\uDCDC\uD83D\uDCDC\uD83D\uDCDC\n" + result.getDecryptedText());
            } else {
                getOutputBruteTextArea.setText("Не удалось расшифровать текст.");
            }
        } catch (IOException e) {
            getOutputBruteTextArea.setText("Произошла ошибка при чтении файла: " + e.getMessage());
        }
    }

}
