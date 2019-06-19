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

import bisq.core.payment.RevolutAccount;
import bisq.core.payment.payload.RevolutAccountPayload;

public class RevolutPaymentAccountConverter extends AbstractPaymentAccountConverter<RevolutAccount, RevolutAccountPayload, RevolutPaymentAccount> {

    @Override
    public RevolutAccount toBusinessModel(RevolutPaymentAccount rest) {
        RevolutAccount business = new RevolutAccount();
        business.init();
        business.setAccountId(rest.accountId);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public RevolutPaymentAccount toRestModel(RevolutAccount business) {
        RevolutPaymentAccount rest = toRestModel((RevolutAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public RevolutPaymentAccount toRestModel(RevolutAccountPayload business) {
        RevolutPaymentAccount rest = new RevolutPaymentAccount();
        rest.accountId = business.getAccountId();
        toRestModel(rest, business);
        return rest;

    }

}
