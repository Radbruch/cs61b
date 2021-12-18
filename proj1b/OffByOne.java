
public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y){
        int char1 = x;
        int char2 = y;
        int diff = char1 - char2;
        return diff == 1 || diff == -1;
    }
}
