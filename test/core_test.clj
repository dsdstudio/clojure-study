(ns core-test
  (:require [clojure.test :refer :all])
)

(println "Melong World!")

(testing "숫자 처리 테스트"
  (is (= 3 (+ 1 2)) "1+2는 3이라능"))

;; str 처리

; "clojure에서 interleave는 두 메시지를 조합한다"
(interleave "karok" "ligand")
;> (\k \l \a \i \r \g \o \a \k \n) 

; str 인자로 문자를 넘기지않는경우라서 지연시퀀스를 리턴한다.
(str (interleave "melong" "haha"))
;> "clojure.lang.LazySeq@fe8fa746"

; 이런경우에는 apply 함수를 이용해서 지연 시퀀스를 문자열형태로 만들수 있다
(apply str (interleave "melong" "haha"))
;> "mhealhoa"

;; boolean 과 nil 

; clojure에서의 boolean 규칙 
; true는 참, false 는 거짓 
; 참과 거짓을 판단하는상황에서는 nil과 false 이외의 모든것은 true로 평가됨

; 빈 리스트는 참으로 평가됨 
(if () "succ" "fail") 
;> "succ"

; 심지어 0도 참으로 평가됨  -_- 헤깔리기 쉽다능 
(if 0 "succ" "fail") 
;> "succ"

; clojure 에서 predicate 은 참/ 거짓을 판변하는 함수인데 보통 predicate 뒤에 ?을 붙인형태의 이름으로 가지고 있다. 
; ex) true? false? zero? 
; 사용법) (zero? expr)

; 다른언어에서처럼 애매하게 형변환같은거는 아예 안일어나고 순수하게 expr 값 자체가 true일때만 true를 반환하도록 설계되어있다능 
(true? true)
;> true
(true? ())
;> false

