package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.PERFECT_MONEY_ID)
public class PerfectMoneyPaymentAccount extends PaymentAccount {

    public String accountNr;

    public PerfectMoneyPaymentAccount() {
        super(PaymentMethod.PERFECT_MONEY_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("accountNr", this.accountNr);
        validations.notEmpty("accountNr", this.accountNr);
        validations.throwIfAnyValidation();
    }
}
