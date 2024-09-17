package billing.component;

public class SilverPlan extends HealthInsurancePlan{
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
    SilverPlan(){
        super.setCoverage(0.9);
    }
}
