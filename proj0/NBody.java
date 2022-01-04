
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bunny
 * @create 2022-01-04 17:41
 */
public class NBody {
    private static final String backimg = "images/starfield.jpg";

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


    public static void main(String[] args) {
        if (args.length > 0) {
            double T = Double.valueOf(args[0]);
            double dt = Double.valueOf(args[1]);
            String imgPath = args[2];

            Planet[] planets = readPlanets(imgPath);
            double radius = readRadius(imgPath);

            StdDraw.picture(0.5, 0.5, backimg);

            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.enableDoubleBuffering();
            double time = 0;
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            while (time < T) {
                for (int i = 0; i < planets.length; i++) {
                    xForces[i] = planets[i].calcNetForceExertedByX(planets);
                    yForces[i] = planets[i].calcNetForceExertedByY(planets);
                    planets[i].update(dt, xForces[i], yForces[i]);
                }
                StdDraw.picture(0.5, 0.5, backimg);
                for (Planet p : planets) {
                    p.draw();
                }
                StdDraw.show();
                StdDraw.pause(10);
                time += dt;
            }

            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            }
        }


    }


}
