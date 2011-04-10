package DTO;

import java.io.Serializable;

/**
 * Defines candidate as a Data Transfer Object
 * @author defiler
 */
public class CandidateDTO implements Serializable {

    private String login;
    private String firstName;
    private String lastName;
    private String programme;

    /**
     * Returns candidate's first name.
     * @return {@code String} containing candidate's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *Sets candidate's first name.
     * @param firstName {@code String} with first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns candidate's last name.
     * @return {@code String} containing candidate's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets candidate's last name.
     * @param lastName {@code String} with last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns candidate's login.
     * @return {@code String} containing candidate's login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets candidate's login.
     * @param login {@code String} with login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns candidate's election programme.
     * @return {@code String} with programme.
     */
    public String getProgramme() {
        return programme;
    }

    /**
     * Sets candidate's election programme.
     * @param programme {@code String} with programme
     */
    public void setProgramme(String programme) {
        this.programme = programme;
    }
}
