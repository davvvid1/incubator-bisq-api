package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.FASTER_PAYMENTS_ID)
public class FasterPaymentsPaymentAccount extends PaymentAccount {

    public String accountNr;

    public String sortCode;

    public FasterPaymentsPaymentAccount() {
        super(PaymentMethod.FASTER_PAYMENTS_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("accountNr", this.accountNr);
        validations.notEmpty("accountNr", this.accountNr);
        validations.notNull("sortCode", this.sortCode);
        validations.notEmpty("sortCode", this.sortCode);
        validations.throwIfAnyValidation();
    }
}
