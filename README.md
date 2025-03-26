<img src="https://github.com/jihwanprogramer/TodoList/blob/main/ReadmeImage/todo.png?raw=true" alt="배너" width="100%"/>

<br/>
<br/>

# 1. Project Overview (프로젝트 개요)

- 프로젝트 이름: 일정 관리
- 프로젝트 설명: Spring 프레임워크 를 이용하여 간단한 일정 관리를 만듬.

## API 명세서
https://documenter.getpostman.com/view/43187382/2sAYkHpJQY

## ERD
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FpDVDS%2FbtsMURuLSF4%2F1G58ltbMQbeYYsTcXUKJPK%2Fimg.png" alt="배너" width="100%"/>




# 2. Key Features (주요 기능)

- **Todo 항목 생성**
    - 사용자는 새로운 Todo 항목을 생성할 수 있음


- **Todo 항목 조회**
    - 사용자는 ID로 특정 Todo 항목을 조회할 수 있음

- **조건에 맞는 Todo 조회**
    - 사용자는 수정일 또는 작성자 이름에 따라 Todo 항목을 필터링하여 조회 가능


- **Todo 항목 수정**
    - 사용자는 기존의 Todo 항목을 수정 가능


- **Todo 항목 삭제**
    - 사용자는 ID로 특정 Todo 항목을 삭제 



# 3. Technology Stack (기술 스택)

## Language

|        |                                                                                                                  |
|--------|------------------------------------------------------------------------------------------------------------------|
| Java   | <img src="https://github.com/jihwanprogramer/calulators/blob/main/img/Java.jpg?raw=true" alt="Java" width="200"> | 
| Spring | Spring Framework를 사용하여 RESTful API 구현                                                                            |
| JDBC   | 데이터베이스와의 연결 및 CRUD 작업을 위한 JDBC 사용                                                                                |
## Version Control

|     |                                                                                                   |
|-----|---------------------------------------------------------------------------------------------------|
| Git | <img src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/GIT.jpg?raw=true" width="200"> |

## JDK Version

|        |                                                                                                                  |
|--------|------------------------------------------------------------------------------------------------------------------|
| JDK 17 | <img src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/JDK23.jpg?raw=true" alt="JDK 11" width="180"> |

|

<br/>

# 4. Project Structure (프로젝트 구조)

```
todoProject
├── src/
│   ├── controller/                 # TodoController 클래스 (REST API 엔드포인트)
│   │   └── TodoController.java
│   ├── dto/                        # 데이터 전송 객체 (DTO)
│   │   ├── TodoRequestDto.java     # 요청 DTO
│   │   └── TodoResponseDto.java    # 응답 DTO
│   ├── entity/                     # Todo 엔티티 클래스
│   │   └── Todo.java
│   ├── repository/                 # 데이터베이스 작업을 처리하는 리포지토리
│   │   ├── TodoRepository.java     # 인터페이스
│   │   └── TodoRepositoryImpl.java # 구현 클래스
│   └── service/                    # 비즈니스 로직을 처리하는 서비스
│       ├── TodoService.java        # 인터페이스
│       └── TodoServiceImpl.java    # 구현 클래스
└── README.md                       # 프로젝트 개요 및 사용법
```

<br/>

# 5. Development Workflow (개발 워크플로우)

## 브랜치 전략 (Branch Strategy)

적절한 클래스 사용과 다양한 메소드 활용이 주목적이기에 빠르게 수정 가능한
직접적인 Main Branch을 바로 사용했습니다.

## 블록 구문

한 줄짜리 블록일 경우라도 {}를 생략하지 않고, 명확히 줄 바꿈 하여 사용한다

```
        if (todo == null && name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "값이 없다");
        }

        if (todos.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "저장된 비밀번호가 없습니다.");
        }

        if (!todos.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호 일치 오류");
        }
```

<br/>
<br/>
카멜 표기법을 이용하여 가독성을 향상시켰다.

```
TodoResponseDto saveTodo(Todo todo);
Todo findTodoById(Long id);  

```

<br/>

## 메소드 네이밍

메소드 작성 시 아래 네이밍 규칙을 준수하여 의미 전달을 명확하게 한다.<br/>
메소드명이 길어지더라도 의미 전달의 명확성에 목적을 두어 작성한다.<br/>

```
List<TodoResponseDto> findConditionTodo(Date date, String name);

int updateTodo(Long id, String Todo, String name);
```

# 6. 트러블 슈팅


# 7. 수행 결과

## 1.Todo 항목 생성
```
POST /todos
Body: {
  "todo": "할 일",
  "name": "작성자 이름",
  "password": "비밀번호"
}
```

<br/>


## 2.Todo 항목 조회
```
GET /todos/{id}
```

<br/>


## 3.조건에 맞는 Todo 조회
```
GET /todos?editDate={date}&name={name}
```
<br/>

## 4.조건에 맞는 Todo 조회
```
PATCH /todos/{id}
Body: {
  "todo": "수정된 할 일",
  "name": "수정된 작성자 이름",
  "password": "비밀번호"
}
```
<br/>

## 5.Todo 항목 삭제
```
DELETE /todos/{id}
```