package greatwizard;

import java.util.Observable;

public class GreatWizard extends Observable {
    private String watchedValue;

    public GreatWizard(final String value) {
        watchedValue = value;
    }

    public final void setValue(final String value) {
        watchedValue = value;
        // mark as value changed
        setChanged();
        // trigger notification
        notifyObservers(value);
    }
}
