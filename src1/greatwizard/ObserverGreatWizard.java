package greatwizard;

import java.util.Observable;
import java.util.Observer;

public class ObserverGreatWizard implements Observer {
    private String message = "";

    public ObserverGreatWizard(final GreatWizard greatWizard) {
        greatWizard.addObserver(this::update);
    }

    /**
     * In acest fel la fiecare actualizare mesajul va
     * fi modificat corespunzator.
     * @param observable = cei care sunt observati
     * @param o = noul mesaj
     */
    @Override
    public void update(final Observable observable, final Object o) {
        message += o;
        message += "\n";
    }

    public final String getMessage() {
        return this.message;
    }
}
