package springboot.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.shop.domain.*;
import springboot.shop.repository.MemberRepository;

import java.util.List;

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
        return memberRepository.findById(member.getMemberId()) != null;
    }

    public Member creatMember(MemberForm memberForm){
        Member member = new Member();
        member.setEmail(memberForm.getEmail());
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        member.setName(memberForm.getName());
        member.setAddress(memberForm.getAddress());
        member.setRole(Role.USER);
        return member;
    }

    public List<Member> findAll(SearchCond cond){
        int pageSize = 10;
        int naviSize = 10;
        int count = memberRepository.count();

        cond.setPageSize(pageSize);
        PageHandlerVO ph = new PageHandlerVO(count, naviSize, cond);
        return memberRepository.findAll(ph);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username);

        if(member==null){
            throw new UsernameNotFoundException(username);
        }

        return new MemberAdaptor(member);
    }
}
