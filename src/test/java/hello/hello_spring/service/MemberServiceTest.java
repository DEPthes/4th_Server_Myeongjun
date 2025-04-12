package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MemberServiceTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    MemberService service = new MemberService(repository);

    @AfterEach
    void clearStore() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("jpa");

        //when
        service.join(member1);
        service.join(member2);

        //then
        assertThat(service.findOne(member1.getId()).get()).isEqualTo(member1);
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        Member member3 = new Member();
        member3.setName("jpa");

        //when
        service.join(member1);
        //service.join(member3);

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> service.join(member2));

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}