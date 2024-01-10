package billing.component;

public class BronzePlan extends HealthInsurancePlan{
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
    BronzePlan(){
        super.setCoverage(0.9);
    }
}
