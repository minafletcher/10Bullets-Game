import java.awt.Color;

public class Bullet extends AGamePiece {
  int gen; // the generation of this Bullet

  // standard constructor
  Bullet(int radius, Color c, MyPosn position, MyPosn velocity, int gen) {
    super(radius, c, position, velocity);
    this.gen = gen;
  }

  // constructor for setting constant features of Bullet
  Bullet() {
    this(2, Color.PINK, new MyPosn(250, 300), new MyPosn(0, -8), 1);
  }

  /*
   * fields:
   * this.radius ... int
   * this.c ... Color
   * this.position ... MyPosn
   * this.velocity ... MyPosn
   * this.gen ... int
   * methods:
   * this.draw() ... WorldImage
   * this.move() ... IGamePiece
   * this.place(WorldScene background) ... WorldScene
   * this.isOffScreen(int width, int height) ... boolean
   * this.isTouchingShip(Ship that) ... boolean
   * this.isTouchingBullet(Bullet that) ... boolean
   * this.isTouching(ILoGamePiece that) ... boolean
   * this.isTouching(IGamePiece that) ... boolean
   * this.explode() ... ILoGamePiece
   * this.explodeAcc(int gen, int bullNum) ... ILoGamePiece
   * this.grow() ... int
   * methods of fields:
   * this.position.add(MyPosn p) ... MyPosn
   * this.position.isOffScreen(int width, int height) ... boolean
   * this.position.touching(MyPosn otherPosition, int radius) ... boolean
   * this.position.xCoord() ... int
   * this.position.yCoord() ... int
   * this.position.magnitude() ... int
   * this.velocity.add(MyPosn p) ... MyPosn
   * this.velocity.isOffScreen(int width, int height) ... boolean
   * this.velocity.touching(MyPosn otherPosition, int radius) ... boolean
   * this.velocity.xCoord() ... int
   * this.velocity.yCoord() ... int
   * this.velocity.magnitude() ... int
   */

  // outputs new Bullet in a new position after 1 tick
  public IGamePiece move() {
    return new Bullet(this.radius, this.c, this.position.add(this.velocity), this.velocity,
        this.gen);
  }

  // is this Bullet touching that IGamePiece?
  public boolean isTouching(IGamePiece that) {
    return that.isTouchingBullet(this);
  }

  // is this IGamePiece touching that Bullet?
  // although a Bullet can touch another Bullet, we don't want to account for it
  // because nothing changes when that happens
  public boolean isTouchingBullet(Bullet that) {
    return false;
  }

  // explodes this Bullet
  public ILoGamePiece explode() {
    return this.explodeAcc(this.gen, 0);
  }

  // Explodes this Bullet, keeping track of the original generation and what
  // bullet number is currently being made
  public ILoGamePiece explodeAcc(int gen, int bullNum) {
    if (bullNum <= gen) {
      return new ConsLoGamePiece(new Bullet(this.grow(), this.c, this.position, new MyPosn(
          (int) Math.round(
              this.velocity.magnitude() * Math.cos(Math.toRadians(bullNum * (360 / (gen + 1))))),
          (int) Math.round(
              this.velocity.magnitude() * Math.sin(Math.toRadians(bullNum * (360 / (gen + 1)))))),
          gen + 1), this.explodeAcc(gen, bullNum + 1));
    }
    else {
      return new MtLoGamePiece();
    }
  }

  // returns the new radius of a Bullet, with a radius 2 pixels bigger than the
  // original
  public int grow() {
    if (this.radius >= 9) {
      return this.radius;
    }
    else {
      return this.radius + 2;
    }
  }
}