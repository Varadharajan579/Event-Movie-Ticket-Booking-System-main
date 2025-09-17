package pbl.project.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    private int user_id;
    @NonNull
    private String user_name;
    @NonNull
    private String password;
    @NonNull
    private String user_email;
    @NonNull
    private Long user_contact;
    @NonNull
    private String user_role;

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getUser_email() {
        return user_email;
    }

    public Long getUser_contact() {
        return user_contact;
    }

    public String getUser_role() {
        return user_role;
    }

}
