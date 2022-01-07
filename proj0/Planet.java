import static java.lang.Math.floor;
import static java.lang.Math.round;

public class Planet {
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the body.
    public final double G = 6.67 * Math.pow(10, -11);;

    /** Initialize a planet body. */
    public Planet(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    /** Copy a planet. */
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    /** Return anotherPlanet's x-position minus this planet x-position.(dx) */
    private double dx(Planet anotherPlanet){
        if (anotherPlanet.equals(this)) {
            return 0;
        } else {
            return anotherPlanet.xxPos - this.xxPos;
        }
    }

    /** Return anotherPlanet's y-position minus this planet y-position.(dy) */
    private double dy(Planet anotherPlanet){
        if (anotherPlanet.equals(this)) {
            return 0;
        } else {
            return anotherPlanet.yyPos - this.yyPos;
        }
    }

    /** Return straight-line distance between this planet and anotherPlanet. */
    public double calcDistance(Planet anotherPlanet){
        if (anotherPlanet.equals(this)) {
            return 0;
        } else {
            double dysquare = Math.pow(dy(anotherPlanet), 2);
            double dxsquare = Math.pow(dx(anotherPlanet), 2);
            dysquare = floor(dysquare) + round(10 * (dysquare - floor(dysquare))) / 10;
            dxsquare = floor(dxsquare) + round(10 * (dxsquare - floor(dxsquare))) / 10;
            double rsquare = dxsquare + dysquare;
            double r = Math.sqrt(rsquare);
            r = floor(r) + round(10 * (r - floor(r))) / 10;
            return r;
        }
    }

    /** Return total force exerted by anotherPlanet (no-signed). */
    public double calcForceExertedBy(Planet anotherPlanet){
        if (anotherPlanet.equals(this)) {
            return 0;
        } else {
            double numerator = (G * this.mass * anotherPlanet.mass);
            double denominator = Math.pow(dx(anotherPlanet),2) + Math.pow(dy(anotherPlanet),2);
            return numerator / denominator;
        }
    }
    public double calcForceExertedByX(Planet anotherPlanet){
        double fx = calcForceExertedBy(anotherPlanet) * dx(anotherPlanet) / calcDistance(anotherPlanet);
        return fx;
    }
    public double calcForceExertedByY(Planet anotherPlanet){
        double fy = calcForceExertedBy(anotherPlanet) * dy(anotherPlanet) / calcDistance(anotherPlanet);
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] planetArray){
        double netForceExertedByX = 0;
        for (int i = 0; i < planetArray.length; i++){
            if (planetArray[i].equals(this)) {
                netForceExertedByX += 0;
            } else {
                netForceExertedByX += calcForceExertedByX(planetArray[i]);

            }
        }
        return netForceExertedByX;
    }
    public double calcNetForceExertedByY(Planet[] planetArray){
        double netForceExertedByY = 0;
        for (int i = 0; i < planetArray.length; i++){
            if (planetArray[i].equals(this)) {
                netForceExertedByY += 0;
            } else {
                netForceExertedByY += calcForceExertedByY(planetArray[i]);
            }
        }
        return netForceExertedByY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    /* Draws the image of one planet. */
    public void draw() {
        /* Stamps starfield.jpg in a specified position. */
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
