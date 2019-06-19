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

import bisq.core.payment.UpholdAccount;
import bisq.core.payment.payload.UpholdAccountPayload;

public class UpholdPaymentAccountConverter extends AbstractPaymentAccountConverter<UpholdAccount, UpholdAccountPayload, UpholdPaymentAccount> {

    @Override
    public UpholdAccount toBusinessModel(UpholdPaymentAccount rest) {
        UpholdAccount business = new UpholdAccount();
        business.init();
        business.setAccountId(rest.accountId);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public UpholdPaymentAccount toRestModel(UpholdAccount business) {
        UpholdPaymentAccount rest = toRestModel((UpholdAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public UpholdPaymentAccount toRestModel(UpholdAccountPayload business) {
        UpholdPaymentAccount rest = new UpholdPaymentAccount();
        rest.accountId = business.getAccountId();
        toRestModel(rest, business);
        return rest;

    }

}
