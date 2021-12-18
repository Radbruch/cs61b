
public class OffByN implements CharacterComparator {
    private int n = 0;

    public OffByN(int N){
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        int char1 = x;
        int char2 = y;
        int diff = char1 - char2;
        return diff == n || diff == -n;
    }
}
