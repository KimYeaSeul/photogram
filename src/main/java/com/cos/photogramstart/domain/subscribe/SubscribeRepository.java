package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{

    // native query
    @Modifying
    @Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId);
    // return 을 int 로 설정시 성공하면 1(건), 실패하면 -1 반환
    // => 변경된 행의 개수가 리턴됨. 0= 오류는 아니지만  insert가 안됐다.
    // 하지만 void로 하고 나중에 try-catch 하겠십니다.
    @Modifying // db에 변경을 주는 네이티브쿼리 사용시 필요.
    @Query(value="DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId=:toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);
}
