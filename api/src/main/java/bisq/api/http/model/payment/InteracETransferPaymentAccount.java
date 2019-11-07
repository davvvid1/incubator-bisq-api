package bisq.api.http.model.payment;

import bisq.api.http.model.Validatable;
import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.INTERAC_E_TRANSFER_ID)
public class InteracETransferPaymentAccount extends PaymentAccount implements Validatable {

    public String emailOrMobileNr;

    public String holderName;

    public String question;

    public String answer;

    public InteracETransferPaymentAccount() {
        super(PaymentMethod.INTERAC_E_TRANSFER_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("emailOrMobileNr", this.emailOrMobileNr);
        validations.notEmpty("emailOrMobileNr", this.emailOrMobileNr);
        validations.notNull("holderName", this.holderName);
        validations.notEmpty("holderName", this.holderName);
        validations.notNull("question", this.question);
        validations.notEmpty("question", this.question);
        validations.notNull("answer", this.answer);
        validations.notEmpty("answer", this.answer);
        validations.throwIfAnyValidation();
    }
}
