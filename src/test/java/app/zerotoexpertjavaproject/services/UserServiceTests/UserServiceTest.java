package app.zerotoexpertjavaproject.services.UserServiceTests;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.mappers.UserDTO;
import app.zerotoexpertjavaproject.repositories.UserRepository;
import app.zerotoexpertjavaproject.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



class UserServiceTest {

    private final UserService userService;

    private UserServiceTest(){
        UserRepository userRepository = Mockito.mock(UserRepository.class);
       this.userService = new UserService(userRepository);
    }

    @Test
    void shouldReturnTrueWhenAllFieldsInAuthRequestBodyIsCorrect() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("username123","password123","email@wp.pl");
        //then
        Assertions.assertTrue(userService.validateAuthRequestBodyData(authRequestBody));
    }

    @Test
    void shouldReturnFalseWhenEmailFieldInAuthRequestBodyIsIncorrect() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("username123","password123","emailwp.pl");
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }

    @Test
    void shouldReturnFalseWhenUsernameFieldIsBlankInAuthRequestBody() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("","password123","emailwp.pl");
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }

    @Test
    void shouldReturnFalseWhenEmailFieldIsBlankInAuthRequestBody() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("username123","password123","");
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }


    @Test
    void shouldReturnFalseWhenPasswordFieldIsBlankInAuthRequestBody() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("username123","","email@wp.pl");
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }

    @Test
    void shouldReturnFalseWhenPasswordFieldIsTooLongInAuthRequestBody() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("username123"
                ,"toLongPasswordMoreThan30Letters","email@wp.pl");
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }

    @Test
    void shouldReturnFalseWhenUsernameFieldIsTooLongInAuthRequestBody() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("toLongPasswordMoreThan30Letters"
                ,"password123","email@wp.pl");
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }

    @Test
    void shouldReturnFalseWhenEmailFieldIsTooLongInAuthRequestBody() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("username123"
                ,"password123","toLongPasswordMoreThan30Letters@wp.pl");
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }

    @Test
    void shouldReturnFalseWhenEmailFieldIsNullInAuthRequestBody() {
        //given
        AuthRequestBody authRequestBody = new AuthRequestBody("username123"
                ,"password123",null);
        //then
        Assertions.assertFalse(userService.validateAuthRequestBodyData(authRequestBody));
    }

    //reszta to do testow integracyjnych, mozna tez skorzystac z techniki InMemoryRepository
    //i implementowac zachowanie bazy danych na mapie ale mozna tez potestowac to na postmanie

}