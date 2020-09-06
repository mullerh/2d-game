package model.types;

//represents the ItemType Earth
public class Earth extends Type {

    //EFFECTS: constructs an ItemType Earth
    public Earth() {
        element = Element.EARTH;
    }

    //EFFECTS: returns attack multiplier against the defending type (2 against FIRE, 1 against WATER and NORMAL,
    //         0.5 against EARTH, and 0.25 against PLANT)
    @Override
    public double attackMultiplier(Element defendingElement) {
        double multiplier;

        switch (defendingElement) {
            case FIRE:
                multiplier = 2;
                break;
            case WATER:
            case NORMAL:
                multiplier = 1;
                break;
            case EARTH:
                multiplier = 0.5;
                break;
            //PLANT case
            default:
                multiplier = 0.25;
        }
        return multiplier;
    }
}
