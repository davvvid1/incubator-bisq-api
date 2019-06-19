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

import bisq.core.payment.F2FAccount;
import bisq.core.payment.payload.F2FAccountPayload;

public class F2FPaymentAccountConverter extends AbstractPaymentAccountConverter<F2FAccount, F2FAccountPayload, F2FPaymentAccount> {

    @Override
    public F2FAccount toBusinessModel(F2FPaymentAccount rest) {
        F2FAccount business = new F2FAccount();
        business.init();
        business.setCity(rest.city);
        business.setContact(rest.contact);
        business.setExtraInfo(rest.extraInfo);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public F2FPaymentAccount toRestModel(F2FAccount business) {
        F2FPaymentAccount rest = toRestModel((F2FAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public F2FPaymentAccount toRestModel(F2FAccountPayload business) {
        F2FPaymentAccount rest = new F2FPaymentAccount();
        rest.city = business.getCity();
        rest.contact = business.getContact();
        rest.extraInfo = business.getExtraInfo();
        toRestModel(rest, business);
        return rest;
    }

}
