import java.awt.Color;

import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.WorldImage;

public abstract class AGamePiece implements IGamePiece {
  int radius;
  Color c;
  MyPosn position;
  MyPosn velocity;

  // standard constructor
  AGamePiece(int radius, Color c, MyPosn position, MyPosn velocity) {
    this.radius = radius;
    this.c = c;
    this.position = position;
    this.velocity = velocity;
  }

  /*
   * fields:
   * this.radius ... int
   * this.c ... Color
   * this.position ... MyPosn
   * this.velocity ... MyPosn
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
   * this.explodeAcc(int, int) ... ILoGamePiece
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

  // render this IGamePiece as an image
  public WorldImage draw() {
    return new CircleImage(this.radius, OutlineMode.SOLID, this.c);
  }

  // moves this IGamePiece
  public abstract IGamePiece move();

  // places this IGamePiece on the background
  public WorldScene place(WorldScene background) {
    return background.placeImageXY(this.draw(), this.position.xCoord(), this.position.yCoord());
  }

  // is this IGamePiece off-screen?
  public boolean isOffScreen(int width, int height) {
    return this.position.isOffScreen(width, height);
  }

  // is this IGamePiece touching that IGamePiece?
  public abstract boolean isTouching(IGamePiece that);

  // is this IGamePiece touching anything in that ILoGamePiece?
  public boolean isTouching(ILoGamePiece that) {
    return that.isTouching(this);
  }

  // is this IGamePiece touching that Ship?
  public boolean isTouchingShip(Ship that) {
    return this.position.touching(that.position, this.radius + that.radius);
  }

  // is this IGamePiece touching that Bullet?
  public boolean isTouchingBullet(Bullet that) {
    return this.position.touching(that.position, this.radius + that.radius);
  }

  // explode this IGamePiece
  public abstract ILoGamePiece explode();

  // explode this IGamePiece, accumulating resultant list
  public abstract ILoGamePiece explodeAcc(int gen, int bullNum);

  // returns the updated radius of an IGamePiece
  public abstract int grow();
}