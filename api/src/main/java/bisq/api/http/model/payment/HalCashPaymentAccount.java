package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.HAL_CASH_ID)
public class HalCashPaymentAccount extends PaymentAccount {

    public String mobileNr;

    public HalCashPaymentAccount() {
        super(PaymentMethod.HAL_CASH_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("mobileNr", this.mobileNr);
        validations.notEmpty("mobileNr", this.mobileNr);
        validations.throwIfAnyValidation();
    }
}
