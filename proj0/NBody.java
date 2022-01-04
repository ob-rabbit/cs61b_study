
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bunny
 * @create 2022-01-04 17:41
 */
public class NBody {

    public static Planet[] readPlanets(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int nums = 0;
        Planet[] res = null;
        int index = 0;
        if (!in.isEmpty()) {
            nums = in.readInt();
            double result = in.readDouble();
            res = new Planet[nums];
            in.readLine();
            while (in.hasNextLine() && index < nums) {
                String info = in.readLine();
                res[index] = getPlanetInfo(info);
                index++;
            }
        }
        return res;
    }

    public static double readRadius(String planetsTxtPath) {

        In in = new In(planetsTxtPath);
        int nums = 0;
        double result = 0.0;
        if (!in.isEmpty()) {
            nums = in.readInt();
            result = in.readDouble();
        }
        return result;
    }

    private static Planet getPlanetInfo(String info) {
        String[] ss = info.split(" ");
        List<String> strings = new ArrayList<>(6);
        for (String s : ss) {
            if (!s.equals("")) {
                strings.add(s);
            }
        }
        double x = Double.valueOf(strings.get(0));
        double y = Double.valueOf(strings.get(1));
        double xv = Double.valueOf(strings.get(2));
        double yv = Double.valueOf(strings.get(3));
        double mass = Double.valueOf(strings.get(4));
        String imgpath = strings.get(5);
        Planet p = new Planet(x, y, xv, yv, mass, imgpath);
        return p;
    }
}
