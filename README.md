# Random Epigram

This is a  **Spring Boot** web application that displays random epigrams (short, pithy sayings or quotes) with the following features:

- Displays a random epigram on page load.
- Users can manually trigger the loading of another random epigram.
- Users can enable or disable the automatic reloading of epigrams at a set interval.
- Users can contribute their own epigrams to the system.
- Epigrams are stored in a MongoDB database using Spring boot Data MongoDB.
## Features
1. **Display Random Epigrams:**
A random epigram is displayed when the page is first loaded. The epigrams are fetched from the MongoDB database.

2. **Manual Epigram Loading:**
Users can click a button to manually trigger the loading of a new random epigram.

3. **Automatic Reloading:**
There is a feature to enable or disable automatic epigram reloading at regular intervals. Users can control whether this feature is active.

4. **User-Contributed Epigrams:**
Users can submit their own epigrams, which will be stored in the MongoDB database for future random selection.

## Technology Stack
- **Backend:**

    - Java with Spring Boot
    - Spring Data MongoDB for database interactions
- **Frontend:**
   - Thymeleaf to render HTML
   - Reactjs for triggering manual reloads and controlling automatic reloading
- **Database:**

   - MongoDB Atlas (for storing epigrams)
  
## Prerequisites
To run this application, you'll need:

 - Java 23 
 - Maven (for building the project)

## Installation and Setup

1. **Clone the repository:**

```bash
git clone https://github.com/edwardsmuk/epigram.git
cd epigram
```

2. **Build the project:**

   Run the following command to build the project using Maven:

```bash
mvn clean install
```

3. **Run the application:**

    You can now run the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start and run on http://localhost:8080.

## API Endpoints
1. **GET** ```/api/v1/epigrams/random/1```
   Fetch a random epigram from the database.
   Response example:
   ```json
   {
   "id": "64c1efab256f4e17a0a576c8",
   "content": "Experience is the name everyone gives to their mistakes.",
   "author": "Oscar Wilde"
   }
   ```
2. **POST** ```/api/v1/epigrams```
   - Submit a new epigram to the database.

  - **Request Body example:**

```json
{
"content": "The only way to do great work is to love what you do.",
"author": "Steve Jobs"
}
```

## How to Use the Application
1. **View Random Epigram:**
On the main page, a random epigram will be displayed by default.

2. **Manually Load Another Epigram:**
Click the "Load Another Epigram" button to fetch a new random epigram from the server.

3. **Enable/Disable Automatic Reloading:**
Toggle the "Enable Auto Reload" button to enable or disable the automatic reloading of epigrams every 10 seconds.

4. **Contribute Your Own Epigram:**
Use the form provided on the page to submit your own epigram. Once submitted, it will be saved to the MongoDB database and become part of the random selection.
