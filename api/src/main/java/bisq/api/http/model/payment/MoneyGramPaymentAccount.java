package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.MONEY_GRAM_ID)
public class MoneyGramPaymentAccount extends PaymentAccount {

    public String holderName;

    public String countryCode;

    public String state;

    public String email;

    public MoneyGramPaymentAccount() {
        super(PaymentMethod.MONEY_GRAM_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.notNull("countryCode", this.countryCode);
        validations.notEmpty("countryCode", this.countryCode);
        validations.countryCode("countryCode", this.countryCode);
        validations.notNull("email", this.email);
        validations.notEmpty("email", this.email);
        validations.throwIfAnyValidation();
    }
}
