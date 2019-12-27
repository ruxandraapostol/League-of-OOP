package greatwizard;

import java.util.Observable;

public class GreatWizard extends Observable {
    private String watchedValue;

    public GreatWizard(String value) {
        watchedValue = value;
    }

    public void setValue(String value) {

        // daca valoarea s-a schimbat notific observatorul
        if(!watchedValue.equals(value)) {
            watchedValue = value;

            // mark as value changed
            setChanged();
            // trigger notification
            notifyObservers(value);
        }
    }
}
