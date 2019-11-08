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

package bisq.core.payment;

import bisq.core.account.witness.AccountAgeWitnessService;
import bisq.core.exceptions.NotFoundException;
import bisq.core.exceptions.ValidationException;
import bisq.core.locale.CryptoCurrency;
import bisq.core.locale.CurrencyUtil;
import bisq.core.locale.FiatCurrency;
import bisq.core.locale.TradeCurrency;
import bisq.core.payment.validation.AltCoinAddressValidator;
import bisq.core.user.Preferences;
import bisq.core.user.User;
import bisq.core.util.validation.InputValidator;

import com.google.inject.Inject;

import java.util.List;

public class PaymentAccountManager {

    private final AccountAgeWitnessService accountAgeWitnessService;
    private final Preferences preferences;
    private final User user;
    private AltCoinAddressValidator altCoinAddressValidator;

    @Inject
    public PaymentAccountManager(AccountAgeWitnessService accountAgeWitnessService, AltCoinAddressValidator altCoinAddressValidator, Preferences preferences, User user) {
        this.accountAgeWitnessService = accountAgeWitnessService;
        this.altCoinAddressValidator = altCoinAddressValidator;
        this.preferences = preferences;
        this.user = user;
    }

    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount) {
        if (paymentAccount instanceof CryptoCurrencyAccount) {
            CryptoCurrencyAccount cryptoCurrencyAccount = (CryptoCurrencyAccount) paymentAccount;
            TradeCurrency tradeCurrency = cryptoCurrencyAccount.getSingleTradeCurrency();
            if (tradeCurrency == null) {
                throw new ValidationException("CryptoCurrency account must have exactly one trade currency");
            }
            altCoinAddressValidator.setCurrencyCode(tradeCurrency.getCode());
            InputValidator.ValidationResult validationResult = altCoinAddressValidator.validate(cryptoCurrencyAccount.getAddress());
            if (!validationResult.isValid) {
                throw new ValidationException(validationResult.errorMessage);
            }
        }
//        TODO we should validate payment account here as well
        user.addPaymentAccount(paymentAccount);
        TradeCurrency singleTradeCurrency = paymentAccount.getSingleTradeCurrency();
        List<TradeCurrency> tradeCurrencies = paymentAccount.getTradeCurrencies();
        if (singleTradeCurrency != null) {
            if (singleTradeCurrency instanceof FiatCurrency)
                preferences.addFiatCurrency((FiatCurrency) singleTradeCurrency);
            else
                preferences.addCryptoCurrency((CryptoCurrency) singleTradeCurrency);
        } else if (!tradeCurrencies.isEmpty()) {
            tradeCurrencies.forEach(tradeCurrency -> {
                if (tradeCurrency instanceof FiatCurrency)
                    preferences.addFiatCurrency((FiatCurrency) tradeCurrency);
                else
                    preferences.addCryptoCurrency((CryptoCurrency) tradeCurrency);
            });
        }
        if (!(paymentAccount instanceof CryptoCurrencyAccount)) {
            if (singleTradeCurrency == null && !tradeCurrencies.isEmpty()) {
                if (tradeCurrencies.contains(CurrencyUtil.getDefaultTradeCurrency()))
                    paymentAccount.setSelectedTradeCurrency(CurrencyUtil.getDefaultTradeCurrency());
                else
                    paymentAccount.setSelectedTradeCurrency(tradeCurrencies.get(0));

            }
            accountAgeWitnessService.publishMyAccountAgeWitness(paymentAccount.getPaymentAccountPayload());
        }
        return paymentAccount;
    }

    public void removePaymentAccount(String id) {
        PaymentAccount paymentAccount = user.getPaymentAccount(id);
        if (paymentAccount == null) {
            System.out.println("Throw");
             throw new NotFoundException("Payment account not found: " + id);
        }
        user.removePaymentAccount(paymentAccount);
    }
}
