package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdapter;
import springboot.shop.domain.MemberForm;
import springboot.shop.domain.Role;
import springboot.shop.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member save(Member member){
        if(isDuplicateMember(member)){
            throw new IllegalStateException("duplicate member id");
        }
        return memberRepository.save(member);
    }

    private boolean isDuplicateMember(Member member){
        return memberRepository.findById(member.getId()) != null;
    }

    public Member creatMember(MemberForm memberForm){
        Member member = new Member();
        member.setId(memberForm.getId());
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        member.setName(memberForm.getName());
        member.setEmail(memberForm.getEmail());
        member.setAddress(memberForm.getAddress());
        member.setRole(Role.USER);
        return member;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username);

        if(member==null){
            throw new UsernameNotFoundException(username);
        }

        return new MemberAdapter(member);
    }
}
