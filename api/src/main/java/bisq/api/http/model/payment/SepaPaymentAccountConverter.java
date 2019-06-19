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

import bisq.core.locale.CountryUtil;
import bisq.core.payment.SepaAccount;
import bisq.core.payment.payload.SepaAccountPayload;

import java.util.List;

public class SepaPaymentAccountConverter extends AbstractPaymentAccountConverter<SepaAccount, SepaAccountPayload, SepaPaymentAccount> {

    @Override
    public SepaAccount toBusinessModel(SepaPaymentAccount rest) {
        SepaAccount business = new SepaAccount();
        business.init();
        business.setBic(rest.bic);
        business.setIban(rest.iban);
        business.setHolderName(rest.holderName);
        CountryUtil.findCountryByCode(rest.countryCode).ifPresent(business::setCountry);
        business.getAcceptedCountryCodes().clear();
        if (rest.acceptedCountries != null)
            rest.acceptedCountries.forEach(business::addAcceptedCountry);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public SepaPaymentAccount toRestModel(SepaAccount business) {
        SepaPaymentAccount rest = toRestModel((SepaAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public SepaPaymentAccount toRestModel(SepaAccountPayload business) {
        SepaPaymentAccount rest = new SepaPaymentAccount();
        rest.iban = business.getIban();
        rest.bic = business.getBic();
        rest.countryCode = business.getCountryCode();
        rest.holderName = business.getHolderName();
        List<String> tradeCurrencies = business.getAcceptedCountryCodes();
        if (tradeCurrencies != null)
            rest.acceptedCountries.addAll(tradeCurrencies);
        toRestModel(rest, business);
        return rest;

    }

}
