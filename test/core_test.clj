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


;; map, keyword, 구조체 

;; 네가지방식 모두 같은 표현이다. 클로저에서는 쉼표 역시 공백으로 처리한다.
(def inventors {"Lisp" "borong" "clojure" "mickey"})
(def inventors {"Lisp" "borong", "clojure" "mickey"})
(def inventors {"Lisp","borong", "clojure","mickey"})

;; 단지 맵이기때문에 인자가 짝수인지는 확인한다. 홀수인자를 주게되면 예외가 발생한다 
; (def inventors {"Lisp" "borong" "clojure"})

; 맵의 값을 얻기위해 get함수를 사용할수 있다. 
; (doc get)
(get inventors "Lisp" "엄훠 값이 없엉")
;> "borong"

; 키워드는 콜론으로 시작한다, 또한, 평가하는경우 자기자신을 반환. 
:foo
;> :foo

; 키워드를 통해서도 맵을 만들수있다. 당연한 얘기겠지만  get할때 키워드를 키로 사용해서 값을 가져올수있다. 
(def inventors {:Lisp "borong"})
(inventors :Lisp)
;> "borong"
(inventors "Lisp")
;> nil

; 키워드는 구조체의 속성이 될수있다. 구조체를 만들때는 defstruct를 이용하여 선언할수있다능 
(defstruct people :firstName :lastName)

; 구조체를 선언한뒤 struct 함수를 이용해서 구조체의 인스턴스를 만들수 있다 
(def borong (struct people "borong" "kim"))

(:firstName borong)
;> "borong"

; struct-map 함수를 통해서 구조체를 초기화 하면 원래속성을 대신할 값을 설정하거나, 원래 속성에 없는 값을 추가해서 인스턴스를 만들수 있다 

(struct-map people :age 30)
;> {:firstName nil, :lastName nil, :age 30)


;; 함수 
; 함수 네이밍컨벤션은 보통 - 로 단어구분을 한다 getName -> get-name 뭐 이런식 -_-? 
; predicate 함수인경우는 ?를 뒤에 붙인다 -> true? nil? 

; 함수정의에는 defn 을 이용
(defn hello [] 3)

(hello)
;> 3

;[] 브라켓은 인자를 정의하는 부분인데, 케이스에따라 다른동작을 하도록 만들수도 있다. 
(defn hello 
 "이거슨 헬로 함수"
 ([] "인자가 없다옹")
 ([s] s))

(hello)
;> "이거슨 헬로 함수"

(hello "a")
;> "a"

; varargs 는 인자 지정할때 &로 정의할수 있다. 예를 들면 
; [& args] <-- 가변 인자. 
; [p1 p2 & persons] <-- 세번째인자부터는 persons에 바인딩된다. 

; var, 바인딩, 이름공간  

(def foo 10)
; > #'user/foo

foo
; 10

(var foo)
; > #'user/foo

; 리더 매크로로 네임스페이스 접근 
#'foo 
; > #'user/foo

; 함수 인자 바인딩 
(defn triple [number] (* 3 number))
; > #'user/triple 

; number 인자에 10이 바인딩되고 함수가 평가된 결과가 리턴된다. 
(triple 10)
; > 30 

; 디스트럭쳐링 
; 인자로 주는 컬렉션의 일부만 사용할때에도 전체 컬렉션을 변수에다가 바인딩해야되는데.. 
; 예를 들면 function(obj) { dosomething(obj.x); } 
; 인자의 키를 지정하거나 컬렉션인경우 특정 인자만 바인딩하는것이 가능하다. 
(defn hello-author [{name :name}] 
	(println "Hello, " name)) 
(hello-author {:name "Melong" :age 33})
; > Hello, Melong

; let 함수를 이용해 컬렉션중 필요한부분만 바인딩하여 새로운 컬렉션을 만들어냈다. 
(let [[x y] [1 2 3]] 
 	[x y])
; > [1 2]

; 앞 2개인자는 무시하고 3번째것만 바인딩하는것도 가능 
(let [[_ _ z] [1 2 3]]
 z)
; > 3
