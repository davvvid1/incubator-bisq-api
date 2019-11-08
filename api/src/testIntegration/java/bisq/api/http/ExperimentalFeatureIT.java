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

package bisq.api.http;

import bisq.api.http.model.ChangePassword;
import bisq.api.http.model.payment.SepaPaymentAccount;

import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.arquillian.cube.docker.impl.client.containerobject.dsl.Container;
import org.arquillian.cube.docker.impl.client.containerobject.dsl.DockerContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;

@RunWith(Arquillian.class)
public class ExperimentalFeatureIT {

    @DockerContainer
    Container alice = ContainerFactory.createApiContainer("alice", "8080->8080", 3333, false, false, false);

    @InSequence
    @Test
    public void waitForAllServicesToBeReady() throws InterruptedException {
        ApiTestHelper.waitForAllServicesToBeReady();
    }

    @InSequence(1)
    @Test
    public void createPaymentAccount_always_returns501() {
        SepaPaymentAccount accountToCreate = ApiTestHelper.randomValidCreateSepaAccountPayload();
        Response response = given().
                port(getAlicePort()).
                contentType(ContentType.JSON).
                body(accountToCreate).
                when().
                post("/api/v1/payment-accounts");
        expect501(response);
    }

    @InSequence(1)
    @Test
    public void removePaymentAccountById_always_returns501() {
        expect501(given().port(getAlicePort()).when().delete("/api/v1/payment-accounts/xyz"));
    }

    @InSequence(1)
    @Test
    public void searchPaymentAccounts_always_returns200() {
        given().port(getAlicePort()).when().get("/api/v1/payment-accounts").then().statusCode(200);
    }

    @InSequence(1)
    @Test
    public void getVersionDetails_always_returns200() {
        given().port(getAlicePort()).when().get("/api/v1/payment-accounts").then().statusCode(200);
    }

    @InSequence(1)
    @Test
    public void changePassword_always_returns200() {
//
        given().
                port(getAlicePort()).
                body(new ChangePassword("abc", null)).
                contentType(ContentType.JSON).
//
        when().
                post("/api/v1/user/password").
//
        then().
                statusCode(204);
    }

    private void expect501(Response response) {
        response.then().
                statusCode(501).
                body("errors[0]", equalTo("Experimental features disabled")).
                body("errors.size()", equalTo(1));
    }

    private int getAlicePort() {
        return alice.getBindPort(8080);
    }
}
