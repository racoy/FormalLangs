import java.util.*;


public enum Lexems {

    BOOL("bool"),
    DATATYPE("datatype"),
    KEYWORD("keyword");

    //BOOL("bool", 1, "q0", "q4", new HashMap<String, Set<String>>().put().put(), );

    private String name;
    private Set<String> value;

    public Set<String> getValue() {
        return value;
    }
    public String getName() {
        return name;
    }



    Lexems(String name) {
        this.name = name;
        if (name.equals("bool")) {
            value = new HashSet<String>();
            value.add("true");
            value.add("false");
        }

        if (name.equals("datatype")) {
            value = new HashSet<String>();
            value.add("boolean");
            value.add("byte");
            value.add("short");
            value.add("int");
            value.add("long");
            value.add("double");
            value.add("float");
            value.add("char");
        }

        if (name.equals("keyword")) {
            value = new HashSet<String>();
            value.add("begin");
            value.add("else");
            value.add("end");
            value.add("if");
            value.add("in");
            value.add("let");
            value.add("then");
            value.add("val");
            value.add("while");
            value.add("break");
        }

    }
}
