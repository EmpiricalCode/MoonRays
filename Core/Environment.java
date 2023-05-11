package Core;

import java.awt.Color;
import java.util.ArrayList;

import Core.Structures.Entity;
import Core.Utility.Camera;
import Core.Utility.ColorRGB;
import Core.Utility.Vector3D;

public class Environment {

    public static final ColorRGB AMBIENT_COLOR = new ColorRGB(16, 60, 156);
    
    // TODO: make a function that accumulates all RENDERABLE entities (ex. rectangular prism is really 12 triangles)
    private ArrayList<Entity> entities;

    private Camera camera;

    // Main constructor
    public Environment(Camera camera) {
        this.camera = camera;
        this.entities = new ArrayList<Entity>();
    }

    // Getter method for the camera
    public Camera getCamera() {
        return this.camera;
    }

    // Gets the environment's entities
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    // Adds a world object
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    // Returns the ambient color of the environment given a 
    // unit vector away from the origin
    public static ColorRGB getAmbientColor(Vector3D direction) {

        ColorRGB residualColor = new ColorRGB(200, 240, 255);
        ColorRGB colorDifference = ColorRGB.subtract(residualColor, Environment.AMBIENT_COLOR);

        double directionAxisAngle = Math.atan(Math.abs(direction.getZ()) / Math.max(0.01, Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getY(), 2))));

        colorDifference = ColorRGB.multiply(colorDifference,Math.sin(directionAxisAngle));

        return ColorRGB.subtract(residualColor, colorDifference);
    }
}
