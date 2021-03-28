import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizator {
    static HashSet<Lexems> wordLexeme;
    static {
        wordLexeme = new HashSet<>();
        wordLexeme.add(Lexems.BOOL);
        wordLexeme.add(Lexems.DATATYPE);
        wordLexeme.add(Lexems.KEYWORD);
    }

    static Pattern idPattern = Pattern.compile("[a-zA-Z]\\w*");
    static Pattern wPattern = Pattern.compile("\\w");


    static String isWordLexeme(String s) {
        for (Lexems l: wordLexeme) {
            if (l.getValue().contains(s)) {
                return l.getName();
            }
        }
        return null;
    }

    public static List<Pair<String, String>> goAnalize(String s){
        ArrayList<Pair<String, String>> answ = new ArrayList<>();
        String cashString = "";
        char cashChar = 'N';
        boolean realFlag = false;

        for (char c : s.toCharArray()) {
            Matcher skipMatcher = wPattern.matcher("" + c);
            if (skipMatcher.find()) {
                cashString += c;
                continue;
            }

            if (c == '.') {
                try {
                    Long.parseLong(cashString);
                    realFlag = true;
                    cashString += c;
                    continue;
                } catch (NumberFormatException ignored) {}
            }

            if (cashString.length() > 0) {
                char lastChar = cashString.toCharArray()[cashString.length() - 1];
                if ((realFlag) && ((c == '+') || (c == '-')) && (lastChar == 'e' || lastChar == 'E')) {
                    cashString += c;
                    continue;
                }
            }

            if (realFlag) {
                realFlag = false;
                if (cashString.length() > 0) {
                    try {
                        Double.parseDouble(cashString);
                        answ.add(new Pair<>("real", cashString));
                        cashString = "";
                        //continue;
                    } catch (NumberFormatException e) {
                        answ.add(new Pair<>("invalid lexeme", cashString));
                        cashString = "";
                        //continue;
                    }
                }
            }

            if (cashString.length() > 0) {
                Matcher idMatcher = idPattern.matcher(cashString);
                if ((idMatcher.find()) && (idMatcher.start() == 0)) {
                    if (Analizator.isWordLexeme(cashString) != null) {
                        answ.add(new Pair<>(Analizator.isWordLexeme(cashString), cashString));
                    } else {
                        answ.add(new Pair<>("id", cashString));
                    }
                    cashString = "";
                }
            }

            if (cashString.length() > 0) {
                try {
                    Integer.parseInt(cashString);
                    answ.add(new Pair<>("integer", cashString));
                    cashString = "";
                } catch (NumberFormatException ignored) {}
            }

            if (cashString.length() > 0) {
                answ.add(new Pair<>("invalid lexeme", cashString));
                cashString = "";
            }

            if (Lexems.OPERATION.getValue().contains("" + c)) {
                if (cashChar == 'N') {
                    cashChar = c;
                } else {
                    if (Lexems.OPERATION.getValue().contains("" + c + cashChar)) {
                        answ.add(new Pair<>(Lexems.OPERATION.getName(), "" + c + cashChar));
                    }
                    answ.add(new Pair<>("invalid lexeme", "" + c + cashChar));
                    cashChar = 'N';
                }
            }

            if ((c != cashChar) && (cashChar != 'N')) {
                answ.add(new Pair<>(Lexems.OPERATION.getName(), "" + cashChar));
                cashChar = 'N';
            }

            if (Lexems.SPECIAL.getValue().contains("" + c)) {
                answ.add(new Pair<>(Lexems.SPECIAL.getName(), "" + c));
            }

            if (Lexems.WHITESPACE.getValue().contains("" + c)) {
                answ.add(new Pair<>(Lexems.WHITESPACE.getName(), "" + c));
            }
        }
        return answ;
    }
}
