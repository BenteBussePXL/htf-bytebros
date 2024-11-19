package be.pxl.bytebros.backend.service;

import be.pxl.bytebros.backend.controller.data.ConvertStringToHexDecRequest;
import be.pxl.bytebros.backend.controller.data.DecodeBase64ToStringRequest;
import be.pxl.bytebros.backend.controller.data.DecodeMorseRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class AlienEncService {

    public String decodeMorse(DecodeMorseRequest decodeMorseRequest) {
        String morseCode = decodeMorseRequest.morseCode();
        char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                ',', '.', '?', ' '};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--..", "/"};

        String[] chars = morseCode.split(" ");
        StringBuilder str = new StringBuilder();

        for (String chr : chars) {
            for (int j = 0; j < morse.length; j++) {
                if (morse[j].equals(chr)) {
                    str.append(abc[j]);
                }
            }
        }
        return str.toString().toUpperCase();
    }

    public String convertStringToHexDec(ConvertStringToHexDecRequest convertStringToHexDecRequest){
        String stringCode = convertStringToHexDecRequest.stringCode();
        List<String> hexDecList = new ArrayList<>();

        for (int i = 0; i < stringCode.length(); i++){
            hexDecList.add(stringCode.substring(i, Math.min(stringCode.length(), i + 2)));
        }
        return hexDecList.toString();
    }

    public String decodeBase64ToString(DecodeBase64ToStringRequest decodeBase64ToStringRequest){
        String encodedString = decodeBase64ToStringRequest.encodedString();

        // Decode data
        byte[] valueDecoded = Base64.getDecoder().decode(encodedString);
        return Arrays.toString(valueDecoded);
    }
}
