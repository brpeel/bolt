package com.btn.bolt.data.user

import spock.lang.Specification

class UserControllerTest extends Specification {

    UserController controller
    UserDAO dao = Mock(UserDAO)

    void setup() {
        controller = new UserController(dao)
    }

    def "Get returns user when it exists"() {
        given:
        dao.getUser(1) >> new User(1l, "some@email.com", "Ben", "Jerry", 100l)

        when:
        def result = controller.get(1)

        then:
        result.id == 1
        result.email == "some@email.com"
        result.firstName == "Ben"
        result.lastName == "Jerry"
        result.points == 100
    }

    def "Get returns null when id does not match a user"() {
        given:
        dao.getUser(1) >> new User(1l, "some@email.com", "Ben", "Jerry", 100l)

        when:
        def result = controller.get(2)

        then:
        result == null
        noExceptionThrown()
    }

    def "Create returns id when a new user is created"() {
        given:
        User user = new User()

        user.email = "some@email.com"
        user.firstName = "Ben"
        user.lastName = "Jerry"
        user.points = 100

        int id = Math.random().longValue()

        dao.insert(_) >> id

        when:
        def result = controller.create(user)

        then:
        result == id
    }

    def "Delete removes the user"() {

        when:
        controller.delete(1)

        then:
        1 * dao.delete(_)
    }
}
