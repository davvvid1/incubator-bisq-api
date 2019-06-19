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

import bisq.core.payment.payload.PaymentAccountPayload;
import bisq.core.payment.payload.PaymentMethod;

import java.util.HashMap;
import java.util.Map;



import javax.ws.rs.WebApplicationException;

@SuppressWarnings("deprecation")
public final class PaymentAccountHelper {

    private static Map<String, PaymentAccountConverter<? extends bisq.core.payment.PaymentAccount, ? extends PaymentAccountPayload, ? extends PaymentAccount>> converters = new HashMap<>();

    static {
        converters.put(PaymentMethod.ADVANCED_CASH_ID, new AdvancedCashPaymentAccountConverter());
        converters.put(PaymentMethod.ALI_PAY_ID, new AliPayPaymentAccountConverter());
        converters.put(PaymentMethod.BLOCK_CHAINS_ID, new CryptoCurrencyPaymentAccountConverter());
        converters.put(PaymentMethod.CASH_DEPOSIT_ID, new CashDepositPaymentAccountConverter());
        converters.put(PaymentMethod.CHASE_QUICK_PAY_ID, new ChaseQuickPayPaymentAccountConverter());
        converters.put(PaymentMethod.CLEAR_X_CHANGE_ID, new ClearXchangePaymentAccountConverter());
        converters.put(PaymentMethod.F2F_ID, new F2FPaymentAccountConverter());
        converters.put(PaymentMethod.FASTER_PAYMENTS_ID, new FasterPaymentsPaymentAccountConverter());
        converters.put(PaymentMethod.HAL_CASH_ID, new HalCashPaymentAccountConverter());
        converters.put(PaymentMethod.INTERAC_E_TRANSFER_ID, new InteracETransferPaymentAccountConverter());
        converters.put(PaymentMethod.MONEY_BEAM_ID, new MoneyBeamPaymentAccountConverter());
        converters.put(PaymentMethod.MONEY_GRAM_ID, new MoneyGramPaymentAccountConverter());
        converters.put(PaymentMethod.NATIONAL_BANK_ID, new NationalBankAccountPaymentAccountConverter());
        converters.put(PaymentMethod.PERFECT_MONEY_ID, new PerfectMoneyPaymentAccountConverter());
        converters.put(PaymentMethod.POPMONEY_ID, new PopmoneyPaymentAccountConverter());
        converters.put(PaymentMethod.PROMPT_PAY_ID, new PromptPayPaymentAccountConverter());
        converters.put(PaymentMethod.REVOLUT_ID, new RevolutPaymentAccountConverter());
        converters.put(PaymentMethod.SAME_BANK_ID, new SameBankAccountPaymentAccountConverter());
        converters.put(PaymentMethod.SEPA_ID, new SepaPaymentAccountConverter());
        converters.put(PaymentMethod.SEPA_INSTANT_ID, new SepaInstantPaymentAccountConverter());
        converters.put(PaymentMethod.SPECIFIC_BANKS_ID, new SpecificBanksAccountPaymentAccountConverter());
        converters.put(PaymentMethod.SWISH_ID, new SwishPaymentAccountConverter());
        converters.put(PaymentMethod.UPHOLD_ID, new UpholdPaymentAccountConverter());
        converters.put(PaymentMethod.US_POSTAL_MONEY_ORDER_ID, new USPostalMoneyOrderPaymentAccountConverter());
        converters.put(PaymentMethod.WECHAT_PAY_ID, new WeChatPayPaymentAccountConverter());
        converters.put(PaymentMethod.WESTERN_UNION_ID, new WesternUnionPaymentAccountConverter());
    }

    @SuppressWarnings("unchecked")
    public static bisq.core.payment.PaymentAccount toBusinessModel(PaymentAccount rest) {
        PaymentAccountConverter converter = converters.get(rest.paymentMethod);
        if (converter != null) {
            return converter.toBusinessModel(rest);
        }
        throw new WebApplicationException("Unsupported paymentMethod:" + rest.paymentMethod, 400);
    }

    @SuppressWarnings("unchecked")
    public static PaymentAccount toRestModel(bisq.core.payment.PaymentAccount business) {
        String paymentMethodId = business.getPaymentMethod().getId();
        PaymentAccountConverter converter = converters.get(paymentMethodId);
        if (converter != null) {
            return converter.toRestModel(business);
        }
        throw new IllegalArgumentException("Unsupported paymentMethod:" + paymentMethodId);
    }

    @SuppressWarnings("unchecked")
    public static PaymentAccount toRestModel(PaymentAccountPayload business) {
        String paymentMethodId = business.getPaymentMethodId();
        PaymentAccountConverter converter = converters.get(paymentMethodId);
        if (converter != null) {
            PaymentAccount paymentAccount = converter.toRestModel(business);
            paymentAccount.paymentDetails = business.getPaymentDetails();
            return paymentAccount;
        }
        throw new IllegalArgumentException("Unsupported paymentMethod:" + paymentMethodId);
    }
}
