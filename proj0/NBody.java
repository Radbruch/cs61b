import java.lang.reflect.Array;
import java.util.Scanner;

public class NBody {

    public static double readRadius(String address){
        In in = new In(address);
        int numberOfPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String address){
        In in = new In(address);
        int numberOfPlanet = in.readInt();
        double radius = in.readDouble();
        Planet[] arrayOfPlanet = new Planet[numberOfPlanet];
        for (int i = 0; i < numberOfPlanet; i++){
            double x_coordinates = in.readDouble();
            double y_coordinates = in.readDouble();
            double x_velocity = in.readDouble();
            double y_velocity = in.readDouble();
            double mass = in.readDouble();
            String nameOfImageFile = in.readString();
            arrayOfPlanet[i] = new Planet(x_coordinates, y_coordinates, x_velocity, y_velocity, mass, nameOfImageFile);

        }
        return arrayOfPlanet;
    }
    public static void main(String[] args){
        double T; /** Stoping Time */
        double dt; /** Time Step */
        String filename;
        Scanner input = new Scanner(System.in);
        T = Double.parseDouble(input.next());
        dt = Double.parseDouble(input.next());
        filename = input.next();
        double radius = NBody.readRadius(filename);
        Planet[] arrayOfPlanet = NBody.readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        double time = 0.0;

        StdAudio x = new StdAudio();
        x.play("audio/2001.mid");

        while (time < T){
            double[] xForces = new double[arrayOfPlanet.length];
            double[] yForces = new double[arrayOfPlanet.length];
            for (int i = 0; i < arrayOfPlanet.length; i++){
                xForces[i] = arrayOfPlanet[i].calcNetForceExertedByX(arrayOfPlanet);
                yForces[i] = arrayOfPlanet[i].calcNetForceExertedByY(arrayOfPlanet);
                arrayOfPlanet[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,imageToDraw,2 * radius, 2 * radius);
            for (int i = 0; i < arrayOfPlanet.length; i++){
                arrayOfPlanet[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

    }
}
