package be.pxl.bytebros.backend.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlienEncService {

    public String decodeMorse() {
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
        return str.trim().toUpperCase();
    }

    public String convertStringToHexDec(){
        String stringCode = "dTG5iA9Rx9ahBmi1";
        List<String> hexaDec = new ArrayList<String>();

        for (int i = 0; i < stringCode.length(); i++){
            hexaDec.add(stringCode.substring(i, Math.min(stringCode.length(), i + 2)));
        }
        System.out.println(hexaDec);
        return hexaDec.toString();
    }
}
