## Build tools & versions used
- Many of my choices in version of particular tools is based on the minSDK(26).

- bumptech Glide
    'com.github.bumptech.glide:glide:4.11.0'
    'com.github.bumptech.glide:compiler:4.11.0'
- squareup Retrofit
    'com.squareup.retrofit2:retrofit:2.9.0'
    'com.squareup.retrofit2:converter-gson:2.9.0'
    "com.squareup.okhttp3:okhttp:4.9.0"
- Androidx.swiperefreshlayout
    'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
- org.kodein.di for Dependency injection
    "org.kodein.di:kodein-di-generic-jvm:6.5.5"
    "org.kodein.di:kodein-di-framework-android-x:6.5.5"
- com.jakewharton.threetenabp for datetime support on older Android versions
    "com.jakewharton.threetenabp:threetenabp:1.1.0"
- android lifecycle
    'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
    'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
## Steps to run the app
- Extract all the files from the zip file
- Open the project in android studio
- Build the project
- Run it in an Pixel 4(SDK 26) phone emulator

## What areas of the app did you focus on?
- Making sure the the viewmodel retains the data without it being lost on screen rotation
- For Malformed data in required fields to cause the list to not show up
- Prevent images from being reloaded

## What was the reason for your focus? What problems were you trying to solve?
- Having the viewmodel retain the data throughout the lifecycle of a fragment
allows for apps to stop making uncessary calls to the database.
- When ingesting Malformed data it appears that the GSON Factory via Retrofit will allow for 
nulls to be ingested into non-nullable fields. In order to get arond this I had to create a 
custom function that will check for nulls in non-nullable fields
- Leveraged Glide's ability to cache images(set by default) as long as the URL is the same. 
It also allowed me to add a default image if an image failed to load.

## How long did you spend on this project?
- Around 4 hours to dev and implement all the features and another 2 hours to test it and 1 hour for the README

## Did you make any trade-offs for this project? What would you have done differently with more time?
- Would have liked to have incorporated a navigation controller in the application, 
but since this is only 1 screen there was no purpose in it.
- Would like to have implememented another screen to show more details about the employee.

## What do you think is the weakest part of your project?
- Probably testing, would have liked to use Mockito in some instances.

## Did you copy any code or dependencies? Please make sure to attribute them here!
- GSON Factory issues in Retrofit with null
https://stackoverflow.com/questions/52837665/why-kotlin-data-classes-can-have-nulls-in-non-nullable-fields-with-gson?rq=1

- Got my Resource class from these sites
https://www.youtube.com/watch?v=JsktEQvoHEs&ab_channel=PhilippLackner
https://www.youtube.com/watch?v=B-dJTFeOAqw&t=983s&ab_channel=PhilippLackner

- References for coroutines
https://developer.android.com/topic/libraries/architecture/coroutines

- Pull to Refresh
https://guides.codepath.com/android/implementing-pull-to-refresh-guide

- Kodein
https://kodein.org/Kodein-DI/?5.0/getting-started

## Is there any other information you’d like us to know?
- I added the malformed list API endpoint, empty list API endpoint and normal list API endpoint to the API.

