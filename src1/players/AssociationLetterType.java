package players;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AssociationLetterType {
    private Map<String, String> letterType = new HashMap<String, String>();
    public AssociationLetterType(){
        letterType.put("K", "Knight");
        letterType.put("R", "Rogue");
        letterType.put("P", "Pyromancer");
        letterType.put("W", "Wizard");
    }

    public final String wordByLetter (final String letter){
        return letterType.get(letter);
    }
}
