- This project is a demonstration of Android Clean Architecture pattern and it used one of the api from public api                         https://github.com/public-apis/public-apis.
- The App basically displays list of an animal(cat) in vertical grid format. This project used Corutines for network call and used         jetpack Compose for Ui building and Coil is used fo Image Loading.
- The Project mainly focused on applying the principles of Clean Architecure.
-  The project segregated into three parts ie
  
    1. Data -> Contains network and db  implementations
    2. Domain-> Usecases and repository interfaces
    3. Presentation -> Ui screens and viewmodels
 
-  Usecases applies the business rules and fed data to its ie Viewmodel and from viewmodel UI will eventually eventually gets updated.
-  For simplicity and maintainability,the project uses HILT dependency injection.
-  The project contains  test cases for both UI and Kotlin : Jetpack compose testing libraries and Junit are used for it. 
