package tools;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
public class Tools {

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
