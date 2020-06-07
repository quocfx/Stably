package com.stably.shortproject.service;

import org.springframework.stereotype.Service;

@Service
public class BaseConversion {
    // Base 62
	private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int baseLength = allowedCharacters.length;

    // Convert base 10 to base 62
    public String encode(long input){
        StringBuilder encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % baseLength)]);
            input = input / baseLength;
        }

        return encodedString.reverse().toString();
    }

    // Convert base 62 back to base 10
    public long decode(String input) {
        char[] characters = input.toCharArray();
        int length = characters.length;
        long decoded = 0;

        // counter is used to avoid reversing input string
        int counter = 1;
		for (int i = 0; i < length; i++) {
			decoded += allowedString.indexOf(characters[i]) * Math.pow(baseLength, length - counter);
			counter++;
		}
		return decoded;
    }
}