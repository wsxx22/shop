package com.makeup.role.domain;

import com.makeup.role.domain.dto.CreateRoleDto;
import com.makeup.role.domain.exception.InvalidRoleException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleFacadeTest {

    RoleFacade roleFacade;
    Set<Role> db;

    {
        db = new HashSet<>();
        roleFacade = new RoleConfiguration().roleFacade(db);
    }

    @AfterEach
    void clearDatabaseAfterTest(){
        db.clear();
    }

    @Test
    @DisplayName("Should add role to database")
    public void shouldAddRole(){

        CreateRoleDto adminRole = new CreateRoleDto(1L, "ADMIN");
        CreateRoleDto userRole = new CreateRoleDto(2L, "USER");

        roleFacade.addRole(adminRole);
        roleFacade.addRole(userRole);

        assertThat(db, hasSize(2));
        assertThat(db, hasItem(Role.builder().id(2L).role("USER").build()));
    }

    @Test
    @DisplayName("Should throw exception when role exists during adding to database")
    public void shouldThrowExceptionWhenRoleExists() {

        CreateRoleDto adminRole = new CreateRoleDto(1L, "ADMIN");
        CreateRoleDto adminRole2 = new CreateRoleDto(2L, "ADMIN");

        roleFacade.addRole(adminRole);

        assertThrows(InvalidRoleException.class,
                () -> { roleFacade.addRole(adminRole2); });
    }

    @Test
    @DisplayName("Should find role by name from database")
    public void shouldFindRole(){

        CreateRoleDto adminRole = new CreateRoleDto(1L, "ADMIN");
        roleFacade.addRole(adminRole);

        CreateRoleDto createRoleDto = roleFacade.findRole("ADMIN");

        assertThat(createRoleDto, notNullValue());
        assertThat(createRoleDto.getId(), is(1L));
        assertThat(createRoleDto.getRole(), equalTo("ADMIN"));
    }

    @Test
    @DisplayName("Should throw exception when role not exists")
    public void shouldThrowExceptionWhenRoleNotExists(){

        CreateRoleDto adminRole = new CreateRoleDto(1L, "ADMIN");
        roleFacade.addRole(adminRole);

        assertThrows(InvalidRoleException.class,
                () -> { roleFacade.findRole("USER"); });
    }

    @Test
    @DisplayName("Should find all roles from database")
    public void shouldFindAllRoles(){

        CreateRoleDto adminRole = new CreateRoleDto(1L, "ADMIN");
        CreateRoleDto userRole = new CreateRoleDto(2L, "USER");
        roleFacade.addRole(adminRole);
        roleFacade.addRole(userRole);

        Set<CreateRoleDto> roles = roleFacade.findRoles();

        assertThat(roles, hasSize(2));
        assertThat(roles, hasItem(CreateRoleDto.builder().id(2L).role("USER").build()));
    }

    @Test
    @DisplayName("Should return empty set when roles not exists in database")
    public void shouldReturnEmptyRolesSet(){

        Set<CreateRoleDto> roles = roleFacade.findRoles();

        assertThat(roles, hasSize(0));
        assertThat(roles, empty());
    }

}
