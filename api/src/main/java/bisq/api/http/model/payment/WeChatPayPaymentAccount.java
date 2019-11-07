package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentMethod.WECHAT_PAY_ID)
public class WeChatPayPaymentAccount extends PaymentAccount {

    public String accountNr;

    public WeChatPayPaymentAccount() {
        super(PaymentMethod.WECHAT_PAY_ID);
    }

    public void validate() {
        Validations validations = this.getValidations();
        validations.notNull("accountNr", this.accountNr);
        validations.notEmpty("accountNr", this.accountNr);
        validations.throwIfAnyValidation();
    }
}
