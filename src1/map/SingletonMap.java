package map;
import java.util.ArrayList;

public final class SingletonMap {
    private static SingletonMap instance = null;
    private ArrayList<String> map;

    private SingletonMap(final ArrayList<String> map) {
        this.map = map;
    }

    public static SingletonMap getInstance(final ArrayList<String> map) {
        if (instance == null) {
            instance = new SingletonMap(map);
        }
        return instance;
    }

    public char position(final int i, final int j) {
        return  map.get(i).charAt(j);
    }
}
