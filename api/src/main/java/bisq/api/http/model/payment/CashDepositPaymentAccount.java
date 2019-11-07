package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.CASH_DEPOSIT_ID)
public class CashDepositPaymentAccount extends PaymentAccount {

    public String accountNr;

    public String accountType;

    public String bankId;

    public String bankName;

    public String branchId;

    public String countryCode;

    public String holderName;

    public String holderEmail;

    public String holderTaxId;

    public String requirements;

    public CashDepositPaymentAccount() {
        super(PaymentMethod.CASH_DEPOSIT_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("countryCode", this.countryCode);
        validations.notEmpty("countryCode", this.countryCode);
        validations.countryCode("countryCode", this.countryCode);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.notNull("holderEmail", this.holderEmail);
        validations.notEmpty("holderEmail", this.holderEmail);
        validations.throwIfAnyValidation();
    }
}
