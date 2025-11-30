package Twelfth_HW;

class TeacherVisitor implements Visitor {
    private String subjectType;
    private int credits;

    public TeacherVisitor(String subjectType, int credits) {
        this.subjectType = subjectType;
        this.credits = credits;
    }

    @Override
    public void visit(HumanitarianStudent s) {
        if ("humanitarian".equals(subjectType)) {
            s.addCredits(credits);
            System.out.println("Teacher (" + subjectType + ") taught " + s.getName() + ". +"+credits+" credits.");
        } else {
            System.out.println("Error: " + subjectType + " teacher cannot teach Humanitarian student.");
        }
    }

    @Override
    public void visit(NaturalStudent s) {
        if ("natural".equals(subjectType)) {
            s.addCredits(credits);
            System.out.println("Teacher (" + subjectType + ") taught " + s.getName() + ". +"+credits+" credits.");
        } else {
            System.out.println("Error: " + subjectType + " teacher cannot teach Natural student.");
        }
    }

    @Override
    public void visit(MixedStudent s) {
        s.addCredits(credits);
        System.out.println("Teacher (" + subjectType + ") taught " + s.getName() + ". +"+credits+" credits.");
    }
}

// (Accounting / Parents)
class IncomeVisitor implements Visitor {
    private int amount;
    private String source;

    public IncomeVisitor(String source, int amount) {
        this.source = source;
        this.amount = amount;
    }

    private void giveMoney(Student s) {
        if (!s.isExpelled()) {
            s.addMoney(amount);
            System.out.println(s.getName() + " received " + source + ": " + amount);
        }
    }

    @Override public void visit(HumanitarianStudent s) { giveMoney(s); }
    @Override public void visit(NaturalStudent s) { giveMoney(s); }
    @Override public void visit(MixedStudent s) { giveMoney(s); }
}

class ExpenseVisitor implements Visitor {
    private int amount;
    private String purpose;

    public ExpenseVisitor(String purpose, int amount) {
        this.purpose = purpose;
        this.amount = amount;
    }

    private void takeMoney(Student s) {
        if (s.isExpelled()) return;

        boolean success = s.spendMoney(amount);
        if (success) {
            System.out.println(s.getName() + " paid for " + purpose + ": " + amount);
        } else {
            System.out.println(s.getName() + " FAILED to pay for " + purpose + ". EXPELLED!");
        }
    }

    @Override public void visit(HumanitarianStudent s) { takeMoney(s); }
    @Override public void visit(NaturalStudent s) { takeMoney(s); }
    @Override public void visit(MixedStudent s) { takeMoney(s); }
}