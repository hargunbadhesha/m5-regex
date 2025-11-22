package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * The Main method for this assignment.
     * You can optionally run this to interactively try the three methods.
     * @param args parameters are unused
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    // Method 1 for checking if a string matches a regex: using Pattern.matches
    // TODO: Modify this code to check if the given string is non-empty, contains at least
    //       one lower case letter, at least one upper case letter, and at least one digit. If the
    //       string has all of these properties, the method should return true. If it is missing one
    //       or more properties, it should return false.

    /**
     * Returns whether a given string is non-empty, contains one lower case letter,
     * at least one upper case letter, at least one digit, and meets the minimum length.
     * @param str the string to check for the properties in
     * @param minLength the minimum length required for the password
     * @return whether the string satisfies the password requirements
     */
    public static boolean checkForPassword(String str, int minLength) {
        if (str == null || str.length() < minLength) {
            return false;
        }

        // at least one lowercase
        final boolean hasLower = Pattern.matches(".*[a-z].*", str);
        // at least one uppercase
        final boolean hasUpper = Pattern.matches(".*[A-Z].*", str);
        // at least one digit
        final boolean hasDigit = Pattern.matches(".*\\d.*", str);

        return hasLower && hasUpper && hasDigit;
    }


    /**
     * Returns a list of email addresses that occur in a given string.
     * @param str the string to look for email addresses in
     * @return a list containing the email addresses in the string.
     */
    public static List<String> extractEmails(String str) {
        // one or more allowed characters before @, and domain is either mail.utoronto.ca or utoronto.ca
        final Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@(mail\\.utoronto\\.ca|utoronto\\.ca)");
        final Matcher matcher = pattern.matcher(str);
        final List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    // Method 3 for checking if a string conforms to a regex: using String.matches



    /**
     * Checks whether a given string contains the same capital letter twice.
     * @param str the string to look for doubles in
     * @return whether str contains the same capital letter twice.
     */
    public static boolean checkForDoubles(String str) {
        // .* any chars, capture a capital letter, then later the same capital again (backreference)
        return str.matches(".*([A-Z]).*\\1.*");
    }
}
