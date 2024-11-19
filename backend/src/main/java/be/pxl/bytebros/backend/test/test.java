package be.pxl.bytebros.backend.test;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String morseCode = "--. ...- .--- --- .-.. -..- - --.- .-- --- ..- ..-";
        char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                ',', '.', '?'};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--.."};

        String[] chars = morseCode.split(" ");
        String str = "";

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < morse.length; j++) {
                if (morse[j].equals(chars[i])) {
                    str = str + abc[j] + " ";
                }
            }
        }
        System.out.println(str.trim().toUpperCase());
    }
}
