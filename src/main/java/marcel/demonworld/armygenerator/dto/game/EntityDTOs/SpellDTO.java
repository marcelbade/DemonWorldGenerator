package marcel.demonworld.armygenerator.dto.game.EntityDTOs;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SpellDTO {

    private Integer Id;

    private String faction;

    private String spellName;

    private String effect;

    private String duration;

    private String requirements;

    private String target;

    private String spellTier;

    private String abbreviatedEffect;

    private Boolean isSelected;

}
