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
