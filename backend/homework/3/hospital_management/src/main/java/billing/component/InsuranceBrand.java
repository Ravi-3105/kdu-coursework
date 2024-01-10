package billing.component;

public interface InsuranceBrand {
    double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking);
}
