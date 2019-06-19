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

import bisq.core.exceptions.ValidationException;
import bisq.core.locale.CryptoCurrency;
import bisq.core.locale.CurrencyUtil;
import bisq.core.locale.FiatCurrency;
import bisq.core.locale.TradeCurrency;
import bisq.core.payment.payload.PaymentAccountPayload;

import java.util.List;
import java.util.Optional;

public abstract class AbstractPaymentAccountConverter<B extends bisq.core.payment.PaymentAccount, BP extends PaymentAccountPayload, R extends PaymentAccount> implements PaymentAccountConverter<B, BP, R> {

    protected void toBusinessModel(B business, R rest) {
        if (rest.accountName != null)
            business.setAccountName(rest.accountName);
        business.getTradeCurrencies().clear();
        CurrencyConverter currencyConverter;
        if (rest instanceof CryptoCurrencyPaymentAccount)
            currencyConverter = new CryptoCurrencyConverter();
        else
            currencyConverter = new FiatCurrencyConverter();

        if (rest.selectedTradeCurrency != null)
            business.setSelectedTradeCurrency(currencyConverter.convert(rest.selectedTradeCurrency));
        if (rest.tradeCurrencies != null)
            rest.tradeCurrencies.forEach(currencyCode -> business.addCurrency(currencyConverter.convert(currencyCode)));
    }

    protected void toRestModel(R rest, B business) {
        rest.id = business.getId();
        rest.accountName = business.getAccountName();
        TradeCurrency selectedTradeCurrency = business.getSelectedTradeCurrency();
        if (selectedTradeCurrency != null)
            rest.selectedTradeCurrency = selectedTradeCurrency.getCode();
        List<TradeCurrency> tradeCurrencies = business.getTradeCurrencies();
        if (tradeCurrencies != null)
            tradeCurrencies.forEach(currency -> rest.tradeCurrencies.add(currency.getCode()));
    }

    protected void toRestModel(R rest, BP business) {
        rest.paymentDetails = business.getPaymentDetails();
    }

    private interface CurrencyConverter {
        TradeCurrency convert(String currencyCode);
    }

    private static class FiatCurrencyConverter implements CurrencyConverter {
        @Override
        public TradeCurrency convert(String currencyCode) {
            return new FiatCurrency(currencyCode);
        }
    }

    private static class CryptoCurrencyConverter implements CurrencyConverter {
        @Override
        public TradeCurrency convert(String currencyCode) {
            Optional<CryptoCurrency> cryptoCurrencyOptional = CurrencyUtil.getCryptoCurrency(currencyCode);
            if (!cryptoCurrencyOptional.isPresent()) {
                throw new ValidationException("Unsupported crypto currency code: " + currencyCode);
            }
            return cryptoCurrencyOptional.get();
        }
    }
}
