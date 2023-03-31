package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberForm {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Length(min = 6, max=20, message = "아이디는 6자 이상, 20자 이하로 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    public MemberForm() {
    }

    public MemberForm(String id, String password, String name, String email, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
