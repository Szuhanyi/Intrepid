package output;

public class OutputConsole extends OutputDestination {

    @Override
    public void print(String s) {

        System.out.println(s);
    }

}
