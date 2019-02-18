# Welcome to Eat&Basta

This is a project for an Android development course. The goal of this application is to replicate the feature 
of a food delivery app.
The **features** of this app are:
 - The possibility to login or register with firebase authentication
 - The possibility to reset credential
 - An activity with the restaurants list(composed by image,name,address and minimum order) and the 
 possibility to choose to view the items in a linear way or in a grid way
 - An activity with the list of the menu in that restaurant and the possibility to order food

## Technology used
 - **Glide** to load image into the ImageView
 - **Material design** components
 - **SharedPreferences** to save the preferences of the users
 - **RecyclerView** for the lists of restaurant and menu
 - **Firebase** to authenticate the user and register new users
 - **Room** for data persistence
 - **Volley** for remote Api request