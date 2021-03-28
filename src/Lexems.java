import java.util.*;


public enum Lexems {

    BOOL("bool"),
    DATATYPE("datatype"),
    KEYWORD("keyword"),
    OPERATION("operation"),
    SPECIAL("special"),
    WHITESPACE("whitespace");


    private final String name;
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
            value = new HashSet<>();
            value.add("true");
            value.add("false");
        }

        if (name.equals("datatype")) {
            value = new HashSet<>();
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
            value = new HashSet<>();
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

        if (name.equals("operation")) {
            value = new HashSet<>();
            value.add("+");
            value.add("+=");
            value.add("-");
            value.add("-=");
            value.add("*");
            value.add("*=");
            value.add("/");
            value.add("/=");
            value.add("%");
            value.add("%=");
            value.add("=");
            value.add("==");
            value.add("!=");
            value.add("!");
            value.add("&");
            value.add("&&");
            value.add("|");
            value.add("||");
            value.add(">");
            value.add(">=");
            value.add("<");
            value.add("<=");
        }

        if (name.equals("special")) {
            value = new HashSet<>();
            value.add(".");
            value.add(",");
            value.add(":");
            value.add(";");
            value.add("?");
            value.add("(");
            value.add(")");
            value.add("{");
            value.add("}");
            value.add("[");
            value.add("]");
        }

        if (name.equals("whitespace")) {
            value = new HashSet<>();
            value.add(" ");
            value.add("\n");
            value.add("\t");
        }
    }
}
