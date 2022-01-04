
/**
 * @author Bunny
 * @create 2022-01-04 11:34
 */
public class Planet {

    private static final double G = 6.67e-11;

    public double xxPos;

    public double yyPos;

    //Its current velocity in the x direction
    public double xxVel;


    //Its current velocity in the y direction
    public double yyVel;

    //The name of the file that corresponds to the image that depicts the planet
    public double mass;

    public String imgFileName;

    public Planet() {
    }

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }


    public double calcDistance(Planet p) {
        double x = this.xxPos - p.xxPos;
        double y = this.yyPos - p.yyPos;
        return Math.sqrt(x * x + y * y);
    }

    public double calcForceExertedBy(Planet p) {
        double r2 = Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2);
        return G * this.mass * p.mass / r2;
    }

    public double calcForceExertedByY(Planet p) {
        double r = calcDistance(p);
        if (r == 0.0) {
            return 0;
        }
        double f = calcForceExertedBy(p);
        double dx = p.yyPos - this.yyPos;
        return f * dx / r;
    }

    public double calcForceExertedByX(Planet p) {
        double r = calcDistance(p);
        if (r == 0.0) {
            return 0;
        }
        double f = calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        return f * dx / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double result = 0.0;
        for (Planet p : planets) {
            result += calcForceExertedByX(p);
        }
        return result;

    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double result = 0.0;
        for (Planet p : planets) {
            result += calcForceExertedByY(p);
        }
        return result;
    }

    public void update(double time, double dx, double dy) {
        double ax = dx / this.mass;
        double ay = dy / this.mass;
        this.xxVel += time * ax;
        this.yyVel += time * ay;
        this.xxPos += time * this.xxVel;
        this.yyPos += time * this.yyVel;
    }
}
