<img src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/Kiosk.jpg?raw=true" alt="배너" width="80%"/>

<br/>
<br/>

# 1. Project Overview (프로젝트 개요)

- 프로젝트 이름: 키오스크 만들기
- 프로젝트 설명: 클래스를 이용하여 간단한 키오스크를 만듬.

# 2. Key Features (주요 기능)

- **메뉴 선택 기능**:
    - 사용자는 메인 메뉴에서 카테고리를 선택할 수 있음.
    - 선택한 카테고리의 메뉴 항목을 확인할 수 있음.


- **장바구니 추가 기능**:
    - 선택한 메뉴 항목을 장바구니에 추가할 수 있음.
    - 추가된 메뉴 항목은 사용자에게 확인 요청 후 장바구니에 저장.


- **주문 확인 기능**:
    - 장바구니에 담긴 메뉴 항목을 확인할 수 있음
    - 장바구니의 총 금액을 계산하여 출력.


- **장바구니 비우기 기능**:
    - 사용자가 장바구니를 비울 수 있는 기능 제공.


- **주문하기 기능**:
    - 장바구니에 있는 품목을 주문할 수 있음.
    - 주문 후 장바구니가 비워짐.


- **메뉴 항목 설명 기능**:
    - 각 메뉴 항목의 이름, 가격 및 설명을 확인할 수 있음.

# 3. Technology Stack (기술 스택)

## Language

|      |                                                                                                                  |
|------|------------------------------------------------------------------------------------------------------------------|
| Java | <img src="https://github.com/jihwanprogramer/calulators/blob/main/img/Java.jpg?raw=true" alt="Java" width="200"> | 

## Version Control

|     |                                                                                                   |
|-----|---------------------------------------------------------------------------------------------------|
| Git | <img src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/GIT.jpg?raw=true" width="200"> |

## JDK Version

|        |                                                                                                                  |
|--------|------------------------------------------------------------------------------------------------------------------|
| JDK 23 | <img src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/JDK23.jpg?raw=true" alt="JDK 11" width="180"> |

|

<br/>

# 4. Project Structure (프로젝트 구조)

```
caclulatorProject
src/
├── Cart            # 장바구니 기능을 관리하는 클래스
├── MenuItem        # 개별 메뉴 항목을 정의하는 클래스
│── Kiosk           # 키오스크의 메인 기능을 담당하는 클래스
│── Menu            # 특정 카테고리의 메뉴를 관리하는 클래스
│── Main            # 프로그램의 진입점이 되는 클래스
├── .gitignore      # Git 무시 파일 목록
└── README.md       # 프로젝트 개요 및 사용법
```

<br/>

# 5. Development Workflow (개발 워크플로우)

## 브랜치 전략 (Branch Strategy)

적절한 클래스 사용과 다양한 메소드 활용이 주목적이기에 빠르게 수정 가능한
직접적인 Main Branch을 바로 사용했습니다.

## 블록 구문

한 줄짜리 블록일 경우라도 {}를 생략하지 않고, 명확히 줄 바꿈 하여 사용한다

```
if (UpResultInput == 1) {
       operatorType.UpResult(firstNumber, secondNumber);
       }
else if (UpResultInput == 3) {
       operatorType.ViewAllResult();
       } 
else if (UpResultInput == 4) {
       break;
}
```

<br/>
<br/>
카멜 표기법을 이용하여 가독성을 향상시켰다.

```
double totalPrice = cart.getTotalPrice(); // 카멜 표기법
cart.viewCart(); // 카멜 표기법
String userInput = scanner.next(); // 카멜 표기법
completeOrder(); // 카멜 표기법

```

<br/>

## 메소드 네이밍

메소드 작성 시 아래 네이밍 규칙을 준수하여 의미 전달을 명확하게 한다.<br/>
메소드명이 길어지더라도 의미 전달의 명확성에 목적을 두어 작성한다.<br/>

```
public void viewCart()

public void clearCart()
```

# 6. 트러블 슈팅

### 문제 상황

주문 시 `NullPointerException`이 발생하여 콘솔 실행이 되지 않았습니다.

### 발생 과정

1. Kiosk 프로그램을 실행합니다.
2. 메뉴에서 주문을 선택합니다.
3. 주문을 진행합니다.

### 원인 분석

`User` Enum의 `viewUser()` 메소드에서 `values()`를 호출할 때 객체가 초기화되지 않아 발생했습니다. Enum의 특성상, 메소드가 static으로 정의되어 있어야 제대로 작동합니다.

### 해결 방법

Enum 메소드를 static으로 변경하여 문제를 해결했습니다. 아래와 같이 코드를 수정했습니다.

```
  public static void viewUser(){
    System.out.println("사용자의 신분을 선택해주세요");
    for(int i=0;i<users.length;i++)
    {
      System.out.println((i+1)+". "+users[i]+": "+users[i].getSale()+"%");
    }
  }
```

자세한 링크:https://computerreport.tistory.com/62

# 7. 수행 결과

## 1.장바구니 담기

<img width="100%" alt="코드 컨벤션" src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/Main1.jpg?raw=true">

<br/>
<br/>

## 2.주문하기

<img width="100%" alt="코드 컨벤션" src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/Main2.jpg?raw=true">

<br/>
<br/>

## 3.사용자 별 할인

<img width="100%" alt="코드 컨벤션" src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/Main3.jpg?raw=true">
