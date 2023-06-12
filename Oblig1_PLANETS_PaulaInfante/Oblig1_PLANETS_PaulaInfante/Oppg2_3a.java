public class Oppg2_3a {
  private String name;
  private double radius;
  private double gravity;

  Oppg2_3a() {

  }

  Oppg2_3a(String name, double radius, double gravity) {
    this.name = name;
    this.radius = radius;
    this.gravity = gravity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getGravity() {
    return gravity;
  }

  public void setGravity() {
    this.gravity = gravity;
  }

  public void printPlanet() {
    System.out.println(" The planet " + name + " has a radius of: " + radius + "km, and the gravity is: " + gravity + "m/(s^2)");
  }

}
