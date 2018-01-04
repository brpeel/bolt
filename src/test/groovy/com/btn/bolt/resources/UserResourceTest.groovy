package com.btn.bolt.resources

import com.btn.bolt.data.Controller
import com.btn.bolt.data.user.User
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

class UserResourceTest extends Specification {

    UserResource resource
    Controller controller = Mock(Controller)

    void setup() {
        resource = new UserResource(controller)
    }

    def "Get calls getUser on the UserController"() {

        given:
        long id = 1

        when:
        resource.get(id)

        then:
        1 * controller.getUser(id)
    }

    def "Post calls create on the UserController"() {
        given:
        User user = new User()

        when:
        resource.post(Mock(HttpServletRequest), user)

        then:
        1 * controller.createUser(user)
    }

    def "Delete calls delete on the UserController"() {

        given:
        long id = 1

        when:
        resource.delete(id)

        then:
        1 * controller.deleteUser(id)
    }
}
