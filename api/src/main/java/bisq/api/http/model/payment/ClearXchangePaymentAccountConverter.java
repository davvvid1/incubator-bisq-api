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

import bisq.core.payment.ClearXchangeAccount;
import bisq.core.payment.payload.ClearXchangeAccountPayload;

public class ClearXchangePaymentAccountConverter extends AbstractPaymentAccountConverter<ClearXchangeAccount, ClearXchangeAccountPayload, ClearXchangePaymentAccount> {

    @Override
    public ClearXchangeAccount toBusinessModel(ClearXchangePaymentAccount rest) {
        ClearXchangeAccount business = new ClearXchangeAccount();
        business.init();
        business.setEmailOrMobileNr(rest.emailOrMobileNr);
        business.setHolderName(rest.holderName);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public ClearXchangePaymentAccount toRestModel(ClearXchangeAccount business) {
        ClearXchangePaymentAccount rest = toRestModel((ClearXchangeAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public ClearXchangePaymentAccount toRestModel(ClearXchangeAccountPayload business) {
        ClearXchangePaymentAccount rest = new ClearXchangePaymentAccount();
        rest.emailOrMobileNr = business.getEmailOrMobileNr();
        rest.holderName = business.getHolderName();
        toRestModel(rest, business);
        return rest;
    }

}
