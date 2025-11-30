package Twelfth_HW;

abstract class BaseStudent implements Student {
    protected int credits = 0;
    protected int requiredCredits;
    protected int money;
    protected boolean isExpelled = false;

    public BaseStudent(int requiredCredits, int startMoney) {
        this.requiredCredits = requiredCredits;
        this.money = startMoney;
    }

    @Override
    public void addCredits(int amount) {
        this.credits += amount;
    }

    @Override
    public void addMoney(int amount) {
        this.money += amount;
    }

    @Override
    public boolean spendMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        } else {
            isExpelled = true; // Студент відрахований через брак коштів
            return false;
        }
    }

    @Override
    public boolean isExpelled() { return isExpelled; }

    @Override
    public int getCredits() { return credits; }

    @Override
    public int getRequiredCredits() { return requiredCredits; }
}

// Конкретні реалізації
class HumanitarianStudent extends BaseStudent {
    public HumanitarianStudent(int creds, int money) { super(creds, money); }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    @Override
    public String getName() { return "Humanitarian Student"; }
}

class NaturalStudent extends BaseStudent {
    public NaturalStudent(int creds, int money) { super(creds, money); }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    @Override
    public String getName() { return "Natural Student"; }
}

class MixedStudent extends BaseStudent {
    public MixedStudent(int creds, int money) { super(creds, money); }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    @Override
    public String getName() { return "Natural-Humanitarian Student"; }
}
