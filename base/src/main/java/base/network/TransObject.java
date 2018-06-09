package base.network;

public class TransObject {
    private Class<?> clazz;
    private byte[] bytes;

    public TransObject() {

    }
    public TransObject(Class<?> clazz, byte[] bytes) {
        this.clazz = clazz;
        this.bytes = bytes;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
