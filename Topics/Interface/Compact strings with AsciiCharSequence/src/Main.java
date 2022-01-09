import java.util.*;

class AsciiCharSequence implements CharSequence {
    byte[] arr;
    
    AsciiCharSequence (byte[] str) {
        arr = new byte [str.length];
        System.arraycopy(str, 0, arr, 0, str.length);
    }
    
    @Override
    public int length() {
        return arr.length;
    }
    
    @Override
    public char charAt(int index) {
        return (char)arr[index];
    }
    
    @Override
    public String toString() {
        return new String(arr);
    }
    
    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(arr, start, end));
    }
}
