package nl.hu.bep.tom.helloku;

public class SynchronizedString {

    private StringBuilder builder = new StringBuilder();


    public void appendLine(String text) {
        synchronized (this) {
            builder.append(text + "\n");
        }
    }

    public String getValue() {
        String result;
        synchronized (this) {
            result = builder.toString();
        }
        return result;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public void clear() {
        synchronized (this){
            this.builder = new StringBuilder();
        }
    }
}
