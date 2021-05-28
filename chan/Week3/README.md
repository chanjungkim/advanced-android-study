# advanced-android-study

### 3주차(Repository, Room + a)

### MVC, MVP, MVVM

- M: Model
- V: View

#### MVC: Model, View, Controller

- 초기에 웹 개발(Spring 등)에서 사용한 패턴이 앱 개발 쪽으로 넘어옴. 
- 의존: Model과 View에 의존한다.
- Activity, Fragment는 View인 동시에 Controller의 역할을 하기도 한다.
- 장점: 구조가 단순하여 직관적, 쉽게 적용. 규모가 작은 프로젝트인 경우 좋고, 모든 코드가 몰려있어 코드를 파악하기에도 좋다.
- 단점: 모든 코드가 Activity, Fragment 쪽에 몰리는 경향이 있어 코드가 커져 수천줄이 넘기도한다. 그에 따라 스파게티 코드가 되기쉽고, 시간이 지나면 유지보수가 힘들다.

#### MVP: Model, View, Presenter

- MVC 패턴에서는 UI 로직과 비지니스로직이 Activity, Fragment에 몰려있다. 이를 분리하기 위해 나온 게 MVP.
- MVC와 비슷하다.(Model과 View의 역할 동일)
- Controller 대신 Presenter 사용
- Presenter는 View와 Model에 인스턴스를 갖고, View와 1:1 관계를 갖는다.
- 장점: View와 Model 간의 의존성이 없다. UI와 비지니스 로직이 분리되어 Unit Test가 수월해진다.
- 단점: View와 Presenter가 1:1 관계를 유지해야해서 Presenter를 재사용할 수 없다. View가 늘어날 때마다 Presenter가 늘어남.

#### MVVM: Model, View, ViewModel

- MVP에서 View와 Presenter가 1:1로 묶여있는 문제가 있었고, MVVM은 이것을 끊는데 집중한다.
- 우리가 ViewModel에서 사용하는 class 이름과 같고, MVVM에서 사용이 되지만, 여기서 표현하는 ViewModel하고는 다르다.
- Presenter 대신 ViewModel을 사용한다.
- 핵심: View는 ViewModel에 데이터 구독을 요청하고, ViewModel은 View에 표현할 데이터를 Observable하게 전달하며, ViewModel과 View는 1:N의 관계에 있다.(재사용 가능)
- ViewModel이 View에 의존성을 갖지 않도록 Data Binding 라이브러리가 필수적으로 사용된다.

##### 권장하는 애플리케이션 설계

- Activity 또는 Fragment는 단지 ViewModel만을 참조한다.
- Activity나 Fragment는 ViewModel의 하위계층이 어떻게 되는 모른다.
- ViewModel은 Repository를 참조하고 화면을 구성하는데 필요한 데이터를 가져온다.
- Repository는 두 가지 타입의 모델(네트워크, 로컬 저장소)를 참조한다.
- [SOLID 원칙](https://hckcksrl.medium.com/solid-%EC%9B%90%EC%B9%99-182f04d0d2b#:~:text=%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%EC%84%A4%EA%B3%845%EB%8C%80,%EB%94%B0%EC%84%9C%20SOILD%20%EC%9B%90%EC%B9%99%EC%9D%B4%EB%9D%BC%EA%B3%A0%20%EB%B6%80%EB%A5%B8%EB%8B%A4.)

### Repository, Room + a

#### Room

- Database: 데이터를 보관하는 집합
- Entity: Database를 구성하는 Model로 DAO에서 사용이 되며, 하나의 Database에 여러 개를 가질 수 있다.
- DAO(Data Access Object): SQL Query를 작성하여 요청 및 응답을 정의하는 inferface

#### Repository

![MVVM](img_mvvm.png)

- Database, Network 등에 사용된다.
- 깔끔한 API 호출을 할 수 있다.(ViewModel에서 단순히 가져오기만 한다.)
- 다른 부분에서 내부적으로 어떻게 처리되는 지 모르게 한다(위와 동일)
- Network에서 가져와야하는지, Database에서 가져와야하는지 정해서 가져온다.(ex. 전파 수신 약전계(오프라인 혹은 느린 네트워크 상황)에서도 원활히 동작할 수 있게한다.)
- 테스트 가능하게 한다.(ref.[MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver))
- Repository class를 만들어서 구현한다.
- DTO(Data Trarnsfer Object): 필요없는 데이터는 제외시키고, 사용하기 편한 구조로 변환시킨다.

#### View

- VO(View Object): View에서 사용하는 묶음 또는 형태를 가진 모델
