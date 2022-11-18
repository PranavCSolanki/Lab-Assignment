// Name    :- Pranav Chandrakant Solanki.
// Roll no :- 72
// PRN     :- 1132221001

package problem;

class Invalid_Durability extends Exception {
    Invalid_Durability() {
        System.out.println("-**-Exception occured : Durability must be greater than 0 and less than or equal to 1-**-");
    }
}

class Invalid_waranty extends Exception {
    Invalid_waranty() {
        System.out.println("-**-Exception occured : waranty must be greater than 0 -**-");
    }
}

abstract class Equipment {
    String make;
    String model;
    int purchaseYear;

    public Equipment(String make, String model, int purchaseYear) {
        this.make = make;
        this.model = model;
        this.purchaseYear = purchaseYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    abstract int replacement_Year();

    public void showDetails() {
        System.out.println("Equipment [make=" + make + ", model=" + model + ", purchaseYear=" + purchaseYear + "]");
    }

}

class BatteryPowered_Equipment extends Equipment {
    private int waranty;

    public BatteryPowered_Equipment(String make, String model, int purchaseYear, int waranty) {
        super(make, model, purchaseYear);
        this.waranty = waranty;

    }

    public int getwaranty() {
        return waranty;
    }

    public void setwaranty(int waranty) throws Invalid_waranty{
        this.waranty = waranty;
        if (waranty <= 0) {
            throw new Invalid_waranty();
        }
    }

    public int replacement_Year() {
        int replacement_Year = waranty + super.getPurchaseYear();
        return replacement_Year;
    }

}

class FuelPowered_Equipment extends Equipment {
    int usage;
    int MaximumDays;

    public FuelPowered_Equipment(String make, String model, int purchaseYear, int usage, int maximumDays) {
        super(make, model, purchaseYear);
        this.usage = usage;
        MaximumDays = maximumDays;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int replacement_Year() {
        int replacement_Year = MaximumDays / usage + super.getPurchaseYear();
        return replacement_Year;
    }

}

class Standard_Equipment extends Equipment {
    double durability;
    int maximumretention = 7;

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) throws Invalid_Durability {
        this.durability = durability;
        if (durability == 0 && durability < 1 || durability > 1) {
            // throws exception if durability is less than 0 and greater than or equal to 1
            throw new Invalid_Durability();
        }
    }

    public int replacement_Year() {
        int replacement_Year = (int) (maximumretention * durability + super.getPurchaseYear());
        return replacement_Year;
    }

    public Standard_Equipment(String make, String model, int purchaseYear, double durability, int maximum_retention) {
        super(make, model, purchaseYear);
        this.durability = durability;
        this.maximumretention = maximum_retention;
    }

}

public class Equipments {
    public static void main(String[] args) {
        System.out.println("Bigining Rake");
        try {
            Standard_Equipment se = new Standard_Equipment(null, null, 0, 0, 0);
            se.setMake("JCB");
            se.setModel("AMR01");
            se.setPurchaseYear(2022);
            se.setDurability(5);
            se.showDetails();
            System.out.println("\n Replacement Year: " + se.replacement_Year());

        } catch (Invalid_Durability e) {
            System.out.println(e.getMessage());
        }

        try {
            FuelPowered_Equipment fpe = new FuelPowered_Equipment(null, null, 0, 0,0);
            fpe.setMake("Botch Mower");
            fpe.setModel("Rotak 40");
            fpe.setPurchaseYear(2020);
            fpe.setUsage(200);
            fpe.showDetails();
            System.out.println("\n" + "\nReplacement Year : " + fpe.replacement_Year());
            fpe.setMake("Havana Chain Saw");
            fpe.setModel("Rotak 22");
            fpe.setPurchaseYear(2019);
            fpe.setUsage(300);
            fpe.showDetails();
            System.out.println("\n" + "\nReplacement Year : " + fpe.replacement_Year());

            BatteryPowered_Equipment bpe = new BatteryPowered_Equipment(null, null, 0, 0);
            bpe.setMake("Nikita Edge Trimmer");
            bpe.setModel("RBC411U");
            bpe.setPurchaseYear(2021);
            bpe.setwaranty(3);
            bpe.showDetails();
            System.out.println("\n" + "\nReplacement Year :" + bpe.replacement_Year());
            bpe.setMake("Nikita Brush Cutter");
            bpe.setModel("RBC411U");
            bpe.setPurchaseYear(2020);
            bpe.setwaranty(0);
            bpe.showDetails();
            System.out.println("\n" + "\nReplacement Year :" + bpe.replacement_Year());
        } catch (Invalid_waranty e) {
            System.out.println(e.getMessage());
        }
    }
}
