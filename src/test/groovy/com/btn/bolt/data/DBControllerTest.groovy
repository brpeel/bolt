package com.btn.bolt.data

import com.btn.bolt.data.transfer.Transfer
import com.btn.bolt.data.transfer.TransferDAO
import com.btn.bolt.data.user.User

import com.btn.bolt.data.user.UserDAO
import spock.lang.Specification

class DBControllerTest extends Specification {
    Controller controller
    UserDAO userDao = Mock(UserDAO)
    TransferDAO transferDao = Mock(TransferDAO)

    void setup() {
        controller = new DBController(userDao, transferDao)
    }

    def "Users can be queried by user id"() {
        given:
        userDao.getUser(1) >> new User(1l, "some@email.com", "Ben", "Jerry", 100l)

        when:
        def result = controller.getUser(1)

        then:
        result.id == 1
        result.email == "some@email.com"
        result.firstName == "Ben"
        result.lastName == "Jerry"
        result.points == 100
    }

    def "If a user does not exist then nothing is returned"() {
        given:
        userDao.getUser(1) >> new User(1l, "some@email.com", "Ben", "Jerry", 100l)

        when:
        def result = controller.getUser(2)

        then:
        result == null
        noExceptionThrown()
    }

    def "The new user id is returned when a user is created"() {
        given:
        User user = new User()

        user.email = "some@email.com"
        user.firstName = "Ben"
        user.lastName = "Jerry"
        user.points = 100

        int id = Math.random().longValue()

        userDao.insert(_) >> id

        when:
        def result = controller.createUser(user)

        then:
        result == id
    }

    def "Users can be deleted"() {

        when:
        controller.deleteUser(1)

        then:
        1 * userDao.delete(_)
    }


    def "Transfers can be queried by user id"() {
        given:
        transferDao.get(1) >> new Transfer(1,1,100)

        when:
        def result = controller.getTransfer(1)

        then:
        result.id == 1
        result.userId == 1
        result.points == 100
    }

    def "If a transfer does not exist then nothing is returned"() {
        given:
        transferDao.get(1) >> new Transfer(1,1,100)

        when:
        def result = controller.getUser(2)

        then:
        result == null
        noExceptionThrown()
    }
}
