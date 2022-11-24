package org.example;

import pack.Packer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String hex = "0123456789abcdef";
    public static String toHex(byte[] buffer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buffer.length; i++) {
            int v = buffer[i];
            if (v < 0) {
//                v = (~buffer[i]) + 1;
                v += 256;
            }
            if (v < 16) {
                sb.append('0');
                sb.append(hex.charAt(v));
            }
            else {
                int a = v / 16;
                int b = v % 16;
                sb.append(hex.charAt(a));
                sb.append(hex.charAt(b));
            }
            sb.append(' ');
        }
        return sb.toString();
    }
    public static void main( String[] args ) throws IllegalAccessException {
        Test1 t1 = new Test1();
        t1.setA(661286);
        t1.setB(1238490123);
        Packer packer = new Packer();
        byte[] buffer = packer.serialize(t1);
        System.out.println(toHex(buffer));
    }
}
