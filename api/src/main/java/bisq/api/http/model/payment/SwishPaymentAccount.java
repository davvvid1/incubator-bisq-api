package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.SWISH_ID)
public class SwishPaymentAccount extends PaymentAccount {

    public String mobileNr;

    public String holderName;

    public SwishPaymentAccount() {
        super(PaymentMethod.SWISH_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("mobileNr", this.mobileNr);
        validations.notEmpty("mobileNr", this.mobileNr);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.throwIfAnyValidation();
    }
}
