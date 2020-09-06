package model.types;

//represents the ItemType Water
public class Water extends Type {

    //EFFECTS: constructs an ItemType Water
    public Water() {
        element = Element.WATER;
    }

    //EFFECTS: returns attack multiplier against the defending type (2 against FIRE, 0.5 against WATER, 1 against
    //         EARTH and NORMAL , and 0.25 against PLANT)
    @Override
    public double attackMultiplier(Element defendingElement) {
        double multiplier;

        switch (defendingElement) {
            case FIRE:
                multiplier = 2;
                break;
            case WATER:
                multiplier = 0.5;
                break;
            case EARTH:
            case NORMAL:
                multiplier = 1;
                break;
            //PLANT case
            default:
                multiplier = 0.25;
        }
        return multiplier;
    }

}
