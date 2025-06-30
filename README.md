# SkyCast ☀️

SkyCast is a web application built with **Spring Boot** and **Thymeleaf** that allows users to search for real-time weather data by city. It integrates with the **OpenWeatherMap API** and displays:

- City and country
- Temperature and feels-like temperature
- Humidity and pressure
- Wind speed and direction
- Weather conditions and icons
- Sunrise and sunset times

The app features a clean, mobile-friendly UI using **Bootstrap** and includes favicons for a polished look.
---

## Features
✅ Search weather by city  
✅ Real-time data from OpenWeatherMap  
✅ Responsive design with Bootstrap  
✅ Beautiful weather icons  
✅ Sunrise and sunset times converted to local time  
✅ Favicons for different platforms and devices

Light Mode :
![image](https://github.com/user-attachments/assets/5d75f126-237b-4695-8ef7-0f4cc8ae78f9)

Dark Mode :

![image](https://github.com/user-attachments/assets/3315fc02-4e58-4038-97c1-4dc3bf9f675a)


## Tech Stack

- Java
- Spring Boot
- Thymeleaf
- Bootstrap
- OpenWeatherMap API

---

## How to Run

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/skycast.git
    cd skycast
    ```

2. Add your OpenWeatherMap API key to `src/main/resources/application.properties`:

    ```
    weather.api.key=YOUR_API_KEY
    weather.api.url=https://api.openweathermap.org/data/2.5/weather
    ```

3. Build and run the project:

    ```bash
    mvn spring-boot:run
    ```

4. Open your browser and visit:

    ```
    http://localhost:8080
    ```
