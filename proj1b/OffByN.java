
public class OffByN implements CharacterComparator {
    int n = 0;

    public OffByN(int N){
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        int char1 = x;
        int char2 = y;
        if (97 <= x && x <= 122 && 97 <= y && y <= 122) {
            int diff = char1 - char2;
            return diff == n || diff == -n;
        }
        throw new IllegalArgumentException("x and y must be lowercase letters");
    }
}
