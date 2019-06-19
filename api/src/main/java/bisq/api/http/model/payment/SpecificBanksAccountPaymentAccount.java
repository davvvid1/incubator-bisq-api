package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName(PaymentMethod.SPECIFIC_BANKS_ID)
public class SpecificBanksAccountPaymentAccount extends PaymentAccount {

    public List<String> acceptedBanks = new ArrayList<>();

    public String accountNr;

    public String accountType;

    public String bankId;

    public String bankName;

    public String branchId;

    public String countryCode;

    public String holderName;

    public String holderTaxId;

    public SpecificBanksAccountPaymentAccount() {
        super(PaymentMethod.SPECIFIC_BANKS_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notEmpty("acceptedBanks", this.acceptedBanks);
        validations.notNull("accountNr", this.accountNr);
        validations.notEmpty("accountNr", this.accountNr);
        validations.notNull("bankId", this.bankId);
        validations.notEmpty("bankId", this.bankId);
        validations.notNull("bankName", this.bankName);
        validations.notEmpty("bankName", this.bankName);
        validations.notNull("branchId", this.branchId);
        validations.notEmpty("branchId", this.branchId);
        validations.notNull("countryCode", this.countryCode);
        validations.notEmpty("countryCode", this.countryCode);
        validations.countryCode("countryCode", this.countryCode);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.throwIfAnyValidation();
    }
}
