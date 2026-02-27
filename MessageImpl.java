import java.io.Serializable;

public class MessageImpl implements Message, Serializable {

    private String message;
    private int charCount;
    private int digitCount;

    public MessageImpl(String message) {
        this.message = message;
    }

    @Override
    public void setCounts() {
        charCount = 0;
        digitCount = 0;
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) charCount++;
            else if (Character.isDigit(c)) digitCount++;
        }
    }

    @Override
    public int getCharacterCount() {
        return charCount;
    }

    @Override
    public int getDigitCount() {
        return digitCount;
    }
}
