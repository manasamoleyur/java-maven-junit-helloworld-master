package com.example.assignment;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataTests {


    @Test
    public void verifyStraightMatchingText() throws Exception {
        String textToMatch = "This testing is the song that never ends";
        boolean result = false;

        Scanner sc = new Scanner(new FileInputStream("src/TestData.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains(textToMatch)) {
                result = true;
                break;
            }
        }
        assertTrue(result, textToMatch + " not found");
    }

    @Test
    public void verifyMatchingTextWithRegex() throws Exception {


        boolean result = false;

        Scanner sc = new Scanner(new FileInputStream("src/TestData.txt"));
        // The regex pattern
        Pattern pattern = Pattern.compile("\\$[0-9]+,[0-9]+,[0-9]+,[0-9]+\\.[0-9]+");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Matcher match = pattern.matcher(line);
            if (match.find()) {
                System.out.println("Matched Content: " + line.substring(match.start(), match.end()));
                result = true;
                break;
            }
        }
        assertTrue(result, "Pattern did not match any string");

    }

    @Test
    public void verifyMatchingTextExists() throws Exception {
        String textToFind = "S a m p l e    C o m p a n y";
        boolean result = false;

        Scanner sc = new Scanner(new FileInputStream("src/TestData.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains(textToFind)) {
                result = true;
                break;
            }
        }
        assertTrue(result, textToFind + " not found");
    }


}

