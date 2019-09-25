import javalib.worldimages.*; // images, like RectangleImage or OverlayImages
import javalib.funworld.*; // the abstract World class and the big-bang library

interface IGamePiece {
  // move this IGamePiece
  IGamePiece move();

  // renders this IGamePiece as an image
  WorldImage draw();

  // Places this IGamePiece on the background
  WorldScene place(WorldScene background);

  // is this IGamePiece off-screen?
  boolean isOffScreen(int width, int height);

  // is this IGamePiece touching that IGamePiece
  boolean isTouching(IGamePiece that);
  
  //is this game piece touching anything in that list of game pieces?
  boolean isTouching(ILoGamePiece that);

  // is this IGamePiece touching a ship?
  boolean isTouchingShip(Ship that);

  // is this IGamePiece touching a Bullet?
  boolean isTouchingBullet(Bullet that);

  // explode this game piece
  ILoGamePiece explode();

  // explode this game piece, accumulating the resultant list of game pieces
  ILoGamePiece explodeAcc(int gen, int bullNum);

  // returns the increased radius of this IGamePiece appropriately
  int grow();
}