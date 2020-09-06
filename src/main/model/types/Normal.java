package model.types;

//represents the ItemType Normal
public class Normal extends Type {

    //EFFECTS: constructs an ItemType Normal
    public Normal() {
        element = Element.NORMAL;
    }

    //EFFECTS: returns attack multiplier against the defending type (0.75 against FIRE, WATER, EARTH, and PLANT, and
    //         1.5 against NORMAL)
    @Override
    public double attackMultiplier(Element defendingType) {
        double multiplier;

        switch (defendingType) {
            case FIRE:
            case WATER:
            case EARTH:
            case PLANT:
                multiplier = 0.75;
                break;
            //NORMAL case
            default:
                multiplier = 1.5;
        }
        return multiplier;
    }
}
