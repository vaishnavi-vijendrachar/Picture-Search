The app follows MVVM architecture and uses retrofit and Glide library to fetch and display image.
Implements Recyclerview to display images and scroll through them

1. Architecture : MVVM
-Mades code more granular, hence makes it easy to test
-Clear segregation of durig
-Easy to maintain code and scalable
2. Network Library : Retrofit 2
-Type safe Http client for android that retrieves JSON using REST WebsService
-Easy to consume JSON
-Serialises JSON into POJO
3. Image library : Glide
-An image loading and caching library for Android focused on smooth scrolling
4. UI- Uses recyclerview to display the images -its economical with  memory since it resuse the cells while scrolling.

Enhancements :
1. Caching the images :  Using Glide to cache images.
Alternative - Can also save the images in local directory
2. Implement pagination : add refersh button end of the recyclerview, when triggered will make a retrofit call which will fetch next set of items into the view
Alternative - Can also implement paging library
3. click through  the thumbnail to load the photo URL in the users web browser : 
-add clicklistent callback in the apater
-setonclickListenetr for image onCreateViewHolder method
-get the position outside the adapter
