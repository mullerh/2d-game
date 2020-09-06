package model.types;

//represents the ItemType Fire
public class Fire extends Type {

    //EFFECTS: constructs an ItemType Fire
    public Fire() {
        element = Element.FIRE;
    }

    //EFFECTS: returns attack multiplier against the defending type (0.5 against FIRE, 0.25 against WATER, 1 against
    //         EARTH and NORMAL , and 2 against PLANT)
    @Override
    public double attackMultiplier(Element defendingElement) {
        double multiplier;

        switch (defendingElement) {
            case FIRE:
                multiplier = 0.5;
                break;
            case WATER:
                multiplier = 0.25;
                break;
            case EARTH:
            case NORMAL:
                multiplier = 1;
                break;
            //PLANT case
            default:
                multiplier = 2;
        }
        return multiplier;
    }
}
