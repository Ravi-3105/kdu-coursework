package billing.component;

public class GoldPlan extends HealthInsurancePlan{
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
    GoldPlan(){
        super.setCoverage(0.9);
    }
}
