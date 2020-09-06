package model.types;

//represents an attack element which can be one of earth, fire, normal, plant, or water
public abstract class Type {

    public enum Element {
        EARTH, FIRE, NORMAL, PLANT, WATER;
    }

    protected Element element;

    //EFFECTS: creates new ItemType (default Normal)
    public Type() {
        element = Element.NORMAL;
    }

    //Return Type of the ItemType (default Normal)
    public Element getElement() {
        return element;
    }

    //REQUIRES: type must be one of: "Fire", "Earth", "Plant", "Water", "Normal"
    //EFFECTS: returns an ItemType that matches the input, with a default return of new Normal();
    public static Type parseItemType(String type) {
        Type parsedType;

        switch (type) {
            case "Fire":
                parsedType = new Fire();
                break;
            case "Earth":
                parsedType = new Earth();
                break;
            case "Plant":
                parsedType = new Plant();
                break;
            case "Water":
                parsedType = new Water();
                break;
            //NORMAL case
            default:
                parsedType = new Normal();
        }

        return parsedType;
    }

    //EFFECTS: returns ItemType's string representation
    public String getTypeString() {
        return element.toString().substring(0, 1) + element.toString().substring(1).toLowerCase();
    }

    //EFFECTS: returns the attack multiplier based on what the type is attacking
    public abstract double attackMultiplier(Element defendingType);
}