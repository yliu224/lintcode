package string.lc604;

public class StringIterator {
    private String cstr;
    private char c;
    private int size;

    public StringIterator(String compressedString) {
        this.cstr = compressedString;
        this.size = 0;
    }

    public char next() {
        //注意细节
        if (cstr.isEmpty() && size == 0) {
            return ' ';
        }
        if (size == 0) {
            c = cstr.charAt(0);
            cstr = cstr.substring(1);
            size = getSize();
        }
        size--;
        return c;
    }

    //注意细节
    public boolean hasNext() {
        return !cstr.isEmpty() || size != 0;
    }

    private int getSize() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cstr.length(); i++) {
            if (cstr.charAt(i) >= '0' && cstr.charAt(i) <= '9') {
                sb.append(cstr.charAt(i));
            } else {
                break;
            }
        }
        cstr = cstr.substring(sb.length());
        try {
            return Integer.parseInt(sb.toString());
        } catch (Exception e) {
            throw new RuntimeException("Not a valide size " + sb.toString());
        }
    }
}
