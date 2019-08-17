package com.makeup.user.domain

import com.makeup.user.domain.dto.CreateUserDto
import com.makeup.user.domain.exceptions.InvalidUserException
import com.makeup.user.domain.exceptions.PasswordConstraintException
import com.makeup.utils.GlobalAuthorization
import com.makeup.utils.ParameterizedException
import lombok.AccessLevel
import lombok.experimental.FieldDefaults
import org.mindrot.jbcrypt.BCrypt
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.ConcurrentHashMap

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserFacadeSpec extends Specification{

    @Shared
    UserFacade userFacade

    @Shared
    ConcurrentHashMap<String, User> db = new ConcurrentHashMap<>()

    def setupSpec(){
        userFacade = new UserConfiguration().userFacade(db)
    }

    def cleanup() {
        db.clear()
        if (GlobalAuthorization.authorized)
            userFacade.logout()
    }

    def 'should create account'(){
        given:
        def createUserDto = CreateUserDto.builder()
                .login('janek')
                .email('janek@wp.pl')
                .password('Password123@').build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)

        then:
        db.size() == 1
    }

    @Unroll
    def 'should throw exception when username is invalid during create account. Username: #username'(String username){
        given:
        def createUserDto = CreateUserDto.builder()
                .login(username)
                .email('janek@wp.pl')
                .password('Password123@').build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)

        then:
        InvalidUserException exception = thrown()
        exception.getMessage() ==
                InvalidUserException.CAUSE.LOGIN_BLANK.getMessage() ||
                InvalidUserException.CAUSE.LOGIN_MIN_LENGTH.getMessage() ||
                InvalidUserException.CAUSE.LOGIN_MAX_LENGTH.getMessage()

        where:
        username            | _
        ''                  | _
        'jane'              | _
        'janek2323123@KKK'  | _
    }

    @Unroll
    def 'should throw exception when password is invalid during create account. Password: #password'(String password) {
        given:
        def createUserDto = CreateUserDto.builder()
                .login('janek')
                .email('janek@wp.pl')
                .password(password).build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)

        then:
        PasswordConstraintException exception = thrown()
        exception.message == ParameterizedException.exception

        where:
        password            | _
        ''                  | _
        '            '      | _
        'Pass word123@'     | _
        'Passw'             | _
        'Password@'         | _
        'password123@'      | _
        'Password1233'      | _
        'Password12345!@#'  | _
    }

    def 'should change password user'(){

        given:
        def createUserDto = CreateUserDto.builder()
                .login('janek')
                .email('janek@wp.pl')
                .password('Password123@').build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)
        userFacade.login('janek', 'Password123@')
        userFacade.changePassword('Janek123@')

        then:
        BCrypt.checkpw('Janek123@', db.get('janek').password)
    }

    @Unroll
    def 'should throw exception when password is invalid during change password. Password: #password'(String password) {
        given:
        def createUserDto = CreateUserDto.builder()
                .login('janek')
                .email('janek@wp.pl')
                .password('Janek123@').build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)
        userFacade.login('janek','Janek123@')
        userFacade.changePassword(password)

        then:
        PasswordConstraintException exception = thrown()
        exception.message == ParameterizedException.exception

        where:
        password            | _
        ''                  | _
        '            '      | _
        'Pass word123@'     | _
        'Passw'             | _
        'Password@'         | _
        'password123@'      | _
        'Password1233'      | _
        'Password12345!@#'  | _
    }

    def 'should login when username or password are correct'(){
        given:
        def createUserDto = CreateUserDto.builder()
                .login('janek')
                .email('janek@wp.pl')
                .password('Password123@').build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)
        userFacade.login('janek', 'Password123@')

        then:
        GlobalAuthorization.isAuthorized()
        GlobalAuthorization.name == 'janek'
    }

    @Unroll
    def 'should not login when username or password are incorrect. #username | #password'(String username, String password){
        given:
        def createUserDto = CreateUserDto.builder()
                .login('janek')
                .email('janek@wp.pl')
                .password('Password123@').build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)
        userFacade.login(username, password)

        then:
        InvalidUserException exception = thrown()
        exception.getMessage() == InvalidUserException.CAUSE.CORRECT_LOGIN_OR_PASSWORD.getMessage()
        println GlobalAuthorization.name
        !GlobalAuthorization.isAuthorized()
        GlobalAuthorization.name == null


        where:
        username    | password
        'janek'     | 'Password'
        'janek22'   | 'Password123@'
        'janek22'   | 'Password'
        'janek'   | 'Password123'
    }

    def 'should logout'(){
        given:
        def createUserDto = CreateUserDto.builder()
                .login('janek')
                .email('janek@wp.pl')
                .password('Password123@').build()
        def role = 'USER'

        when:
        userFacade.create(createUserDto, role)
        userFacade.login('janek', 'Password123@')
        userFacade.logout()

        then:
        !GlobalAuthorization.isAuthorized()
        GlobalAuthorization.name == null
    }
}
