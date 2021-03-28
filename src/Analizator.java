import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizator {
    static HashSet<Lexems> lexems;
    {
        lexems = new HashSet<Lexems>();
        lexems.add(Lexems.BOOL);
        lexems.add(Lexems.DATATYPE);
        lexems.add(Lexems.KEYWORD);
    }

    static Pattern letterPattern = Pattern.compile("[a-zA-Z]");
    static Pattern whiteSpacePattern = Pattern.compile("[ \\n\\t]");
    static Pattern idPattern = Pattern.compile("[a-zA-Z]\\w*");



    static String isLexem(String s) {
        for (Lexems l: lexems) {
            if (l.getValue().contains(s)) {
                return l.getName();
            }
        }
        return null;
    }


    public static List<Pair<String, String>> goAnalize(String s){
        ArrayList<Pair<String, String>> answ = new ArrayList<>();
        String cashString = "";
        boolean splitF = false;
        for (char c : s.toCharArray()) {
            Matcher whiteSpaceMatcher = whiteSpacePattern.matcher("" + c);
            if (whiteSpaceMatcher.find()) {

                if (Analizator.isLexem(cashString) != null) {
                    answ.add(new Pair<>(Analizator.isLexem(cashString), cashString));
                    cashString = "";
                }
                else {
                    Matcher idMatcher = idPattern.matcher(cashString);
                }
                answ.add(new Pair<>("whiteSpace", "" + c));
                cashString = "";
                continue;
            }
            cashString += c;
        }
        return answ;
    }
}
