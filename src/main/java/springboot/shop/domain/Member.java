package springboot.shop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
@EqualsAndHashCode
public class Member {
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String address;
    private Role role;

    public Member() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, password, name, email, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(email, member.email) && Objects.equals(password, member.password) && Objects.equals(name, member.name) && Objects.equals(address, member.address) && role == member.role;
    }
}
