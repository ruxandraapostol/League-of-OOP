package greatwizard;

import java.util.Observable;
import java.util.Observer;

public class ObserverGreatWizard implements Observer {
    private String message = "";

    public ObserverGreatWizard(GreatWizard greatWizard){
        greatWizard.addObserver(this::update);
    }
    @Override
    public void update(Observable observable, Object o) {
        message += o;
        message += "\n";
    }

    public String getMessage(){
        return this.message;
    }
}
