Weather Data Parser

A Java-based application that fetches weather data from the WeatherAPI, parses it, and stores daily weather statistics into a MySQL database for multiple European capital cities.

## 📋 Table of Contents
- [Overview](#-overview)
- [Features](#-features)
- [Installation](#-installation)
- [Usage](#-usage)
- [API Reference](#-api-reference)
- [Database Schema](#-database-schema)
- [Potential Use Cases](#-potential-use-cases)
- [Acknowledgments](#-acknowledgments)

# 📖 Overview

This application collects daily weather data from the WeatherAPI for various European capital cities and stores it in a structured MySQL database. It enables users to analyze weather trends, compare conditions across regions, and more—excluding hourly weather data for simplicity and efficiency.

# ✨ Features

- Fetches and stores daily weather data (min temp, max temp, average temp, humidity, etc.)
- Supports multiple European capitals using a predefined Enum list
- Avoids duplicate entries with optimized database queries
- Simple API integration and database setup

## ⚙️ Installation

- Prerequisites
- Java 11 or higher
- MySQL Server
- WeatherAPI account & API key

## Setup Guide

``` 
#Clone the repository
git clone https://github.com/yourusername/weather-data-parser.git
cd weather-data-parser

# Configure database
# 1. Create MySQL database `weather_db`
# 2. Import the provided schema

# Add your WeatherAPI key in the source code (apiUrl variable)
```


## 🚀 Usage

1. Set collection data start date, run the program to fetch and store weather data:


    javac WeatherDataParser.java
    java WeatherDataParser


2. Cycle through European capitals using the Enum list to populate the database.
3. Query the database to analyze weather patterns.

## 🌐 API Reference

- WeatherAPI Endpoint:

```
http://api.weatherapi.com/v1/forecast.json?key=YOUR_API_KEY&q=CityName&days=1&aqi=no
```
- API Parameters:
  - ```key```: Your API key
  - ```q```: City name
  - ```days```: Number of forecast days (set to 1)
  - ```aqi```: Air quality index (set to no)

## 🗄️ Database Schema

- Tables:

  - locations
   - location_id (Primary Key)
   - city
   - country
   - lat
   - lon

- weather_data
    - weather_id (Primary Key)
    - location_id (Foreign Key)
    - date
    - min_temp_c
    - max_temp_c
    - avg_temp_c
    - avg_humidity
    - max_wind_kph
    - condition
    - precip_mm
    - totalsnow_cm
    - uv_index

## 💡 Potential Use Cases

- Weather Trend Analysis: Compare seasonal weather patterns across different European cities.

- Travel Planning: Identify optimal weather conditions for trips.

- Climate Research: Analyze historical weather data.

- Agricultural Planning: Use weather trends for farming strategies.

## 🙌 Acknowledgments

- WeatherAPI for providing the weather data

- Inspiration from open-source weather tracking projects