package model.types;

//represents the ItemType Plant
public class Plant extends Type {

    //EFFECTS: constructs an ItemType Plant
    public Plant() {
        element = Element.PLANT;
    }

    //EFFECTS: returns attack multiplier against the defending type (0.25 against FIRE, 2 against WATER and EARTH,
    //         0.5 against PLANT, and 1 against NORMAL)
    @Override
    public double attackMultiplier(Element defendingElement) {
        double multiplier;

        switch (defendingElement) {
            case FIRE:
                multiplier = 0.25;
                break;
            case WATER:
            case EARTH:
                multiplier = 2;
                break;
            case PLANT:
                multiplier = 0.5;
                break;
            //NORMAL case
            default:
                multiplier = 1;
        }
        return multiplier;
    }
}
