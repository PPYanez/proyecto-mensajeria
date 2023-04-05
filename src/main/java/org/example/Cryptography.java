package org.example;

public class Cryptography {

    static String encrypter(String message){
        char[] chars = message.toCharArray();
        String new_message = "";
        for(char x : chars){
            x += 3;
            new_message = new_message + x;
        }
        return new_message;
    }

}
