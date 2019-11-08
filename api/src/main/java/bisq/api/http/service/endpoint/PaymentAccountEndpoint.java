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

package bisq.api.http.service.endpoint;

import bisq.api.http.model.PayloadValidator;
import bisq.api.http.model.PaymentAccountList;
import bisq.api.http.model.payment.PaymentAccount;
import bisq.api.http.model.payment.PaymentAccountHelper;
import bisq.api.http.service.ExperimentalFeature;

import bisq.core.payment.PaymentAccountManager;
import bisq.core.user.User;

import bisq.common.UserThread;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "payment-accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentAccountEndpoint {

    private final ExperimentalFeature experimentalFeature;
    private final PayloadValidator payloadValidator;
    private final PaymentAccountManager paymentAccountManager;
    private final User user;

    @Inject
    public PaymentAccountEndpoint(ExperimentalFeature experimentalFeature,
                                  PayloadValidator payloadValidator,
                                  PaymentAccountManager paymentAccountManager,
                                  User user) {
        this.experimentalFeature = experimentalFeature;
        this.payloadValidator = payloadValidator;
        this.paymentAccountManager = paymentAccountManager;
        this.user = user;
    }

    @Operation(summary = "Remove payment account", description = ExperimentalFeature.NOTE)
    @DELETE
    @Path("/{id}")
    public void removeById(@Suspended AsyncResponse asyncResponse, @PathParam("id") String id) {
        UserThread.execute(() -> {
            try {
                experimentalFeature.assertEnabled();
                paymentAccountManager.removePaymentAccount(id);
                asyncResponse.resume(Response.status(Response.Status.NO_CONTENT).build());
            } catch (Throwable e) {
                asyncResponse.resume(e);
            }
        });
    }

    @Operation(summary = "Create payment account", description = ExperimentalFeature.NOTE + "\nInspect models section at the bottom of the page for valid PaymentAccount sub-types schemas", responses = @ApiResponse(content = @Content(schema = @Schema(implementation = PaymentAccount.class))))
    @POST
    public void create(@Suspended AsyncResponse asyncResponse, PaymentAccount account) {
        UserThread.execute(() -> {
            try {
                experimentalFeature.assertEnabled();
                payloadValidator.validateRequiredRequestPayload(account);
                bisq.core.payment.PaymentAccount paymentAccount = PaymentAccountHelper.toBusinessModel(account);
                PaymentAccount result = PaymentAccountHelper.toRestModel(paymentAccountManager.addPaymentAccount(paymentAccount));
                asyncResponse.resume(result);
            } catch (Throwable e) {
                asyncResponse.resume(e);
            }
        });
    }

    @Operation(summary = "Get existing payment accounts", responses = @ApiResponse(content = @Content(schema = @Schema(implementation = PaymentAccountList.class))))
    @GET
    public void find(@Suspended AsyncResponse asyncResponse) {
        UserThread.execute(() -> {
            try {
                asyncResponse.resume(getAccountList());
            } catch (Throwable e) {
                asyncResponse.resume(e);
            }
        });
    }

    private PaymentAccountList getAccountList() {
        PaymentAccountList paymentAccountList = new PaymentAccountList();
        paymentAccountList.paymentAccounts = getPaymentAccountList().stream()
                .map(PaymentAccountHelper::toRestModel)
                .collect(Collectors.toList());
        return paymentAccountList;
    }

    private List<bisq.core.payment.PaymentAccount> getPaymentAccountList() {
        Set<bisq.core.payment.PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        return null == paymentAccounts ? Collections.emptyList() : new ArrayList<>(paymentAccounts);
    }
}
