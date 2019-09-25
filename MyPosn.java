import javalib.worldimages.Posn;

public class MyPosn extends Posn {

  // standard constructor
  MyPosn(int x, int y) {
    super(x, y);
  }

  // constructor to convert from a Posn to a MyPosn
  MyPosn(Posn p) {
    this(p.x, p.y);
  }

  /*
   * fields:
   * this.x ... int
   * this.y ... int
   * methods:
   * this.add(MyPosn p) ... MyPosn
   * this.isOffScreen(int width, int height) ... boolean
   * this.touching(MyPosn otherPosition, int radius) ... boolean
   * this.xCoord() ... int
   * this.yCoord() ... int
   * this.magnitude() ... int
   */

  // adds the value of the given MyPosn to this one
  MyPosn add(MyPosn p) {
    return new MyPosn(this.x + p.x, this.y + p.y);
  }

  // is this MyPosn off-screen?
  boolean isOffScreen(int width, int height) {
    return (this.x < 0 || this.x > width) || (this.y < 0 || this.y > height);
  }

  // is this MyPosn touching the given MyPosn, within a certain given radius?
  boolean touching(MyPosn otherPosition, int radius) {
    return Math.abs(this.x - otherPosition.x) <= radius
        && Math.abs(this.y - otherPosition.y) <= radius;
  }

  // get x coord from this MyPosn
  int xCoord() {
    return this.x;
  }

  // get y coord from this MyPosn
  int yCoord() {
    return this.y;
  }

  // calculates magnitude of this MyPosn and rounds it to the nearest number
  int magnitude() {
    return (int) Math.round(Math.pow(Math.pow(this.x, 2) + Math.pow(this.y, 2), 0.5));
  }
}