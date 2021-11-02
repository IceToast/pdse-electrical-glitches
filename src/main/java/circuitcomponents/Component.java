package circuitcomponents;

import exceptions.MissingInputException;

import java.util.List;

public interface Component {

    List<Component> getInputs();

    void addInput(Component... component);

    void calculateState() throws MissingInputException;

    int calculateToggleTime();

    boolean getState();

    void setState(boolean state);

}
