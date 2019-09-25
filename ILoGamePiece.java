import javalib.funworld.WorldScene;

public interface ILoGamePiece {
  // places all IGamePiece in this ILoGamePiece on the background
  WorldScene placeAll(WorldScene background);

  // places all IGamePiece in this ILoGamePiece on the background
  // accumulating scene so far
  WorldScene placeAllAcc(WorldScene acc);

  // moves all IGamePiece in this ILoGamePiece
  ILoGamePiece moveAll();

  // removes all off-screen IGamePiece from this ILoGamePiece
  ILoGamePiece removeOffscreen(int width, int height);

  // is anything in this ILoGamePiece touching that IGamePiece?
  boolean isTouching(IGamePiece that);

  // how many elements are in this ILoGamePiece?
  int length();

  // remove any elements in this ILoGamePiece that are touching any elements of
  // that ILoGamePiece
  ILoGamePiece removeTouchers(ILoGamePiece that);

  // explodes elements from this ILoGamePiece that touch elements of that
  // ILoGamePiece
  ILoGamePiece explodeTouchers(ILoGamePiece that);

  // are there any IGamePiece left in this ILoGamePiece?
  boolean anyLeft();

  // append things from the given ILoGamePiece onto this ILoGamePiece
  ILoGamePiece append(ILoGamePiece that);
}