/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.analyzer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class TextAnalyzer {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
 Scanner in = new
 Scanner (System.in);    
 String name = in.nextLine();
         /*"C:\\Users\\Admin\\Documents\\NetBeansProjects\\TextAnalyzer\\InputText.txt"; JOptionPane.showInputDialog ("Пожалуйста, ведите путь к файлу");*/
 FileInputStream wordStream = new FileInputStream (name); //подключаем входной файл текстом C:\\Users\\Admin\\Documents\\InputText.txt*/
 int lenght = wordStream.available(); //определяем размер входных данных
 byte [] data = new byte [lenght]; // создаем массив данных определенного ранее размера
 wordStream.read(data); // считывае в этот массив данные входного файла
 String text = new String (data); // "переписываем" полученные данные в строку
 text =text.toLowerCase(); //делаем все буквы строчными
 
FileInputStream stopwordstream = new FileInputStream ("C:\\Users\\Admin\\Documents\\NetBeansProjects\\TextAnalyzer\\stopwords.txt"); //подключаем входной файл cо словами-исключениями
int lenghstopwords = stopwordstream.available(); //определяем размер входных данных
byte [] stopData = new byte [lenghstopwords]; // создаем массив данных определенного ранее размера
stopwordstream.read(stopData); // считывае в этот массив данные входного файла
String stopwords = new String (stopData); // "переписываем" полученные данные в текст

String [] stopWordsArray =  stopwords.split("\\b");
List<String> stopWords = new ArrayList<>(Arrays.asList(stopWordsArray));  
String [] wordsArray = text.split("[\\p{P} \\t\\n\\r&&[^.]]+|\\.(?!\\S)");  // разбиваем строку входного текста на отдельные слова и записываем его в массив 
List<String> words = new ArrayList<>(Arrays.asList(wordsArray)); //перезаписываем в список
List<String> brakes = new ArrayList<>(Arrays.asList("{", "}", "[", "]", ")", "("));
words.removeAll(stopWords); // удаляем все местоимения, союзы, предлоги
words.removeAll(brakes); // удаляем все скобки
HashMap<String,Integer> wordMap = new HashMap<>(); // создаем и объявляем новую карту
 Integer item;
 for (String sourceWord: words) //по всему списку слов 
 { 
    String word = sourceWord.trim(); // обрезаем лишние пробелы от слов
    if (!word.equals(""))  // если элемент не равен " ", 
    {
     item = wordMap.get(word); // то слово записываем в карту, одновременно подсчитывая, сколько раз оно встречается
     if (item == null) wordMap.put(word, 1); // если нет в списке то добавить со значением 1
     else wordMap.put(word, item + 1); // если есть такое слово, то +1
  }
 /*}
 for (Map.Entry entry : wordMap.entrySet())
 {
System.out.println("Word ---- " + entry.getKey() + " --- Value: " + entry.getValue()); для вывода на экран всех слов с кол-вом их повторений */
 } 
 
  List topWordMap = new ArrayList (wordMap.entrySet()); // создаем новый список со значениями и ключами из карты
      Collections.sort(topWordMap, new Comparator<Map.Entry<Integer, Integer>>() 
      {
@Override
public int compare(Map.Entry<Integer, Integer> b, Map.Entry<Integer, Integer> a) {
return b.getValue() - a.getValue();  // сортируем по значению (количеству слов)
        }
    });
   int j=topWordMap.size();
   int z =topWordMap.size()-10;   // выводим на печать 10 самых популярных :)
   System.out.println("Top words: "+topWordMap.subList(z, j));     
     //System.out.println(topWordMap);             
                  
    }
                  
}


    
