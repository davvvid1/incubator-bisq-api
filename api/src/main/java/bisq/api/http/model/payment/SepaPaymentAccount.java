package bisq.api.http.model.payment;

import bisq.api.http.model.Validatable;
import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName(PaymentMethod.SEPA_ID)
public class SepaPaymentAccount extends PaymentAccount {

    public String countryCode;

    public String holderName;

    public String bic;

    public String iban;

    public List<String> acceptedCountries = new ArrayList<>();

    public SepaPaymentAccount() {
        super(PaymentMethod.SEPA_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("countryCode", this.countryCode);
        validations.notEmpty("countryCode", this.countryCode);
        validations.countryCode("countryCode", this.countryCode);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.notNull("bic", this.bic);
        validations.notEmpty("bic", this.bic);
        validations.notNull("iban", this.iban);
        validations.notEmpty("iban", this.iban);
        validations.notEmpty("acceptedCountries", this.acceptedCountries);
        validations.throwIfAnyValidation();
    }
}
