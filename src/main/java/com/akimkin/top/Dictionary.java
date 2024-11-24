package com.akimkin.top;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {
    private static Scanner scanner = new Scanner(System.in);


    private static Map<Map<String, String>, Long> dictionary = new HashMap<>();

    public int requestCount;

    public static void addWord() {
        System.out.println("Введите новое слово для записи в словарь ");
        String word;
        word = scanner.nextLine();
        System.out.println("Введите перевод слова");
        String translation = scanner.nextLine();
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put(word, translation);
        dictionary.put(tempMap, 0L);
        scanner.close();

    }

    public static void showWordAndTranslation() {
//Выводит на экран искомое слово с переводом и счетчиком
        showWord();
        // увеличивает счетчик на единицу при
        Map<String, String> tempMap = new HashMap<>();
        tempMap = dictionary.keySet().stream().collect(Collectors.toList()).get(0);
        long tempCount = 0L;
        tempCount = dictionary.get(tempMap);
        tempCount++;
        dictionary.replace(tempMap, tempCount);

    }

    public static void changeTranslationOfTheWord() {
        System.out.println("Введите слово для изменения его перевода");
        String word;
        System.out.println("Введите новый перевод слова");
        String translation;
        Long tempCount = 0L;
        translation = scanner.nextLine();
        word = scanner.nextLine();
        if (dictionary.containsKey(word)) {
            Map<String, String> tempMap = new HashMap<>();
            tempMap = dictionary.keySet().stream().collect(Collectors.toList()).get(0);
            tempMap.replace(word, translation);
            tempCount = dictionary.get(tempMap);
            dictionary.put(tempMap, tempCount);

        } else {
            addWord();
        }
        scanner.close();
    }

    public static void showWord() {
        System.out.println("Для поиска перевода введите слово ");
        String word = scanner.nextLine();
        dictionary.keySet().stream().filter(s -> s.containsKey(word)).forEach(System.out::println);


    }

    public static void changeWord() {
        System.out.println("Введите слово для его изменения");
        String word;
        System.out.println("Введите новое слово");
        String newWord;
        newWord = scanner.nextLine();
        word = scanner.nextLine();
        Long tempCount = 0L;
        try {
            if (dictionary.containsKey(word)) {
                Map<String, String> tempMap = new HashMap<>();
                tempMap = dictionary.keySet().stream().collect(Collectors.toList()).get(0);
                String translation;
                translation = tempMap.get(word);
                dictionary.remove(tempMap);
                Map<String, String> newMap = new HashMap<>();
                newMap.put(newWord, translation);
                dictionary.put(newMap, tempCount);


            }
        } catch (NullPointerException e) {
            e.getMessage();


        }


    }

    public static void topThenPopularityWord() {


    try {
        Set<String>collection= dictionary.entrySet().stream().sorted(new Comparator<Map.Entry<Map<String, String>, Long>>() {

            @Override
            public int compare(Map.Entry<Map<String, String>, Long> o1, Map.Entry<Map<String, String>, Long> o2) {

                return  (int)(o1.getValue()-o2.getValue());
            }
        }).collect(Collectors.toList()).get(0).getKey().keySet();

        int count=0;
      for (String s:collection){
          System.out.println(1+count+"-oe место: "+s);
          count++;
if (count==10){
    break;
}
      }
    }catch (Exception e){
        e.getMessage();
    }



}
public  static void  topThenOutsiders(){
    try {
        Set<String>collection= dictionary.entrySet().stream().sorted(new Comparator<Map.Entry<Map<String, String>, Long>>() {

            @Override
            public int compare(Map.Entry<Map<String, String>, Long> o1, Map.Entry<Map<String, String>, Long> o2) {

                return  (int)(o1.getValue()-o2.getValue());
            }
        }.reversed()).collect(Collectors.toList()).get(0).getKey().keySet();

        int count=0;
        for (String s:collection){
            System.out.println(1+count+"-oe место: "+s);
            count++;
            if (count==10){
                break;
            }
        }
    }catch (Exception e){
        e.getMessage();
    }


}
}