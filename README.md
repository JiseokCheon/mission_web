# mission_web

### 누구나 여러 사용자들이 참여할 있는 1일 미션을 등록해 미션 진행을 관리하는 웹 사이트

##### 예) 1일 1알고리즘 풀이


+ spring boot 
+ JPA
+ spring security
+ OAuth2 

<br>
<br>

## cascade 오류
부모가 둘이 Entity (fk가 둘)인 상태에서 CascadeType.ALL로 설정해서 Referential integrity constraint violation 발생
-> cascade 사용하지 않고 수동으로 삭제, 추가 해줌
