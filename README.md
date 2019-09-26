
Making login session  
====================

## *First Review* ##
2019.09.20
1. General
   + 이론적으로 찾아보는 것도 좋지만 직접 일단 다 때려넣어보면서 확인해라 
   + static 잘 모르겠으면 안 쓰는게 상책이다. 
   + 그런데 static 모르면 되겠나. static은 정적변수이다. 이게 전역변수와는 무엇이 다를까? 
   + ~~가비지 컬렉션에 대한 가벼운 공부~~
   + ### 싱글톤 디자인 패턴 synchronized에 대하여 ###
   + alt+enter는 warning 없앤다는 생각보다는 **Why?** 인간들이 여기서 이걸 왜 쓸까 항상 이유를 찾아라. 그게 공부다.
   + alt+enter해서 나오는 해결방법이 여러개다. 다 살펴보고 뭐가 더 좋은지 이해를 꼼꼼이 따져라. **코드 분석** 
   + 근데 사실 alt+enter 해결 나올때 잘 모르겠다면 if로 짜진 선택지가 제일 좋음
   + Log를 최대한 많이 남겨보는 연습을 하라. Runtask로 쳐다보면서 검출하기 어렵다. 
   + Debug도 사용을 해보도록 노력해보자...
   + ~~method용, class 용 주석이 따로 있다.~~
   + ~~method 주석은 /** + enter 이다. 이걸 쓰면 파라메터나 리턴 형식 틀을 자동으로 만들어줘서 편하다.~~ 
   + [클래스 주석 참고링크](https://thinkerodeng.tistory.com/227)
   + ~~주석 // 남발 그만해라. 주석은 혼자 보는게 아니다. 누가 봐도 이해를 할 수 있도록 제대로 된 형식의 주석을 쓰려고 노력해라.~~
   + 접근제한자 너무 어려운데 그래도 제대로 이해를 하려고 노력해야한다. 
   + ~~spring에서는 annotation을 진짜 많이 쓴다. 뭔지 모르니까 한 번 들여다 보자.~~
2. class RetroResponse 
   + ~~선언한 이름이 서버의 파라메터 이름과 동일하면 @SerializedName annotation을 사용하지 않아도 된다.~~
   + ~~대부분은 다르게 사용하고, 자바에서 변수 선언할 때 이름 형태는 user_type 보다는 userType 이런 형태를 사용한다.~~ 
   + ~~api에서 정보를 받아와서 어차피 값이 채워져있는 RetroResponse의 입장에서 값을 정해주는 setter를 쓸 일은 거의 없다(코드줄 줄이기)~~
   + ~~Model 에서 사람들이 만드는 toString()은 디버그용이 맞기는 하다. 어차피 모든 자바 class가 최상위 class인 object를 상속하고, 거기에 toString()이 이미 사용하는 것~~. 
   + ~~그런데 toString()은 좀 구질구질하고, 차라리 debug 함수를 하나 만들어라. 이때 그냥 log 출력하면 못 알아보니까 String써주는데 이때도 toString()이 아닌 String.format을 이용하자.~~ 
   + ~~서버 api가 주는 값들을 다 선언해두지 않았다. 서버에서 주는 정보는 다 쓸모가 있다. 이런 부분에서 코드 줄인다고 하지 말고 다 선언해둬라.~~
3. class MainActivity
   + ~~### 여기다 로그인에 관련된 동작 코드를 짰어야 했다. 앱은 Login이 처음이다. 그래서 Login하는 동작을 하는 Activity가 main 엑티비티가 되는 것이다. ###~~
   + ~~정확히 말하면 로그인을 하고, api를 호출해서 정보를 받았어야했다. 그리고 뒤 이은 Activity에서는 그 자료를 뿌려주기만 해야했다.~~ 
   + ~~그래서 여기 이름도 LoginActivity로 바꿔야한다.~~ 
   + ~~###지금은 로그인을 id:2, pw: 1로 막아뒀는데 이럼 안된다. 일부러 오류가 발생하도록 코드를 짜야했고, 틀린 정보를 보내서 서버가 보내는 대답을 처리하는 부분을 짜야했다. ###~~
   + retrofit에서 enqueue()를 사용해서 비동기 처리를 했는데 사실 로그인은 모두 동기처리를 해야한다. 로그인이 틀리면 다른 뒤에 다 안되게 해야하니까.
   + ~~그런데 추세는 enqueue()쓰면서 앞에 진행될 때 progressbar 하나 박아서 다른거 다 못하게 만들어서 비동기 메소드 쓰면서 동기처럼 보이게 하기다.~~ 
4. class LoginActivity 
   + ~~assert 사용을 멈춰주세요. 이건 진짜 쓰면 안된다. assert는 test하는 코드에서나 쓰는거고 만약 배포용에 나갔다 하면 그냥 죽는다고 생각해라.~~ 
   + ~~값 나오는 부분 때문이었는데 assert 말고 if를 사용해라. 클래식이 최고이고 안전한 것.~~ 
   + ~~isSuccessful() 이 함수는 정말 못 믿을 함수다. 얘가 어떤 경위로 오게 되는지 모르고, 심지어 400, 500이 와도 얘가 호출이 되는 경우가 있다.~~ 
   + ~~그리고 dnx api의 경우에는 get과 post만 사용한다. 이런 서버 정책상 isSuccessful은 사용못하고 더 세밀한 거름망이 필요하다.~~
   + ~~이때 사용하는게 api response의 result 코드이다. 얠 보면 진짜 성공인지 가라인지 판별나니까 (이걸로 로그인 되나 안되나 잡으라는건가?? 싶기도 함)~~
   + ~~onFailure은 하드웨어적인 오류로 참으로 대국적인 오류다.~~ 
5. class RetrofitClientInstance 
   + 왜 꼭 getClient를 사용해서 객체를 만들었는가? 그냥 클래스 객체 만들어서 넘겨도 되는데. (진짜 RetofitClientInstance라는 객체를 만들어서 쓸 수 있다. 왜 이거 안썼는지 생각해라)
   + ~~profiler 쓰지 말고 okHttpClient 라이브러리에서 제공하는 logging inspection을 사용해라. Client 검사라서 retrofit client 만드는 class 안에 들어가 있는 것. 여기도 결국 로그를 찍는 것이다. 헤더까지 다 보여준다.~~ 
   + getClient의 static 지워보기. 객체를 들고다니는게 아니라 클래스 전체를 들고나니는 느낌으로 뭐 하라는데 뭐지...
6. ~~GetDataService~~ 
   + ~~메소드 이름 saveDate 너무 오해 유발하는 이름이고 더 확실하게 얘가 뭘 하는지 보여주는 이름을 써야한다.~~ 
   
   
## Significant commit track ##

2019.09.20
[b2a489d](https://github.com/absinthe4902/first_assignment/commit/b2a489dec31a45a6800d45676bf6fe10c808ef6c)

what's new?
- Add all the comments to explain the code. Please read again later.
- Add korean strings.xml to support korean language setting. 

what to do?
- *change the name of variable in RetroResponse.java*
- Try another implement than Serializable interface
- maybe add splash image would be great.

2019.09.19
 [328e4cc](https://github.com/absinthe4902/first_assignment/commit/328e4cc06c02bf6e6241f0d10e4e85344c04ed4d)


what's new? 
- use all of the parametar the api needs. 
- AKA full funcation built!



--------------------------------

**My frist assignment in DNX.** 
What I have to do is making a very simple login session. 
When I insert correct id and pw, then the program will call REST API to get a response. 
After that, I have to print all of the information in the response message. 

### Key point ###
* Using REST api
* Using retrofit 

### Due date 2019 09 27 ### 




    
