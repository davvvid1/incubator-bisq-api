package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.PROMPT_PAY_ID)
public class PromptPayPaymentAccount extends PaymentAccount {

    public String promptPayId;

    public PromptPayPaymentAccount() {
        super(PaymentMethod.PROMPT_PAY_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("promptPayId", this.promptPayId);
        validations.notEmpty("promptPayId", this.promptPayId);
        validations.throwIfAnyValidation();
    }
}
