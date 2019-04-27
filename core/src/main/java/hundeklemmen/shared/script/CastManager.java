package hundeklemmen.shared.script;

public class CastManager {
    public String asString(Object text){
        return String.valueOf(text);
    }
    public int asInt(Object text){
        return Integer.parseInt(String.valueOf(text));
    }
    public double asDouble(Object text){
        return Double.parseDouble(String.valueOf(text));
    }
    public float asFloat(Object text){
        return Float.parseFloat(String.valueOf(text));
    }
    public byte asByte(Object text){
        return (byte) text;
    }
    public Number asNumber(Object text){
        return (Number) text;
    }
}
