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
import bisq.core.payment.MoneyGramAccount;
import bisq.core.payment.payload.MoneyGramAccountPayload;

public class MoneyGramPaymentAccountConverter extends AbstractPaymentAccountConverter<MoneyGramAccount, MoneyGramAccountPayload, MoneyGramPaymentAccount> {

    @Override
    public MoneyGramAccount toBusinessModel(MoneyGramPaymentAccount rest) {
        MoneyGramAccount business = new MoneyGramAccount();
        business.init();
        CountryUtil.findCountryByCode(rest.countryCode).ifPresent(business::setCountry);
        business.setEmail(rest.email);
        business.setFullName(rest.holderName);
        business.setState(rest.state);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public MoneyGramPaymentAccount toRestModel(MoneyGramAccount business) {
        MoneyGramPaymentAccount rest = toRestModel((MoneyGramAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public MoneyGramPaymentAccount toRestModel(MoneyGramAccountPayload business) {
        MoneyGramPaymentAccount rest = new MoneyGramPaymentAccount();
        rest.countryCode = business.getCountryCode();
        rest.email = business.getEmail();
        rest.holderName = business.getHolderName();
        rest.state = business.getState();
        toRestModel(rest, business);
        return rest;
    }

}
