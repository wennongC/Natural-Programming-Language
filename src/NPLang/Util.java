package NPLang;

public class Util {
    public static String repeatTab(int times) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < times; i++) res.append("\t");
        return res.toString();
    }
}
