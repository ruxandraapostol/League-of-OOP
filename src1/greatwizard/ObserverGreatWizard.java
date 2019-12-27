package greatwizard;

import java.util.Observable;
import java.util.Observer;

public class ObserverGreatWizard implements Observer {

    public ObserverGreatWizard(GreatWizard greatWizard){
        greatWizard.addObserver(this::update);
    }
    @Override
    public void update(Observable observable, Object o) {
        System.out.println(o);
    }
}
