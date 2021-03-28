public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 2: (<класс лексем, данная лексема>)");
        String strCode = "int a = 123;\n" +
                "double b = 2.2e5;\n" +
                "boolean break = false;\n" +
                "\n" +
                "while (b > a && !break) {\n" +
                "  b = b - a\n" +
                "  if (b <= 0 && a > 0) {\n" +
                "    break;\n" +
                "  }\n" +
                "  a = a / 15.0;\n" +
                "}";
        Analizator.goAnalize(strCode).forEach(System.out::println);

    }
}
