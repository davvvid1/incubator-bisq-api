package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.BLOCK_CHAINS_ID)
public class CryptoCurrencyPaymentAccount extends PaymentAccount {

    public String address;

    public CryptoCurrencyPaymentAccount() {
        super(PaymentMethod.BLOCK_CHAINS_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("address", this.address);
        validations.notEmpty("address", this.address);
        validations.throwIfAnyValidation();
    }
}
