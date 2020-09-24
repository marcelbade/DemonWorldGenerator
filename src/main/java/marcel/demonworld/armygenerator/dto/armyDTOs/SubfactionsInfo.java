package marcel.demonworld.armygenerator.dto.armyDTOs;


/**
 * DTO stores information about one subfaction.
 * <p>
 * subFaction = stringified subFaction enum
 * compliantToRules = flag to show whether the point total of the subfaction is rule compliant
 * subTotal = current point total for one subfaction
 */
public class SubfactionsInfo {

    private String subFaction;
    private boolean compliantToRules;
    private int subTotal;

    public SubfactionsInfo(String subFaction, boolean compliantToRules, int subTotal) {
        this.subFaction = subFaction;
        this.compliantToRules = compliantToRules;
        this.subTotal = subTotal;
    }

    public String getSubFaction() {
        return subFaction;
    }

    public void setSubFaction(String subFaction) {
        this.subFaction = subFaction;
    }

    public boolean isCompliantToRules() {
        return compliantToRules;
    }

    public void setCompliantToRules(boolean compliantToRules) {
        this.compliantToRules = compliantToRules;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
