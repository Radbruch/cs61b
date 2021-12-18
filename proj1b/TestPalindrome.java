import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate*/
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome1() {
        String input1 = "a";
        String input2 = "";
        String input3 = "racecar";
        String input4 = "noon";
        String input5 = "Racecar";
        String input6 = "horse";
        String input7 = "rancor";
        String input8 = "aaaaab";

        boolean expected1 = true;
        boolean actual1 = Palindrome.isPalindrome(input1);
        org.junit.Assert.assertEquals(expected1, actual1);

        boolean expected2 = true;
        boolean actual2 = Palindrome.isPalindrome(input2);
        org.junit.Assert.assertEquals(expected2, actual2);

        boolean expected3 = true;
        boolean actual3 = Palindrome.isPalindrome(input3);
        org.junit.Assert.assertEquals(expected3, actual3);

        boolean expected4 = true;
        boolean actual4 = Palindrome.isPalindrome(input4);
        org.junit.Assert.assertEquals(expected4, actual4);

        boolean expected5 = false;
        boolean actual5 = Palindrome.isPalindrome(input5);
        org.junit.Assert.assertEquals(expected5, actual5);

        boolean expected6 = false;
        boolean actual6 = Palindrome.isPalindrome(input6);
        org.junit.Assert.assertEquals(expected6, actual6);

        boolean expected7 = false;
        boolean actual7 = Palindrome.isPalindrome(input7);
        org.junit.Assert.assertEquals(expected7, actual7);

        boolean expected8 = false;
        boolean actual8 = Palindrome.isPalindrome(input8);
        org.junit.Assert.assertEquals(expected8, actual8);
    }

    @Test
    public void testIspalindrome2(){
        CharacterComparator cc = new OffByOne();

        String input1 = "ij"; // true
        String input2 = "ji"; // true
        String input3 = "iaj"; // true
        String input4 = "jai"; // true
        String input5 = "jabi"; // true
        String input6 = "malbn"; // true
        String input7 = "mblman"; // true
        String input8 = "jaj"; // false
        String input9 = "mbln"; // false
        String input10 = "nw"; // false

        boolean actrual1 = Palindrome.isPalindrome(input1, cc);
        org.junit.Assert.assertTrue(actrual1);

        boolean actrual2 = Palindrome.isPalindrome(input2, cc);
        org.junit.Assert.assertTrue(actrual2);

        boolean actrual3 = Palindrome.isPalindrome(input3, cc);
        org.junit.Assert.assertTrue(actrual3);

        boolean actrual4 = Palindrome.isPalindrome(input4, cc);
        org.junit.Assert.assertTrue(actrual4);

        boolean actrual5 = Palindrome.isPalindrome(input5, cc);
        org.junit.Assert.assertTrue(actrual5);

        boolean actrual6 = Palindrome.isPalindrome(input6, cc);
        org.junit.Assert.assertTrue(actrual6);

        boolean actrual7 = Palindrome.isPalindrome(input7, cc);
        org.junit.Assert.assertTrue(actrual7);

        boolean actrual8 = Palindrome.isPalindrome(input8, cc);
        org.junit.Assert.assertFalse(actrual8);

        boolean actrual9 = Palindrome.isPalindrome(input9, cc);
        org.junit.Assert.assertFalse(actrual9);

        boolean actrual10 = Palindrome.isPalindrome(input10, cc);
        org.junit.Assert.assertFalse(actrual10);

    }
}
