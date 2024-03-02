The sql driver has been used in this project is Postgresql
To run this code, you have to ensure the existance of the following dependencies :
    * Spring security
    * Spring Data Jpa
    * Lombook
    * Postgresql Driver
The application manipulate images locally, that is to say it doesn't store images in the database.
Otherwise, it stores it in a local directroy which the path has ben mentionned in the HomeController.java, PublicationController and AnonnceController. 
So, for runnig this code in another computer, this path sould be adpted.
