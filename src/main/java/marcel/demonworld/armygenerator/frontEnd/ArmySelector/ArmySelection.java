package marcel.demonworld.armygenerator.frontEnd.ArmySelector;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Class sets the selected Army globally for the enture UI
 */
@Component
@Setter
@Getter
public class ArmySelection {

    private String selectedArmy = "Zwerge";

}
