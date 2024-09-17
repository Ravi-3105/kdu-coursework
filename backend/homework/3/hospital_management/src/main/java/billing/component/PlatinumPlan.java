package billing.component;


public class PlatinumPlan extends HealthInsurancePlan{

    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.08 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
    PlatinumPlan(){
        super.setCoverage(0.9);
    }
}
