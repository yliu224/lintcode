package mock_state.leetc273_e;

public class Solution {
    public String numberToWords(int num) {
        String number = getxxx_xxx_xxxString(num);
        return number.substring(0, number.length() - 1);
    }

    private String getxxx_xxx_xxxString(int n) {
        if (n >= 1000000000000L) {
            throw new RuntimeException(n + " not valid");
        }
        int d = n / 1000000000;
        StringBuilder sb = new StringBuilder();
        switch (d) {
            case 0:
                return getxxx_xxxString(n);
            default:
                sb.append(getxxxString(d));
                sb.append("Billion ");
        }
        if (n % 1000000000 != 0) {
            sb.append(getxxx_xxxString(n % 1000000000));
        }
        return sb.toString();
    }

    private String getxxx_xxxString(int n) {
        if (n >= 1000000000) {
            throw new RuntimeException(n + " not valid");
        }
        int d = n / 1000000;
        StringBuilder sb = new StringBuilder();
        switch (d) {
            case 0:
                return getxxxxString(n);
            default:
                sb.append(getxxxString(d));
                sb.append("Million ");
        }
        if (n % 1000000 != 0) {
            sb.append(getxxxxString(n % 1000000));
        }
        return sb.toString();
    }

    private String getxxxxString(int n) {
        if (n >= 1000000) {
            throw new RuntimeException(n + " is not valid");
        }
        int d = n / 1000;
        StringBuilder sb = new StringBuilder();
        switch (d) {
            case 0:
                return getxxxString(n);
            default:
                sb.append(getxxxString(d));
                sb.append("Thousand ");
        }
        if (n % 1000 != 0) {
            sb.append(getxxxString(n % 1000));
        }
        return sb.toString();
    }

    private String getxxxString(int n) {
        if (n >= 1000) {
            throw new RuntimeException(n + " not valid");
        }
        int d = n / 100;
        StringBuilder sb = new StringBuilder();
        switch (d) {
            case 0:
                return getxxString(n);
            default:
                sb.append(getxString(d));
                sb.append("Hundred ");

        }
        if (n % 100 != 0) {
            sb.append(getxxString(n % 100));
        }
        return sb.toString();
    }

    private String getxString(int n) {
        switch (n) {
            case 0:
                return "Zero ";
            case 1:
                return "One ";
            case 2:
                return "Two ";
            case 3:
                return "Three ";
            case 4:
                return "Four ";
            case 5:
                return "Five ";
            case 6:
                return "Six ";
            case 7:
                return "Seven ";
            case 8:
                return "Eight ";
            case 9:
                return "Nine ";
            default:
                throw new RuntimeException(n + " not valid");
        }
    }

    private String get1xString(int n) {
        switch (n) {
            case 10:
                return "Ten ";
            case 11:
                return "Eleven ";
            case 12:
                return "Twelve ";
            case 13:
                return "Thirteen ";
            case 14:
                return "Fourteen ";
            case 15:
                return "Fifteen ";
            case 16:
                return "Sixteen ";
            case 17:
                return "Seventeen ";
            case 18:
                return "Eighteen ";
            case 19:
                return "Nineteen ";
            default:
                throw new RuntimeException(n + " not valid");
        }
    }

    private String getxxString(int n) {
        if (n >= 100) {
            throw new RuntimeException(n + " not valid");
        }
        int d = n / 10;
        StringBuilder sb = new StringBuilder();
        switch (d) {
            case 0:
                return getxString(n);
            case 1:
                return get1xString(n);
            case 2:
                sb.append("Twenty ");
                break;
            case 3:
                sb.append("Thirty ");
                break;
            case 4:
                sb.append("Forty ");
                break;
            case 5:
                sb.append("Fifty ");
                break;
            case 6:
                sb.append("Sixty ");
                break;
            case 7:
                sb.append("Seventy ");
                break;
            case 8:
                sb.append("Eighty ");
                break;
            case 9:
                sb.append("Ninety ");
                break;
            default:
                throw new RuntimeException(n + " not valid");
        }
        if (n % 10 != 0) {
            sb.append(getxString(n % 10));
        }
        return sb.toString();
    }
}
