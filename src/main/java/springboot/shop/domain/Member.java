package springboot.shop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
@EqualsAndHashCode
public class Member {
    private String id;
    private String password;
    private String name;
    private String email;
    private String address;
    private Role role;

    public Member() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(name, member.name) && Objects.equals(email, member.email) && Objects.equals(address, member.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, name, email, address);
    }
}
