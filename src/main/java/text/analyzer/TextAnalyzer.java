/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class TextAnalyzer {

    public static String readFileContent(String filename) throws FileNotFoundException, IOException {
        FileInputStream wordStream = new FileInputStream(filename); //подключаем входной файл текстом C:\\Users\\Admin\\Documents\\InputText.txt*/
        int lenght = wordStream.available(); //определяем размер входных данных
        byte[] data = new byte[lenght]; // создаем массив данных определенного ранее размера
        wordStream.read(data); // считывае в этот массив данные входного файла
        return new String(data);
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Please, put the full file path:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        File file = new File(name);
        if (!file.exists()) {
            System.out.println("Sorry! Wrong path provided!");
            System.exit(0);
        }

        String text = readFileContent(name);

        BracketsValidator validator = new BracketsValidator(); //создаем новый объект из класса BracketsValidator
        boolean correct = validator.validate(text); // переменная correct результат выполнения метода validate объявленного в классе BracketsValidator
        System.out.println("Brakes are " + (correct ? "" : "in") + "correct");
        String stopwords = readFileContent(TextAnalyzer.class.getClassLoader().getResource("stopwords.txt").getFile()); // "переписываем" полученные данные в текст

        String[] stopWordsArray = stopwords.split("\\b");
        List<String> stopWords = new ArrayList<>(Arrays.asList(stopWordsArray));
        text = text.toLowerCase(); //делаем все буквы строчными
        String[] wordsArray = text.split("[\\p{P} \\t\\n\\r&&[^.]]+|\\.(?!\\S)");  // разбиваем строку входного текста на отдельные слова и записываем его в массив 
        List<String> words = new ArrayList<>(Arrays.asList(wordsArray)); //перезаписываем в список
        List<String> brakes = new ArrayList<>(Arrays.asList("{", "}", "[", "]", ")", "("));
        words.removeAll(stopWords); // удаляем все местоимения, союзы, предлоги
        words.removeAll(brakes); // удаляем все скобки
        HashMap<String, Integer> wordMap = new HashMap<>(); // создаем и объявляем новую карту
        Integer item;
        for (String sourceWord : words) //по всему списку слов 
        {
            String word = sourceWord.trim(); // обрезаем лишние пробелы от слов
            if (!word.equals("")) // если элемент не равен " ", 
            {
                item = wordMap.get(word); // то слово записываем в карту, одновременно подсчитывая, сколько раз оно встречается
                if (item == null) {
                    wordMap.put(word, 1); // если нет в списке то добавить со значением 1
                } else {
                    wordMap.put(word, item + 1); // если есть такое слово, то +1
                }
            }
        }

        List topWordMap = new ArrayList(wordMap.entrySet()); // создаем новый список со значениями и ключами из карты
        Collections.sort(topWordMap, (Map.Entry<Integer, Integer> b, Map.Entry<Integer, Integer> a) -> a.getValue() - b.getValue());
// сортируем по значению (количеству слов)
        for (int i = 0; i < 10; i++) {
            System.out.println("Top words " + (i + 1) + " place: " + topWordMap.get(i));
        }

    }

}
