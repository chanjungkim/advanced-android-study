## RXjava란?
ReactiveX 를 Java언어로 포팅한 라이브러리.

## 그럼 ReactiveX란?
Observable한 결과들을 이용하여 비동기나 이벤트 based 프로그램작성을 도와주는 라이브러리.   
옵저버 패턴을 확장하여 만들어졌다.

## 왜 Observables을 사용하는가?
비동기 이벤트들의 스트림을 심플하게 만들기 위해. 콜백으로부터 자유로워지고, 읽기 쉽고 버그로부터 덜 위험하다.

### RXjava의 구성
크게 Observable, Observer로 구성됨
1. Observable
    - 어떠한 데이터를 관찰할 수 있는 형태로 바꿔주는 클래스
2. Observer
    - Observable의 관찰가능한 데이터를 전달받고 전달받은 결과에따른 행동을 수행하는 클래스
  
   
![Obs](https://t1.daumcdn.net/cfile/tistory/99D4D7415D4E552B20)     
Observable은 onNext, onComplete, onError 등의 함수로 Observer에 전달하고 Observer는 이러한 함수를 구현해서 결과에대한 반응을 한다.

#### 예제 코드
```java
  Observable<String> observable = Observable
  .just("selfish", "developer")//단일 객체 발행
  .subscribeOn(Schedulers.io()) //Observable 객체가 실행될 스레드
  .observeOn(AndroidSchedulers.mainThread()); //Observer 구현체가 실행될 스레드

  observable.subscribe(new Observer<String>() {
    @Override
    public void onSubscribe(Disposable d) {
 
    }
 
    @Override
    public void onNext(String s) {
        Log.d(TAG, "onNext: " + s);
 
    }
 
    @Override
    public void onError(Throwable e) {
 
    }
 
    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }
});
출처: https://selfish-developer.com/entry/RxJava-Observable-Observer?category=851285 [아는 개발자]
```

### 연산자         
[연산자](http://reactivex.io/documentation/ko/operators.html)
    
    
### Single
Observable의 한 형태이지만 항상 한가지 값 또는 Error 둘중 하나만 emit 한다.
이러한 이유때문에 원래 Observable을 구독할때 사용하던 세개의 메서드 (onNext, onError, onCompleted) 대신에   
onSuccess - Single은 자신이 emit하는 하나의 값을 이 메서드를 통해 전달한다
onError - Single은 emit 오류시에 이 메서드를 통해 Throwable 객체를 전달한다.
  
Single은 이 메서드중 하나만 그리고 한번만 호출하며, 메서드가 호출되면 Single의 생명주기가 끝나고 구독도 종료된다.

### Subject
Subject는 Observable과 Observer의 두가지 속성을 모두 가진다. 즉 Observable을 구독하고 동시에 Observable이기도 하기때문에 결과들을 emit할수 있다.  
또한 Cold Observable을 Hot Observable로변경한다.
>Cold는 구독하기 전까지 데이터를 방출하지 않는 Lazy한 접근법이고
Hot은 구독자의 존재 여부 관계없이 데이터블 발행하는 Observable이다.  

### Scheduler
스케줄러는 구독과 발행이 동작하는 스레드를 지정할 수 있게해준다.
- subscribeOn Observable에서 데이터 흐름이 발행되는 스레드
- observerOn 처리된 결과를 구독자에게 발행하는 스레드
- subcribeOn만 호출하면 Observable의 모든 흐름이 설정한 스레드에서 실행됨
- 스케줄러를 지정하지않으면 main 스레드에서 동작

