
public class Palindrome {

    public static Deque<Character> wordToDeque(String word){
        Deque<Character> wordlist = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            wordlist.addLast(word.charAt(i));
        }
        return wordlist;
    }

    public static boolean isPalindrome(String word){
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            Deque<Character> wordlist = new LinkedListDeque<>();
            wordlist = wordToDeque(word);
            String backward = "";
            int size = wordlist.size();
            for (int i = 0; i < size; i++) {
                backward += wordlist.removeLast();
            }
            return word.equals(backward);
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() % 2 == 0){
            // Length of word is even.
            for (int i = 0; i < word.length()/2; i++) {
                if (cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1)) == false) {
                    return false;
                }
            }
            return true;
        } else {
            // Length of word is odd.
            for (int i = 0; i < (word.length()-1)/2; i++) {
                if (cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1)) == false) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args){
        CharacterComparator x = new OffByOne();
        System.out.println(isPalindrome("flake", x));
    }
}
