package Task6;

/*
Your task is to write a program to check whether a given credit card number is valid or invalid. You
can read more about the process of checking credit card numbers at several websites, including
http://www.validcreditcardnumber.com/; the description below is a summary of the properties that
we will be using for this lab.

The first several digits of the card number indicate the issuing network of the card:
    - Visa card numbers all start with 4
    - American Express card numbers all start with 34 or 37
    - MasterCard card numbers all start with a number between 51 and 55 (inclusive), or with a
        number between 2221 and 2720 (inclusive)

The remainder of the card number is allocated by the card issuer. In general, a valid credit card
number can be anywhere between 13 and 19 digits; however, depending on the issuer, the valid
lengths can vary:
    - All Visa card numbers are of length 13, 16 or 19
    - All American Express card numbers are of length 15
    - All MasterCard card numbers are of length 16

Finally, the final digit of all credit card numbers is a check digit – that is, a digit computed from the
other digits of the card number. Including this digit means that simple errors in manually entering a
card number (such as a single mistyped digit or permutations of successive digits) will be caught
because the card will not be validated. For credit cards, the check digit is computed using the Luhn
Algorithm (https://en.wikipedia.org/wiki/Luhn_algorithm).

To test whether a credit card number is valid, proceed as follows (adapted from the Wikipedia page
above):
    1. Proceeding from the rightmost digit and moving left, double the value of every second
        digit. If the result of this doubling is greater than 9, then subtract 9 from the product.
    2. Take the sum of the resulting digits.
    3. The credit card number is valid if and only if the sum is a multiple of 10.

As a concrete example, consider the number “79927398713”. The validation process would proceed
as shown in the following table


The sum of the resulting digits is 7+9+9+4+7+6+9+7+7+2+3=70, so the number is valid according to
the Luhn algorithm.

Account number     7 9  9 2 7 3 9 8  7 1 3
Double every other 7 18 9 4 7 6 9 16 7 2 3
Digits to sum      7 9  9 4 7 6 9 7  7 2 3


What you need to do
You must implement a method to validate a credit card number. The method signature should be as
follows:
        boolean checkCardNumber (String cardNumber)
Your method should carry out all of the checks listed on the previous page and should return true if
the card number is valid and false if it is not. You can use the sample (fictional) credit card numbers
from websites such as http://www.getcreditcardnumbers.com/ to test your code. Be sure to test on
both valid and invalid numbers!



 */

public class CreditCard {

    static boolean checkCardNumber (String cardNumber) {
        // Check length first -- can't be valid if length is out of range
        if (cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }
        
        // Check for a valid prefix, and also check for correct length while doing this
        switch (cardNumber.charAt(0)) {
            case '4':
            // Visa cards should be 13, 16, or 19 digits long
            if (cardNumber.length() != 13 && cardNumber.length() != 16 && cardNumber.length() != 19) {
                return false;
            }
            break;

            
            case '3':
            // American Express cards should have 4 or 7 as second digit and should be 15 digits long
            if (cardNumber.length() != 15) {
                return false;
            }
            char digit2 = cardNumber.charAt(1);
            if (digit2 != '4' && digit2 != '7') {
                return false;
            }
            break;
            
            
            case '5':
            // MasterCard (old cards) -- should 16 digits with prefix between 51 and 55
            if (cardNumber.length() != 16) {
                return false;
            }
            int prefix = Integer.valueOf(cardNumber.substring(0, 2));
            if (prefix < 51 || prefix > 55) {
                return false;
            }
            break;
            
            
            case '2':
            // MasterCard (new cards) -- should be 16 digits with prefix between 2221 and 2720
            if (cardNumber.length() != 16) {
                return false;
            }
            int prefix2 = Integer.valueOf(cardNumber.substring(0, 4));
            if (prefix2 < 2221 || prefix2 > 2720) {
                return false;
            }
            break;
            
            
            default:
            // Prefix must be invalid so can't be a valid number
            return false;
        }
        
        
        // If we get here, prefix and length must be right. Time to do the Luhn check.
        boolean even = false;
        int total = 0;
        
        // Loop backwards through the string one character at a time
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            
            // Convert to a number
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            
            // Double even digits
            if (even) {
                int sum = digit * 2;
                if (sum > 9) {
                    sum -= 9;
                }
                total += sum;
            } else {
                total += digit;
            }
            
            // Even and odd alternate
            even = !even;
        }
        
        // Card is valid exactly if final total is a multiple of 10
        return (total % 10 == 0);
    }

    public static void main (String[] args) {

        // can only use "" respect for the string, not ''
        String number = "79927398713";  

        System.out.println(checkCardNumber(number));
    }
}
