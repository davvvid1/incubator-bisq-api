package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.US_POSTAL_MONEY_ORDER_ID)
public class USPostalMoneyOrderPaymentAccount extends PaymentAccount {

    public String holderName;

    public String postalAddress;

    public USPostalMoneyOrderPaymentAccount() {
        super(PaymentMethod.US_POSTAL_MONEY_ORDER_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.notNull("postalAddress", this.postalAddress);
        validations.notEmpty("postalAddress", this.postalAddress);
        validations.throwIfAnyValidation();
    }
}
