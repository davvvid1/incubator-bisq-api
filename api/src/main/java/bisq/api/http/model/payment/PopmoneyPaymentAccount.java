package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.POPMONEY_ID)
public class PopmoneyPaymentAccount extends PaymentAccount {

    public String accountId;

    public String holderName;

    public PopmoneyPaymentAccount() {
        super(PaymentMethod.POPMONEY_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("accountId", this.accountId);
        validations.notEmpty("accountId", this.accountId);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.throwIfAnyValidation();
    }
}
