package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.CHASE_QUICK_PAY_ID)
public class ChaseQuickPayPaymentAccount extends PaymentAccount {

    public String email;

    public String holderName;

    public ChaseQuickPayPaymentAccount() {
        super(PaymentMethod.CHASE_QUICK_PAY_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("email", this.email);
        validations.notEmpty("email", this.email);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.throwIfAnyValidation();
    }
}
