package com.assignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CreateTestData {


    public static PrintStream printer;

    static {
        try {
            FileOutputStream fOStream = new FileOutputStream("src/TestData.txt");
            printer = new PrintStream(fOStream, true, UTF_8.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("File not found");
        }
    }

    private static final int pageWidth = 90;
    private static final int pageLength = 55;
    private static final int noOfLines = 700;


    private int currWidth = 0;
    private static int currLine = 0;
    private int currPage = 1;
    public static int totalNoOfLines = 0;
    private boolean inPageHeader = false;
    private boolean inPageFooter = false;
    public static boolean flagForTestingWord = false;
    public static boolean flagForFinanceWord = false;
    public static int counterForfinanceWord = 0;


    public CreateTestData(PrintStream printer) throws Exception {
        this.printer = printer;

    }

    public void print(String str) {

        //str= generateRandomWords();
        str = str.replace("\t", "    ");
        String[] lines = str.concat("\r\n#").split("\\r?\\n");
        // print first
        printWords(lines[0]);
        for (int i = 1; i < lines.length - 1; i++) {
            newline();
            printWords(lines[i]);

        }
    }

    private String generateRandomWords() {

        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ. ,";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";


        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string

        int length= random.nextInt(10)+1;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString() + " ";


        return randomString;
    }


    private void printWords(String str) {

        String[] words = str.concat(" #").split(" ");
        printWord(words[0]);
        for (int i = 1; i < words.length - 1; i++) {
            if (currWidth < pageWidth) {
                printer.print(" ");
                currWidth++;
            }
            printWord(words[i]);
        }
    }

    /**
     * The smallest unit of appending to the document,
     */
    private void printWord(String word) {
        // determines when to print a footer
        if (currLine == 53 && !inPageFooter) {

            printPageFooter();
        }
        // determines when to print a header
        if (currLine == 0 && !inPageHeader) {
            printPageHeader();
        }

        // determines to print 'testing' word in every page
        if (currLine == 3 && flagForTestingWord == false) {
            word = word + " testing";
            flagForTestingWord = true;
        }
        // determines to print Finance word once
        if (currLine == 20 && flagForFinanceWord == false) {
            word = word + " Finance";
            flagForFinanceWord = true;
        }
        // determines when to print finance twice
        if (currLine == 6 && counterForfinanceWord < 2) {
            word = word + " finance";
            counterForfinanceWord++;
        }
        int remainingSpaceOnLine = pageWidth - currWidth;
        if (word.length() < remainingSpaceOnLine) {
            printer.print(word);
            currWidth += word.length();
        } else if (word.length() < pageWidth) {
            newline();
            printWord(word);
        }
    }

    public void newline() {
        currLine++;
        totalNoOfLines++;
        if (currLine > pageLength) {
            newPage();
        } else {
            currWidth = 0;
            printer.println();
        }
    }

    public void newPage() {

        currWidth = 0;
        currLine = 0;
        currPage++;
        flagForTestingWord = false;
        printer.println();
    }

    private void printPageHeader() {
        inPageHeader = true;
        myPageHeader();
        inPageHeader = false;
    }

    private void printPageFooter() {
        inPageFooter = true;
        myPageFooter();
        inPageFooter = false;
    }

    private void myPageHeader() {
        print("\r");
        for (int i = 0; i < 80; i++) {
            print("=");
        }
        newline();
        String padding = " ";
        String sampleCompany = "Sample Company ";
        int l = pageWidth- (sampleCompany.length()+1);
        for (int i = 0; i < l; i++)
            padding += " ";
        print(padding + sampleCompany);
        newline();
    }

    private void myPageFooter() {
        print("\r");
        for (int i = 0; i < 65; i++) {
            print("-");
        }
        String padding = "";

        int l = (pageWidth) / 2;
        for (int i = 0; i < l; i++)
            padding += " ";
        newline();
        print(padding + currPage);
        newline();
        print("S a m p l e    C o m p a n y");
        newline();

    }

    public static void main(String[] args) throws Exception {
        CreateTestData test = new CreateTestData(printer);
        for (int i = 0; totalNoOfLines < noOfLines; i++) {
            test.print("This is the song that never ends. Yes, it goes on and on my friend. "
                    + "Some people started singing it not knowing what it was "
                    + "and they'll continue singing it forever just because $999,999,999,999.99"
                    + "This is the song that never ends. Yes, it goes on and on my friend. "
                    + "\tSome people started singing it not knowing what it was "
                    + "and they'll continue singing it forever just because..."
                    + "This is the song that never ends. Yes, it goes on and on my friend. "
                    + "Some people started singing it not knowing what it was "
                    + "and they'll continue singing it forever just because $999,999,999,999.99"
                    + "This is the song that never ends. Yes, it goes on and on my friend. "
                    + "\tSome people started singing it not knowing what it was "
                    + "and they'll continue singing it forever just because..."
                    + "Some people started singing it not knowing what it was "
                    + "and they'll continue singing it forever just because..."
                    + "This is the song that never ends. Yes, it goes on and on my friend. "
                    + "Some people started singing it not knowing what it was "
                    + "\tand they'll continue singing it forever just because $999,999,999,999.99"
                    + "This is the song that never ends. Yes, it goes on and on my friend. "
                    + "Some people started singing it not knowing what it was "
                    + "and they'll continue singing it forever just because $999,999,999,999.99"
                    + "This is the song that never ends. Yes, it goes on and on my friend. "
                    + "Some people started singing it not knowing what it was ");
        }

        while (currLine < 52) {
            test.newline();
        }
        test.print("<EOF>");

    }

}

