package be.pxl.bytebros.backend.test;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String stringCode = "PbBHIXQyyqdHmwqjDzp";
        StringBuilder hexBuilder = new StringBuilder();

        for (char ch : stringCode.toCharArray()) {
            hexBuilder.append(String.format("%02X", (int) ch)).append(" ");
        }
        System.out.println(hexBuilder.toString().trim().toLowerCase());
    }
}
