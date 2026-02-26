public class MessageImpl implements Message {

    @Override
    public int countCharacters(String s) {
        // TODO: count alphabet characters (a-z, A-Z)
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) count++;
        }
        return count;
    }

    @Override
    public int countDigits(String s) {
        // TODO: count digit characters (0-9)
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) count++;
        }
        return count;
    }
}
