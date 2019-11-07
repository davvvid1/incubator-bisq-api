package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.REVOLUT_ID)
public class RevolutPaymentAccount extends PaymentAccount {

    public String accountId;

    public RevolutPaymentAccount() {
        super(PaymentMethod.REVOLUT_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("accountId", this.accountId);
        validations.notEmpty("accountId", this.accountId);
        validations.throwIfAnyValidation();
    }
}
