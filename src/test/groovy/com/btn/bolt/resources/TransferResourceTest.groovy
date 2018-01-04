package com.btn.bolt.resources

import com.btn.bolt.data.Controller
import com.btn.bolt.data.transfer.Transfer
import com.btn.bolt.data.user.User
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest
import javax.ws.rs.core.Response

class TransferResourceTest extends Specification {

    TransferResource resource
    Controller controller = Mock(Controller)

    void setup() {
        resource = new TransferResource(controller)
    }

    def "Transfers can be queried by id"() {
        given:
        long id = 1

        when:
        resource.get(id)

        then:
        1 * controller.getTransfer(id)
    }

    def "Posting to the transfer resource creates a transfer when the user exists"() {
        given:
        controller.getUser(_) >> new User()
        Transfer transfer = new Transfer(1, 1, 100)

        when:
        resource.post(Mock(HttpServletRequest), transfer)

        then:
        1 * controller.createTransfer(transfer)
    }


    def "Posting to the transfer resource results in a BAD Request code if the user id does not exist"() {
        given:
        controller.getUser(_) >> null
        Transfer transfer = new Transfer(1, 1, 100)

        when:
        Response response = resource.post(Mock(HttpServletRequest), transfer)

        then:
        0 * controller.createTransfer(transfer)
        response.getStatus() == Response.Status.BAD_REQUEST.statusCode
    }
}
