/**
 * Developed by Mohammad Mahdi Salmani
 */

class Direction{
    public enum Sides {
        UP,DOWN,RIGHT,LEFT,UP_RIGHT,UP_LEFT,DOWN_RIGHT,DOWN_LEFT
    }

    public static int X(Sides direction) {
        switch (direction) {
            case UP:
            case DOWN:
                return 0;
            case RIGHT:
            case UP_RIGHT:
            case DOWN_RIGHT:
                return 1;
            default:
                return -1;
        }
    }

    public static int Y(Sides direction) {
        switch (direction) {
            case RIGHT:
            case LEFT:
                return 0;
            case UP:
            case UP_RIGHT:
            case UP_LEFT:
                return 1;
            default:
                return -1;
        }
    }
}
