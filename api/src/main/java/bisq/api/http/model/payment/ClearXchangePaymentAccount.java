package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.CLEAR_X_CHANGE_ID)
public class ClearXchangePaymentAccount extends PaymentAccount {

    public String emailOrMobileNr;

    public String holderName;

    public ClearXchangePaymentAccount() {
        super(PaymentMethod.CLEAR_X_CHANGE_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("emailOrMobileNr", this.emailOrMobileNr);
        validations.notEmpty("emailOrMobileNr", this.emailOrMobileNr);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.throwIfAnyValidation();
    }
}
