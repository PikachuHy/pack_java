package pack;

import java.lang.reflect.Field;

public class Packer {
    public int getNeededSize(Object o) {
        int total = 0;
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Class<?> fieldType = field.getType();
            if (fieldType.isPrimitive()) {
                String typeName = fieldType.getName();
                if (typeName.equals("int")) {
                    total += 4;
                }
                else if (typeName.equals("long")) {
                    total += 8;
                }
            }
            else {

            }
        }
        return total;
    }
    public byte[] serialize(Object o) throws IllegalAccessException {
        int total = getNeededSize(o);
        buffer = new byte[total];
        pos = 0;
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            System.out.println(field.getType());
            System.out.println(field.getName());
            field.setAccessible(Boolean.TRUE);
            System.out.println(field.get(o));
            Class<?> fieldType = field.getType();
            String typeName = fieldType.getName();
            if (typeName.equals("int")) {
                intToByte((Integer) field.get(o));
            }
            else if (typeName.equals("long")) {
                longToByte((Long) field.get(o));
            }
        }

        return buffer;
    }
    private void intToByte(int v) {
        for (int i = 0; i < 4; i++) {
            buffer[pos++] = (byte) (v >> (8 * i));
        }
    }
    private void longToByte(long v) {
        for (int i = 0; i < 8; i++) {
            buffer[pos++] = (byte) (v >> (8 * i));
        }
    }
    private void floatToByte(byte[] bytes, float v) {
        intToByte(Float.floatToIntBits(v));
    }
    private void doubleToByte(byte[] bytes, double v) {
        longToByte(Double.doubleToLongBits(v));
    }
    private byte[] buffer;
    private int pos;
}
