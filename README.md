# Coroutine Example
[![Akhilesh StackOverflow](https://img.shields.io/badge/Akhilesh-StackOverflow-orange.svg?style=for-the-badge)](https://stackoverflow.com/users/1548824/akhilesh0707)
[![Akhilesh LinkedIn](https://img.shields.io/badge/Akhilesh-LinkedIn-blue.svg?style=for-the-badge)](https://www.linkedin.com/in/akhilesh0707/)

Kotlin Coroutine example of Image download, apply filter on image, network calls using retrofit and coroutine, CRUD operation using Room DB and coroutines, Kotlin coroutine flows exmaples

Created multiple branches based on funtionality

1. Image download and image proccessing
<img src="https://github.com/akhilesh0707/CoroutineExample/blob/master/previews/image_download_process.png" width="300">

2. Retrofit call to show list of countries using coroutines
<img src="https://github.com/akhilesh0707/CoroutineExample/blob/master/previews/retrofit_mvvm.png" width="300">

3. Room database CRUD operation with coroutines 

<img src="https://github.com/akhilesh0707/CoroutineExample/blob/master/previews/room_1.png" width="300"> <img src="https://github.com/akhilesh0707/CoroutineExample/blob/master/previews/room2.png" width="300"> <img src="https://github.com/akhilesh0707/CoroutineExample/blob/master/previews/room3.png" width="300">

4. Asynchronous Flow example create, properties,cancellation, operators, buffering, composing and exception handling 

```
fun main() {
    runBlocking {
        getPrimeFlow().collect {
            println("Receive: $it")
        }
    }
}

fun getPrimeFlow(): Flow<Int> = flow {
    val primeNumList = listOf(2, 3, 5, 7, 11, 13, 19, 23, 29)
    primeNumList.forEach {
        emit(it)
    }
}
```

5. Coroutines flow example with retrofit and MVVM
<img src="https://github.com/akhilesh0707/CoroutineExample/blob/master/previews/flow_mvvm_retrofit.png" width="300">

## Tech Stack
- Coroutines - Coroutines for asynchronous programming
- Retrofit 2 - OkHttp3 - request/response API
- Glide - For image loading.
- LiveData - use LiveData to see UI update with data changes
- ViewModel - Archtecture
- Room - To store data

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

1. Download/clone zip
2. Extract the downloaded Zip
3. Start android Studio, Close any existing project and navigate through import existing android project.


### Prerequisites

Working [Java](https://www.oracle.com/technetwork/java/javase/downloads/index.html) instalation and [Android Studio](https://developer.android.com/studio/) for devlopment.


## Built With

* [Android Studio](https://developer.android.com/studio/)


## Authors

* **Akhilesh Patil** - *Stackoverflow profile* - [Stackoverflow profile](https://stackoverflow.com/users/1548824/akhilesh0707)
			  *Linkedin profile* - [Linkedin profile](https://www.linkedin.com/in/akhilesh0707/)

