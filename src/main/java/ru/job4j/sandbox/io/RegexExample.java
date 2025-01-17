package ru.job4j.sandbox.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("раз два three", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Pattern pattern1 = Pattern.compile("раз", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        String text1 = "рАз два three";
        Matcher matcher1 = pattern.matcher(text1);
        System.out.println(matcher1.matches());

        String text2 = "раз Два tHree";
        Matcher matcher2 = pattern.matcher(text2);
        System.out.println(matcher2.matches());

        Matcher matcher3 = pattern1.matcher("и раз семь");
        System.out.println(matcher3.find());
        System.out.println(matcher3.start());
        System.out.println(matcher3.end());
        System.out.println(matcher3.group(0));
        System.out.println(matcher3.replaceAll("hhhh"));

        Pattern pattern10 = Pattern.compile("11");
        String text = "111111";
        Matcher matcher10 = pattern10.matcher(text);
        while (matcher10.find()) {
            System.out.println("Найдено совпадение: " + matcher10.group());
        }
    }
}
