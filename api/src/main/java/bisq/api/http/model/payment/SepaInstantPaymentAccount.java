package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName(PaymentMethod.SEPA_INSTANT_ID)
public class SepaInstantPaymentAccount extends PaymentAccount {

    public String countryCode;

    public String holderName;

    public String bic;

    public String iban;

    public List<String> acceptedCountries = new ArrayList<>();

    public SepaInstantPaymentAccount() {
        super(PaymentMethod.SEPA_INSTANT_ID);
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
        validations.notNull("bic", this.bic);
        validations.notEmpty("bic", this.bic);
        validations.notEmpty("acceptedCountries", this.acceptedCountries);
        validations.throwIfAnyValidation();
    }
}
