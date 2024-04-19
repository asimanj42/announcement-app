## 📢 Announcement Application

This web application is a user-friendly platform allowing users to register/login and share announcements, aimed at
enhancing communication efficiency within communities.

## 🛠️ Tech Stack and Approaches

- **Java** : The application is developed in the Java programming language.
- **Spring Boot** : Utilized for rapid and straightforward application development.
- **Spring Data** : Employed for database operations.
- **Spring Security** : Integrated for user authentication and authorization.
- **JWT (JSON Web Token)** : Used for secure identity verification.
- **Docker** : Facilitates portability and deployment of the application.
- **Gradle** : Dependency management and project configuration.
- **Redis**: Utilized for data caching.
- **Liquibase**: Manages database schema and version control.
- **Clean Code**: Followed principles to ensure code quality and readability.

## Installation

If you use docker, you can run the following command to start the application:

```bash
docker pull asimanj42/announcement-app
docker run -p 8080:8080 asimanj42/announcement-app
```

If you want to run the application locally, you can follow the steps below:

1. Clone the repository:

```bash
git clone https://github.com/asimanj42/announcement-app.git
```

2. Navigate to the project directory:

```bash
cd announcement-app
```

3. Run the application:

```bash
./gradlew bootRun
```

## Usage

1. **Register/Login:** Users can register for a new account or log in with existing credentials.
2. **Share Announcements:** Logged-in users can share announcements by providing relevant information.
3. **View Announcements:** All users can view announcements shared by others.

## Contributing

Contributions are welcome! Please feel free to open a pull request or submit an issue for any bugs or feature requests.


## Contact

For any inquiries or feedback, please contact [asimanmammadli2023@gmail.com](mailto:asimanmammadli2023@gmail.com).
