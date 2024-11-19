package be.pxl.bytebros.backend.service;


import org.springframework.stereotype.Service;

@Service
public class KianService {


    public String IsStringInOrder() {
        String value = "iJkohbjbLMMGEsf6QdUwMyEFQf8X3OxcugdvBBpYLT015ZRfyeMN06swx0z23xiZ0CGcMonVLeQfGoTvkOiTWvhuiofjOoZSYzn6GtmGU3ZzP3HlzHmgFYB792JYwV";


        char[] charArray = value.toCharArray();
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] < prev) {
                return "N";
            }

            prev = charArray[i];
        }
        return "Y";
    }


    public String decipherCyper(){
        String encryptedText = "vngryymzuhwffx";

        // Caesar cipher shift (default: 3)
        int shift = 9;

        // Decrypt the text
        return decryptCaesarCipher(encryptedText, shift);
    }

    public static String decryptCaesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                // Handle lowercase letters
                char base = 'a';
                char decryptedChar = (char) ((ch - base - shift + 26) % 26 + base);
                result.append(decryptedChar);
            } else {
                // Non-alphabetic characters remain unchanged
                result.append(ch);
            }
        }

        return result.toString();
    }
}
