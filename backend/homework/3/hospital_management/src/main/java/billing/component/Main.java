package billing.component;

import org.example.Logging;
import org.example.User;


public class Main {
    public static void main(String[] args){
        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        String premium = String.valueOf( insurancePlan.computeMonthlyPremium(5000, 56, true));
        Logging.print("Premium to be paid: ".concat(premium));
    }
}
