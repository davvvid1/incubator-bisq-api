package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.F2F_ID)
public class F2FPaymentAccount extends PaymentAccount {

    public String contact;

    public String city;

    public String extraInfo;

    public F2FPaymentAccount() {
        super(PaymentMethod.F2F_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("contact", this.contact);
        validations.notEmpty("contact", this.contact);
        validations.notNull("city", this.city);
        validations.notEmpty("city", this.city);
        validations.throwIfAnyValidation();
    }
}
