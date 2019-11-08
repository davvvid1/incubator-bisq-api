/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.api.http.model.payment;

import bisq.api.http.model.Validatable;
import bisq.api.http.model.Validations;

import bisq.core.exceptions.ConstraintViolationException;
import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;



import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        discriminatorProperty = "paymentMethod",
        discriminatorMapping = {
                @DiscriminatorMapping(value = PaymentMethod.ADVANCED_CASH_ID, schema = AdvancedCashPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.ALI_PAY_ID, schema = AliPayPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.CASH_DEPOSIT_ID, schema = CashDepositPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.CHASE_QUICK_PAY_ID, schema = ChaseQuickPayPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.CLEAR_X_CHANGE_ID, schema = ClearXchangePaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.BLOCK_CHAINS_ID, schema = CryptoCurrencyPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.F2F_ID, schema = F2FPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.FASTER_PAYMENTS_ID, schema = FasterPaymentsPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.HAL_CASH_ID, schema = HalCashPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.INTERAC_E_TRANSFER_ID, schema = InteracETransferPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.MONEY_BEAM_ID, schema = MoneyBeamPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.MONEY_GRAM_ID, schema = MoneyGramPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.NATIONAL_BANK_ID, schema = NationalBankAccountPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.PERFECT_MONEY_ID, schema = PerfectMoneyPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.POPMONEY_ID, schema = PopmoneyPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.PROMPT_PAY_ID, schema = PromptPayPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.REVOLUT_ID, schema = RevolutPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.SAME_BANK_ID, schema = SameBankAccountPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.SEPA_ID, schema = SepaPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.SEPA_INSTANT_ID, schema = SepaInstantPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.SPECIFIC_BANKS_ID, schema = SpecificBanksAccountPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.SWISH_ID, schema = SwishPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.UPHOLD_ID, schema = UpholdPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.US_POSTAL_MONEY_ORDER_ID, schema = USPostalMoneyOrderPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.WECHAT_PAY_ID, schema = WeChatPayPaymentAccount.class),
                @DiscriminatorMapping(value = PaymentMethod.WESTERN_UNION_ID, schema = WesternUnionPaymentAccount.class)
        }
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "paymentMethod", include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdvancedCashPaymentAccount.class, name = PaymentMethod.ADVANCED_CASH_ID),
        @JsonSubTypes.Type(value = AliPayPaymentAccount.class, name = PaymentMethod.ALI_PAY_ID),
        @JsonSubTypes.Type(value = CashDepositPaymentAccount.class, name = PaymentMethod.CASH_DEPOSIT_ID),
        @JsonSubTypes.Type(value = ChaseQuickPayPaymentAccount.class, name = PaymentMethod.CHASE_QUICK_PAY_ID),
        @JsonSubTypes.Type(value = ClearXchangePaymentAccount.class, name = PaymentMethod.CLEAR_X_CHANGE_ID),
        @JsonSubTypes.Type(value = CryptoCurrencyPaymentAccount.class, name = PaymentMethod.BLOCK_CHAINS_ID),
        @JsonSubTypes.Type(value = F2FPaymentAccount.class, name = PaymentMethod.F2F_ID),
        @JsonSubTypes.Type(value = FasterPaymentsPaymentAccount.class, name = PaymentMethod.FASTER_PAYMENTS_ID),
        @JsonSubTypes.Type(value = HalCashPaymentAccount.class, name = PaymentMethod.HAL_CASH_ID),
        @JsonSubTypes.Type(value = InteracETransferPaymentAccount.class, name = PaymentMethod.INTERAC_E_TRANSFER_ID),
        @JsonSubTypes.Type(value = MoneyBeamPaymentAccount.class, name = PaymentMethod.MONEY_BEAM_ID),
        @JsonSubTypes.Type(value = MoneyGramPaymentAccount.class, name = PaymentMethod.MONEY_GRAM_ID),
        @JsonSubTypes.Type(value = NationalBankAccountPaymentAccount.class, name = PaymentMethod.NATIONAL_BANK_ID),
        @JsonSubTypes.Type(value = PerfectMoneyPaymentAccount.class, name = PaymentMethod.PERFECT_MONEY_ID),
        @JsonSubTypes.Type(value = PopmoneyPaymentAccount.class, name = PaymentMethod.POPMONEY_ID),
        @JsonSubTypes.Type(value = PromptPayPaymentAccount.class, name = PaymentMethod.PROMPT_PAY_ID),
        @JsonSubTypes.Type(value = RevolutPaymentAccount.class, name = PaymentMethod.REVOLUT_ID),
        @JsonSubTypes.Type(value = SameBankAccountPaymentAccount.class, name = PaymentMethod.SAME_BANK_ID),
        @JsonSubTypes.Type(value = SepaPaymentAccount.class, name = PaymentMethod.SEPA_ID),
        @JsonSubTypes.Type(value = SepaInstantPaymentAccount.class, name = PaymentMethod.SEPA_INSTANT_ID),
        @JsonSubTypes.Type(value = SpecificBanksAccountPaymentAccount.class, name = PaymentMethod.SPECIFIC_BANKS_ID),
        @JsonSubTypes.Type(value = SwishPaymentAccount.class, name = PaymentMethod.SWISH_ID),
        @JsonSubTypes.Type(value = UpholdPaymentAccount.class, name = PaymentMethod.UPHOLD_ID),
        @JsonSubTypes.Type(value = USPostalMoneyOrderPaymentAccount.class, name = PaymentMethod.US_POSTAL_MONEY_ORDER_ID),
        @JsonSubTypes.Type(value = WeChatPayPaymentAccount.class, name = PaymentMethod.WECHAT_PAY_ID),
        @JsonSubTypes.Type(value = WesternUnionPaymentAccount.class, name = PaymentMethod.WESTERN_UNION_ID)
})
public abstract class PaymentAccount implements Validatable {

    public String id;

    public String accountName;

    @SuppressWarnings("WeakerAccess")
    public String paymentDetails;

    public String paymentMethod;

    public String selectedTradeCurrency;

    public List<String> tradeCurrencies = new ArrayList<>();

    public PaymentAccount(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void validate() {}

    public Validations getValidations() {
        ConstraintViolationException.Builder builder = new ConstraintViolationException.Builder();
        Validations validations = new Validations(builder);
        validations.notEmpty("accountName", accountName);
        validations.notEmpty("selectedTradeCurrency", selectedTradeCurrency);
        return validations;
    }
}
