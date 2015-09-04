package gr.geomike.ted.api.db.dao;

public class QueryParameter {
    public enum Type {
        VARCHAR, INT, DATE
    }

    private String name;
    private String stringValue;
    private Type type;
    private Object value;
    private int intValue;

    /*public QueryParameter(String name, Type type, String stringValue, int intValue, Any value){
        this.name = name;
        this.type = type;
        this.stringValue = stringValue;
        this.intValue = intValue;
    }*/
    public QueryParameter(String name, String value){
        this.name = name;
        this.type = Type.VARCHAR;
        this.value = value;
    }
    public QueryParameter(String name, int value){
        this.name = name;
        this.type = Type.INT;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public <Any> Any getValue() {
        if (type == Type.VARCHAR)
            return((Any)((String)(value)));
        else if (type == Type.INT)
            return((Any)((Integer)(value)));

        return ((Any)((String)(value)));
    }
}
