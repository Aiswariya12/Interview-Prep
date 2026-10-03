# 🎓 InterviewPrep — Full Stack Interview Preparation & Mock Test Platform

**InterviewPrep** is an enterprise-grade, full-stack technical interview preparation and mock assessment web application built with **React.js (Vite + Tailwind CSS)**, **Java Spring Boot 3**, **Spring Security + JWT**, **Spring Data JPA / Hibernate**, and **MySQL**.

---

## ✨ Key Features

### 👨‍🎓 Student Portal
1. **Dynamic Mock Test Engine**: Select Subject, Sub-Topic, Difficulty (`EASY`, `MEDIUM`, `HARD`, `ALL`), Question Count (`5–20+`), Duration, and Negative Marking (`+1` / `-0.25`).
2. **Real-Time Examination Environment**:
   - Interactive single-question view with syntax-styled code snippets.
   - Live countdown timer with low-time alerts and **automatic submission** on timeout.
   - Interactive Question Palette showing **Answered**, **Unanswered**, **Marked for Review**, and **Current** question states.
3. **Automated Evaluation & Detailed Result Analysis**:
   - Instant calculation of Total Score, Percentage, Accuracy, Correct/Wrong/Skipped counts, and Time Taken.
   - Interactive **Recharts** Doughnut & Line visualizations.
   - Full Question-by-Question Solution Review with **Detailed Explanations** for every answer.
4. **Weak Topic Detection & AI Interview Coach**:
   - Automatically identifies sub-topics where accuracy drops below 70%.
   - Recommends targeted topic drills and provides a 3-step revision roadmap via the **AI Interview Coach**.
5. **Bookmarks, Daily Challenge, Streaks & Badges**:
   - One-click question bookmarking for revision.
   - Daily 5-question technical sprint with +50 streak bonus points.
   - Automatic achievement badges (*First Mock Completed*, *High Achiever*, *Flawless Execution*, *7-Day Streak*).
6. **Smart AI Assistant Chatbot**:
   - Interactive floating AI chatbot available across student and admin portals.
   - Draggable & repositionable anywhere on the screen (saves user preferred position).
   - Provides direct, concise responses, arithmetic computation, personalized greetings, and instant knowledge queries.
   - Automatically disappears during mock test examinations to ensure exam integrity.
   - Includes a **Refresh Chat** button to instantly reset conversation context.

### 🛡️ Admin Control Center
1. **Platform Overview Analytics**: Total Students, Total Questions, Completed Mock Tests, Active Subjects, and Platform-wide Average Score.
2. **Question Bank Management (CRUD)**: Create, edit, delete, search, and filter questions with code snippets, options A–D, correct answer, marks, negative marks, and explanations.
3. **Subject & Topic Management**: Create and manage technical tracks and sub-topics dynamically.
4. **Student Directory**: View registered students, their college/branch details, and active practice streaks.

---

## 🔐 Credentials & Access

| Role | Email | Password | Access Method |
| :--- | :--- | :--- | :--- |
| **Student Demo** | `student@interviewprep.com` | `Student@123` | 1-Click Auto-fill on `/login` |
| **Platform Administrator** | `pradhanaiswariya1@gmail.com` | `Aiswariya00` | Manual entry on `/login` |

*(Student demo can be auto-filled via the 1-click button on the `/login` page; Admin logs in securely via manual entry.)*

---

## 🏗️ System Architecture

```text
Selectify/
├── backend/                  # Java 17 + Spring Boot 3 REST API
│   ├── src/main/java/com/interviewprep/
│   │   ├── config/           # SecurityConfig, OpenApiConfig, DataInitializer
│   │   ├── controller/       # Auth, Subject, Question, MockTest, Analytics, Bookmark, DailyChallenge, Admin, AiChat
│   │   ├── dto/              # Request/Response Data Transfer Objects
│   │   ├── entity/           # JPA Entities (User, Subject, Topic, Question, MockTest, MockQuestion, Bookmark, etc.)
│   │   ├── exception/        # GlobalExceptionHandler & Custom Exceptions
│   │   ├── repository/       # Spring Data JPA Repositories
│   │   ├── security/         # JwtTokenProvider, JwtAuthenticationFilter, UserPrincipal
│   │   └── service/          # Business Logic & Evaluation Engine
│   ├── database_schema.sql   # Complete MySQL 8.0 DDL Schema Script
│   └── pom.xml
│
└── frontend/                 # React 18 + Vite + Tailwind CSS + Recharts
    ├── src/
    │   ├── components/       # Navbar, Footer, ProtectedRoute, AIStudyCoachModal, AIChatBot
    │   ├── context/          # AuthContext (JWT Persistence & Role Guards)
    │   ├── pages/            # LandingPage, Login, Register, Student & Admin Views
    │   └── services/         # Axios API Client, AI Service (concise Q&A, math, Wikipedia API, Gemini)
    └── package.json
```

---

## 🚀 Running the Project Locally

### 1. Start the Spring Boot Backend (`Port 8082` / `8081`)
By default, the backend runs with the `h2` profile so it starts **instantly with zero database setup** and automatically seeds subjects, topics, and curated interview questions.

```bash
cd backend
./mvnw spring-boot:run
```

- **Backend API Base URL**: `http://localhost:8082`
- **Swagger UI Documentation**: `http://localhost:8082/swagger-ui.html`
- **H2 Database Console**: `http://localhost:8082/h2-console` (JDBC URL: `jdbc:h2:mem:interviewprep_db`)

### 2. Start the React Frontend (`Port 5173`)
```bash
cd frontend
npm install
npm run dev
```
- **Frontend URL**: `http://localhost:5173`
